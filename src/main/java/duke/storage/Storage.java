package duke.storage;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
        String taskDate;
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
                taskDate = line.split(" \\| ")[3];
                tasks.add(new Deadline(taskDescription, taskDate));
                break;
            case "E":
                taskDate = line.split(" \\| ")[3];
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