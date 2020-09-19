package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showSavingMessage();
        try {
            storage.write(tasks);
        } catch (IOException e) {
            throw new DukeException("Error while saving tasks");
        }
    }
}
