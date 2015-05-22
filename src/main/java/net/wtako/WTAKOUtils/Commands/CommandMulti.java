package net.wtako.WTAKOUtils.Commands;

import net.wtako.WTAKOUtils.Utils.CommandsMulti;
import net.wtako.WTAKOUtils.Utils.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.InvocationTargetException;

public class CommandMulti implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return callCommand(sender, args, "MAIN_COMMAND");
    }

    public boolean callCommand(CommandSender sender, String[] args, String targetCommandName) {
        try {
            final CommandsMulti targetCommand = CommandsMulti
                    .valueOf(targetCommandName.toUpperCase().replace("-", "_"));
            if (!sender.hasPermission(targetCommand.getRequiredPermission())) {
                sender.sendMessage(Lang.NO_PERMISSION_COMMAND.toString());
                return true;
            }
            targetCommand.getTargetClass().getDeclaredConstructor(CommandSender.class, String[].class)
                    .newInstance(sender, args);
            return true;
        } catch (final IllegalArgumentException | InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            return false;
        }
    }
}
