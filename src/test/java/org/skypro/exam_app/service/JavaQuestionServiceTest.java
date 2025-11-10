package org.skypro.exam_app.service;

import org.junit.jupiter.api.Test;
import org.skypro.exam_app.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JavaQuestionServiceTest {

    @Autowired
    private JavaQuestionService javaQuestionService;

    @Test
    void addGetAllUnique() {
        int base = javaQuestionService.getAll().size();

        javaQuestionService.add(new Question("Q1", "A1"));
        javaQuestionService.add(new Question("Q1", "A1"));

        assertEquals(base + 1, javaQuestionService.getAll().size());
        assertTrue(javaQuestionService.getAll().contains(new Question("Q1", "A1")));
    }

    @Test
    void removeExistingAndFailOnMissing() {
        Question q = new Question("Q2", "A2");
        javaQuestionService.add(q);

        assertDoesNotThrow(() -> javaQuestionService.remove(q));
        assertThrows(NoSuchElementException.class,
                () -> javaQuestionService.remove(q));
    }

    @Test
    void findExactMatchOrThrow() {
        Question q = new Question("Q3", "A3");
        javaQuestionService.add(q);

        assertEquals(q, javaQuestionService.find("Q3", "A3"));
        assertThrows(NoSuchElementException.class,
                () -> javaQuestionService.find("Q3", "WRONG"));
    }

    @Test
    void getRandomQuestionNotEmpty() {
        assertNotNull(javaQuestionService.getRandomQuestion());
    }

    @Test
    void getRandomQuestionThrowsOnEmpty() {
        while (!javaQuestionService.getAll().isEmpty()) {
            var question = javaQuestionService.getAll().stream().findFirst().get();
            javaQuestionService.remove(question);
        }
        assertThrows(NoSuchElementException.class, javaQuestionService::getRandomQuestion);
    }
}
