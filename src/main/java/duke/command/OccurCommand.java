package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class OccurCommand extends Command {
    private LocalDate date;

    public OccurCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        ui.showOccurMessage(this.date);
        tasks.printOccur(this.date);
    }
}
