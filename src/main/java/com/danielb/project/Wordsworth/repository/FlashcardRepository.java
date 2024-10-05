package com.danielb.project.Wordsworth.repository;

import com.danielb.project.Wordsworth.model.Flashcard;
import com.danielb.project.Wordsworth.model.FlashcardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByFlashcardSet(FlashcardSet flashcardSet);
    Optional<Flashcard> findByIdAndFlashcardSet(Long id, FlashcardSet flashcardSet);
}