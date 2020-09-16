package duke.fileaccess;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;


public class FileAccess {
    public static ArrayList<Task> readFile(){
        File f = new File("data/duke.txt");
        Scanner s = null;

        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            return new ArrayList<Task>();
        }

        ArrayList<Task> taskList = new ArrayList<>();

        String command;
        String isDoneStatus;
        String taskDescription;
        String taskDate;
        String line;

        while (s.hasNext()) {
            line = s.nextLine();
            command = line.split(" \\| ")[0];
            isDoneStatus = line.split(" \\| ")[1];
            taskDescription = line.split(" \\| ")[2];

            switch (command) {
            case "T":
                taskList.add(new ToDo(taskDescription));
                break;
            case "D":
                taskDate = line.split(" \\| ")[3];
                taskList.add(new Deadline(taskDescription, taskDate));
                break;
            case "E":
                taskDate = line.split(" \\| ")[3];
                taskList.add(new Event(taskDescription, taskDate));
                break;
            // Add default statement
            }
            if (isDoneStatus.equals("1")) {
                taskList.get(taskList.size() -1).setIsDone(true);
            }
        }
        return taskList;
    }

    public static void writeFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task task : taskList) {
            if (task instanceof ToDo) {
                fw.write("T" + " | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription());
            } else if (task instanceof Deadline) {
                fw.write("D" + " | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + " | " + ((Deadline) task).getBy());
            } else if (task instanceof Event) {
                fw.write("D" + " | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + " | " + ((Event) task).getAt());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
