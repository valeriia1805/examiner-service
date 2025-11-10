package org.skypro.exam_app.repository;

import jakarta.annotation.PostConstruct;
import org.skypro.exam_app.domain.Question;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {

    private final Set<Question> data = new LinkedHashSet<>();

    @PostConstruct
    void init() {
        data.add(new Question("Что такое JVM?", "Виртуальная машина Java"));
        data.add(new Question("Разница между == и equals?", "== сравнивает ссылки, equals — содержимое"));
        data.add(new Question("Что делает ключевое слово final?", "Запрещает переопределение/изменение"));
    }

    @Override
    public Question add(Question q) {
        data.add(q);
        return q;
    }

    @Override
    public Question remove(Question q) {
        if (!data.remove(q)) {
            throw new NoSuchElementException("Question not found");
        }
        return q;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(data);
    }
}
