/**
 * TaskList is class for instantiating objects which are lists of up to 100 tasks
 *
 * @wangwaynesg (Wang Wayne)
 */

public class TaskList {
    private Task[] list;
    private static int listLength = 0;

    public TaskList() {
        list = new Task[100];
    }

    public void addToList(Task task) {
        list[TaskList.listLength++] = task;
        // System.out.println("added: " + task.getDescription());
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.listLength + " tasks in the list.");
    }

    public void printList() {
        if (TaskList.listLength == 0) {
            System.out.println("list is currently empty!");
        } else {
            int counter = 1;
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < TaskList.listLength ; i++) {
                System.out.println(counter++ + "." + list[i].toString());
            }
        }
    }
    public int getListLength() {
        return TaskList.listLength;
    }

    public void markAsDone(int index) {
        System.out.println("Nice! I've marked this task as done:");
        list[index].markAsDone();
        System.out.println(list[index].getStatusIcon() + " " + list[index].getDescription());
    }
}
