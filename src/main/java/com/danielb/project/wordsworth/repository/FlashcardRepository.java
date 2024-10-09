package com.danielb.project.wordsworth.repository;

import com.danielb.project.wordsworth.model.Flashcard;
import com.danielb.project.wordsworth.model.FlashcardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByFlashcardSet(FlashcardSet flashcardSet);
    Optional<Flashcard> findByIdAndFlashcardSet(Long id, FlashcardSet flashcardSet);
}