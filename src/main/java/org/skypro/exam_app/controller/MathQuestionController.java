package org.skypro.exam_app.controller;

import lombok.RequiredArgsConstructor;
import org.skypro.exam_app.domain.Question;
import org.skypro.exam_app.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/math")
@RequiredArgsConstructor
public class MathQuestionController {

    private final QuestionService mathQuestionService;

    @GetMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer) {
        return mathQuestionService.add(new Question(question, answer));
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam String question, @RequestParam String answer) {
        return mathQuestionService.remove(new Question(question, answer));
    }

    @GetMapping("/find")
    public Question find(@RequestParam String question, @RequestParam String answer) {
        return mathQuestionService.find(question, answer);
    }

    @GetMapping
    public Collection<Question> getAll() {
        return mathQuestionService.getAll();
    }
}
