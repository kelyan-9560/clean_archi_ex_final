package domain.services;

import domain.dto.TaskDto;
import domain.models.Task;

import java.time.LocalDateTime;

public class MappingService {
    public static TaskDto taskToDto(Task task){
        return new TaskDto(task.getId(), task.getDescription(),
                task.getCreationDate() == null ? null : task.getCreationDate().toString(),
                task.getDueDate() == null ? null :task.getDueDate().toString(),
                task.getCloseDate() == null ? null : task.getCloseDate().toString(),
                task.getState() == null ? null : task.getState() ,task.getSubtasks()== null ? null :task.getSubtasks());
    }


    public static Task taskDtoToTask(TaskDto task){
        return new Task(
                task.id, task.description,
                task.creationDate == null ? null :LocalDateTime.parse(task.creationDate),
                task.dueDate == null ? null :LocalDateTime.parse(task.dueDate),
                task.closeDate == null ? null :LocalDateTime.parse(task.closeDate),
                task.state == null ? null :task.state,
                task.subtasks == null ? null :task.subtasks
        );
    }
}
