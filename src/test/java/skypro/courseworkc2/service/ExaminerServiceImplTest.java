package skypro.courseworkc2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.courseworkc2.exceptions.AmountOutOfBoundException;
import skypro.courseworkc2.model.Question;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    static List<Question> JAVA_QUESTIONS = List.of(
            new Question("jq1", "ja1"),
            new Question("jq2", "ja2"),
            new Question("jq3", "ja3"),
            new Question("jq4", "ja4"));
    static List<Question> MATH_QUESTIONS = List.of(
            new Question("mq1", "ma1"),
            new Question("mq2", "ma2"),
            new Question("mq3", "ma3"),
            new Question("mq4", "ma4"));

    @BeforeEach
    void setUp() {
        examinerService = new ExaminerServiceImpl(jService, mService);
        when(jService.getAll()).thenReturn(JAVA_QUESTIONS);
        when(mService.getAll()).thenReturn(MATH_QUESTIONS);
    }

    @Mock
    JavaQuestionService jService;
    @Mock
    MathQuestionService mService;

    ExaminerServiceImpl examinerService;

    @Test
    void randomQuestionsTest() {
        when(jService.getRandomQuestion()).thenReturn(JAVA_QUESTIONS.get(0));
        when(mService.getRandomQuestion())
                .thenReturn(MATH_QUESTIONS.get(0))
                .thenReturn(MATH_QUESTIONS.get(1));
        Collection<Question> act = examinerService.getQuestions(3);
        assertThat(act).containsExactlyInAnyOrder(
                JAVA_QUESTIONS.get(0),
                MATH_QUESTIONS.get(0),
                MATH_QUESTIONS.get(1));
    }

    @Test
    void amountOutOfBoundExceptionTest() {

        assertThrows(AmountOutOfBoundException.class, () -> examinerService.getQuestions(10));
    }
}