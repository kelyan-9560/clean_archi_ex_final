import domain.kernel.TaskRepository;
import domain.services.InputParser;
import domain.services.TaskService;
import infrastructure.InFileRepository;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("args.length : " + args.length);

        TaskRepository taskRepository = new InFileRepository();
        TaskService taskService = new TaskService(taskRepository);

        InputParser inputParser = new InputParser(taskService, List.of(args));

        try {
            /*
            inputParser.commandIsValid("agenda");
            inputParser.instructionIsValid("update");
            inputParser.argsIsValid("c");

             */

            inputParser.instructionValidation("remove");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
