public class ToDoList {
    private String[] list;
    private static int listLength = 0;

    public ToDoList () {
        this.list = new String[100];
    }

    public void addToList(String str) {
        this.list[ToDoList.listLength++] = str;
        System.out.println("added: " + str);
    }

    public void printList() {
        if (ToDoList.listLength == 0) {
            System.out.println("list is currently empty!");
        }
        else {
            int counter = 1;
            for (int i = 0; i < ToDoList.listLength; i++) {
                System.out.println(counter++ + ". " + list[i]);
            }
        }
    }

    public int getListLength() {
        return ToDoList.listLength;
    }
}
