package ftb.utils.mod.cmd.admin;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.IChatComponent;

import ftb.lib.api.cmd.CommandLM;
import ftb.lib.api.cmd.CommandLevel;
import ftb.utils.mod.FTBU;
import ftb.utils.world.LMWorldServer;

public class CmdDelWarp extends CommandLM {

    public CmdDelWarp() {
        super("delwarp", CommandLevel.OP);
    }

    public String getCommandUsage(ICommandSender ics) {
        return '/' + commandName + " <ID>";
    }

    public String[] getTabStrings(ICommandSender ics, String args[], int i) throws CommandException {
        if (i == 0) return LMWorldServer.inst.warps.list();
        return super.getTabStrings(ics, args, i);
    }

    public IChatComponent onCommand(ICommandSender ics, String[] args) throws CommandException {
        checkArgs(args, 1);
        if (LMWorldServer.inst.warps.set(args[0], null)) return FTBU.mod.chatComponent("cmd.warp_del", args[0]);
        return error(FTBU.mod.chatComponent("cmd.warp_not_set", args[0]));
    }
}
