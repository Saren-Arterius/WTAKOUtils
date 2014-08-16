package net.wtako.WTAKOUtils.Commands.Wut;

import java.text.MessageFormat;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Lang;
import net.wtako.WTAKOUtils.Utils.ValueComparator;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ArgCountEntity {

    public ArgCountEntity(final CommandSender sender, final String[] args) {

        new BukkitRunnable() {

            @Override
            public void run() {
                Integer listItems = 10;
                if (args.length >= 4) {
                    try {
                        listItems = Integer.parseInt(args[3]);
                    } catch (final Exception e) {

                    }
                }
                World world = sender instanceof Player ? ((Player) sender).getWorld() : Main.getInstance().getServer()
                        .getWorlds().get(0);
                if (args.length >= 3) {
                    world = Main.getInstance().getServer().getWorld(args[2]);
                    if (world == null) {
                        for (final World loopWorld: Main.getInstance().getServer().getWorlds()) {
                            if (loopWorld.getName().toLowerCase().startsWith(args[2])) {
                                world = loopWorld;
                            }
                        }
                    }
                    if (world == null) {
                        world = sender instanceof Player ? ((Player) sender).getWorld() : Main.getInstance()
                                .getServer().getWorlds().get(0);
                    }
                }
                EntityType entityType = null;
                if (args.length >= 2) {
                    try {
                        entityType = EntityType.valueOf(args[1].toUpperCase());
                    } catch (final IllegalArgumentException e) {
                        sender.sendMessage(MessageFormat.format(Lang.COUNT_ENTITY_NO_SUCH_THING.toString(), args[1]));
                    }
                }
                final TreeMap<String, Integer> countMap = new TreeMap<String, Integer>();
                int entityCount = 0;
                for (final Entity entity: world.getEntities()) {
                    if (entityType != null && entity.getType() != entityType) {
                        continue;
                    }
                    entityCount++;
                    final String chunkName = entity.getLocation().getChunk().toString();
                    if (countMap.containsKey(chunkName)) {
                        countMap.put(chunkName, countMap.get(chunkName) + 1);
                    } else {
                        countMap.put(chunkName, 1);
                    }
                }
                final ValueComparator bvc = new ValueComparator(countMap);
                final TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(bvc);
                sortedMap.putAll(countMap);
                int counter = 0;
                sender.sendMessage(MessageFormat.format(Lang.COUNT_ENTITY_TOP_CHUNKS.toString(), listItems,
                        world.getName(), entityType != null ? entityType.name() : Lang.COUNT_ENTITY_ENTITIES.toString()));
                for (final Entry<String, Integer> entry: sortedMap.entrySet()) {
                    if (counter++ >= listItems) {
                        break;
                    }
                    sender.sendMessage(MessageFormat.format(Lang.PRINT_FORMAT.toString(), counter, entry.getKey(),
                            entry.getValue()));
                }
                sender.sendMessage(MessageFormat.format(Lang.PRINT_TOTAL.toString(), entityCount));
            }

        }.runTaskAsynchronously(Main.getInstance());

    }

}
