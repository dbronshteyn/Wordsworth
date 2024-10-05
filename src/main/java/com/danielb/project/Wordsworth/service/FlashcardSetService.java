package com.danielb.project.Wordsworth.service;

import com.danielb.project.Wordsworth.model.FlashcardSet;
import com.danielb.project.Wordsworth.repository.FlashcardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardSetService {

    private FlashcardSetRepository flashcardSetRepository;

    @Autowired
    public FlashcardSetService(FlashcardSetRepository flashcardSetRepository) {
        this.flashcardSetRepository = flashcardSetRepository;
    }

    public FlashcardSet save(FlashcardSet flashcardSet) {
        return flashcardSetRepository.save(flashcardSet);
    }

    public List<FlashcardSet> findAll() {
        return flashcardSetRepository.findAll();
    }

    public FlashcardSet findById(Long id) {
        return flashcardSetRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        flashcardSetRepository.deleteById(id);
    }
}