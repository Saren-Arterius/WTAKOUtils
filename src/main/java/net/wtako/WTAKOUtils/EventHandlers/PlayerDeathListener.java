package net.wtako.WTAKOUtils.EventHandlers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener {

    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Entity killer = victim.getKiller();
    }
}
