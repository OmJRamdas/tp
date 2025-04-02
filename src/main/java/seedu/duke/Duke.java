package seedu.duke;

import commands.Command;
import exceptions.FileCannotBeFoundException;
import storage.Storage;
import ui.Ui;
import parser.Parser;
import instrument.InstrumentList;
import user.UserList;
import user.UserUtils;
import utils.IsOverdueChecker;
import utils.LowStockChecker;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     * hello
     */
    private final Ui ui;
    private final Parser parser;
    private final InstrumentList instrumentList;
    private final Storage storage;
    private final ScheduledExecutorService scheduler;
    private final UserList userList;
    private final UserUtils userUtils;

    private final String saveFilePath = "./data/SirDukeBox.txt";

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(ui, saveFilePath);
        scheduler = Executors.newSingleThreadScheduledExecutor();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);

        InstrumentList currentInstrumentList;
        try {
            currentInstrumentList = storage.loadOldFile();
        } catch (FileCannotBeFoundException e) {
            currentInstrumentList = new InstrumentList();
        }
        instrumentList = currentInstrumentList;

        startDailyOverdueCheck();
    }


    /**
     * Starts a scheduled task to check for overdue instruments once per day.
     */
    private void startDailyOverdueCheck() {
        scheduler.scheduleAtFixedRate(() -> {
            IsOverdueChecker.checkAll(instrumentList);
        }, 0, 24, TimeUnit.HOURS); // Runs immediately, then every 24 hours
    }

    private void startStockCheck() {
        LowStockChecker.checkAll(instrumentList.getList());
    }

    public void runDuke() {
        ui.printStartMessage();
        startStockCheck();
        boolean isExit = false;

        while (!isExit) {
            try {

                String userInput = ui.readUserInput();
                String command = ui.getCommand(userInput);
                String input = ui.getRemainingWords(userInput);

                assert command != null;
                assert input != null;

                Command commandObj = parser.parse(command, input);
                commandObj.execute(instrumentList, ui, userUtils);
                isExit = commandObj.isExit();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            storage.saveCurrentFile(instrumentList);
        } catch (IOException e) {
            throw new FileCannotBeFoundException(saveFilePath);
        } finally {
            shutdownScheduler();
        }
    }

    private void shutdownScheduler() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(3, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

    public static void main(String[] args) {
        new Duke().runDuke();
    }
}
