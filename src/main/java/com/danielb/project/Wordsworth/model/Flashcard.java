package com.danielb.project.Wordsworth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String term;
    private String definition;
    private int correctAttempts;
    private int totalAttempts;
    private boolean mastered;

    @ManyToOne
    @JoinColumn(name = "flashcard_set_id")
    @JsonBackReference
    private FlashcardSet flashcardSet;

    public Flashcard(String term, String definition, int correctAttempts, int totalAttempts, boolean mastered, FlashcardSet flashcardSet) {
        this.term = term;
        this.definition = definition;
        this.correctAttempts = correctAttempts;
        this.totalAttempts = totalAttempts;
        this.mastered = mastered;
        this.flashcardSet = flashcardSet;
    }

}