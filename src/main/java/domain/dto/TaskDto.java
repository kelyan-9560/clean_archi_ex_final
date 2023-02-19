package domain.dto;

import domain.models.Task;
import domain.models.TaskId;
import domain.models.TaskState;

import java.util.List;

public class TaskDto {
    public TaskId id;
    public String description;
    public String creationDate;
    public String dueDate;
    public String closeDate;
    public TaskState state;
    public List<Task> subtasks;

    public TaskDto(){}
    public TaskDto(TaskId id, String description, String creationDate, String dueDate, String closeDate, TaskState state, List<Task> subtasks) {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.state = state;
        this.subtasks = subtasks;
    }
}
