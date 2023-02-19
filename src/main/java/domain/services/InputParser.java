package domain.services;

import domain.exception.ParserException;
import domain.models.Task;
import domain.models.TaskBuilder;
import domain.models.TaskState;
import utils.DateUtils;
import java.io.IOException;
import java.util.List;


public class InputParser {

    private final ParserValidator parserValidator = new ParserValidator();
    private final TaskService taskService;
    private final List<String> args;

    public InputParser(TaskService taskService, List<String> args) throws ParserException {
        this.taskService = taskService;
        parserValidator.commandHeaderIsValid(args.get(0));
        this.args = args;
    }

    public void instructionValidation(String instruction) throws ParserException, IOException {
        parserValidator.instructionIsValid(instruction);

        var tempArgs = args.subList(2, args.size());

        switch (instruction){
            case "add":
                var task = addInstruction(tempArgs);
                taskService.saveTask(task);
                break;
            case "list":
                System.out.println("list");
                var allTask = taskService.all();
                System.out.println(allTask);
                break;
            case "remove":
                System.out.println("remove");
                var taskId = tempArgs.get(0);
                System.out.println("taskId : " + taskId);
                taskService.removeTaskById(taskId);
                break;
            case "update":
                var taskUpdate = updateInstruction(tempArgs);
                taskService.update(tempArgs.get(0), taskUpdate);
                break;
            default:
                throw new ParserException("Invalid instruction");
        }
    }

    public Task addInstruction(List<String> args) throws ParserException {
        TaskBuilder taskBuilder = new TaskBuilder();

        for (int i = 0; i < args.size() - 1; i++) {
            String arg = args.get(i);

            if(arg.charAt(0) != '-') return null;

            var argumentName = arg.charAt(1);
            parserValidator.argumentIsValid(argumentName);

            if (argumentName == 'c'){
                String contentValue = args.get(i+1);
                taskBuilder.setDescription(contentValue);
            }

            if (argumentName == 'd'){
                var dueDateValue = arg.split(":")[1];
                taskBuilder.setDueDate(DateUtils.stringToDate(dueDateValue));
            }
        }
        return taskBuilder.build();
    }

    public Task updateInstruction(List<String> args) throws ParserException {
        var argsWithoutId = args.subList(1, args.size());

        TaskBuilder taskBuilder = new TaskBuilder();

        for (int i = 0; i < argsWithoutId.size(); i++) {
            String arg = argsWithoutId.get(i);

            if(arg.charAt(0) != '-') return null;

            var argumentName = arg.charAt(1);
            parserValidator.argumentIsValid(argumentName);

            if (argumentName == 'c'){
                String value = argsWithoutId.get(i+1);
                taskBuilder.setDescription(value);
            }

            if (argumentName == 'd'){
                var value = arg.split(":")[1];
                taskBuilder.setDueDate(DateUtils.stringToDate(value));
            }

            if(argumentName =='s'){
                var value = arg.split(":")[1];
                parserValidator.statusIsValid(value);
                taskBuilder.setState(TaskState.valueOf(value));
            }
        }
        return taskBuilder.build();
    }
}