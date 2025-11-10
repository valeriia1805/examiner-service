package org.skypro.exam_app.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class Question {

    @NotBlank
    private final String question;

    @NotBlank
    private final String answer;
}
