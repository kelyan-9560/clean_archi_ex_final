package domain.models;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
    private final TaskId id;
    private final String description;
    private LocalDateTime dueDate;
    private LocalDateTime closeDate;
    private TaskState state;
    private final List<Task> subtasks;


    public Task(TaskId id, String description, LocalDateTime dueDate, LocalDateTime closeDate, TaskState state, List<Task> subtasks) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.state = state;
        this.subtasks = subtasks;
    }

    public static Task of(TaskId id, String description, LocalDateTime dueDate, LocalDateTime closeDate, TaskState state, List<Task> subtasks) {
        return new Task(id, description, dueDate, closeDate, state, subtasks);
    }

    public static Task WithContent(TaskId id, String description){
        return new Task(id, description, null, null, TaskState.TODO, null);
    }

    public static Task WithContentAndDueDate(TaskId id,String description, LocalDateTime dueDate){
        return new Task(id, description, dueDate, null, TaskState.TODO, null);
    }

    public void makeTaskDone(){
        this.state = TaskState.DONE;
        this.closeDate = LocalDateTime.now();

    }

    public void updateDueDateTask(LocalDateTime newDueDate){
        this.dueDate = newDueDate;
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
