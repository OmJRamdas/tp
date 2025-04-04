package parser;

import commands.Command;
import commands.HelpCommand;
import commands.instrument.ListCommand;
import commands.user.UserCommand;
import commands.instrument.AddInstrumentCommand;
import commands.instrument.DeleteCommand;
import commands.ExitCommand;
import commands.instrument.ReserveCommand;
import commands.instrument.ReturnCommand;
import commands.DefaultCommand;
import commands.datetime.ExtendCommand;
import commands.finance.FinanceCommand;
import commands.recommend.RecommendCommand;

public class Parser {
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String RESERVE = "reserve";
    private static final String EXTEND = "extend";
    private static final String RETURN = "return";
    private static final String EXIT = "exit";
    private static final String USER = "user";
    private static final String FINANCE = "finance";
    private static final String RECOMMEND = "recommend";

    public Parser() {
    }

    public static Command parse(String command, String input) {
        switch (command) {
        case HELP:
            return new HelpCommand();
        case LIST:
            return new ListCommand(input);
        case USER:
            return new UserCommand();
        case ADD:
            return new AddInstrumentCommand(input, false);
        case DELETE:
            return new DeleteCommand(input);
        case RESERVE:
            return new ReserveCommand(input);
        case EXTEND:
            return new ExtendCommand(input);
        case RETURN:
            return new ReturnCommand(input);
        case FINANCE:
            return new FinanceCommand(input);
        case EXIT:
            return new ExitCommand();
        case RECOMMEND:
            return new RecommendCommand(input);
        default:
            return new DefaultCommand();
        }
    }

    public static String parseFileDirectories(String outputFilePath) {
        if (outputFilePath == null || outputFilePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        int index = outputFilePath.lastIndexOf("/");
        return outputFilePath.substring(0, index);
    }

    public static String[] parseFileEntryToInstrument(String line) {
        String[] splitInput = line.split("\\|");
        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }
        return splitInput;
    }
}
