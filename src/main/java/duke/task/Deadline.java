package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Deadline</h1>
 * Represents a <code>Deadline</code> Task which is a subclass of <code>Task</code>
 * with its corresponding deadline date stored as <code>by</code>.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
