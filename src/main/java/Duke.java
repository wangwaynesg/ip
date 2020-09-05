/**
 * Individual Project Main class
 *
 * @wangwaynesg (Wang Wayne)
 */

import java.util.Scanner;

public class Duke {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreetingMessage() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
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
        if (line.split(command).length < 2 || line.split(command)[1] == " ") {
            throw new DukeException("☹ OOPS!!! Missing task description!");
        } else {
            switch (command) {
            case COMMAND_TODO:
                return line.substring(line.indexOf(' ') + 1);
            case COMMAND_DEADLINE:
            case COMMAND_EVENT:
                if (!line.contains("/by ") && command.equals(COMMAND_DEADLINE)) {
                    throw new DukeException("☹ OOPS!!! Missing or incorrect /by statement");
                }
                if (!line.contains("/at ") && command.equals(COMMAND_EVENT)) {
                    throw new DukeException("☹ OOPS!!! Missing or incorrect /at statement");
                }
                return line.substring(line.indexOf(' ') + 1, line.indexOf('/') - 1);
            default:
                return null;
            }
        }
    }

    public static String getTaskDate(String command, String line) throws DukeException {
        switch (command) {
        case COMMAND_DEADLINE:
            return line.split("/by ")[1];
        case COMMAND_EVENT:
            return line.split("/at ")[1];
        default:
            return null;
        }
    }

    public static int getDoneIndex(String command, String line) throws DukeException {
        if (line.split(command).length < 2 || line.split(command)[1] == " ") {
            throw new DukeException("☹ OOPS!!! Missing index of task!");
        }
        try {
            return Integer.parseInt(line.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! No integer index detected!");
        }
    }

    public static void main(String[] args) {
        printGreetingMessage();

        // Initialize new instance of a ToDoList object
        TaskList list = new TaskList();

        String line;
        String command;

        // Initialize Scanner and read in user input
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        // Get command, taskDescription and taskDate
        command = getCommand(line);

        while (!command.equals(COMMAND_BYE)) {
            printHorizontalLine();
            try {
                switch (command) {
                case COMMAND_LIST:
                    list.printList();
                    break;
                case COMMAND_DONE:
                    list.markAsDone(getDoneIndex(command, line));
                    break;
                case COMMAND_TODO:
                    list.addToList(new ToDo(getTaskDescription(command, line)));
                    break;
                case COMMAND_DEADLINE:
                    list.addToList(new Deadline(getTaskDescription(command, line), getTaskDate(command, line)));
                    break;
                case COMMAND_EVENT:
                    list.addToList(new Event(getTaskDescription(command, line), getTaskDate(command, line)));
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
