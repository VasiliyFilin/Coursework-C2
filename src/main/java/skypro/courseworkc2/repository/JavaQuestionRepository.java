package skypro.courseworkc2.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import skypro.courseworkc2.model.Question;

import java.util.*;
@Repository("javaRepository")
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> javaQA = new HashSet<>();

    @PostConstruct
    private void init() {
        javaQA.add(new Question("jq1", "ja1"));
        javaQA.add(new Question("jq2", "ja2"));
        javaQA.add(new Question("jq3", "ja3"));
        javaQA.add(new Question("jq4", "ja4"));
        javaQA.add(new Question("jq5", "ja5"));
    }
    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        javaQA.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (javaQA.remove(question)) {
            return question;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(javaQA);
    }

}
