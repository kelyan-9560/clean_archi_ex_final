package domain.kernel;

import domain.models.Task;

import java.util.List;

public interface TaskRepository {
    void add(Task task);
    List<Task> list();
    void remove(String id);
    Task getTaskById(String id);
}
