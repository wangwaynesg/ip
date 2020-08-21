import java.util.Scanner;

public class Duke {
    public static void printHorizontalLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        // Greeting Message
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        // Initialize new instance of a ToDoList object
        ToDoList list = new ToDoList();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            printHorizontalLine();
            if (line.equals("list")) {
                //printList
                list.printList();
            }
            else {
                //addToList
                list.addToList(line);
            }
            printHorizontalLine();
            // Read next line
            line = in.nextLine();
        }

        // Exit Message
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
