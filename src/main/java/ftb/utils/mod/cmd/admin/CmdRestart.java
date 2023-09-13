package ftb.utils.mod.cmd.admin;

import java.io.File;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.IChatComponent;

import ftb.lib.FTBLib;
import ftb.lib.api.cmd.CommandLM;
import ftb.lib.api.cmd.CommandLevel;
import latmod.lib.LMFileUtils;

public class CmdRestart extends CommandLM {

    public CmdRestart() {
        super("restart", CommandLevel.OP);
    }

    public IChatComponent onCommand(ICommandSender ics, String[] args) throws CommandException {
        restart();
        return null;
    }

    public static void restart() {
        LMFileUtils.newFile(new File(FTBLib.folderMinecraft, "autostart.stamp"));
        FTBLib.getServer().initiateShutdown();
    }
}
