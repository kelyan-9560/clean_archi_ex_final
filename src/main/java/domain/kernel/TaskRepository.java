package domain.kernel;

import domain.models.Task;

import java.io.IOException;
import java.util.List;

public interface TaskRepository {
    void add(Task task) throws IOException;
    List<Task> list();
    void remove(String id) throws IOException;
    Task getTaskById(String id);
}
