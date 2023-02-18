package domain.services;

import domain.kernel.TaskRepository;
import domain.models.Task;
import domain.models.TaskId;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void createTaskWithDescription(TaskId id, String description) throws IOException {
        this.repository.add(Task.WithContent(id, description));
    }

    public void createTaskWithDescriptionAndDueDate(TaskId id, String description, LocalDateTime dueDate) throws IOException {
        this.repository.add(Task.WithContentAndDueDate(id, description, dueDate));
    }
    public void saveTask(Task task) throws IOException {
        this.repository.add(task);
    }
    public List<Task> all(){
        return this.repository.list();
    }
    public void removeTaskById(String id) throws IOException {
        this.repository.remove(id);
    }

    public Task getTaskById(String id){
        return this.repository.getTaskById(id);
    }

    public void updateDueDateTask(String id,LocalDateTime newDueDate) throws IOException {
        Task task = this.repository.getTaskById(id);
        this.repository.remove(id);
        task.updateDueDateTask(newDueDate);
        this.repository.add(task);
    }

    public void makeTaskDone(String id) throws IOException {
        Task task = this.repository.getTaskById(id);
        this.repository.remove(id);
        task.makeTaskDone();
        this.repository.add(task);

    }

}
