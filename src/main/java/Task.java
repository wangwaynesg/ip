/**
 * Task is class for instantiating objects tasks
 * Tasks are used in TaskList
 *
 * @wangwaynesg (Wang Wayne)
 */

public class Task {
    private String description;
    private boolean isDone;

    public Task (String description) {
        this.description = description;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}