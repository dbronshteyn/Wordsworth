package com.danielb.project.wordsworth.repository;

import com.danielb.project.wordsworth.model.FlashcardSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardSetRepository extends JpaRepository<FlashcardSet, Long> {
}