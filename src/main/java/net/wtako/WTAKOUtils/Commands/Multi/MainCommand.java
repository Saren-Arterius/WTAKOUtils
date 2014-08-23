package net.wtako.WTAKOUtils.Commands.Multi;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Lang;

import org.bukkit.command.CommandSender;

public class MainCommand {

    public MainCommand(final CommandSender sender, String[] args) {

        if (args.length < 1) {
            sender.sendMessage(Lang.HELP_MULTI.toString());
            return;
        }

        String origCommand = "";
        for (int i = 0; i < args.length; i++) {
            origCommand += args[i];
            if (i < args.length - 1) {
                origCommand += " ";
            }
        }

        for (final String command: origCommand.split("; *")) {
            if (command.length() == 0 || command.equalsIgnoreCase(" ")) {
                continue;
            }
            Main.getInstance().getServer().dispatchCommand(sender, command);
        }

    }
}
