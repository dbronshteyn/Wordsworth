package com.danielb.project.wordsworth.unit;
import com.danielb.project.wordsworth.model.GenerateRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenerateRequestUnitTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidGenerateRequest() {
        GenerateRequest request = new GenerateRequest();
        request.setSubject("Test Subject");
        request.setPrompt("Test Prompt");
        request.setTotal(5);
        request.setIncludeCode(true);

        Set<ConstraintViolation<GenerateRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testSubjectIsBlank() {
        GenerateRequest request = new GenerateRequest();
        request.setSubject("");
        request.setPrompt("Test Prompt");
        request.setTotal(5);
        request.setIncludeCode(true);

        Set<ConstraintViolation<GenerateRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("Subject is required", violations.iterator().next().getMessage());
    }

    @Test
    void testPromptIsBlank() {
        GenerateRequest request = new GenerateRequest();
        request.setSubject("Test Subject");
        request.setPrompt("");
        request.setTotal(5);
        request.setIncludeCode(true);

        Set<ConstraintViolation<GenerateRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("Prompt is required", violations.iterator().next().getMessage());
    }

    @Test
    void testTotalIsNull() {
        GenerateRequest request = new GenerateRequest();
        request.setSubject("Test Subject");
        request.setPrompt("Test Prompt");
        request.setTotal(0);
        request.setIncludeCode(true);

        Set<ConstraintViolation<GenerateRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }
}