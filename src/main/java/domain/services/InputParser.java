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
import domain.models.CommandArguments;
import domain.models.CommandInstructions;

import java.io.IOException;
import java.util.List;


public class InputParser {

    final List<String> args;

    public InputParser(List<String> args) {
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
        try{
            CommandInstructions.valueOf(input.toUpperCase());
            return true;
        }catch (Exception e){
            ParserException.instructions();
            return false;
        }
    }

    private Boolean argsIsValid(String input){
        try{
            CommandInstructions.valueOf(input.toUpperCase());
            return true;
        }catch (Exception e){
            ParserException.arg();
            return false;
        }
    }

    public void instructionValidation(String instruction){
        args.remove(0);
        args.remove(1);

        switch (instruction){
            case "add":
                addInstructionIsValid(args);
                break;
            case "list":
                listInstructionIsValid(args);
            case "remove":
                removeInstructionIsValid(args);
            break;
            case "update":
                updateInstructionIsValid(args);
            break;
            default:
                //todo throws
                //return "error";
            break;
        }

    }

    /*
    update
        ca doit etre un id apres
            et apres on  a soit -c / -d / -s
    add
        -c || -d

    remove


    agenda update id
    * */

//agenda add -c "hello world"
//agenda add -d:2022-03-01 -c "finalize the agenda exercise"

    public Boolean addInstructionIsValid(List<String> args){
        if (isOdd(args.size())) return false;

        String arg = args.get(0);
        if(arg.charAt(0) != '-') return false;
        if(arg.charAt(1) !='c') return false;



        return true;
    }

    public Boolean listInstructionIsValid(List<String> args){
        return false;
    }

    public Boolean updateInstructionIsValid(List<String> args){
        if (isOdd(args.size())) return false;

        for (String arg : args) {
            CommandArguments commandArguments = CommandArguments.valueOf(arg.toUpperCase());

            if (arg.equals("-c") || arg.equals("-d")){
                return true;
            }
        }
        return false;
    }

    public Boolean removeInstructionIsValid(List<String> args){
        //TODO
        return false;
    }

    private boolean isOdd(int number){
        return number % 2 == 0;
    }

}
