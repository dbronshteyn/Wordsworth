package com.danielb.project.wordsworth.controller;

import com.danielb.project.wordsworth.model.Flashcard;
import com.danielb.project.wordsworth.model.FlashcardSet;
import com.danielb.project.wordsworth.service.FlashcardService;
import com.danielb.project.wordsworth.service.FlashcardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing flashcards.
 */
@RestController
@RequestMapping("/api/flashcard-sets/{setId}/flashcards")
public class FlashcardController {

    private final FlashcardService flashcardService;
    private final FlashcardSetService flashcardSetService;

    @Autowired
    public FlashcardController(FlashcardService flashcardService, FlashcardSetService flashcardSetService) {
        this.flashcardService = flashcardService;
        this.flashcardSetService = flashcardSetService;
    }

    /**
     * Get all flashcards in a flashcard set.
     *
     * @param setId the id of the flashcard set
     * @return a list of flashcards
     */
    @GetMapping
    public ResponseEntity<List<Flashcard>> getAllFlashcards(@PathVariable Long setId) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            List<Flashcard> flashcards = flashcardService.findByFlashcardSet(flashcardSet);

            return new ResponseEntity<>(flashcards, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Get a flashcard by its id.
     *
     * @param setId the id of the flashcard set
     * @param id    the id of the flashcard
     * @return the flashcard if found, otherwise not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Flashcard> getFlashcardById(@PathVariable Long setId, @PathVariable Long id) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            Flashcard flashcard = flashcardService.findByIdAndFlashcardSet(id, flashcardSet);
            if (flashcard != null) {
                return new ResponseEntity<>(flashcard, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create a new flashcard.
     *
     * @param setId    the id of the flashcard set
     * @param flashcard the flashcard to create
     * @return the created flashcard if flashcard set exists, otherwise not found
     */
    @PostMapping
    public ResponseEntity<Flashcard> createFlashcard(@PathVariable Long setId, @RequestBody Flashcard flashcard) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            flashcard.setFlashcardSet(flashcardSet);
            Flashcard createdFlashcard = flashcardService.save(flashcard);

            return new ResponseEntity<>(createdFlashcard, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Update a flashcard.
     *
     * @param setId    the id of the flashcard set
     * @param id       the id of the flashcard
     * @param flashcard the flashcard to update
     * @return the updated flashcard if flashcard set and flashcard exist, otherwise not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Flashcard> updateFlashcard(@PathVariable Long setId, @PathVariable Long id, @RequestBody Flashcard flashcard) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            Flashcard existingFlashcard = flashcardService.findByIdAndFlashcardSet(id, flashcardSet);
            if (existingFlashcard != null) {
                flashcard.setId(id);
                flashcard.setFlashcardSet(flashcardSet);
                Flashcard updatedFlashcard = flashcardService.save(flashcard);

                return new ResponseEntity<>(updatedFlashcard, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a flashcard.
     *
     * @param setId the id of the flashcard set
     * @param id    the id of the flashcard
     * @return no content if flashcard set and flashcard exist, otherwise not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long setId, @PathVariable Long id) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            Flashcard existingFlashcard = flashcardService.findByIdAndFlashcardSet(id, flashcardSet);
            if (existingFlashcard != null) {
                flashcardService.deleteById(id);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}