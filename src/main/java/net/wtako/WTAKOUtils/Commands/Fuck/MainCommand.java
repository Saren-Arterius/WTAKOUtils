package net.wtako.WTAKOUtils.Commands.Fuck;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Config;
import net.wtako.WTAKOUtils.Utils.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class MainCommand {

    @SuppressWarnings("deprecation")
    public MainCommand(final CommandSender sender, String[] args) {

        if (args.length < 1) {
            sender.sendMessage(Lang.HELP_FUCK.toString());
            return;
        }

        final Player target = Main.getInstance().getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Lang.FUCK_BUDDY_IS_OFFLINE.toString());
            return;
        }

        if (sender instanceof Player && (sender == target)) {
            sender.sendMessage(Lang.SELF_FUCK.toString());
            return;
        }

        int power = 1;
        int times = 1;

        if (args.length > 1) {
            try {
                power = Integer.parseInt(args[1]);
                power = power > Config.FUCK_MAX_POWER.getInt() ? Config.FUCK_MAX_POWER.getInt() : power;
                power = power < 1 ? 1 : power;
            } catch (final NumberFormatException ignored) {
            }
        }

        if (args.length > 2) {
            try {
                times = Integer.parseInt(args[2]);
                times = times > Config.FUCK_MAX_TIMES.getInt() ? Config.FUCK_MAX_TIMES.getInt() : times;
                times = times < 1 ? 1 : times;
            } catch (final NumberFormatException ignored) {
            }
        }

        for (int i = 0; i < times; i++) {
            target.getWorld().createExplosion(target.getLocation(), power);
        }
        sender.sendMessage(MessageFormat.format(Lang.YOU_FUCKED_PLAYER.toString(), target.getName(), times, power));
        sender.sendMessage(Lang.YOU_ARE_HAPPY_NOW.toString());
        target.sendMessage(MessageFormat.format(Lang.PLAYER_FUCKED_YOU.toString(), sender.getName(), times, power));
        target.sendMessage(Lang.YOU_ARE_HAPPY_NOW.toString());
    }
}
