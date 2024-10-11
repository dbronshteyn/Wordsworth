package com.danielb.project.wordsworth.controller;

import com.danielb.project.wordsworth.service.DatabaseSeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for seeding the database.
 */
@RestController
@RequestMapping("/api/seed")
public class DatabaseSeedController {

    private final DatabaseSeed databaseSeed;

    @Autowired
    public DatabaseSeedController(DatabaseSeed databaseSeed) {
        this.databaseSeed = databaseSeed;
    }

    /**
     * Seed the database.
     *
     * @return a response entity with status 201 created
     */
    @PostMapping("/generate")
    public ResponseEntity<Void> seedDatabase() {
        databaseSeed.seedDatabase();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
