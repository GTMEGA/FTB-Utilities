package ftb.utils.mod.cmd;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.IChatComponent;

import ftb.lib.api.cmd.CommandLM;
import ftb.lib.api.cmd.CommandLevel;
import ftb.utils.mod.FTBU;
import ftb.utils.world.LMPlayerServer;

public class CmdDelHome extends CommandLM {

    public CmdDelHome() {
        super("delhome", CommandLevel.ALL);
    }

    public String getCommandUsage(ICommandSender ics) {
        return '/' + commandName + " <ID>";
    }

    public String[] getTabStrings(ICommandSender ics, String[] args, int i) throws CommandException {
        if (i == 0) return LMPlayerServer.get(ics).homes.list();
        return null;
    }

    public IChatComponent onCommand(ICommandSender ics, String[] args) throws CommandException {
        LMPlayerServer p = LMPlayerServer.get(ics);
        checkArgs(args, 1);

        if (p.homes.set(args[0], null)) return FTBU.mod.chatComponent("cmd.home_del", args[0]);
        return error(FTBU.mod.chatComponent("cmd.home_not_set", args[0]));
    }
}
