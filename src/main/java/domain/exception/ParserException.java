package domain.exception;

public class ParserException extends Exception{


    public ParserException(String message) {
        super(message);
    }

    public static ParserException command(){
        return new ParserException("Command is not correct");
    }

    public static ParserException instructions() {
        return new ParserException("Instructions is not correct");
    }

    public static ParserException arg(){
        return new ParserException("Arg is not correct");
    }

}
