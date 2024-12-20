package com.danielb.project.wordsworth.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of flashcards.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;
    private int totalFlashcards;
    private boolean favorite;

    @OneToMany(mappedBy = "flashcardSet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Flashcard> flashcards;

    /**
     * Get the total number of flashcards in the set.
     *
     * @return the total number of flashcards
     */
    @JsonProperty
    public int getTotalFlashcards() {
        return flashcards.size();
    }

    /**
     * Ensure that the flashcards list is initialized before persisting or updating.
     */
    @PrePersist
    @PreUpdate
    private void ensureFlashcardsInitialized() {
        if (this.flashcards == null) {
            this.flashcards = new ArrayList<>();
        }
    }

}