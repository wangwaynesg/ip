package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Event</h1>
 * Represents a <code>Event</code> Task which is a subclass of <code>Task</code>
 * with its corresponding event date stored as <code>at</code>.
 */
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
