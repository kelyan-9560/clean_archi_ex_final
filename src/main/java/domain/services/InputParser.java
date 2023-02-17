package domain.services;

/*
agenda add -c "hello world"
agenda add -d:2022-03-01 -c "finalize the agenda exercise"
agenda list
agenda update 123 -d:2022-04-01
agenda remove 123
agenda update 123 -s:done
 */

import domain.exception.ParserException;
import domain.models.CommandInstructions;


public class InputParser {

    final String[] args;

    public InputParser(String[] args) {
        this.args = args;
    }


    public Boolean commandIsValid(String input) throws ParserException {
        if(input.equals("agenda")){
            return true;
        }else {
            throw ParserException.command();
        }
    }

    private Boolean instructionIsValid(String input){
        for(CommandInstructions commandInstructions : CommandInstructions.values()){
            if(commandInstructions.getInstruction().equals(input)){
                return true;
            }
        }
        return false;
    }
    
    
    
    public Boolean argsNumber(String instruction){
        switch (instruction){
            case "add":
                return true;
            case "list":
                return true;
            case "remove":
                return true;
            case "update":
                return true;
            default:
                return false;
        }
    }


    



}
