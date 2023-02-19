package infrastructure;

import domain.kernel.TaskRepository;
import domain.models.Task;
import domain.models.TaskId;
import domain.models.TaskState;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InFileRepositoryTest {

    TaskRepository repository = new InFileRepository();

    InFileRepositoryTest() throws IOException {
    }

    @Test
    void add() throws IOException {

        Task taskWithContent = createTaskWithContent();

        repository.add(taskWithContent);
        Task taskGet = repository.getTaskById(taskWithContent.getId().getId());
        assertEquals(taskWithContent.getId().getId(), taskGet.getId().getId());


        Task taskWithContentAndDueDate = createTaskWithContentAndDueDate();

        repository.add(taskWithContentAndDueDate);
        Task taskGet2 = repository.getTaskById(taskWithContentAndDueDate.getId().getId());
        assertEquals(taskWithContentAndDueDate.getId().getId(), taskGet2.getId().getId());

        Task task = createTask();

        repository.add(task);
        Task taskGet3 = repository.getTaskById(task.getId().getId());
        assertEquals(task.getId().getId(), taskGet3.getId().getId());
    }

    @Test
    void list() throws IOException {
        Task task1 = createTask();
        repository.add(task1);

        var list = repository.list();
        assertNotNull(list);
    }

    @Test
    void date() {
        String date = LocalDateTime.now().toString();
        System.out.println(date);
        assertEquals(1,1);
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
    void getTaskById() throws IOException {
        Task task = createTask();
        repository.add(task);
        Task taskGet = repository.getTaskById(task.getId().getId());
        assertEquals(task.getDueDate(), taskGet.getDueDate());
        assertEquals(task.getDescription(), taskGet.getDescription());
        assertEquals(task.getId().getId(), taskGet.getId().getId());
        assertEquals(task.getCreationDate(), taskGet.getCreationDate());

        Task task2 = createTaskWithContent();
        assertThrows(IllegalArgumentException.class, () -> repository.getTaskById(task2.getId().getId()));
    }

    Task createTask() {
        TaskId id = repository.nextId();
        return Task.of(id, "description", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), TaskState.TODO, null);
    }
    Task createTaskWithContent() {
        TaskId id = repository.nextId();
        return Task.WithContent(id, "description");
    }
    Task createTaskWithContentAndDueDate() {
        TaskId id = repository.nextId();
        return Task.WithContentAndDueDate(id, "description", LocalDateTime.now());
    }
}