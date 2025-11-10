package org.skypro.exam_app.service;

import org.junit.jupiter.api.Test;
import org.skypro.exam_app.domain.Question;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaQuestionServiceTest {

    @Test
    void addGetAllUnique() {
        JavaQuestionService svc = new JavaQuestionService();
        int base = svc.getAll().size();

        svc.add(new Question("Q1", "A1"));
        svc.add(new Question("Q1", "A1"));

        assertEquals(base + 1, svc.getAll().size());
        assertTrue(svc.getAll().contains(new Question("Q1", "A1")));
    }

    @Test
    void removeExistingAndFailOnMissing() {
        JavaQuestionService svc = new JavaQuestionService();
        Question q = new Question("Q2", "A2");
        svc.add(q);

        assertDoesNotThrow(() -> svc.remove(q));
        assertThrows(NoSuchElementException.class,
                () -> svc.remove(q));
    }

    @Test
    void findExactMatchOrThrow() {
        JavaQuestionService svc = new JavaQuestionService();
        Question q = new Question("Q3", "A3");
        svc.add(q);

        assertEquals(q, svc.find("Q3", "A3"));
        assertThrows(NoSuchElementException.class,
                () -> svc.find("Q3", "WRONG"));
    }

    @Test
    void getRandomQuestionNotEmpty() {
        JavaQuestionService svc = new JavaQuestionService();
        assertNotNull(svc.getRandomQuestion());
    }

    @Test
    void getRandomQuestionThrowsOnEmpty() {
        var svc = new JavaQuestionService();
        while (!svc.getAll().isEmpty()) {
            var question = svc.getAll().stream().findFirst().get();
            svc.remove(question);
        }
        assertThrows(NoSuchElementException.class, svc::getRandomQuestion);
    }
}
