package org.skypro.exam_app.repository;

import jakarta.annotation.PostConstruct;
import org.skypro.exam_app.domain.Question;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Repository("mathQuestionRepository")
public class MathQuestionRepository implements QuestionRepository {

    private final Set<Question> data = new LinkedHashSet<>();

    @PostConstruct
    void init() {
        data.add(new Question("2 + 2", "4"));
        data.add(new Question("5 * 7", "35"));
        data.add(new Question("12 - 5", "7"));
        data.add(new Question("9 / 3", "3"));
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
