package com.danielb.project.Wordsworth.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    @Test
    void testFlashcardConstructor() {
        Flashcard flashcard = new Flashcard("term", "definition", 1, 2, true, null);
        assertEquals("term", flashcard.getTerm());
        assertEquals("definition", flashcard.getDefinition());
        assertEquals(1, flashcard.getCorrectAttempts());
        assertEquals(2, flashcard.getTotalAttempts());
        assertTrue(flashcard.isMastered());
        assertNull(flashcard.getFlashcardSet());
    }

    @Test
    void testSettersAndGetters() {
        Flashcard flashcard = new Flashcard();
        flashcard.setTerm("new term");
        flashcard.setDefinition("new definition");
        flashcard.setCorrectAttempts(3);
        flashcard.setTotalAttempts(4);
        flashcard.setMastered(false);

        assertEquals("new term", flashcard.getTerm());
        assertEquals("new definition", flashcard.getDefinition());
        assertEquals(3, flashcard.getCorrectAttempts());
        assertEquals(4, flashcard.getTotalAttempts());
        assertFalse(flashcard.isMastered());
    }
}