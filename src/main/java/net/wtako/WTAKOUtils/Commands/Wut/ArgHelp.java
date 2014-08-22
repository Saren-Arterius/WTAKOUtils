package net.wtako.WTAKOUtils.Commands.Wut;

import net.wtako.WTAKOUtils.Utils.CommandHelper;
import net.wtako.WTAKOUtils.Utils.CommandsWut;

import org.bukkit.command.CommandSender;

public class ArgHelp {

    public ArgHelp(final CommandSender sender, String[] args) {
        CommandHelper.sendHelp(sender, CommandsWut.values(), "");
    }
}
