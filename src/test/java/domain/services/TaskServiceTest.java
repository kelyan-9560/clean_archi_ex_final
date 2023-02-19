package domain.services;

import domain.kernel.TaskRepository;

import domain.models.Task;
import domain.models.TaskId;
import domain.models.TaskState;
import infrastructure.InFileRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    TaskRepository repository = new InFileRepository();
    TaskService taskService = new TaskService(repository);

    TaskServiceTest() throws IOException {
    }

    @Test
    void createTaskWithDescription() throws IOException {
        var id = createId();
        taskService.createTaskWithDescription(id, "description");
        Task task = taskService.getTaskById(id.getId());

        assertEquals(task.getDescription(), "description");
        assertEquals(task.getId().getId(), id.getId());
    }

    @Test
    void createTaskWithDescriptionAndDueDate() throws IOException {
        var id = createId();
        var description = "description";
        var date = LocalDateTime.now();
        taskService.createTaskWithDescriptionAndDueDate(id, description, date);
        Task task = taskService.getTaskById(id.getId());

        assertEquals(task.getDescription(), description);
        assertEquals(task.getId().getId(), id.getId());
        assertEquals(task.getDueDate(), date);
    }

    @Test
    void saveTask() throws IOException {
        var id = createId();
        var description = "description";
        var date = LocalDateTime.now();
        var task = new Task(id, description,date, date, date, TaskState.TODO, null);
        taskService.saveTask(task);
        Task createdTask = taskService.getTaskById(id.getId());

        assertEquals(createdTask.getDescription(), description);
        assertEquals(createdTask.getId().getId(), id.getId());
        assertEquals(createdTask.getDueDate(), date);
    }

    @Test
    void all() throws IOException {
        var id = createId();
        var description = "description";
        var date = LocalDateTime.now();
        var task = new Task(id, description,date, date, date, TaskState.TODO, null);
        taskService.saveTask(task);

        var list = taskService.all();
        assertNotNull(list);
    }

    @Test
    void removeTaskById() throws IOException {
        var id = createId();
        var description = "description";
        var date = LocalDateTime.now();
        var task = new Task(id, description,date, date, date, TaskState.TODO, null);
        taskService.saveTask(task);
        taskService.removeTaskById(id.getId());
        assertThrows(IllegalArgumentException.class, () -> taskService.getTaskById(id.getId()));
    }

    @Test
    void getTaskById() throws IOException {
        var id = createId();
        var description = "description";
        var date = LocalDateTime.now();
        var task = new Task(id, description,date, date, date, TaskState.TODO, null);
        taskService.saveTask(task);
        var taskGet = taskService.getTaskById(id.getId());
        assertNotNull(taskGet);
    }

    @Test
    void updateDueDateTask() throws IOException {
        var id = createId();
        var description = "description";
        var date = LocalDateTime.now();
        var task = new Task(id, description,date, date, date, TaskState.TODO, null);
        taskService.saveTask(task);

        var date2 = LocalDateTime.now();
        taskService.updateDueDateTask(id.getId(), date2);
        var updatedTask = taskService.getTaskById(id.getId());
        assertEquals(updatedTask.getDueDate(), date2);
    }

    @Test
    void makeTaskDone() throws IOException {
        var id = createId();
        var description = "description";
        var date = LocalDateTime.now();
        var task = new Task(id, description,date, date, date, TaskState.TODO, null);
        taskService.saveTask(task);

        taskService.makeTaskDone(id.getId());

        var updatedTask = taskService.getTaskById(id.getId());
        assertEquals(updatedTask.getState(), TaskState.DONE);
    }


    @Test
    void makeTaskInProgress() throws IOException {
        var id = createId();
        var description = "description";
        var date = LocalDateTime.now();
        var task = new Task(id, description,date, date, date, TaskState.TODO, null);
        taskService.saveTask(task);


        var taskInProgress = Task.of(id, description, date, date, date, TaskState.PROGRESS, null);
        taskService.update(id.getId(), taskInProgress);

        var updatedTask = taskService.getTaskById(id.getId());
        assertEquals(updatedTask.getState(), TaskState.PROGRESS);
    }


    TaskId createId(){
        return repository.nextId();
    }
}