package org.skypro.exam_app.service;

import org.junit.jupiter.api.Test;
import org.skypro.exam_app.domain.Question;
import org.skypro.exam_app.exception.MethodNotAllowedForMathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@SpringBootTest
class MathQuestionServiceTest {

    @Autowired
    private MathQuestionService mathQuestionService;

    @Test
    void add_find_remove() {
        Question q = new Question("3 + 14", "17");
        assertThrows(MethodNotAllowedForMathException.class, () -> mathQuestionService.add(q));
    }

    @Test
    void getAll_and_random() {
        assertThrows(MethodNotAllowedForMathException.class, () -> mathQuestionService.getAll());
    }

    @Test
    @DirtiesContext(methodMode = AFTER_METHOD)
    void getRandom_throws_on_empty() {
        assertDoesNotThrow(() -> mathQuestionService.getRandomQuestion());
    }
}
