package domain.models;

public enum CommandInstructions {
    ADD("addaze"),
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


    public boolean instructionsContains(String input){
        for(CommandInstructions commandInstructions : CommandInstructions.values()){
            if(commandInstructions.getInstruction().equals(input)){
                return true;
            }
        }
        return false;
    }

}
