package org.skypro.exam_app.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.exam_app.domain.Question;
import org.skypro.exam_app.exception.InsufficientQuestionsException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerService examinerService;

    @Test
    void returnsUniqueQuestionsWithRequestedSize() {
        when(questionService.getAll()).thenReturn(Set.of(
                new Question("Q1","A1"),
                new Question("Q2","A2"),
                new Question("Q3","A3"),
                new Question("Q4","A4")
        ));

        var out = examinerService.getQuestions(3);
        assertEquals(3, out.size());
        assertEquals(out.size(), out.stream().distinct().count());
    }

    @Test
    void throwsBadRequestWhenAmountTooLarge() {
        when(questionService.getAll()).thenReturn(Set.of(new Question("Q1","A1")));

        InsufficientQuestionsException exception =
                assertThrows(InsufficientQuestionsException.class, () -> examinerService.getQuestions(2));
        assertEquals("Requested 2 questions, but only 1 available", exception.getMessage());
    }

    @Test
    void zeroIsAllowed() {
        when(questionService.getAll()).thenReturn(Set.of(new Question("Q1","A1")));

        var out = examinerService.getQuestions(0);
        assertTrue(out.isEmpty());
    }
}
