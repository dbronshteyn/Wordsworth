package com.danielb.project.Wordsworth.unit;

import com.danielb.project.Wordsworth.model.Flashcard;
import com.danielb.project.Wordsworth.model.FlashcardSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardUnitTest {

    @Test
    void testFlashcardConstructor() {
        Flashcard flashcard = new Flashcard(1L,"term", "definition", 1, 2, true, null);
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

    @Test
    void testFlashcardWithSet() {
        FlashcardSet flashcardSet = new FlashcardSet(1L, "Sample Set", "Sample Description", 0, false, new ArrayList<>());
        Flashcard flashcard = new Flashcard(2L,"term", "definition", 0, 0, false, flashcardSet);

        assertEquals("term", flashcard.getTerm());
        assertEquals("definition", flashcard.getDefinition());
        assertEquals(flashcardSet, flashcard.getFlashcardSet());
        assertEquals("Sample Set", flashcard.getFlashcardSet().getName());
    }

    @Test
    void testAttemptsLogic() {
        Flashcard flashcard = new Flashcard();
        flashcard.setCorrectAttempts(5);
        flashcard.setTotalAttempts(10);

        assertEquals(5, flashcard.getCorrectAttempts());
        assertEquals(10, flashcard.getTotalAttempts());
    }

    @Test
    void testMasteredLogic() {
        Flashcard flashcard = new Flashcard();
        flashcard.setMastered(true);
        assertTrue(flashcard.isMastered());

        flashcard.setMastered(false);
        assertFalse(flashcard.isMastered());
    }

    @Test
    void testToString() {
        Flashcard flashcard = new Flashcard(null,"term", "definition", 1, 2, true, null);
        String expected = "Flashcard(id=null, term=term, definition=definition, correctAttempts=1, totalAttempts=2, mastered=true, flashcardSet=null)";
        assertEquals(expected, flashcard.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Flashcard flashcard1 = new Flashcard(null,"term", "definition", 1, 2, true, null);
        Flashcard flashcard2 = new Flashcard(null,"term", "definition", 1, 2, true, null);
        Flashcard flashcard3 = new Flashcard(null,"differentTerm", "differentDefinition", 1, 2, true, null);

        assertEquals(flashcard1, flashcard2);
        assertNotEquals(flashcard1, flashcard3);
        assertEquals(flashcard1.hashCode(), flashcard2.hashCode());
        assertNotEquals(flashcard1.hashCode(), flashcard3.hashCode());
    }
}
