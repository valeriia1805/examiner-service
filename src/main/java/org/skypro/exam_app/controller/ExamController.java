package org.skypro.exam_app.controller;

import lombok.RequiredArgsConstructor;
import org.skypro.exam_app.domain.Question;
import org.skypro.exam_app.exception.InsufficientQuestionsException;
import org.skypro.exam_app.service.ExaminerService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExaminerService examinerService;

    @GetMapping("/get/{amount}")
    public Collection<Question> get(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }

    @ExceptionHandler(InsufficientQuestionsException.class)
    @ResponseStatus(BAD_REQUEST)
    private String handleTooManyQuestionsRequested(InsufficientQuestionsException ex) {
        return ex.getMessage();
    }
}
