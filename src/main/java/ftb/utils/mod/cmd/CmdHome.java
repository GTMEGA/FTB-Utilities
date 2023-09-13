package ftb.utils.mod.cmd;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import ftb.lib.BlockDimPos;
import ftb.lib.LMDimUtils;
import ftb.lib.api.cmd.CommandLM;
import ftb.lib.api.cmd.CommandLevel;
import ftb.utils.mod.FTBU;
import ftb.utils.world.LMPlayerServer;
import latmod.lib.LMStringUtils;

public class CmdHome extends CommandLM {

    public CmdHome() {
        super("home", CommandLevel.ALL);
    }

    public String getCommandUsage(ICommandSender ics) {
        return '/' + commandName + " <ID>";
    }

    public String[] getTabStrings(ICommandSender ics, String[] args, int i) throws CommandException {
        if (i == 0) return LMPlayerServer.get(ics).homes.list();
        return null;
    }

    public IChatComponent onCommand(ICommandSender ics, String[] args0) throws CommandException {
        EntityPlayerMP ep = getCommandSenderAsPlayer(ics);
        LMPlayerServer p = LMPlayerServer.get(ep);

        if (args0.length == 0) {
            args0 = new String[] { "home" };
        }

        String[] args = args0;

        if (args[0].equals("list")) {
            String[] list = p.homes.list();
            ics.addChatMessage(
                    new ChatComponentText(list.length + " / " + p.getRank().config.max_homes.getAsInt() + ": "));
            return (list.length == 0) ? null : new ChatComponentText(LMStringUtils.strip(list));
        }

        BlockDimPos pos = p.homes.get(args[0]);

        if (pos == null) return error(FTBU.mod.chatComponent("cmd.home_not_set", args[0]));

        if (ep.dimension != pos.dim && !p.getRank().config.cross_dim_homes.getAsBoolean())
            return error(FTBU.mod.chatComponent("cmd.home_cross_dim"));

        LMDimUtils.teleportPlayer(ep, pos);
        return FTBU.mod.chatComponent("cmd.warp_tp", args[0]);
    }
}
