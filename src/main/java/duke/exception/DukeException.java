package duke.exception;

/**
 * DukeException class is used to throw exceptions in this program.
 */
public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
