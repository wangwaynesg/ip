public class TaskList {
    private Task[] list;
    private static int listLength = 0;

    public TaskList() {
        this.list = new Task[100];
    }

    public void addToList(String description) {
        this.list[TaskList.listLength++] = new Task(description);
        System.out.println("added: " + description);
    }

    public void printList() {
        if (TaskList.listLength == 0) {
            System.out.println("list is currently empty!");
        }
        else {
            int counter = 1;
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < TaskList.listLength ; i++) {
                System.out.println(counter++ + "." + this.list[i].getStatusIcon() + " " + this.list[i].getDescription());
            }
        }
    }
    public int getListLength() {
        return TaskList.listLength;
    }

    public void markAsDone(int index) {
        System.out.println("Nice! I've marked this task as done:");
        this.list[index].markAsDone();
        System.out.println(this.list[index].getStatusIcon() + " " + this.list[index].getDescription());
    }
}
