package org.skypro.exam_app.repository;

import org.junit.jupiter.api.Test;
import org.skypro.exam_app.domain.Question;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MathQuestionRepositoryTest {

    private final MathQuestionRepository repo = new MathQuestionRepository();

    @Test
    void initial_data_present_and_unique_ops() {
        repo.init();
        int base = repo.getAll().size();
        repo.add(new Question("10 + 5", "15"));
        repo.add(new Question("10 + 5", "15"));
        assertEquals(base + 1, repo.getAll().size());
        assertTrue(repo.getAll().contains(new Question("10 + 5", "15")));
    }

    @Test
    void remove_behaviour() {
        repo.init();
        Question q = new Question("8 * 3", "24");
        repo.add(q);
        assertDoesNotThrow(() -> repo.remove(q));
        assertThrows(NoSuchElementException.class, () -> repo.remove(q));
    }
}
