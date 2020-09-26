package duke;

import duke.command.Command;
import duke.exception.DukeException;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Main class of the program.
 * The Duke program is an application which can store, display and check Tasks.
 * There are 3 types of tasks, ToDo, Deadline, Event.
 *
 * @author Wang Wayne
 * @since 2020-09-21
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String filePath = "duke.txt";

    /**
     * Constructor for new Duke Object
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Main method to run the program.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
                break;
            } finally{
                ui.showLine();
            }
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke(filePath);
        duke.run();
    }
}