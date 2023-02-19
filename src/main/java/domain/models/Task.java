package domain.models;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
    private  TaskId id;
    private  String description;
    private  LocalDateTime creationDate;
    private LocalDateTime dueDate;
    private LocalDateTime closeDate;
    private TaskState state;
    private  List<Task> subtasks;

    public Task(){}


    public Task(TaskId id, String description, LocalDateTime creationDate, LocalDateTime dueDate, LocalDateTime closeDate, TaskState state, List<Task> subtasks) {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.state = state;
        this.subtasks = subtasks;
    }


    public static Task of(TaskId id, String description, LocalDateTime creationDate, LocalDateTime dueDate, LocalDateTime closeDate, TaskState state, List<Task> subtasks) {
        return new Task(id, description, creationDate, dueDate, closeDate, state, subtasks);
    }

    public static Task WithContent(TaskId id, String description){
        return new Task(id, description, LocalDateTime.now(), null, null, TaskState.TODO, null);
    }

    public static Task WithContentAndDueDate(TaskId id,String description, LocalDateTime dueDate){
        return new Task(id, description, LocalDateTime.now(), dueDate, null, TaskState.TODO, null);
    }

    public void makeTaskDone(){
        this.state = TaskState.DONE;
        this.closeDate = LocalDateTime.now();

    }

    public String getDescription() {
        return description;
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

    public LocalDateTime getCreationDate(){return this.creationDate;}

    public TaskState getState() {
        return state;
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id + "\n"+
                ", description='" + description + '\'' + "\n"+
                ", createdDate=" + creationDate + "\n"+
                ", dueDate=" + dueDate + "\n"+
                ", closeDate=" + closeDate + "\n"+
                ", state=" + state + "\n"+
                ", subtasks=" + subtasks + "\n"+
                '}' + "\n";
    }
}
