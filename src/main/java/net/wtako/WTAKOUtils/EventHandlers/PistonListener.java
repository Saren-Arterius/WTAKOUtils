package net.wtako.WTAKOUtils.EventHandlers;

import net.wtako.WTAKOUtils.Main;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class PistonListener implements Listener {

    @EventHandler
    public static void onPistonExtend(BlockPistonExtendEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("BugFixes.PreventRailDuplicateWithPiston")) {
            return;
        }
        for (final Block block: event.getBlocks()) {
            if (block.getType() == Material.RAILS || block.getType() == Material.POWERED_RAIL
                    || block.getType() == Material.DETECTOR_RAIL) {
                event.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler
    public static void onPistonRetract(BlockPistonRetractEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("BugFixes.PreventRailDuplicateWithPiston")) {
            return;
        }
        if (event.getBlock().getType() == Material.RAILS || event.getBlock().getType() == Material.POWERED_RAIL
                || event.getBlock().getType() == Material.DETECTOR_RAIL) {
            event.setCancelled(true);
        }
    }
}
