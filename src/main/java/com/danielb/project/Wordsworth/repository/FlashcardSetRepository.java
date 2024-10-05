package com.danielb.project.Wordsworth.repository;

import com.danielb.project.Wordsworth.model.FlashcardSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardSetRepository extends JpaRepository<FlashcardSet, Long> {
}