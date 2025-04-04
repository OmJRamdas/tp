package commands.finance;

import commands.Command;
import finance.FinanceManager;
import instrument.InstrumentList;
import parser.CommandParser;
import ui.Ui;
import user.UserUtils;

public class FinanceCommand extends Command {
    private static final String ADD = "add:";
    private static final String SUB = "subtract:";
    private static final String GET = "get";
    private static final String HELP = "help";


    private CommandParser parser;

    public FinanceCommand(String command) {
        super(command);
        parser = new CommandParser();
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        String[] userInput = parser.splits(this.name);
        try {
            int amount;
            String command = userInput[0];
            switch (command) {
            case HELP:
                ui.printFinanceCommandList();
                break;
            case ADD:
                amount = Integer.parseInt(userInput[1]);
                financeManager.inflowPayment(amount);
                ui.printReceivedAmount(financeManager.getTotalCash());
                break;
            case SUB:
                amount = Integer.parseInt(userInput[1]);
                financeManager.outflowPayment(amount);
                ui.printPaymentAmount(financeManager.getTotalCash());
                break;
            case GET:
                ui.printAmount(financeManager.getTotalCash());
                break;
            default:
                ui.printNoMatchingCommandError();
                break;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
