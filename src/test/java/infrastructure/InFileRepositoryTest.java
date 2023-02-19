package infrastructure;

import domain.kernel.TaskRepository;
import domain.models.Task;
import domain.models.TaskId;
import domain.models.TaskState;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InFileRepositoryTest {

    TaskRepository repository = new InFileRepository();

    InFileRepositoryTest() throws IOException {
    }

    @Test
    void add() throws IOException {
        TaskId id1 = repository.nextId();
        Task taskWithContent = Task.WithContent(id1, "description1");

        repository.add(taskWithContent);
        Task taskGet = repository.getTaskById(id1.getId());
        assertEquals(taskWithContent, taskGet);

        TaskId id2 = repository.nextId();
        Task taskWithContentAndDueDate = Task.WithContentAndDueDate(id2, "description2", null);

        repository.add(taskWithContentAndDueDate);
        Task taskGet2 = repository.getTaskById(id2.getId());
        assertEquals(taskWithContentAndDueDate, taskGet2);

        TaskId id3 = repository.nextId();
        Task task = Task.of(id3, "description3", LocalDateTime.now(), LocalDateTime.now(), TaskState.TODO, null);

        repository.add(task);
        Task taskGet3 = repository.getTaskById(id3.getId());
        assertEquals(task, taskGet3);
    }

    @Test
    void list() {
        var list = repository.list();
        assertNotNull(list);
    }

    @Test
    void remove() throws IOException {
        Task task1 = createTask();

        repository.add(task1);
        Task toDelete = repository.getTaskById(task1.getId().getId());
        assertEquals(toDelete.getId().getId(), task1.getId().getId());
        repository.remove(toDelete.getId().getId());
        System.out.println(repository.list().size());
        assertThrows(IllegalArgumentException.class, () -> repository.getTaskById(task1.getId().getId()));
    }

    @Test
    void getTaskById() {
    }

    Task createTask() throws IOException {
        TaskId id = repository.nextId();
        return Task.of(id, "description", LocalDateTime.now(), LocalDateTime.now(), TaskState.TODO, null);
    }
    Task createTaskWithContent() throws IOException {
        TaskId id = repository.nextId();
        return Task.WithContent(id, "description");
    }
    Task createTaskWithContentAndDueDate() throws IOException {
        TaskId id = repository.nextId();
        return Task.WithContentAndDueDate(id, "description", LocalDateTime.now());
    }
}