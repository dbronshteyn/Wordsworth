package com.danielb.project.wordsworth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GenerateRequest {

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Prompt is required")
    private String prompt;

    @NotNull(message = "Total is required")
    private int total;

    private boolean includeCode;

}
