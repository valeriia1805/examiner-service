package org.skypro.exam_app.domain;

import jakarta.validation.constraints.NotBlank;

public record Question(@NotBlank String question, @NotBlank String answer) {}