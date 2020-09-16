package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();

    public void addToList(Task task) {
        list.add(task);
        // System.out.println("added: " + duke.task.getDescription());
        System.out.println("Got it. I've added this duke.task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }

    public void printList() {
        if (this.list.size() == 0) {
            System.out.println("list is currently empty!");
        } else {
            int counter = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task task : this.list) {
                System.out.println(counter++ + "." + task);
            }
        }
    }

    public int getListLength() {
        return this.list.size();
    }

    public void markAsDone(int index) {
        System.out.println("Nice! I've marked this duke.task as done:");
        this.list.get(index).markAsDone();
        System.out.println(this.list.get(index).getStatusIcon() + " " + this.list.get(index).getDescription());
    }
}
