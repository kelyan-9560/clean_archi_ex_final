package domain.models;

public enum CommandArguments {
    C("c"),
    D("d"),
    S("s");

    private String value;

    CommandArguments(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public Boolean contains(String input){
        for(CommandArguments commandArguments : CommandArguments.values()){
            if(commandArguments.getValue().equals(input)){
                return true;
            }
        }
        return false;
    }

}
