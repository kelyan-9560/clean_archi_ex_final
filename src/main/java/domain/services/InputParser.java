package domain.services;


import domain.exception.ParserException;
import domain.models.Task;
import domain.models.TaskBuilder;
import utils.DateUtils;

import java.io.IOException;
import java.util.List;

/*
agenda add -c "hello world"
agenda add -d:2022-03-01 -c "finalize the agenda exercise"
agenda list
agenda update 123 -d:2022-04-01
agenda remove 123
agenda update 123 -s:done
*/

/*
    update id -c||-d||-s
    add  -c || -d
    remove id
    update id
    */

public class InputParser {

    private final ParserValidator parserValidator = new ParserValidator();
    private final TaskService taskService;
    private final List<String> args;

    public InputParser(TaskService taskService, List<String> args) {
        this.taskService = taskService;
        this.args = args;
    }

    public void instructionValidation(String instruction) throws ParserException, IOException {
        parserValidator.instructionIsValid(instruction);

        System.out.println("---- instructionValidation ----");
        System.out.println("args : " + args);
        var tempArgs = args.subList(2, args.size());
        System.out.println("tempsArgs : " + tempArgs);

        switch (instruction){
            case "add":
                var task = addInstruction(tempArgs);
                System.out.println(task.toString());
                taskService.saveTask(task);
                break;
            case "list":
                System.out.println("list");
                taskService.all();
                break;
            case "remove":
                System.out.println("remove");
                var taskId = tempArgs.get(0);
                System.out.println("taskId : " + taskId);
                //taskService.removeTaskById(taskId);
                break;
            case "update":
                updateInstruction(tempArgs);
                //TODO : Call TaskService to update task
                break;
            default:
                //TODO : throws
            break;
        }
        System.out.println("---- instructionValidation ----");
    }


    public Task addInstruction(List<String> args) throws ParserException {
        System.out.println("---- addInstructionIsValid ----");

        TaskBuilder taskBuilder = new TaskBuilder();

        for (int i = 0; i < args.size() - 1; i++) {
            String arg = args.get(i);

            if(arg.charAt(0) != '-') return null;

            var argumentName = arg.charAt(1);
            parserValidator.argumentIsValid(argumentName);

            if (argumentName == 'c'){
                String contentValue = args.get(i+1);
                System.out.println("-c value");
                System.out.println(contentValue);

                taskBuilder.setDescription(contentValue);
            }

            if (argumentName == 'd'){
                var dueDateValue = arg.split(":")[1];
                System.out.println("-d value");
                System.out.println(dueDateValue);

                taskBuilder.setDueDate(DateUtils.stringToDate(dueDateValue));
            }
        }
        System.out.println("---- addInstructionIsValid ----");
        return taskBuilder.build();
    }

    public void updateInstruction(List<String> args) throws ParserException {
        System.out.println("---- updateInstructionIsValid ----");
        var id = args.get(0);

        var argsWithoutId = args.subList(1, args.size());

        for (int i = 0; i < argsWithoutId.size(); i++) {
            String arg = argsWithoutId.get(i);

            if(arg.charAt(0) != '-') return;

            var argumentName = arg.charAt(1);
            parserValidator.argumentIsValid(argumentName);

            if (argumentName == 'c'){
                String value = argsWithoutId.get(i+1);
                System.out.println("-c value");
                System.out.println(value);
            }

            if (argumentName == 'd'){
                var value = arg.split(":")[1];
                System.out.println("-d value");
                System.out.println(value);
            }

            if(argumentName =='s'){
                var value = arg.split(":")[1];
                parserValidator.statusIsValid(value);
                System.out.println("-s value");
                System.out.println(value);
            }
        }
        System.out.println("---- updateInstructionIsValid ----");
    }

    public Boolean listInstruction(List<String> args){
        return false;
    }

    public Boolean removeInstruction(String id){
        return true;
    }

    private boolean isOdd(int number){
        return number % 2 == 0;
    }

}
