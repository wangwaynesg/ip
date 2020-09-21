package duke.task;

/**
 * <h1>Task</h1>
 * Represents a <code>Task</code> with its corresponding <code>taskDescription</code> and a <code>isDone</code> status
 * based on whether or not the task has been marked as done by the user.
 */
public class Task {
    private final String description;
    private boolean isDone;

    public static final String TICK = "[\u2713]";
    public static final String CROSS = "[\u2718]";

    public Task (String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Sets the <code>isDone</code> status of the Task object.
     * @param bool true or false.
     */
    public void setIsDone(Boolean bool) {
        this.isDone = bool;
    }

    /**
     * Returns the <code>isDone</code> status of the Task object.
     * @return <code>boolean</code> value of <code>isDone</code>.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns an icon for the corresponding <code>isDone</code> status of the Task object.
     * @return <code>String</code> for either TICK or CROSS.
     */
    public String getStatusIcon() {
        return (isDone ? TICK : CROSS);
    }

    /**
     * Returns the <code>taskDescription</code> of the TaskObject.
     * @return <code>String</code> of the <code>taskDescription</code>.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Overriding <code>toString()</code> method for this Task Object.
     * @return <code>String</code> containing the <code>isDone</code> status icon and <code>taskDescription</code>.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
