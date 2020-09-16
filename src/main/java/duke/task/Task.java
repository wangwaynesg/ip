package duke.task;

public class Task {
    private final String description;
    private boolean isDone;

    public static final String TICK = "[\u2713]";
    public static final String CROSS = "[\u2718]";

    public Task (String description) {
        this.description = description;
        isDone = false;
    }

    public void setIsDone(Boolean bool) {
        this.isDone = bool;
    }

    public String getStatusIcon() {
        return (isDone ? TICK : CROSS);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
