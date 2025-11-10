package org.skypro.exam_app.service;


import org.junit.jupiter.api.Test;
import org.skypro.exam_app.exception.InsufficientQuestionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ExaminerServiceImplTest {

    @Autowired
    private ExaminerServiceImpl examinerService;

    @Test
    void returnsUniqueQuestionsWithRequestedSize() {
        var out = examinerService.getQuestions(3);
        assertEquals(3, out.size());
        assertEquals(out.size(), out.stream().distinct().count());
    }

    @Test
    void throwsBadRequestWhenAmountTooLarge() {
        InsufficientQuestionsException exception =
                assertThrows(InsufficientQuestionsException.class, () -> examinerService.getQuestions(100));
        assertEquals("Requested 100 questions, but only 7 available", exception.getMessage());
    }

    @Test
    void zeroIsAllowed() {
        var out = examinerService.getQuestions(0);
        assertTrue(out.isEmpty());
    }
}
