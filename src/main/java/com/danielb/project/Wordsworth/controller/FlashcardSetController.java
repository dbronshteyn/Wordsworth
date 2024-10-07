package com.danielb.project.Wordsworth.controller;

import com.danielb.project.Wordsworth.model.Flashcard;
import com.danielb.project.Wordsworth.model.FlashcardSet;
import com.danielb.project.Wordsworth.repository.FlashcardSetRepository;
import com.danielb.project.Wordsworth.service.DatabaseSeed;
import com.danielb.project.Wordsworth.service.FlashcardSetService;
import com.danielb.project.Wordsworth.service.FlashcardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcard-sets")
public class FlashcardSetController {

    private FlashcardSetService flashcardSetService;
    private FlashcardService flashcardService;
    private DatabaseSeed databaseSeed;

    @Autowired
    public FlashcardSetController(FlashcardSetService flashcardSetService, FlashcardService flashcardService, FlashcardSetRepository flashcardSetRepository, DatabaseSeed databaseSeed) {
        this.flashcardSetService = flashcardSetService;
        this.flashcardService = flashcardService;
        this.databaseSeed = databaseSeed;
    }

    @GetMapping
    public ResponseEntity<List<FlashcardSet>> getAllFlashcardSets() {
        List<FlashcardSet> flashcardSets = flashcardSetService.findAll();
        return ResponseEntity.ok(flashcardSets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardSet> getFlashcardSetById(@PathVariable Long id) {
        FlashcardSet flashcardSet = flashcardSetService.findById(id);

        if (flashcardSet != null) {
            return ResponseEntity.ok(flashcardSet);
        }

        return ResponseEntity.notFound().build();
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
            return ResponseEntity.ok(updatedFlashcardSet);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlashcardSet(@PathVariable Long id) {
        FlashcardSet existingFlashcardSet = flashcardSetService.findById(id);

        if (existingFlashcardSet != null) {
            flashcardSetService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/seed")
    public ResponseEntity<Void> seedDatabase() {
        databaseSeed.seedDatabase();
        return ResponseEntity.noContent().build();
    }


}