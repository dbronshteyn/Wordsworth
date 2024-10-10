package com.danielb.project.wordsworth.service;

import com.danielb.project.wordsworth.model.FlashcardSet;
import com.danielb.project.wordsworth.repository.FlashcardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public FlashcardSet updateFlashcardSet(Long id, FlashcardSet flashcardSet) {
        FlashcardSet existingSet = flashcardSetRepository.findById(id).orElseThrow();

        if (!existingSet.getName().equals(flashcardSet.getName())) {
            existingSet.setName(flashcardSet.getName());
        }
        if (!existingSet.getDescription().equals(flashcardSet.getDescription())) {
            existingSet.setDescription(flashcardSet.getDescription());
        }
        if (existingSet.isFavorite() != flashcardSet.isFavorite()) {
            existingSet.setFavorite(flashcardSet.isFavorite());
        }

        return flashcardSetRepository.save(existingSet);
    }

    @Transactional
    public void deleteById(Long id) {
        flashcardSetRepository.deleteById(id);
    }

    public void deleteAll() {
        flashcardSetRepository.deleteAll();
    }
}