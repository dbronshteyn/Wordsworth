package com.danielb.project.Wordsworth.service;

import com.danielb.project.Wordsworth.model.FlashcardSet;
import com.danielb.project.Wordsworth.repository.FlashcardSetRepository;
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
        existingSet.setName(flashcardSet.getName());
        existingSet.setDescription(flashcardSet.getDescription());
        existingSet.setTotalFlashcards(flashcardSet.getTotalFlashcards());
        existingSet.setFavorite(flashcardSet.isFavorite());

        // Clear and update the flashcards collection
        if (existingSet.getFlashcards() != null) {
            existingSet.getFlashcards().clear();
            if (flashcardSet.getFlashcards() != null) {
                existingSet.getFlashcards().addAll(flashcardSet.getFlashcards());
            }
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