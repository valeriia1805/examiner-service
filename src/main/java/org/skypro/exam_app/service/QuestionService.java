package org.skypro.exam_app.service;

import org.skypro.exam_app.domain.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(Question q);
    Question remove(Question q);
    Question find(String question, String answer);
    Collection<Question> getAll();
    Question getRandomQuestion();
}