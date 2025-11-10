package org.skypro.exam_app.service;

import lombok.RequiredArgsConstructor;
import org.skypro.exam_app.domain.Question;
import org.skypro.exam_app.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service("mathQuestionService")
@RequiredArgsConstructor
public class MathQuestionService implements QuestionService {

    private final QuestionRepository mathQuestionRepository;
    private final Random random = new Random();

    @Override
    public Question add(Question q) {
        return mathQuestionRepository.add(q);
    }

    @Override
    public Question remove(Question q) {
        return mathQuestionRepository.remove(q);
    }

    @Override
    public Question find(String question, String answer) {
        Question probe = new Question(question, answer);
        return mathQuestionRepository.getAll().stream()
                .filter(probe::equals)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Question not found"));
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> list = new ArrayList<>(mathQuestionRepository.getAll());
        if (list.isEmpty()) throw new NoSuchElementException("No questions available");
        return list.get(random.nextInt(list.size()));
    }
}
