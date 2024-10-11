package com.danielb.project.wordsworth.repository;

import com.danielb.project.wordsworth.model.FlashcardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing flashcard sets.
 */
@Repository
public interface FlashcardSetRepository extends JpaRepository<FlashcardSet, Long> {
}