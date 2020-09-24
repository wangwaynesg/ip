package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * User interface class used for reading user inputs and output messages.
 */
public class Ui {
    private static Scanner s = new Scanner(System.in);

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("Use command 'help' for a list of commands!");
        showLine();
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public String readCommand() {
        return s.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showAddTaskMessage(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showMarkAsDoneMessage(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTask(index).getStatusIcon() + " " + tasks.getTask(index).getDescription());
    }

    public void showDeleteMessage(TaskList tasks, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.getTask(index).getStatusIcon() + " " + tasks.getTask(index).getDescription());
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
    }

    public void showSavingMessage() {
        System.out.println("Attempting to save task list...");
    }

    public void showHelp() {
        System.out.println("Show help:\n\thelp");
        System.out.println("Print all tasks in the TaskList:\n\tlist");
        System.out.println("Create new Todo Task:\n\ttodo <taskDescription>");
        System.out.println("Create new deadline by specified date:\n\tdeadline <taskDescription> /by <YYYY-MM-DD>");
        System.out.println("Create new event at specified date:\n\tevent <taskDescription> /at <YYYY-MM-DD>");
        System.out.println("Mark task at specified index as done:\n\tdone <index>");
        System.out.println("Delete the task at specified index:\n\tdelete <index>");
        System.out.println("Find tasks that occur on specified date:\n\toccur <YYYY-MM-DD>");
        System.out.println("Exit the program and saves the current TaskList:\n\tbye");
    }

    public void showOccurMessage(LocalDate date) {
        System.out.println("Finding tasks that occur on " + date + ":");
    }
}
