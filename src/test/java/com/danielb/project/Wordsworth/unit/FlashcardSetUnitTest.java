package com.danielb.project.Wordsworth.unit;

import com.danielb.project.Wordsworth.model.Flashcard;
import com.danielb.project.Wordsworth.model.FlashcardSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class FlashcardSetUnitTest {

    @Test
    void testFlashcardSetConstructor() {
        FlashcardSet flashcardSet = new FlashcardSet();
        assertNull(flashcardSet.getName());
        assertNull(flashcardSet.getDescription());
        assertEquals(0, flashcardSet.getTotalFlashcards());
        assertFalse(flashcardSet.isFavorite());
    }

    @Test
    void testParameterizedConstructor() {
        FlashcardSet flashcardSet = new FlashcardSet(1L, "Test Set", "Test Description", 0, true, new ArrayList<>());
        assertEquals(1L, flashcardSet.getId());
        assertEquals("Test Set", flashcardSet.getName());
        assertEquals("Test Description", flashcardSet.getDescription());
        assertTrue(flashcardSet.isFavorite());
        assertEquals(0, flashcardSet.getFlashcards().size());
    }

    @Test
    void testSettersAndGetters() {
        FlashcardSet flashcardSet = new FlashcardSet();
        flashcardSet.setName("Updated Name");
        flashcardSet.setDescription("Updated Description");
        flashcardSet.setFavorite(true);

        assertEquals("Updated Name", flashcardSet.getName());
        assertEquals("Updated Description", flashcardSet.getDescription());
        assertTrue(flashcardSet.isFavorite());
    }

    @Test
    void testFlashcardsList() {
        FlashcardSet flashcardSet = new FlashcardSet();
        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(new Flashcard(1L, "Term1", "Definition1", 0, 0, false, flashcardSet));
        flashcards.add(new Flashcard(2L, "Term2", "Definition2", 0, 0, false, flashcardSet));

        flashcardSet.setFlashcards(flashcards);

        assertEquals(2, flashcardSet.getFlashcards().size());
        assertEquals("Term1", flashcardSet.getFlashcards().get(0).getTerm());
    }

    @Test
    void testGetTotalFlashcards() {
        FlashcardSet flashcardSet = new FlashcardSet();
        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(new Flashcard(3L, "Term1", "Definition1", 0, 0, false, flashcardSet));
        flashcards.add(new Flashcard(4L,"Term2", "Definition2", 0, 0, false, flashcardSet));

        flashcardSet.setFlashcards(flashcards);

        assertEquals(2, flashcardSet.getTotalFlashcards());
    }

    @Test
    void testToString() {
        FlashcardSet flashcardSet = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        String expected = "FlashcardSet(id=null, name=Test Set, description=Test Description, totalFlashcards=0, favorite=false, flashcards=[])";
        assertEquals(expected, flashcardSet.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        FlashcardSet flashcardSet1 = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        FlashcardSet flashcardSet2 = new FlashcardSet(null, "Test Set", "Test Description", 0, false, new ArrayList<>());
        FlashcardSet flashcardSet3 = new FlashcardSet(null, "Another Set", "Different Description", 0, true, new ArrayList<>());

        assertEquals(flashcardSet1, flashcardSet2);
        assertNotEquals(flashcardSet1, flashcardSet3);
        assertEquals(flashcardSet1.hashCode(), flashcardSet2.hashCode());
        assertNotEquals(flashcardSet1.hashCode(), flashcardSet3.hashCode());
    }

    @Test
    void testTotalFlashcardsWhenAddingFlashcards() {
        FlashcardSet flashcardSet = new FlashcardSet();
        flashcardSet.setFlashcards(new ArrayList<>());

        Flashcard flashcard1 = new Flashcard();
        flashcard1.setTerm("Term1");
        flashcard1.setDefinition("Definition1");
        flashcard1.setFlashcardSet(flashcardSet);

        Flashcard flashcard2 = new Flashcard();
        flashcard2.setTerm("Term2");
        flashcard2.setDefinition("Definition2");
        flashcard2.setFlashcardSet(flashcardSet);

        flashcardSet.getFlashcards().add(flashcard1);

        assertEquals(1, flashcardSet.getTotalFlashcards());


        flashcardSet.getFlashcards().add(flashcard2);

        assertEquals(2, flashcardSet.getTotalFlashcards());
    }
}
