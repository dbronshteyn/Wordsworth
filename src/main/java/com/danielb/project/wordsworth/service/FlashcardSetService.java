package com.danielb.project.wordsworth.service;

import com.danielb.project.wordsworth.model.FlashcardSet;
import com.danielb.project.wordsworth.repository.FlashcardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for managing flashcard sets.
 */
@Service
public class FlashcardSetService {

    private FlashcardSetRepository flashcardSetRepository;

    @Autowired
    public FlashcardSetService(FlashcardSetRepository flashcardSetRepository) {
        this.flashcardSetRepository = flashcardSetRepository;
    }

    /**
     * Save a flashcard set.
     *
     * @param flashcardSet the flashcard set to save
     * @return the saved flashcard set
     */
    @Transactional
    public FlashcardSet save(FlashcardSet flashcardSet) {
        return flashcardSetRepository.save(flashcardSet);
    }

    /**
     * Find all flashcard sets.
     *
     * @return a list of flashcard sets
     */
    public List<FlashcardSet> findAll() {
        return flashcardSetRepository.findAll();
    }

    /**
     * Find a flashcard set by its id.
     *
     * @param id the id of the flashcard set
     * @return the flashcard set if found, otherwise null
     */
    public FlashcardSet findById(Long id) {
        return flashcardSetRepository.findById(id).orElse(null);
    }

    /**
     * Update a flashcard set.
     *
     * @param id          the id of the flashcard set
     * @param flashcardSet the flashcard set to update
     * @return the updated flashcard set
     */
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

    /**
     * Delete a flashcard set by its id.
     *
     * @param id the id of the flashcard set
     */
    @Transactional
    public void deleteById(Long id) {
        flashcardSetRepository.deleteById(id);
    }

    /**
     * Delete all flashcard sets.
     */
    @Transactional
    public void deleteAll() {
        flashcardSetRepository.deleteAll();
    }
}