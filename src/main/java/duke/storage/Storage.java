package duke.storage;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with handling storage on the <code>TaskList</code>.\n
 * There is a load method to import in the saved data from the specified file path.
 * There is a write method to export the data to the specified file path.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Imports the <code>TaskList</code> data from specified file path.
     * @return <code>ArrayList</code> of <code>Task</code> containing imported <code>TaskList</code>.
     * @throws DukeException when file not found, or file format is wrong.
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        Scanner s = null;

        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException("File Not Found!!");
        }

        ArrayList<Task> tasks = new ArrayList<>();

        String taskType;
        String isDoneStatus;
        String taskDescription;
        LocalDate taskDate;
        String line;

        while (s.hasNext()) {
            line = s.nextLine();
            taskType = line.split(" \\| ")[0];
            isDoneStatus = line.split(" \\| ")[1];
            taskDescription = line.split(" \\| ")[2];

            switch (taskType) {
            case "T":
                tasks.add(new ToDo(taskDescription));
                break;
            case "D":
                // Might need to add try/catch for LocalDate.parse here
                taskDate = LocalDate.parse(line.split(" \\| ")[3]);
                tasks.add(new Deadline(taskDescription, taskDate));
                break;
            case "E":
                taskDate = LocalDate.parse(line.split(" \\| ")[3]);
                tasks.add(new Event(taskDescription, taskDate));
                break;
            default:
                throw new DukeException("File wrong format!");
            }
            if (isDoneStatus.equals("1")) {
                tasks.get(tasks.size() -1).setIsDone(true);
            }
        }
        return tasks;
    }

    /**
     * Exports the <code>TaskList</code> into a txt file at specified file path.
     * @param tasks the current <code>TaskList</code>.
     * @throws IOException when there is an error due to writing the txt file.
     */
    public void write(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i) instanceof ToDo) {
                fw.write("T"
                        + " | "
                        + (tasks.getTask(i).getIsDone() ? "1" : "0")
                        + " | "
                        + tasks.getTask(i).getDescription());
            } else if (tasks.getTask(i) instanceof Deadline) {
                fw.write("D"
                        + " | "
                        + (tasks.getTask(i).getIsDone() ? "1" : "0")
                        + " | "
                        + tasks.getTask(i).getDescription() + " | "
                        + ((Deadline) tasks.getTask(i)).getBy());
            } else if (tasks.getTask(i) instanceof Event) {
                fw.write("E"
                        + " | "
                        + (tasks.getTask(i).getIsDone() ? "1" : "0")
                        + " | "
                        + tasks.getTask(i).getDescription() + " | "
                        + ((Event) tasks.getTask(i)).getAt());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
