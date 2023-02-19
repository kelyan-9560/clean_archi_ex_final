package domain.services;

import domain.exception.ParserException;
import domain.models.CommandInstructions;
import domain.models.CommandOptions;
import domain.models.TaskState;

public class ParserValidator {

    public Boolean commandHeaderIsValid(String input) throws ParserException {
        if(input.equals("agenda")){
            return true;
        }else {
            throw ParserException.command();
        }
    }

    public void instructionIsValid(String input) throws ParserException{
        try{
            CommandInstructions.valueOf(input.toUpperCase());
        }catch (Exception e){
            throw ParserException.instruction(input);
        }
    }

    public void statusIsValid(String state) throws ParserException{
        try{
            TaskState.valueOf(state.toUpperCase());
        }catch (Exception e){
            throw ParserException.state(state);
        }
    }

    public void argumentIsValid(Character inputArg) throws ParserException {
        try{
            CommandOptions.valueOf(inputArg.toString().toUpperCase());
        }catch (Exception e){
            throw ParserException.option(inputArg.toString());
        }
    }
}
