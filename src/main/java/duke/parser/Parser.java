package duke.parser;

import duke.Duke;
import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    private static String getCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    private static String getTaskDescription(String fullCommand) throws DukeException{
        String command = getCommand(fullCommand);

        if (fullCommand.split(command).length < 2
            || fullCommand.split(command).equals(" ")) {
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

    private static String getTaskDate(String fullCommand) {
        switch (getCommand(fullCommand)) {
        case COMMAND_DEADLINE:
            return fullCommand.split("/by ")[1];
        case COMMAND_EVENT:
            return fullCommand.split("/at ")[1];
        default:
            return null;
        }
    }

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

    public static String getTarget(String fullCommand) throws DukeException {
        String command = getCommand(fullCommand);

        if (fullCommand.split(command).length < 2
                || fullCommand.split(command).equals(" ")) {
            throw new DukeException("☹ OOPS!!! Missing target!");
        } else {
            return fullCommand.substring(fullCommand.indexOf(' ') + 1);
        }
    }

    public static Command parse(String fullCommand) throws DukeException {
        switch (getCommand(fullCommand)) {
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
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means!");
        }
    }
}
