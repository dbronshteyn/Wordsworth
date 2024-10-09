package com.danielb.project.wordsworth.controller;

import com.danielb.project.wordsworth.model.FlashcardSet;
import com.danielb.project.wordsworth.service.DatabaseSeed;
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

@RestController
@RequestMapping("/api/flashcard-sets")
public class FlashcardSetController {

    private FlashcardSetService flashcardSetService;
    private DatabaseSeed databaseSeed;

    @Autowired
    public FlashcardSetController(FlashcardSetService flashcardSetService, DatabaseSeed databaseSeed) {
        this.flashcardSetService = flashcardSetService;
        this.databaseSeed = databaseSeed;
    }

    @GetMapping
    public ResponseEntity<List<FlashcardSet>> getAllFlashcardSets() {
        List<FlashcardSet> flashcardSets = flashcardSetService.findAll();

        return new ResponseEntity<>(flashcardSets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardSet> getFlashcardSetById(@PathVariable Long id) {
        FlashcardSet flashcardSet = flashcardSetService.findById(id);

        if (flashcardSet != null) {
            return new ResponseEntity<>(flashcardSet, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<FlashcardSet> createFlashcardSet(@Valid @RequestBody FlashcardSet flashcardSet) {
        FlashcardSet createdFlashcardSet = flashcardSetService.save(flashcardSet);

        return new ResponseEntity<>(createdFlashcardSet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlashcardSet> updateFlashcardSet(@PathVariable Long id, @Valid @RequestBody FlashcardSet flashcardSet) {
        FlashcardSet existingFlashcardSet = flashcardSetService.findById(id);

        if (existingFlashcardSet != null) {
            FlashcardSet updatedFlashcardSet = flashcardSetService.updateFlashcardSet(id, flashcardSet);
            return new ResponseEntity<>(updatedFlashcardSet, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlashcardSet(@PathVariable Long id) {
        FlashcardSet existingFlashcardSet = flashcardSetService.findById(id);

        if (existingFlashcardSet != null) {
            flashcardSetService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/seed")
    public ResponseEntity<Void> seedDatabase() {
        databaseSeed.seedDatabase();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}