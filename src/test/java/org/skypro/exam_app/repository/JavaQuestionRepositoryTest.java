package org.skypro.exam_app.repository;


import org.junit.jupiter.api.Test;
import org.skypro.exam_app.domain.Question;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaQuestionRepositoryTest {

    private final JavaQuestionRepository repo = new JavaQuestionRepository();

    @Test
    void add_and_getAll_unique() {
        repo.init();
        int base = repo.getAll().size();
        repo.add(new Question("Q1", "A1"));
        repo.add(new Question("Q1", "A1"));
        assertEquals(base + 1, repo.getAll().size());
        assertTrue(repo.getAll().contains(new Question("Q1", "A1")));
    }

    @Test
    void remove_ok_and_then_throw() {
        repo.init();
        Question q = new Question("Q2", "A2");
        repo.add(q);
        assertDoesNotThrow(() -> repo.remove(q));
        assertThrows(NoSuchElementException.class, () -> repo.remove(q));
    }
}
