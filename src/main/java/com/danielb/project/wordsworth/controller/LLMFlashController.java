package com.danielb.project.wordsworth.controller;

import com.danielb.project.wordsworth.model.Flashcard;
import com.danielb.project.wordsworth.model.FlashcardSet;
import com.danielb.project.wordsworth.model.GenerateRequest;
import com.danielb.project.wordsworth.service.FlashcardService;
import com.danielb.project.wordsworth.service.FlashcardSetService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/llm")
public class LLMFlashController {

    private final ChatClient chatClient;
    private final FlashcardService flashcardService;
    private final FlashcardSetService flashcardSetService;

    @Autowired
    public LLMFlashController(ChatClient.Builder chatClient, FlashcardService flashcardService, FlashcardSetService flashcardSetService) {
        this.chatClient = chatClient.build();
        this.flashcardService = flashcardService;
        this.flashcardSetService = flashcardSetService;
    }

    /**
     * Generates a structured prompt for the LLM to follow, with examples for consistency.
     *
     * @param generateRequest the request containing user parameters
     * @return the structured prompt to send to the LLM
     */
    public String getPrompt(GenerateRequest generateRequest) {
        StringBuilder promptBuilder = new StringBuilder();

        String promptTemplate = """
            Please generate a list of %d flashcards on the subject of %s.
            With the following prompt: %s
            
            Requirements:
            - Each flashcard must follow this exact format:
            **Card #**
            **Term**: [concise term]
            **Definition**: [clear, detailed definition]
            
            - Terms should be specific and focused
            - Definitions should be comprehensive but concise
            - Maximum length per definition: 200 words
            - %s include code snippets where relevant and use Markdown formatting
            
            Format Example:
            **Card #1**
            **Term**: Algorithm
            **Definition**: A step-by-step procedure for calculations.
            
            Please proceed with generating %d cards following this format exactly. 
            No other text or explanations outside of the flashcards.
            """;

        String includeCode = generateRequest.isIncludeCode() ? "Please" : "Do not";

        promptBuilder.append(String.format(promptTemplate,
                generateRequest.getTotal(),
                generateRequest.getSubject(),
                generateRequest.getPrompt(),
                includeCode,
                generateRequest.getTotal()
        ));

        return promptBuilder.toString();
    }

    /**
     * Calls the chat client to get a response from the LLM based on the structured prompt.
     *
     * @param prompt the prompt to send to the LLM
     * @return the LLM's response as a string
     */
    public String generateResponse(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    /**
     * Parses the structured text response from the LLM into Flashcard objects.
     *
     * @param response the LLM response
     * @return a list of Flashcard objects
     */
    private List<Flashcard> parseFlashcards(String response) {
        // Regex pattern to capture each flashcard's term and definition
        Pattern cardPattern = Pattern.compile("\\*\\*Card #(\\d+)\\*\\*\\s*\\*\\*Term\\*\\*: (.*?)\\s*\\*\\*Definition\\*\\*: (.*?)(?=\\*\\*Card|$)", Pattern.DOTALL);
        Matcher matcher = cardPattern.matcher(response);
        List<Flashcard> flashcards = new ArrayList<>();

        while (matcher.find()) {
            String term = matcher.group(2).trim();
            String definition = matcher.group(3).trim();

            Flashcard flashcard = new Flashcard();
            flashcard.setTerm(term);
            flashcard.setDefinition(definition);
            flashcards.add(flashcard);
        }
        return flashcards;
    }


    /**
     * Endpoint that provides information about the LLM being used.
     *
     * @return a string with LLM version info
     */
    @GetMapping("/info")
    public String info() {
        return "LLM being used: llama 3.2";
    }

    /**
     * Endpoint to generate a set of flashcards based on user input.
     *
     * @param generateRequest the user's generate request
     * @return a ResponseEntity with the generated FlashcardSet
     */
    @PostMapping("/generate-flashcards")
    public ResponseEntity<FlashcardSet> generateFlashcards(@RequestBody GenerateRequest generateRequest) {
        String prompt = getPrompt(generateRequest);
        String response = generateResponse(prompt);

        System.out.println("LLM response: " + response);

        try {
            List<Flashcard> flashcards = parseFlashcards(response);
            FlashcardSet flashcardSet = new FlashcardSet();
            flashcardSet.setName(generateRequest.getSubject());
            flashcardSet.setDescription("Generated by LLM");
            flashcardSet.setFlashcards(flashcards);

            FlashcardSet savedSet = flashcardSetService.save(flashcardSet);

            // Save each flashcard individually to ensure they are persisted
            for (Flashcard flashcard : flashcards) {
                flashcard.setFlashcardSet(flashcardSet);
                flashcardService.save(flashcard);
            }

            return new ResponseEntity<>(savedSet, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Failed to generate flashcards: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
