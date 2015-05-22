package net.wtako.WTAKOUtils.EventHandlers;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Config;
import net.wtako.WTAKOUtils.Utils.Lang;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.text.MessageFormat;

public class CommandListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommandCalled(PlayerCommandPreprocessEvent event) {
        if (event.getPlayer().hasPermission(Main.artifactId + ".banned-commands-exempt")) {
            return;
        }
        try {
            String playerCommand = event.getMessage().split(" ")[0].substring(1);
            Config.BANNED_COMMANDS.getStrings().stream().filter(bannedCommand -> bannedCommand.equalsIgnoreCase(playerCommand)).forEach(bannedCommand -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Lang.TITLE.toString() + MessageFormat.format(Lang.COMMAND_BANNED.toString(), playerCommand));
            });
        } catch (Exception ignored) {
        }
    }
}
