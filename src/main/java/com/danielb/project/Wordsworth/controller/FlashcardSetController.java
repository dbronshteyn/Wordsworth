package com.danielb.project.Wordsworth.controller;

import com.danielb.project.Wordsworth.model.FlashcardSet;
import com.danielb.project.Wordsworth.service.FlashcardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcard-sets")
public class FlashcardSetController {

    private FlashcardSetService flashcardSetService;

    @Autowired
    public FlashcardSetController(FlashcardSetService flashcardSetService) {
        this.flashcardSetService = flashcardSetService;
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
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FlashcardSet> createFlashcardSet(@RequestBody FlashcardSet flashcardSet) {
        FlashcardSet createdFlashcardSet = flashcardSetService.save(flashcardSet);

        return ResponseEntity.ok(createdFlashcardSet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlashcardSet> updateFlashcardSet(@PathVariable Long id, @RequestBody FlashcardSet flashcardSet) {
        FlashcardSet existingFlashcardSet = flashcardSetService.findById(id);

        if (existingFlashcardSet != null) {
            flashcardSet.setId(id);
            FlashcardSet updatedFlashcardSet = flashcardSetService.save(flashcardSet);
            return ResponseEntity.ok(updatedFlashcardSet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlashcardSet(@PathVariable Long id) {
        FlashcardSet existingFlashcardSet = flashcardSetService.findById(id);

        if (existingFlashcardSet != null) {
            flashcardSetService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}