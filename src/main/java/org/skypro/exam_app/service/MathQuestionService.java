package org.skypro.exam_app.service;

import org.skypro.exam_app.domain.Question;
import org.skypro.exam_app.exception.MethodNotAllowedForMathException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MathQuestionService implements QuestionService {

    private final Random random = new Random();

    @Override
    public Question add(Question q) {
        throw new MethodNotAllowedForMathException("add");
    }

    @Override
    public Question remove(Question q) {
        throw new MethodNotAllowedForMathException("remove");
    }

    @Override
    public Question find(String question, String answer) {
        throw new MethodNotAllowedForMathException("find");
    }

    @Override
    public Collection<Question> getAll() {
        throw new MethodNotAllowedForMathException("getAll");
    }

    @Override
    public Question getRandomQuestion() {
        int a = ThreadLocalRandom.current().nextInt(1, 101);
        int b = ThreadLocalRandom.current().nextInt(1, 101);
        char[] ops = new char[]{'+', '-', '*', '/'};
        char op = ops[random.nextInt(ops.length)];

        int res = switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> {
                b = Math.max(1, b);
                res = a - (a % b);
                a = res;
                yield a / b;
            }
        };
        return new Question(a + " " + op + " " + b, String.valueOf(res));
    }
}
