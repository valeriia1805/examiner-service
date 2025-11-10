package org.skypro.exam_app.service;

import lombok.RequiredArgsConstructor;
import org.skypro.exam_app.domain.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> sources;
    private final Random random = new Random();

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> pool = new ArrayList<>();
        while (pool.size() < amount) {
            QuestionService service = sources.get(random.nextInt(sources.size()));
            pool.add(service.getRandomQuestion());
        }
        Collections.shuffle(pool);
        return new LinkedHashSet<>(pool.subList(0, amount));
    }
}