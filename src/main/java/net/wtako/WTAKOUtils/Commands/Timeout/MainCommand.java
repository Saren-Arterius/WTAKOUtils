package net.wtako.WTAKOUtils.Commands.Timeout;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Config;
import net.wtako.WTAKOUtils.Utils.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class MainCommand {

    public MainCommand(final CommandSender sender, String[] args) {

        if (args.length < 2) {
            sender.sendMessage(Lang.HELP_TIMEOUT.toString());
            return;
        }

        Long delayTicks;
        try {
            delayTicks = Long.parseLong(args[0]);
            delayTicks = delayTicks > Config.TIMEOUT_MAX_TICKS.getLong() ? Config.TIMEOUT_MAX_TICKS.getLong()
                    : delayTicks;
            delayTicks = delayTicks < 0 ? 0 : delayTicks;
        } catch (final NumberFormatException e) {
            sender.sendMessage(Lang.HELP_TIMEOUT.toString());
            return;
        }

        String command = "";
        for (int i = 1; i < args.length; i++) {
            command += args[i];
            if (i < args.length - 1) {
                command += " ";
            }
        }

        final String finalCommand = command;

        new BukkitRunnable() {

            @Override
            public void run() {
                Main.getInstance().getServer().dispatchCommand(sender, finalCommand);
            }
        }.runTaskLater(Main.getInstance(), delayTicks);

    }
}
