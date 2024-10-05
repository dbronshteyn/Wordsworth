package com.danielb.project.Wordsworth.service;

import com.danielb.project.Wordsworth.model.Flashcard;
import com.danielb.project.Wordsworth.model.FlashcardSet;
import com.danielb.project.Wordsworth.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {

    private FlashcardRepository flashcardRepository;

    @Autowired
    public FlashcardService(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    public Flashcard save(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }

    public List<Flashcard> findAll() {
        return flashcardRepository.findAll();
    }

    public Flashcard findById(Long id) {
        return flashcardRepository.findById(id).orElse(null);
    }

    public List<Flashcard> findByFlashcardSet(FlashcardSet flashcardSet) {
        return flashcardRepository.findByFlashcardSet(flashcardSet);
    }

    public Flashcard findByIdAndFlashcardSet(Long id, FlashcardSet flashcardSet) {
        return flashcardRepository.findByIdAndFlashcardSet(id, flashcardSet).orElse(null);
    }

    public void deleteById(Long id) {
        flashcardRepository.deleteById(id);
    }
}