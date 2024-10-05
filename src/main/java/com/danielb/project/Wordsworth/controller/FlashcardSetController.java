package com.danielb.project.Wordsworth.controller;

import com.danielb.project.Wordsworth.model.Flashcard;
import com.danielb.project.Wordsworth.model.FlashcardSet;
import com.danielb.project.Wordsworth.repository.FlashcardSetRepository;
import com.danielb.project.Wordsworth.service.FlashcardSetService;
import com.danielb.project.Wordsworth.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcard-sets")
public class FlashcardSetController {

    private FlashcardSetService flashcardSetService;
    private FlashcardService flashcardService;

    @Autowired
    public FlashcardSetController(FlashcardSetService flashcardSetService, FlashcardService flashcardService, FlashcardSetRepository flashcardSetRepository) {
        this.flashcardSetService = flashcardSetService;
        this.flashcardService = flashcardService;
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
            FlashcardSet updatedFlashcardSet = flashcardSetService.updateFlashcardSet(id, flashcardSet);
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

    @PostMapping("/seed")
    public ResponseEntity<Void> seedDatabase() {
        // Clear existing data (if necessary)
        flashcardSetService.deleteAll();
        flashcardService.deleteAll();

        // Seed Flashcard Sets
        FlashcardSet set1 = new FlashcardSet();
        set1.setName("Science Terms");
        set1.setDescription("A set of flashcards for science-related terms.");
        set1.setTotalFlashcards(5);
        set1.setFavorite(false);

        Flashcard flashcard1 = new Flashcard("Atom", "The basic unit of a chemical element.", 0, 0, false, set1);
        Flashcard flashcard2 = new Flashcard("Photosynthesis", "Process used by plants to convert light into energy.", 0, 0, false, set1);
        Flashcard flashcard3 = new Flashcard("Molecule", "Two or more atoms bonded together.", 0, 0, false, set1);
        Flashcard flashcard4 = new Flashcard("Cell", "The smallest unit of life.", 0, 0, false, set1);
        Flashcard flashcard5 = new Flashcard("DNA", "Molecule that carries genetic information.", 0, 0, false, set1);

        set1.setFlashcards(List.of(flashcard1, flashcard2, flashcard3, flashcard4, flashcard5));
        flashcardSetService.save(set1);

        // Seed Flashcard Set 2
        FlashcardSet set2 = new FlashcardSet();
        set2.setName("History Dates");
        set2.setDescription("Flashcards for important historical dates.");
        set2.setTotalFlashcards(3);
        set2.setFavorite(true);

        Flashcard flashcard6 = new Flashcard("Declaration of Independence", "Adopted on July 4, 1776.", 0, 0, false, set2);
        Flashcard flashcard7 = new Flashcard("World War I", "Started in 1914 and ended in 1918.", 0, 0, false, set2);
        Flashcard flashcard8 = new Flashcard("Civil Rights Act", "Passed in 1964 to end discrimination.", 0, 0, false, set2);

        set2.setFlashcards(List.of(flashcard6, flashcard7, flashcard8));
        flashcardSetService.save(set2);

        // Seed Flashcard Set 3
        FlashcardSet set3 = new FlashcardSet();
        set3.setName("Programming Languages");
        set3.setDescription("Learn different programming languages.");
        set3.setTotalFlashcards(4);
        set3.setFavorite(false);

        Flashcard flashcard9 = new Flashcard("Java", "A high-level, class-based programming language.", 0, 0, false, set3);
        Flashcard flashcard10 = new Flashcard("Python", "A high-level programming language known for its readability.", 0, 0, false, set3);
        Flashcard flashcard11 = new Flashcard("JavaScript", "A programming language commonly used in web development.", 0, 0, false, set3);
        Flashcard flashcard12 = new Flashcard("C++", "An extension of the C programming language.", 0, 0, false, set3);

        set3.setFlashcards(List.of(flashcard9, flashcard10, flashcard11, flashcard12));
        flashcardSetService.save(set3);

        return ResponseEntity.ok().build();
    }


}