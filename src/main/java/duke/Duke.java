package duke;

import duke.exception.DukeException;
import duke.task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static ArrayList<Task> taskList = null;

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreetingMessage() {
        printHorizontalLine();
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExitMessage() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static String getCommand(String line) {
        return line.split(" ")[0];
    }

    public static String getTaskDescription(String command, String line) throws DukeException {
        if (line.split(command).length < 2 || line.split(command)[1].equals(" ")) {
            throw new DukeException("☹ OOPS!!! Missing duke.task description!");
        } else {
            switch (command) {
            case COMMAND_TODO:
                return line.substring(line.indexOf(' ') + 1);
            case COMMAND_DEADLINE:
                if (!line.contains("/by ")) {
                    throw new DukeException("☹ OOPS!!! Missing or incorrect /by statement");
                }
                return line.substring(line.indexOf(' ') + 1, line.indexOf("/by ") - 1);
            case COMMAND_EVENT:
                if (!line.contains("/at ")) {
                    throw new DukeException("☹ OOPS!!! Missing or incorrect /at statement");
                }
                return line.substring(line.indexOf(' ') + 1, line.indexOf("/at ") - 1);
            default:
                return null;
            }
        }
    }

    public static String getTaskDate(String command, String line) {
        switch (command) {
        case COMMAND_DEADLINE:
            return line.split("/by ")[1];
        case COMMAND_EVENT:
            return line.split("/at ")[1];
        default:
            return null;
        }
    }

    public static int getCommandIndex(String command, String line) throws DukeException {
        if (line.split(command).length < 2 || line.split(command)[1].equals(" ")) {
            throw new DukeException("☹ OOPS!!! Missing index of duke.task!");
        }
        try {
            return Integer.parseInt(line.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! No integer index detected!");
        }
    }

    public static void addToTaskList(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this duke.task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printTaskList() {
        if (taskList.size() == 0) {
            System.out.println("list is currently empty!");
        } else {
            int counter = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task task : taskList) {
                System.out.println(counter++ + "." + task);
            }
        }
    }

    public static void markAsDone(int index) {
        System.out.println("Nice! I've marked this duke.task as done:");
        taskList.get(index).setIsDone(true);
        System.out.println(taskList.get(index).getStatusIcon() + " " + taskList.get(index).getDescription());
    }

    public static void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(index).getStatusIcon() + " " + taskList.get(index).getDescription());
        taskList.remove(index);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        printGreetingMessage();

        // Initialize new instance of a TaskList object
        taskList = new ArrayList<>();

        // Initialize Scanner and read in user input
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String command = getCommand(line);

        while (!command.equals(COMMAND_BYE)) {
            printHorizontalLine();
            try {
                switch (command) {
                case COMMAND_LIST:
                    printTaskList();
                    break;
                case COMMAND_DONE:
                    if (getCommandIndex(command, line) + 1 > taskList.size()) {
                        throw new DukeException("☹ OOPS!!! Index specified is out of list size!");
                    }
                    markAsDone(getCommandIndex(command, line));
                    break;
                case COMMAND_DELETE:
                    if (getCommandIndex(command, line) + 1 > taskList.size()) {
                        throw new DukeException("☹ OOPS!!! Index specified is out of list size!");
                    }
                    deleteTask(getCommandIndex(command, line));
                    break;
                case COMMAND_TODO:
                    addToTaskList(new ToDo(getTaskDescription(command, line)));
                    break;
                case COMMAND_DEADLINE:
                    addToTaskList(new Deadline(getTaskDescription(command, line), getTaskDate(command, line)));
                    break;
                case COMMAND_EVENT:
                    addToTaskList(new Event(getTaskDescription(command, line), getTaskDate(command, line)));
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means!");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }

            printHorizontalLine();

            // Read next line
            line = in.nextLine();
            command = getCommand(line);
        }

        // Exit Message
        printExitMessage();
    }
}
