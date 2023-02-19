package domain.services;

import domain.exception.ParserException;
import domain.kernel.TaskRepository;
import domain.models.Task;
import domain.models.TaskId;
import domain.models.TaskState;
import infrastructure.InFileRepository;
import org.junit.jupiter.api.Test;
import utils.DateUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {
    TaskRepository taskRepository = new InFileRepository();
    TaskService taskService = new TaskService(taskRepository);


    InputParserTest() throws IOException {
    }

    @Test
    void add_instruction() throws ParserException  {
        var args = List.of("agenda", "add", "-d:2022-04-01", "-c", "finalize the agenda exercise");
        InputParser inputParser = new InputParser(taskService, args);

        var tempArgs = args.subList(2, args.size());
        var newTask = inputParser.addInstruction(tempArgs);


        assertNull(newTask.getState());
        assertEquals(newTask.getDescription(), "finalize the agenda exercise");
        assertEquals(newTask.getDueDate(), DateUtils.stringToDate("2022-04-01"));
        assertNull(newTask.getSubtasks());
    }

    @Test
    void update_instruction() throws ParserException, IOException {
        var args = List.of("agenda", "update", "123", "-d:2023-04-01");
        InputParser inputParser = new InputParser(taskService, args);
        var tempArgs = args.subList(2, args.size());

        var task = Task.of(
                TaskId.of("123"),
                "",
                LocalDateTime.now(),
                DateUtils.stringToDate("2023-04-01"),
                null,
                TaskState.TODO,
                null);

        taskService.saveTask(task);

        var updatedTask = inputParser.updateInstruction(tempArgs);

        assertEquals(updatedTask.getDueDate(), DateUtils.stringToDate("2023-04-01"));
    }

    @Test
    void should_throw_when_command_invalid() {
        var args = List.of("pas ok", "add", "-c", "hello world");
        assertThrows(ParserException.class, () -> new InputParser(taskService, args));
    }

    @Test
    void should_throw_when_invalid_instruction() throws ParserException {
        var args = List.of("agenda", "pas ok", "-c", "hello world");
        InputParser inputParser = new InputParser(taskService, args);
        assertThrows(ParserException.class, () -> inputParser.instructionValidation(args.get(1)));
    }


    @Test
    void should_throw_parser_exception_when_invalid_options() throws ParserException {
        var args = List.of("agenda", "add", "-badOptions", "hello world");
        InputParser inputParser = new InputParser(taskService, args);
        assertThrows(ParserException.class, () -> inputParser.instructionValidation(args.get(1)));
    }


}