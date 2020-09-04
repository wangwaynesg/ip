/**
 * ToDo is a subclass of Task
 * ToDo are tasks without any date/time attached to it
 *
 * @wangwaynesg (Wang Wayne)
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
