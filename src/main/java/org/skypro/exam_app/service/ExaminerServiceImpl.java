package org.skypro.exam_app.service;

import lombok.RequiredArgsConstructor;
import org.skypro.exam_app.domain.Question;
import org.skypro.exam_app.exception.InsufficientQuestionsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> sources;

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> pool = sources.stream()
                .flatMap(questionService -> questionService.getAll().stream())
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));

        if (amount < 0 || amount > pool.size()) {
            throw new InsufficientQuestionsException(amount, pool.size());
        }
        Collections.shuffle(pool);
        return new LinkedHashSet<>(pool.subList(0, amount));
    }
}