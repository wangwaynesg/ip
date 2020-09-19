package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static Scanner s = new Scanner(System.in);

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
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
        System.out.println("Saving current tasks...");
    }
}
