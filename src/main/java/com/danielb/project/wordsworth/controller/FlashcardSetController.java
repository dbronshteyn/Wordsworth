package com.danielb.project.wordsworth.controller;

import com.danielb.project.wordsworth.model.FlashcardSet;
import com.danielb.project.wordsworth.service.FlashcardSetService;
import jakarta.validation.Valid;
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
 * REST controller for managing flashcard sets.
 */
@RestController
@RequestMapping("/api/flashcard-sets")
public class FlashcardSetController {

    private final FlashcardSetService flashcardSetService;

    @Autowired
    public FlashcardSetController(FlashcardSetService flashcardSetService) {
        this.flashcardSetService = flashcardSetService;
    }

    /**
     * Get all flashcard sets.
     *
     * @return a list of flashcard sets
     */
    @GetMapping
    public ResponseEntity<List<FlashcardSet>> getAllFlashcardSets() {
        List<FlashcardSet> flashcardSets = flashcardSetService.findAll();

        return new ResponseEntity<>(flashcardSets, HttpStatus.OK);
    }

    /**
     * Get a flashcard set by its id.
     *
     * @param id the id of the flashcard set
     * @return the flashcard set if found, otherwise not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<FlashcardSet> getFlashcardSetById(@PathVariable Long id) {
        FlashcardSet flashcardSet = flashcardSetService.findById(id);

        if (flashcardSet != null) {
            return new ResponseEntity<>(flashcardSet, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create a new flashcard set.
     *
     * @param flashcardSet the flashcard set to create
     * @return the created flashcard set
     */
    @PostMapping
    public ResponseEntity<FlashcardSet> createFlashcardSet(@Valid @RequestBody FlashcardSet flashcardSet) {
        FlashcardSet createdFlashcardSet = flashcardSetService.save(flashcardSet);

        return new ResponseEntity<>(createdFlashcardSet, HttpStatus.CREATED);
    }

    /**
     * Update a flashcard set.
     *
     * @param id          the id of the flashcard set
     * @param flashcardSet the flashcard set to update
     * @return the updated flashcard set if found, otherwise not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<FlashcardSet> updateFlashcardSet(@PathVariable Long id, @Valid @RequestBody FlashcardSet flashcardSet) {
        FlashcardSet existingFlashcardSet = flashcardSetService.findById(id);

        if (existingFlashcardSet != null) {
            FlashcardSet updatedFlashcardSet = flashcardSetService.updateFlashcardSet(id, flashcardSet);
            return new ResponseEntity<>(updatedFlashcardSet, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a flashcard set.
     *
     * @param id the id of the flashcard set
     * @return no content if found, otherwise not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlashcardSet(@PathVariable Long id) {
        FlashcardSet existingFlashcardSet = flashcardSetService.findById(id);

        if (existingFlashcardSet != null) {
            flashcardSetService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}