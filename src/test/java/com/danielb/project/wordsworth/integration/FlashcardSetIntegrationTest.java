package com.danielb.project.wordsworth.integration;

import com.danielb.project.wordsworth.model.Flashcard;
import com.danielb.project.wordsworth.model.FlashcardSet;
import com.danielb.project.wordsworth.repository.FlashcardRepository;
import com.danielb.project.wordsworth.repository.FlashcardSetRepository;
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
public class FlashcardSetIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FlashcardSetRepository flashcardSetRepository;

    @Autowired
    private FlashcardRepository flashcardRepository;

    @BeforeEach
    void setUp() {
        flashcardRepository.deleteAll();
        flashcardSetRepository.deleteAll();
    }

    @Test
    void testGetFlashcardSets() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        flashcardSetRepository.save(flashcardSet);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Set"));
    }

    @Test
    void testCreateFlashcardSet() throws Exception {
        String flashcardSetJson = "{\"name\":\"New Set\",\"description\":\"New Description\",\"favorite\":false,\"flashcards\":[]}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/flashcard-sets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(flashcardSetJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Set"));
    }

    @Test
    void testAddFlashcardToSet() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);

        String flashcardJson = "{\"term\":\"Term1\",\"definition\":\"Definition1\",\"flashcardSet\":{\"id\":" + flashcardSet.getId() + "}}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(flashcardJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.term").value("Term1"));
    }

    @Test
    void testUpdateFlashcardSet() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Old Set", "Old Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);

        String updatedFlashcardSetJson = "{\"name\":\"Updated Set\",\"description\":\"Updated Description\",\"favorite\":true}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/flashcard-sets/" + flashcardSet.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedFlashcardSetJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Set"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.favorite").value(true));
    }

    @Test
    void testDeleteFlashcardSet() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/flashcard-sets/" + flashcardSet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets/" + flashcardSet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetFlashcardSetById() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets/" + flashcardSet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Set"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testGetAllFlashcardsInSet() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);

        Flashcard flashcard1 = new Flashcard(null, "Term1", "Definition1", 0, 0, false, flashcardSet);
        Flashcard flashcard2 = new Flashcard(null, "Term2", "Definition2", 0, 0, false, flashcardSet);
        flashcardRepository.save(flashcard1);
        flashcardRepository.save(flashcard2);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].term").value("Term1"))
                .andExpect(jsonPath("$[1].term").value("Term2"));
    }

    @Test
    void testUpdateFlashcardInSet() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);

        Flashcard flashcard = new Flashcard(null, "Term1", "Definition1", 0, 0, false, flashcardSet);
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
    void testDeleteFlashcardFromSet() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);

        Flashcard flashcard = new Flashcard(null, "Term1", "Definition1", 0, 0, false, flashcardSet);
        flashcard = flashcardRepository.save(flashcard);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards/" + flashcard.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards/" + flashcard.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateFlashcardSetWithMissingFields() throws Exception {
        String flashcardSetJson = "{\"description\":\"New Description\",\"favorite\":false}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/flashcard-sets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(flashcardSetJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAddFlashcardToNonExistentSet() throws Exception {
        String flashcardJson = "{\"term\":\"Term1\",\"definition\":\"Definition1\",\"flashcardSet\":{\"id\":999}}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/flashcard-sets/999/flashcards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(flashcardJson))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateFlashcardSetWithInvalidData() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Old Set", "Old Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);

        String updatedFlashcardSetJson = "{\"name\":\"\",\"description\":\"Updated Description\",\"favorite\":true}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/flashcard-sets/" + flashcardSet.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedFlashcardSetJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteNonExistentFlashcardSet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/flashcard-sets/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddAndDeleteManyFlashcardsInSet() throws Exception {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        flashcardSet = flashcardSetRepository.save(flashcardSet);

        int numberOfFlashcards = 99;
        for (int i = 1; i <= numberOfFlashcards; i++) {
            String flashcardJson = "{\"term\":\"Term" + i + "\",\"definition\":\"Definition" + i + "\",\"flashcardSet\":{\"id\":" + flashcardSet.getId() + "}}";
            mockMvc.perform(MockMvcRequestBuilders.post("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(flashcardJson))
                    .andExpect(status().isCreated());
        }

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets/" + flashcardSet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalFlashcards").value(numberOfFlashcards));

        for (Flashcard flashcard : flashcardRepository.findByFlashcardSet(flashcardSet)) {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/flashcard-sets/" + flashcardSet.getId() + "/flashcards/" + flashcard.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flashcard-sets/" + flashcardSet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalFlashcards").value(0));
    }
}