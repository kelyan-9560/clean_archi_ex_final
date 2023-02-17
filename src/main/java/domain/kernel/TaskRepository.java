package domain.kernel;

import domain.models.Task;

import java.util.List;

public interface TaskRepository {
    void add(Task task);
    List<Task> list();
    void remove(String id);
    String updateDueDateTask(String id);
    String updateStatusDate(String id);
    Task getTaskById(String id);
}
