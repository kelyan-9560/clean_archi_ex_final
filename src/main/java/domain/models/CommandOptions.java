package domain.models;

public enum CommandOptions {
    C("c"),
    D("d"),
    S("s");

    private String value;

    CommandOptions(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public Boolean contains(String input){
        for(CommandOptions commandArguments : CommandOptions.values()){
            if(commandArguments.getValue().equals(input)){
                return true;
            }
        }
        return false;
    }

}
