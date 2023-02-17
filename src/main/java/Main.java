import domain.models.CommandInstructions;
import domain.services.InputParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("args.length : " + args.length);

        CommandInstructions commandInstructions = CommandInstructions.valueOf("add".toUpperCase());
        System.out.println(commandInstructions.getInstruction());

        InputParser inputParser = new InputParser(List.of(args));
    }
}