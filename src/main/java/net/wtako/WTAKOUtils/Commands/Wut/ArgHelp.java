package net.wtako.WTAKOUtils.Commands.Wut;

import net.wtako.WTAKOUtils.Utils.Commands;
import org.bukkit.command.CommandSender;

public class ArgHelp {

    public ArgHelp(final CommandSender sender, String[] args) {
        Commands.sendHelp(sender, Commands.values(), "");
    }
}
