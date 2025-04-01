package commands;

import exceptions.EmptyInstrumentListException;
import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;

public class ListCommand extends Command {
    // Constructor
    public ListCommand() {
        super("Exit");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils) {
        assert instrumentList != null;
        assert ui != null;
        if (instrumentList.getList().isEmpty()) {
            throw new EmptyInstrumentListException("List is empty, let's add some instruments :)");
        } else {
            ui.printInstrumentList(instrumentList.getList());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
