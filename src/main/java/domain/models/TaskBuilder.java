package domain.models;

import java.time.LocalDateTime;
import java.util.List;

public class TaskBuilder {

    private TaskId id;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime dueDate;
    private LocalDateTime closeDate;
    private TaskState state;
    private List<Task> subtasks;


    public TaskBuilder setId(TaskId id) {
        this.id = id;
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public TaskBuilder setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskBuilder setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
        return this;
    }

    public TaskBuilder setState(TaskState state) {
        this.state = state;
        return this;
    }

    public TaskBuilder setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
        return this;
    }


    public Task build() {
        return new Task(id, description, creationDate, dueDate, closeDate, state, subtasks);
    }

}
