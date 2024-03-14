package skypro.courseworkc2.service;

import skypro.courseworkc2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
