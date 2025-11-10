package org.skypro.exam_app.service;


import org.skypro.exam_app.domain.Question;
import org.skypro.exam_app.exception.InsufficientQuestionsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> pool = new ArrayList<>(questionService.getAll());
        if (amount < 0 || amount > pool.size()) {
            throw new InsufficientQuestionsException(amount, pool.size());
        }
        Collections.shuffle(pool);
        return new LinkedHashSet<>(pool.subList(0, amount));
    }
}