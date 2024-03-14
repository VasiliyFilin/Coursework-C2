package skypro.courseworkc2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import skypro.courseworkc2.exceptions.QuestionsNotFoundException;
import skypro.courseworkc2.model.Question;
import skypro.courseworkc2.repository.QuestionRepository;

import java.util.*;

@Service("java")
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository repository;
    private final Random random = new Random();

    public JavaQuestionService(@Qualifier("javaRepository") QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        repository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> questions = repository.getAll();
        if (questions.isEmpty()) {
            throw new QuestionsNotFoundException();
        }
        int nxt = random.nextInt(questions.size());
        int q = 0;
        for (Question question : questions) {
            if (nxt == q) {
                return question;
            }
            q++;
        }
        throw new QuestionsNotFoundException();
    }
}
