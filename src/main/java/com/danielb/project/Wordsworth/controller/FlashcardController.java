package com.danielb.project.Wordsworth.controller;

import com.danielb.project.Wordsworth.model.Flashcard;
import com.danielb.project.Wordsworth.model.FlashcardSet;
import com.danielb.project.Wordsworth.service.FlashcardService;
import com.danielb.project.Wordsworth.service.FlashcardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcard-sets/{setId}/flashcards")
public class FlashcardController {

    private FlashcardService flashcardService;
    private FlashcardSetService flashcardSetService;

    @Autowired
    public FlashcardController(FlashcardService flashcardService, FlashcardSetService flashcardSetService) {
        this.flashcardService = flashcardService;
        this.flashcardSetService = flashcardSetService;
    }

    @GetMapping
    public ResponseEntity<List<Flashcard>> getAllFlashcards(@PathVariable Long setId) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            List<Flashcard> flashcards = flashcardService.findByFlashcardSet(flashcardSet);
            return ResponseEntity.ok(flashcards);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flashcard> getFlashcardById(@PathVariable Long setId, @PathVariable Long id) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            Flashcard flashcard = flashcardService.findByIdAndFlashcardSet(id, flashcardSet);
            if (flashcard != null) {
                return ResponseEntity.ok(flashcard);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Flashcard> createFlashcard(@PathVariable Long setId, @RequestBody Flashcard flashcard) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            flashcard.setFlashcardSet(flashcardSet);
            Flashcard createdFlashcard = flashcardService.save(flashcard);
            return ResponseEntity.ok(createdFlashcard);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flashcard> updateFlashcard(@PathVariable Long setId, @PathVariable Long id, @RequestBody Flashcard flashcard) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            Flashcard existingFlashcard = flashcardService.findByIdAndFlashcardSet(id, flashcardSet);
            if (existingFlashcard != null) {
                flashcard.setId(id);
                flashcard.setFlashcardSet(flashcardSet);
                Flashcard updatedFlashcard = flashcardService.save(flashcard);
                return ResponseEntity.ok(updatedFlashcard);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long setId, @PathVariable Long id) {
        FlashcardSet flashcardSet = flashcardSetService.findById(setId);
        if (flashcardSet != null) {
            Flashcard existingFlashcard = flashcardService.findByIdAndFlashcardSet(id, flashcardSet);
            if (existingFlashcard != null) {
                flashcardService.deleteById(id);
                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.notFound().build();
    }


}