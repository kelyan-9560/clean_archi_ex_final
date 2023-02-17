package domain.models;

public enum CommandInstructions {
    ADD("add"),
    LIST("list"),
    REMOVE("remove"),
    UPDATE("update");


    private final String instruction;


    CommandInstructions(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }

}
