package net.wtako.WTAKOUtils.EventHandlers;

import net.wtako.WTAKOUtils.Main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.BukkitUtil;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;

public class LavaListener implements Listener {

    @EventHandler
    public void onPlayerPlaceLava(PlayerBucketEmptyEvent event) {
        if (event.getPlayer().hasPermission(Main.artifactId + ".admin")) {
            return;
        }
        if (event.getBucket() == Material.LAVA_BUCKET
                && !LavaListener.wgAllowsLava(event.getBlockClicked().getLocation())) {
            event.setCancelled(true);
        }
    }

    public static boolean wgAllowsLava(Location location) {
        try {
            final WorldGuardPlugin worldGuard = LavaListener.getWorldGuard();
            final RegionManager regionManager = worldGuard.getRegionManager(location.getWorld());
            final ApplicableRegionSet set = regionManager.getApplicableRegions(BukkitUtil.toVector(location));
            return set.allows(DefaultFlag.LAVA_FLOW);
        } catch (final Error e) {
            e.printStackTrace();
            return false;
        }
    }

    private static WorldGuardPlugin getWorldGuard() {
        final Plugin plugin = Main.getInstance().getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }

}
