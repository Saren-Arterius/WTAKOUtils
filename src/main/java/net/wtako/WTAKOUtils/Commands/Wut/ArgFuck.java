package net.wtako.WTAKOUtils.Commands.Wut;

import java.text.MessageFormat;

import net.wtako.WTAKOUtils.Utils.CommandHelper;
import net.wtako.WTAKOUtils.Utils.Lang;

import org.bukkit.command.CommandSender;

public class ArgFuck {

    public ArgFuck(final CommandSender sender, String[] args) {

        if (args.length < 2) {
            sender.sendMessage(MessageFormat.format(Lang.HELP_FUCK.toString(), CommandHelper.joinArgsInUse(args, 1)));
            return;
        }

    }
}
