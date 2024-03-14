package skypro.courseworkc2.repository;

import org.junit.jupiter.api.Test;
import skypro.courseworkc2.model.Question;

import static org.assertj.core.api.Assertions.assertThat;

class JavaQuestionRepositoryTest {
    JavaQuestionRepository repository = new JavaQuestionRepository();

    @Test
    void addTest() {
        repository.add(new Question("q1", "a1"));
        repository.add("q2", "a2");
        assertThat(repository.getAll()).containsExactlyInAnyOrder(
                new Question("q1", "a1"),
                new Question("q2", "a2"));
    }

    @Test
    void removeTest() {
        repository.add(new Question("q1", "a1"));
        repository.add("q2", "a2");
        repository.remove(new Question("q2", "a2"));

        assertThat(repository.remove(new Question("q2", "a2"))).isNull();
        assertThat(repository.getAll()).containsExactlyInAnyOrder(
                new Question("q1", "a1"));

    }
}