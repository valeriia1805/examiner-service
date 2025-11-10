package org.skypro.exam_app.repository;


import org.skypro.exam_app.domain.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question q);
    Question remove(Question q);
    Collection<Question> getAll();
}
