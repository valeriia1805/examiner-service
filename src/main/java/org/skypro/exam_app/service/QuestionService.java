package org.skypro.exam_app.service;

import org.skypro.exam_app.domain.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(Question q);
    Question remove(Question q);
    /** Поиск точного совпадения вопрос/ответ. */
    Question find(String question, String answer);
    Collection<Question> getAll();
    /** Случайный вопрос из хранилища. */
    Question getRandomQuestion();
}
