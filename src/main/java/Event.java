/**
 * Event is a subclass of Task
 * Events are tasks that start at a specific time and ends at a specific time
 *
 * @wangwaynesg (Wang Wayne)
 */

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
