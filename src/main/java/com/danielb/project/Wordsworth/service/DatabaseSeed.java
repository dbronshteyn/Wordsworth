package com.danielb.project.Wordsworth.service;

import com.danielb.project.Wordsworth.model.Flashcard;
import com.danielb.project.Wordsworth.model.FlashcardSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseSeed {

    private FlashcardSetService flashcardSetService;
    private FlashcardService flashcardService;

    @Autowired
    public DatabaseSeed(FlashcardSetService flashcardSetService, FlashcardService flashcardService) {
        this.flashcardSetService = flashcardSetService;
        this.flashcardService = flashcardService;
    }

    public void seedDatabase() {

        // Clear existing data (if necessary)
        flashcardSetService.deleteAll();
        flashcardService.deleteAll();

        FlashcardSet set1 = new FlashcardSet();
        set1.setName("Science Terms");
        set1.setDescription("A set of flashcards for science-related terms.");
        set1.setTotalFlashcards(5);
        set1.setFavorite(false);

        Flashcard flashcard1 = new Flashcard(1L, "Atom", "The basic unit of a chemical element.", 0, 0, false, set1);
        Flashcard flashcard2 = new Flashcard(2L, "Photosynthesis", "Process used by plants to convert light into energy.", 0, 0, false, set1);
        Flashcard flashcard3 = new Flashcard(3L, "Molecule", "Two or more atoms bonded together.", 0, 0, false, set1);
        Flashcard flashcard4 = new Flashcard(4L, "Cell", "The smallest unit of life.", 0, 0, false, set1);
        Flashcard flashcard5 = new Flashcard(5L, "DNA", "Molecule that carries genetic information.", 0, 0, false, set1);

        set1.setFlashcards(List.of(flashcard1, flashcard2, flashcard3, flashcard4, flashcard5));
        flashcardSetService.save(set1);

        // Seed Flashcard Set 2
        FlashcardSet set2 = new FlashcardSet();
        set2.setName("History Dates");
        set2.setDescription("Flashcards for important historical dates.");
        set2.setTotalFlashcards(3);
        set2.setFavorite(true);

        Flashcard flashcard6 = new Flashcard(6L, "Declaration of Independence", "Adopted on July 4, 1776.", 0, 0, false, set2);
        Flashcard flashcard7 = new Flashcard(7L,"World War I", "Started in 1914 and ended in 1918.", 0, 0, false, set2);
        Flashcard flashcard8 = new Flashcard(8L,"Civil Rights Act", "Passed in 1964 to end discrimination.", 0, 0, false, set2);

        set2.setFlashcards(List.of(flashcard6, flashcard7, flashcard8));
        flashcardSetService.save(set2);

        // Seed Flashcard Set 3
        FlashcardSet set3 = new FlashcardSet();
        set3.setName("Programming Languages");
        set3.setDescription("Learn different programming languages.");
        set3.setTotalFlashcards(4);
        set3.setFavorite(false);

        Flashcard flashcard9 = new Flashcard(9L,"Java", "A high-level, class-based programming language.", 0, 0, false, set3);
        Flashcard flashcard10 = new Flashcard(10L,"Python", "A high-level programming language known for its readability.", 0, 0, false, set3);
        Flashcard flashcard11 = new Flashcard(11L,"JavaScript", "A programming language commonly used in web development.", 0, 0, false, set3);
        Flashcard flashcard12 = new Flashcard(12L,"C++", "An extension of the C programming language.", 0, 0, false, set3);

        set3.setFlashcards(List.of(flashcard9, flashcard10, flashcard11, flashcard12));
        flashcardSetService.save(set3);
    }
}