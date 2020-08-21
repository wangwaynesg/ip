public class Task {
    private String description;
    private boolean isDone;

    public Task (String description) {
        this.description = description;
        isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getDescription() {
        return this.description;
    }
}
