import domain.exception.ParserException;
import domain.kernel.TaskRepository;
import domain.services.InputParser;
import domain.services.TaskService;
import infrastructure.InFileRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParserException {
        System.out.println("args : " + Arrays.toString(args));

        TaskRepository taskRepository = new InFileRepository();
        TaskService taskService = new TaskService(taskRepository);

        InputParser inputParser = new InputParser(taskService, List.of(args));

        try {
            inputParser.instructionValidation(args[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
