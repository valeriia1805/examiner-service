package org.skypro.exam_app.service;

import org.skypro.exam_app.domain.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

/**
 * Хранилище вопросов по Java.
 */
@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new LinkedHashSet<>();
    private final Random random = new Random();

    public JavaQuestionService() {
        questions.add(new Question("Что такое JVM?", "Виртуальная машина Java"));
        questions.add(new Question("Что делает ключевое слово final?", "Запрещает переопределение/изменение"));
        questions.add(new Question("Разница между == и equals?", "== сравнивает ссылки, equals — содержимое"));
    }

    @Override
    public Question add(Question q) {
        questions.add(q);
        return q;
    }

    @Override
    public Question remove(Question q) {
        if (!questions.remove(q)) {
            throw new NoSuchElementException("Question not found");
        }
        return q;
    }

    @Override
    public Question find(String question, String answer) {
        Question probe = new Question(question, answer);
        for (Question q : questions) {
            if (q.equals(probe)) return q;
        }
        throw new NoSuchElementException("Question not found");
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) throw new NoSuchElementException("No questions available");
        int idx = random.nextInt(questions.size());
        return new ArrayList<>(questions).get(idx);
    }
}
