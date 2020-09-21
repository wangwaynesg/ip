# User Guide

This program is not meant for end-users and therefore there is no user-friendly installer.   
Make sure that `JDK11` is installed before running the program.  
Refer to the next section to learn how to start the program.

## Starting the program

Using IntelliJ

1. Find the project in the `Project Explorer` (usually located at the left of the IntelliK interface)
1. Go to the `src` folder of the project and find the `Duke.java` file (ip/src/main/java/duke/duke.java)
1. Right click on the file and select `Run Duke.main()`
1. The program will run on the `console` (usually at the bottom)
1. Interact with the program through the `console`

Using Command Line
1. `Build` the project using IntelliJ
1. Open the `Terminal`/`Command Prompt`
1. `cd` into the project's `out/production/ip` directory
1. Type `java duke.Duke`, then `Enter` to execute
1. Now you can interact with the program through CLI

## Features 

### Viewing help : `help`

Format: `help`

### Listing all tasks : `list`

Shows a list of all the tasks, along with the task type, task description 
and date by or at if the task is a deadline or event respectively.  
Format: `list`

### Adding a ToDo task : `todo`

Adds a new ToDo task to the task list.  
Format: `todo <description>`

Examples:
* `todo read book`  
Adds a new Todo task with the description `read book`.
* `todo complete user guide`  
Adds a new Todo task with the description `complete user guide`

### Adding a Deadline task : `deadline`

Adds a new Deadline task to the task list.  
Format: `deadline <description> /by <date>`

Put a `/by ` to indicate that the following string is the `<date>` to complete the task by.   
`<date>` must be in the format of YYYY-MM-DD.

Examples:
* `deadline Complete ip project /by 2020-09-28`  
Adds a new task with the description `Complete ip project` and deadline `2020-09-28`.
* `deadline return book /by 2020-09-22`  
Adds a new task with the description `return book` and deadline `2020-09-22`.

### Adding an event task : `event`

Adds a new Event task to the task list.  
Format: `event <description> /at <date>`

Put a `/at ` to indicate that the following string is the `<date>` of the event.   
`<date>` must be in the format of YYYY-MM-DD.

Examples:
* `event project meeting /at 2020-09-25`  
Adds a new task with the description `project meeting` on `2020-09-25`.
* `event recess week /at 2020-09-21`  
Adds a new task with the description `recess week` on `2020-09-21`.

### Marking a task as done : `done`

Marks a task as done.  
Format: `done <index>`

Indicate the `<index>` of the task to mark as done.

Examples:
* `done 2`  
Marks the `second` item in the list as done.

### Deleting a task : `delete`

Deletes a task from the task list.  
Format: `delete <index>`

Indicate the `<index>` of the task to be deleted.

Examples:
* `delete 2`  
Deletes the `second` item in the task list.

### Finding a task : `find`

Find tasks from the task list containing a specific string.  
Format: `find <String>`

Indicate the `<String>` to be searched in the task list.

Examples:
* `find book`  
Searches the task list for any task description containing the word `book` and prints the task(s) out.

### Show tasks which occur on a date : `occur`

Find tasks from the task list which occur or due on a specific date.  
Format: `occur <date>`

Indicate the `<date>` in the format of YYYY-MM-DD.

Examples:
* `occur 2020-09-21`  
Searches the task list for any deadline or event which occurs or is due on `2020-09-21`.

### Exiting the program : `bye`

Exits the program and saves the task list to a local storage text file.  
Format: `bye`

## Storage

The task list is loaded and saved from the `data/duke.txt` file automatically when starting and exiting the program.