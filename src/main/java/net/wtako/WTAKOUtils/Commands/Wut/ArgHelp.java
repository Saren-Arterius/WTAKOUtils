package net.wtako.WTAKOUtils.Commands.Wut;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Lang;

import org.bukkit.command.CommandSender;

public class ArgHelp {

    public ArgHelp(CommandSender sender) {
        sender.sendMessage(Main.getInstance().getName() + " v" + Main.getInstance().getProperty("version"));
        sender.sendMessage("Author: " + Main.getInstance().getProperty("author"));
        sender.sendMessage(Lang.HELP_COUNT_ENTITY.toString());
        sender.sendMessage(Lang.HELP_AOE.toString());
        sender.sendMessage(Lang.HELP_RRE.toString());
        sender.sendMessage(Lang.HELP_RELOAD.toString());
    }

}
