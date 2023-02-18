package infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.kernel.TaskRepository;
import domain.models.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InFileRepository implements TaskRepository {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<List<Task>> mapType = new TypeReference<List<Task>>() {};

    InFileRepository() throws IOException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.init();
    }

    private static final String jsonFilePath = "data.json";
    private List<Task> db = new ArrayList<Task>();

    @Override
    public void add(Task task) throws IOException {
        this.db.add(task);
        this.save();
    }

    @Override
    public List<Task> list() {
        return this.db;
    }

    @Override
    public void remove(String id) throws IOException {
        for(Task task: this.db){
            if(task.getId().getId().equals(id)){
                this.db.remove(task);
                return;
            }
        }
        this.save();
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
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    private void saveFile(String json){
        try {
            FileWriter myWriter = new FileWriter(jsonFilePath);
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}