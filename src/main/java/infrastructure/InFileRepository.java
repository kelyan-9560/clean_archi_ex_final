package infrastructure;

import domain.kernel.TaskRepository;
import domain.models.Task;

import java.util.ArrayList;
import java.util.List;

class InFileRepository implements TaskRepository {

    private static final String jsonFilePath = "data.json";
    private List<Task> db = new ArrayList<Task>();

    @Override
    public void add(Task task) {
        this.db.add(task);
    }

    @Override
    public List<Task> list() {
        return this.db;
    }

    @Override
    public void remove(String id) {
        for(Task task: this.db){
            if(task.getId().getId().equals(id)){
                this.db.remove(task);
                return;
            }
        }
    }
    @Override
    public Task getTaskById(String id) {
        for(Task task: this.db){
            if(task.getId().getId().equals(id)){
                return task;
            }
        }
        throw new IllegalArgumentException("the task with " + id + "does not exist");
    }
}