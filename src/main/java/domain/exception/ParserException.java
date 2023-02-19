package domain.exception;

public class ParserException extends Exception{


    public ParserException(String message) {
        super(message);
    }

    public static ParserException command(){
        return new ParserException("Command is not correct");
    }

    public static ParserException instruction(String instruction) {
        return new ParserException("Instruction <" + instruction + "> is not correct");
    }

    public static ParserException option(String option){
        return new ParserException("Option <" + option + "> is not correct");
    }
    public static ParserException state(String state){
        return new ParserException("State <" + state + "> is not correct");
    }

    public static ParserException optionFormat(){
        return new ParserException("Option format is not correct");
    }

}
