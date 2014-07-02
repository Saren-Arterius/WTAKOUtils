package net.wtako.WTAKOUtils.Commands.Wut;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Lang;
import net.wtako.WTAKOUtils.Utils.ValueComparator;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ArgAmountsOfEntities {

    public ArgAmountsOfEntities(final CommandSender sender, final String[] args) {
        if (!sender.hasPermission(Main.getInstance().getProperty("artifactId") + ".admin")) {
            sender.sendMessage(Lang.NO_PERMISSION_COMMAND.toString());
            return;
        }
        new BukkitRunnable() {

            @Override
            public void run() {
                World world = sender instanceof Player ? ((Player) sender).getWorld() : Main.getInstance().getServer()
                        .getWorlds().get(0);
                if (args.length >= 2) {
                    world = Main.getInstance().getServer().getWorld(args[1]);
                    if (world == null) {
                        for (final World loopWorld: Main.getInstance().getServer().getWorlds()) {
                            if (loopWorld.getName().toLowerCase().startsWith(args[1])) {
                                world = loopWorld;
                            }
                        }
                    }
                }
                final TreeMap<String, Integer> countMap = new TreeMap<String, Integer>();
                final List<Entity> entites = world.getEntities();
                for (final Entity entity: entites) {
                    if (countMap.containsKey(entity.getType().name())) {
                        countMap.put(entity.getType().name(), countMap.get(entity.getType().name()) + 1);
                    } else {
                        countMap.put(entity.getType().name(), 1);
                    }
                }
                final ValueComparator bvc = new ValueComparator(countMap);
                final TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(bvc);
                sortedMap.putAll(countMap);
                int counter = 0;
                sender.sendMessage(MessageFormat.format(ChatColor.GREEN + "Top amount of entities in {0}:",
                        world.getName()));
                for (final Entry<String, Integer> entry: sortedMap.entrySet()) {
                    sender.sendMessage(MessageFormat.format("{0}. {1}: {2}", counter++, entry.getKey(),
                            entry.getValue()));
                }
                sender.sendMessage(MessageFormat.format(ChatColor.GREEN + "Total: {0}", entites.size()));
            }

        }.runTaskAsynchronously(Main.getInstance());
    }

}
