import java.util.Scanner;

public class Duke {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";

    public static void printHorizontalLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static String getCommand(String line) {
        return line.split(" ")[0];
    }

    public static String getTaskDescription(String command, String line) {
        switch (command) {
        case COMMAND_TODO:
            return line.substring(line.indexOf(' '));
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            return line.substring(line.indexOf(' ') + 1, line.indexOf('/') - 1);
        default:
            return null;
        }
    }

    public static String getTaskDate(String command, String line) {
        switch (command) {
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            return line.substring(line.indexOf("/") + 4);
        default:
            return null;
        }
    }

    public static void main(String[] args) {
        // Greeting Message
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        // Initialize new instance of a ToDoList object
        TaskList list = new TaskList();

        String line = null;
        String command = null;
        String taskDescription = null;
        String taskDate = null;

        // Initialize Scanner and read in user input
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        // Get command, taskDescription and taskDate
        command = getCommand(line);
        taskDescription = getTaskDescription(command, line);
        taskDate = getTaskDate(command, line);


        while (!command.equals(COMMAND_BYE)) {
            printHorizontalLine();
            if (command.equals(COMMAND_LIST)) {
                list.printList();
            } else if (command.equals(COMMAND_DONE)) {
                String index = line.split(" ")[1];
                list.markAsDone(Integer.parseInt(index) - 1);
            } else {
                if (command.equals(COMMAND_TODO)) {
                    // add ToDo
                    list.addToList(new ToDo(line.substring(line.indexOf(' ') + 1)));
                } else if (command.equals(COMMAND_DEADLINE)) {
                    // add Deadline
                    list.addToList(new Deadline(taskDescription, taskDate));
                } else if (command.equals(COMMAND_EVENT)) {
                    // add event
                    list.addToList(new Event(taskDescription, taskDate));
                }
            }
            printHorizontalLine();
            // Read next line
            line = in.nextLine();
            command = getCommand(line);
            taskDescription = getTaskDescription(command, line);
            taskDate = getTaskDate(command, line);
        }

        // Exit Message
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}