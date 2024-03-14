package skypro.courseworkc2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import skypro.courseworkc2.exceptions.AmountOutOfBoundException;
import skypro.courseworkc2.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaService;
    private final QuestionService mathService;
    private final Random random = new Random();

    public ExaminerServiceImpl(@Qualifier("java") QuestionService javaService, @Qualifier("math") QuestionService mathService) {
        this.javaService = javaService;
        this.mathService = mathService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        ArrayList<Question> allQuestions = new ArrayList<>(javaService.getAll());
        allQuestions.addAll(mathService.getAll());
        if (amount > allQuestions.size()) {
            throw new AmountOutOfBoundException();
        }
        if (amount == allQuestions.size()) {
            return allQuestions;
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            Question question = random.nextBoolean() ? javaService.getRandomQuestion() : mathService.getRandomQuestion();
            questions.add(question);
        }
        return questions;
    }
}
