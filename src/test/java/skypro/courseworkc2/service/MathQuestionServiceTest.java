package skypro.courseworkc2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.courseworkc2.exceptions.QuestionsNotFoundException;
import skypro.courseworkc2.model.Question;
import skypro.courseworkc2.repository.MathQuestionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    static List<Question> QUESTIONS = List.of(
            new Question("q1", "a1"),
            new Question("q2", "a2"),
            new Question("q3", "a3"),
            new Question("q4", "a4"));
    @Mock
    MathQuestionRepository repository;
    @InjectMocks
    MathQuestionService service;

    @BeforeEach
    void setUp() {
        when(repository.getAll()).thenReturn(QUESTIONS);
    }

    @Test
    void getRandomQuestionTest() {
        for (int i = 0; i < 100; i++) {
            assertTrue(QUESTIONS.contains((service.getRandomQuestion())));
        }
    }

    @Test
    void questionsNotFoundTest() {
        when(repository.getAll()).thenReturn(List.of());
        assertThrows(QuestionsNotFoundException.class, () -> service.getRandomQuestion());
    }
}