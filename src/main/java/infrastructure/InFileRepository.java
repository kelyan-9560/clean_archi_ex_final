package infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.dto.TaskDto;
import domain.kernel.TaskRepository;
import domain.models.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.TaskId;
import domain.services.MappingService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class InFileRepository implements TaskRepository {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<List<TaskDto>> mapType = new TypeReference<List<TaskDto>>() {};

    public InFileRepository() throws IOException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.init();
    }

    private static final String jsonFilePath = "data.json";
    private List<TaskDto> db = new ArrayList<TaskDto>();

    @Override
    public void add(Task task) throws IOException {
        TaskId id = nextId();
        var taskToCreate = Task.of(
                id, task.getDescription(), task.getCreationDate(), task.getDueDate(),
                task.getCloseDate(), task.getState(), task.getSubtasks()
        );

        this.db.add(MappingService.taskToDto(task));
        this.save();
    }

    @Override
    public List<Task> list() {
        return this.db.stream().map(MappingService::taskDtoToTask).collect(Collectors.toList());
    }

    @Override
    public void remove(String id) throws IOException {
        for(TaskDto task: this.db){
            if(task.id.equals(id)){
                this.db.remove(task);
                return;
            }
        }
        this.save();
    }
    @Override
    public Task getTaskById(String id) {
        for(TaskDto task: this.db){
            if(task.id.getId().equals(id)){
                return MappingService.taskDtoToTask(task);
            }
        }
        throw new IllegalArgumentException("the task with " + id + "does not exist");
    }

    @Override
    public TaskId nextId() {
        return TaskId.fromUUID(UUID.randomUUID());
    }

    public void save() throws IOException {
        String arrayToJson = objectMapper.writeValueAsString(this.db);
        this.saveFile(arrayToJson);
    }

    private void init() throws IOException {
        String data = String.valueOf(this.readFile());
        this.db = objectMapper.readValue(data, mapType);
    }

    private StringBuilder readFile() {
        StringBuilder data = new StringBuilder("");
        try {
            File myObj = new File(jsonFilePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    private void saveFile(String json){
        this.resetFile();
        try {
            FileWriter myWriter = new FileWriter(jsonFilePath,false);
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void resetFile(){
        File old_File = new File(jsonFilePath);
        old_File.delete();
        File New_File = new File(jsonFilePath);
        String Overwritten_Content = "[]";
        try {
            FileWriter Overwritten_File = new FileWriter(New_File);
            Overwritten_File.write(Overwritten_Content);
            Overwritten_File.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

