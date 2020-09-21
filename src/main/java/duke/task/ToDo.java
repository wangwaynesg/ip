package duke.task;

/**
 * <h1>Todo</h1>
 * Represent a <code>ToDo</code> Task which is a subclass of <code>Task</code>.
 */
public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
