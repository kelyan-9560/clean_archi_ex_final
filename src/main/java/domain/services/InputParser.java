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

import java.util.List;


public class InputParser {

    final List<String> args;

    public InputParser(List<String> args) {
        this.args = args;
    }

    //OK
    public Boolean commandIsValid(String input) throws ParserException {
        if(input.equals("agenda")){
            return true;
        }else {
            throw ParserException.command();
        }
    }

    //OK
    public Boolean instructionIsValid(String input) throws ParserException{
        try{
            CommandInstructions.valueOf(input.toUpperCase());
            return true;
        }catch (Exception e){
            throw ParserException.instructions();
        }
    }

    //OK
    public Boolean argumentIsValid(Character inputArg) throws ParserException {
        try{
            CommandArguments.valueOf(inputArg.toString().toUpperCase());
            return true;
        }catch (Exception e){
            throw ParserException.arg();
        }
    }

    public void instructionValidation(String instruction) throws ParserException {
        System.out.println("---- instructionValidation ----");
        System.out.println("args : " + args);
        var tempArgs = args.subList(2, args.size());
        System.out.println("tempsArgs : " + tempArgs);

        switch (instruction){
            case "add":
                addInstructionIsValid(tempArgs);
                break;
            case "list":
                listInstructionIsValid(tempArgs);
            case "remove":
                removeInstructionIsValid(tempArgs);
            break;
            case "update":
                updateInstructionIsValid(tempArgs);
            break;
            default:
                //todo throws
                //return "error";
            break;
        }
        System.out.println("---- instructionValidation ----");
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
    public void addInstructionIsValid(List<String> args) throws ParserException {
        System.out.println("---- addInstructionIsValid ----");

        for (int i = 0; i < args.size(); i++) {
            String arg = args.get(i);

            if(arg.charAt(0) != '-') return;

            var argumentName = arg.charAt(1);
            argumentIsValid(argumentName);

            if (argumentName == 'c'){
                String value = args.get(i+1);
                System.out.println("-c value");
                System.out.println(value);
            }

            if (argumentName == 'd'){
                var value = arg.split(":")[1];
                System.out.println("-d value");
                System.out.println(value);
            }
        }
        System.out.println("---- addInstructionIsValid ----");
    }
    
    public Boolean listInstructionIsValid(List<String> args){
        return false;
    }

    public Boolean updateInstructionIsValid(List<String> args){
        //if (isOdd(args.size())) return false;

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
