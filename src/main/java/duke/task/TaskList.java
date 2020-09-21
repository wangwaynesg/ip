package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <h1>TaskList</h1>
 * Represents a list of tasks stored in an <code>ArrayList</code> of <code>Task</code>.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a <code>Task</code> into the <code>TaskList</code>.
     * @param task <code>Task</code> to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a <code>Task</code> from the <code>TaskList</code>.
     * @param index <code>index</code> of <code>Task</code> to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Marks a <code>Task</code> as done in the <code>TaskList</code>.
     * @param index of the <code>Task</code> to be marked as done.
     */
    public void markAsDone(int index) {
        this.tasks.get(index).setIsDone(true);
    }

    /**
     * Returns the size of the current <code>TaskList</code>.
     * @return <code>int</code> size.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a new <code>Task</code> at the specified <code>index</code>.
     * @param index of the <code>Task</code> to be returned.
     * @return <code>Task</code> at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Prints the <code>TaskList</code>.
     */
    public void print() {
        if (this.tasks.size() == 0) {
            System.out.println("Your task list is currently empty!");
        } else {
            int counter = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task task : this.tasks) {
                System.out.println(counter++ + "." + task);
            }
        }
    }

    /**
     * Finds and prints the <code>Task</code> containing the <code>target</code>.
     * @param target <code>String</code> to find in the <code>TaskList</code>.
     */
    public void findTarget(String target) {
        if (this.tasks.size() == 0) {
            System.out.println("Your task list is currently empty!");
        } else {
            int counter = 1;
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : this.tasks) {
                if (task.getDescription().contains(target)) {
                    System.out.println(counter++ + "." + task);
                }
            }
            if (counter == 1) {
                System.out.println("There were no matching tasks found!");
            }
        }
    }

    /**
     * Finds and prints the <code>Task(s)</code> occurring on the <code>LocalDate</code> date.
     * @param date <code>LocalDate</code> to find the occurring <code>Task(s)</code>.
     */
    public void printOccur(LocalDate date) {
        int counter = 1;
        for (Task task : this.tasks) {
            if (task instanceof Deadline) {
                if (((Deadline) task).getBy().equals(date)) {
                    System.out.println(counter++ + "." + task);
                }
            } else if (task instanceof Event) {
                if (((Event) task).getAt().equals(date)) {
                    System.out.println(counter++ + "." + task);
                }
            }
        }
        if (counter == 1) {
            System.out.println("No tasks occur on that date");
        }
    }
}
