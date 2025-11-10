package org.skypro.exam_app.exception;

public class InsufficientQuestionsException extends RuntimeException {
    public InsufficientQuestionsException(int requested, int available) {
        super("Requested %d questions, but only %d available".formatted(requested, available));
    }
}
