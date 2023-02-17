package domain.models;

import java.util.Objects;
import java.util.UUID;

public class TaskId {
    private final String id;


    public TaskId(String id) {
        this.id = id;
    }

    public static TaskId of(String id){
        return new TaskId(id);
    }

    public static TaskId fromUUID(UUID uuid) {
        return new TaskId(uuid.toString());
    }


    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId = (TaskId) o;
        return Objects.equals(id, taskId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
