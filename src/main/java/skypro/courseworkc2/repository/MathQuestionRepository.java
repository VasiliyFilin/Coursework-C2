package skypro.courseworkc2.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import skypro.courseworkc2.model.Question;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository("mathRepository")
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> mathQA = new HashSet<>();
    @PostConstruct
    private void init() {
        mathQA.add(new Question("mq1", "ma1"));
        mathQA.add(new Question("mq2", "ma2"));
        mathQA.add(new Question("mq3", "ma3"));
        mathQA.add(new Question("mq4", "ma4"));
        mathQA.add(new Question("mq5", "ma5"));
    }
    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        mathQA.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (mathQA.remove(question)) {
            return question;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(mathQA);
    }

}
