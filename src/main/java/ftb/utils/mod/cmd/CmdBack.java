package ftb.utils.mod.cmd;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IChatComponent;

import ftb.lib.LMDimUtils;
import ftb.lib.api.cmd.*;
import ftb.utils.mod.FTBU;
import ftb.utils.world.LMPlayerServer;

public class CmdBack extends CommandLM {

    public CmdBack() {
        super("back", CommandLevel.ALL);
    }

    public IChatComponent onCommand(ICommandSender ics, String[] args) throws CommandException {
        EntityPlayerMP ep = getCommandSenderAsPlayer(ics);
        LMPlayerServer p = LMPlayerServer.get(ep);
        if (p.lastDeath == null) return error(FTBU.mod.chatComponent("cmd.no_dp"));
        LMDimUtils.teleportPlayer(ep, p.lastDeath);
        p.lastDeath = null;

        return null;
    }
}
