package domain.models;

public enum TaskState {
    TODO("todo"),
    PENDING("pending"),
    PROGRESS("progress"),
    DONE("done"),
    CANCELED("canceled"),
    CLOSED("closed");;

    private final String state;

    TaskState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
