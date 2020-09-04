/**
 * Deadline is a subclass of Task
 * Deadlines are tasks that need to be done before a specific date/time
 *
 * @wangwaynesg (Wang Wayne)
 */

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
