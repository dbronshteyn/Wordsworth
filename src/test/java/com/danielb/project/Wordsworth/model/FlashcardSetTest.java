package com.danielb.project.Wordsworth.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlashcardSetTest {

    @Test
    void testFlashcardSetConstructor() {
        FlashcardSet flashcardSet = new FlashcardSet();
        flashcardSet.setName("setName");
        assertEquals("setName", flashcardSet.getName());
    }

    @Test
    void testSettersAndGetters() {
        FlashcardSet flashcardSet = new FlashcardSet();
        flashcardSet.setName("new setName");

        assertEquals("new setName", flashcardSet.getName());
    }
}