package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public void markAsDone(int index) {
        this.tasks.get(index).setIsDone(true);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

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
