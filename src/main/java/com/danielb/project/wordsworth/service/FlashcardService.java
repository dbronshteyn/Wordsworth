package com.danielb.project.wordsworth.service;

import com.danielb.project.wordsworth.model.Flashcard;
import com.danielb.project.wordsworth.model.FlashcardSet;
import com.danielb.project.wordsworth.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for managing flashcards.
 */
@Service
public class FlashcardService {

    private FlashcardRepository flashcardRepository;

    @Autowired
    public FlashcardService(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    /**
     * Save a flashcard.
     *
     * @param flashcard the flashcard to save
     * @return the saved flashcard
     */
    @Transactional
    public Flashcard save(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }

    /**
     * Find all flashcards in a flashcard set.
     *
     * @param flashcardSet the flashcard set
     * @return a list of flashcards
     */
    public List<Flashcard> findByFlashcardSet(FlashcardSet flashcardSet) {
        return flashcardRepository.findByFlashcardSet(flashcardSet);
    }

    /**
     * Find a flashcard by its id and the flashcard set it belongs to.
     *
     * @param id           the id of the flashcard
     * @param flashcardSet the flashcard set the flashcard belongs to
     * @return the flashcard if found, otherwise null
     */
    public Flashcard findByIdAndFlashcardSet(Long id, FlashcardSet flashcardSet) {
        return flashcardRepository.findByIdAndFlashcardSet(id, flashcardSet).orElse(null);
    }

    /**
     * Find a flashcard by its id.
     *
     * @param id the id of the flashcard
     * @return the flashcard if found, otherwise null
     */
    @Transactional
    public void deleteById(Long id) {
        flashcardRepository.deleteById(id);
    }

    /**
     * Delete all flashcards.
     */
    @Transactional
    public void deleteAll() {
        flashcardRepository.deleteAll();
    }
}