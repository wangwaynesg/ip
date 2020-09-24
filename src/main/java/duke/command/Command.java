package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents the command parent class for all commands.
 * All commands must have a execute method that takes in objects <code>TaskList</code>,
 * <code>Ui</code> and <code>Storage</code>.
 *
 * All the respective commands are self-explanatory in terms of functionality.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    public boolean isExit() {
        return (this instanceof ExitCommand);
    }
}
