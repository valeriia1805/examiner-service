package org.skypro.exam_app.service;


import org.skypro.exam_app.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}