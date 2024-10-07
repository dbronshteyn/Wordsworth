package com.danielb.project.Wordsworth.integration;

import com.danielb.project.Wordsworth.model.Flashcard;
import com.danielb.project.Wordsworth.model.FlashcardSet;
import com.danielb.project.Wordsworth.repository.FlashcardRepository;
import com.danielb.project.Wordsworth.repository.FlashcardSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FlashcardIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FlashcardSetRepository flashcardSetRepository;

    @Autowired
    private FlashcardRepository flashcardRepository;

    private FlashcardSet flashcardSet;

    @BeforeEach
    void setUp() {
        flashcardRepository.deleteAll();
        flashcardSetRepository.deleteAll();
        flashcardSet = new FlashcardSet(1L, "Test Set", "Test Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);
    }

    @Test
    void testCreateFlashcard() throws Exception {
        String flashcardJson = "{\"term\":\"Term1\",\"definition\":\"Definition1\",\"flashcardSet\":{\"id\":" + flashcardSet.getId() + "}}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(flashcardJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.term").value("Term1"))
                .andExpect(jsonPath("$.definition").value("Definition1"));
    }

    @Test
    void testUpdateFlashcard() throws Exception {
        Flashcard flashcard = new Flashcard(2L, "Term1", "Definition1", 0, 0, false, flashcardSet);
        flashcard = flashcardRepository.save(flashcard);

        String updatedFlashcardJson = "{\"term\":\"Updated Term\",\"definition\":\"Updated Definition\",\"flashcardSet\":{\"id\":" + flashcardSet.getId() + "}}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards/" + flashcard.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedFlashcardJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.term").value("Updated Term"))
                .andExpect(jsonPath("$.definition").value("Updated Definition"));
    }

    @Test
    void testGetFlashcardById() throws Exception {
        Flashcard flashcard = new Flashcard(3L, "Term1", "Definition1", 0, 0, false, flashcardSet);
        flashcard = flashcardRepository.save(flashcard);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards/" + flashcard.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.term").value("Term1"))
                .andExpect(jsonPath("$.definition").value("Definition1"));
    }

    @Test
    void testDeleteFlashcard() throws Exception {
        Flashcard flashcard = new Flashcard(4L, "Term1", "Definition1", 0, 0, false, flashcardSet);
        flashcard = flashcardRepository.save(flashcard);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards/" + flashcard.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards/" + flashcard.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllFlashcardsInSet() throws Exception {
        Flashcard flashcard1 = new Flashcard(5L, "Term1", "Definition1", 0, 0, false, flashcardSet);
        Flashcard flashcard2 = new Flashcard(6L, "Term2", "Definition2", 0, 0, false, flashcardSet);
        flashcardRepository.save(flashcard1);
        flashcardRepository.save(flashcard2);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].term").value("Term1"))
                .andExpect(jsonPath("$[1].term").value("Term2"));
    }
}