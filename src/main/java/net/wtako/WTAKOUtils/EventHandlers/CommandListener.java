package net.wtako.WTAKOUtils.EventHandlers;

import net.wtako.WTAKOUtils.Utils.Config;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void onAccSay(PlayerCommandPreprocessEvent event) {
        if (!Config.BAN_ACC_SAY.getBoolean()) {
            return;
        }
        final String[] args = event.getMessage().split(" ");
        if (args.length < 2) {
            return;
        }
        String name = event.getPlayer().getName().toLowerCase();
        if (name.contains("saren_hk") || name.contains("koyin")) {
            return;
        }
        if ((args[0].equalsIgnoreCase("/acc") || args[0].equalsIgnoreCase("/announcer"))
                && args[1].equalsIgnoreCase("say")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Please no.");
        }
    }
}
