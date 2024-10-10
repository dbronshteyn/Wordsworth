package com.danielb.project.wordsworth.repository;

import com.danielb.project.wordsworth.model.Flashcard;
import com.danielb.project.wordsworth.model.FlashcardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing flashcards.
 */
@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    /**
     * Find all flashcards in a flashcard set.
     *
     * @param flashcardSet the flashcard set
     * @return a list of flashcards
     */
    List<Flashcard> findByFlashcardSet(FlashcardSet flashcardSet);

    /**
     * Find a flashcard by its id and the flashcard set it belongs to.
     *
     * @param id           the id of the flashcard
     * @param flashcardSet the flashcard set the flashcard belongs to
     * @return the flashcard if found, otherwise null
     */
    Optional<Flashcard> findByIdAndFlashcardSet(Long id, FlashcardSet flashcardSet);
}