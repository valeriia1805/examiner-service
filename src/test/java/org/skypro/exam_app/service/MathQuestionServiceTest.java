package org.skypro.exam_app.service;

import org.junit.jupiter.api.Test;
import org.skypro.exam_app.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@SpringBootTest
class MathQuestionServiceTest {

    @Autowired
    private MathQuestionService mathQuestionService;

    @Test
    void add_find_remove() {
        Question q = new Question("3 + 14", "17");
        mathQuestionService.add(q);
        assertEquals(q, mathQuestionService.find("3 + 14", "17"));
        assertDoesNotThrow(() -> mathQuestionService.remove(q));
        assertThrows(NoSuchElementException.class, () -> mathQuestionService.find("3 + 14", "17"));
    }

    @Test
    void getAll_and_random() {
        assertFalse(mathQuestionService.getAll().isEmpty());
        assertNotNull(mathQuestionService.getRandomQuestion());
    }

    @Test
    @DirtiesContext(methodMode = AFTER_METHOD)
    void getRandom_throws_on_empty() {
        while (!mathQuestionService.getAll().isEmpty()) {
            var question = mathQuestionService.getAll().stream().findFirst().get();
            mathQuestionService.remove(question);
        }
        assertThrows(NoSuchElementException.class, () -> mathQuestionService.getRandomQuestion());
    }
}
