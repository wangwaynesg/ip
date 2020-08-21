import java.util.Scanner;

public class Duke {
    public static void printHorizontalLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        String line;
        Scanner in = new Scanner(System.in);

        do {
            line = in.nextLine();
            printHorizontalLine();
            System.out.println(line);
            printHorizontalLine();
        }
        while (!line.equals("bye"));


        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
