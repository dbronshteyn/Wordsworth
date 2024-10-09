package com.danielb.project.wordsworth.service;

import com.danielb.project.wordsworth.model.Flashcard;
import com.danielb.project.wordsworth.model.FlashcardSet;
import com.danielb.project.wordsworth.repository.FlashcardRepository;
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

//    public List<Flashcard> findAll() {
//        return flashcardRepository.findAll();
//    }
//
//    public Flashcard findById(Long id) {
//        return flashcardRepository.findById(id).orElse(null);
//    }

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

    public void deleteById(Long id) {
        flashcardRepository.deleteById(id);
    }

    public void deleteAll() {
        flashcardRepository.deleteAll();
    }
}