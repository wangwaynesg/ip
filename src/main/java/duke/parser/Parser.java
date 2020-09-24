package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;

public class Parser {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_OCCUR = "occur";

    /**
     * Returns the <code>COMMAND</code> of a user input.
     * @param fullCommand user input.
     * @return First part of the user input.
     */
    private static String getCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    /**
     * Returns the <code>taskDescription</code> of a <code>Task</code> from the user input.
     * @param fullCommand user input.
     * @return <code>taskDescription</code> part of the user input.
     * @throws DukeException when the user input is of a wrong format.
     */
    private static String getTaskDescription(String fullCommand) throws DukeException {
        String command = getCommand(fullCommand);

        if (fullCommand.split(command).length < 2
            || fullCommand.split(command)[1].equals(" ")) {
            throw new DukeException("☹ OOPS!!! Missing duke.task description!");
        } else {
            switch (command) {
            case COMMAND_TODO:
                return fullCommand.substring(fullCommand.indexOf(' ') + 1);
            case COMMAND_DEADLINE:
                if (!fullCommand.contains("/by ")) {
                    throw new DukeException("☹ OOPS!!! Missing or incorrect /by statement");
                }
                return fullCommand.substring(fullCommand.indexOf(' ') + 1, fullCommand.indexOf("/by ") - 1);
            case COMMAND_EVENT:
                if (!fullCommand.contains("/at ")) {
                    throw new DukeException("☹ OOPS!!! Missing or incorrect /at statement");
                }
                return fullCommand.substring(fullCommand.indexOf(' ') + 1, fullCommand.indexOf("/at ") - 1);
            default:
                return null;
            }
        }
    }

    /**
     * Returns the <code>taskDate</code> of a <code>Task</code> from the user input.
     * @param fullCommand user input.
     * @return <code>taskDate</code> part of the user input.
     * @throws DukeException when the user input is of a wrong format.
     */
    private static LocalDate getTaskDate(String fullCommand) throws DukeException {
        String taskDateString;
        switch (getCommand(fullCommand)) {
        case COMMAND_DEADLINE:
            taskDateString = fullCommand.split("/by ")[1];
            try {
                return LocalDate.parse(taskDateString);
            } catch (Exception e) {
                throw new DukeException("Date specified is of wrong format! Use YYYY-MM-DD format!");
            }
        case COMMAND_EVENT:
            taskDateString = fullCommand.split("/at ")[1];
            try {
                return LocalDate.parse(taskDateString);
            } catch (Exception e) {
                throw new DukeException("Date specified is of wrong format! Use YYYY-MM-DD format!");
            }
        case COMMAND_OCCUR:
            taskDateString = fullCommand.split(" ")[1];
            try {
                return LocalDate.parse(taskDateString);
            } catch (Exception e) {
                throw new DukeException("Date specified is of wrong format! Use YYYY-MM-DD format!");
            }
        default:
            return null;
        }
    }

    /**
     * Returns the <code>commandIndex</code> of a user input, e.g. done 2 or delete 3.
     * Returns 2 or 3 respectively.
     * @param fullCommand user input.
     * @return <code>commandIndex</code> part of the user input.
     * @throws DukeException when the user input is of a wrong format.
     */
    private static int getCommandIndex(String fullCommand) throws DukeException {
        String command = getCommand(fullCommand);

        if (fullCommand.split(command).length < 2 || fullCommand.split(command)[1].equals(" ")) {
            throw new DukeException("☹ OOPS!!! Missing index of duke.task!");
        }
        try {
            return Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! No integer index detected!");
        }
    }

    /**
     * Returns the <code>targetString</code> when the find command is input.
     * @param fullCommand user input.
     * @return <code>String</code> of the targetString to be found.
     * @throws DukeException when the user input is of a wrong format.
     */
    public static String getTarget(String fullCommand) throws DukeException {
        String command = getCommand(fullCommand);

        if (fullCommand.split(command).length < 2
                || fullCommand.split(command)[1].equals(" ")) {
            throw new DukeException("☹ OOPS!!! Missing target!");
        } else {
            return fullCommand.substring(fullCommand.indexOf(' ') + 1);
        }
    }

    /**
     * Makes sense of the user input and returns the command object accordingly to the command given.
     * @param fullCommand user input.
     * @return <code>Command</code> of sub-type depending on the command given.
     * @throws DukeException when the program does not recognize the command given.
     */
    public static Command parse(String fullCommand) throws DukeException {
        switch (getCommand(fullCommand)) {
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_BYE:
            return new ExitCommand();
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_TODO:
            return new AddCommand(new ToDo(getTaskDescription(fullCommand)));
        case COMMAND_DEADLINE:
            return new AddCommand(new Deadline(getTaskDescription(fullCommand), getTaskDate(fullCommand)));
        case COMMAND_EVENT:
            return new AddCommand(new Event(getTaskDescription(fullCommand), getTaskDate(fullCommand)));
        case COMMAND_DONE:
            return new DoneCommand(getCommandIndex(fullCommand));
        case COMMAND_DELETE:
            return new DeleteCommand(getCommandIndex(fullCommand));
        case COMMAND_FIND:
            return new FindCommand(getTarget(fullCommand));
        case COMMAND_OCCUR:
            return new OccurCommand(getTaskDate(fullCommand));
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means!");
        }
    }
}
