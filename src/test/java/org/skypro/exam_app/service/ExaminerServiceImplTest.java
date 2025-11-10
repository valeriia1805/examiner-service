package org.skypro.exam_app.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void zeroIsAllowed() {
        var out = examinerService.getQuestions(0);
        assertTrue(out.isEmpty());
    }
}
