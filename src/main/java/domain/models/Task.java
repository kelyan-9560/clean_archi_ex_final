package domain.models;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
    private final TaskId id;
    private final LocalDateTime dueDate;
    private final LocalDateTime closeDate;
    private final TaskState state;
    private final List<Task> subtasks;


    public Task(TaskId id, LocalDateTime dueDate, LocalDateTime closeDate, TaskState state, List<Task> subtasks) {
        this.id = id;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.state = state;
        this.subtasks = subtasks;
    }

    public static Task of(TaskId id, LocalDateTime dueDate, LocalDateTime closeDate, TaskState state, List<Task> subtasks) {
        return new Task(id, dueDate, closeDate, state, subtasks);
    }

    public TaskId getId() {
        return id;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public TaskState getState() {
        return state;
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }
}
