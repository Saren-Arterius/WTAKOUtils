package net.wtako.WTAKOUtils.Commands.Wut;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Lang;
import net.wtako.WTAKOUtils.Utils.ValueComparator;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ArgAmountsOfEntities {

    public ArgAmountsOfEntities(final CommandSender sender, final String[] args) {

        new BukkitRunnable() {

            @Override
            public void run() {
                World world = sender instanceof Player ? ((Player) sender).getWorld() : Main.getInstance().getServer()
                        .getWorlds().get(0);
                if (args.length >= 2) {
                    world = Main.getInstance().getServer().getWorld(args[1]);
                    if (world == null) {
                        for (final World loopWorld : Main.getInstance().getServer().getWorlds()) {
                            if (loopWorld.getName().toLowerCase().startsWith(args[1])) {
                                world = loopWorld;
                            }
                        }
                    }
                }
                final TreeMap<String, Integer> countMap = new TreeMap<>();
                final List<Entity> entities = world.getEntities();
                for (final Entity entity : entities) {
                    if (countMap.containsKey(entity.getType().name())) {
                        countMap.put(entity.getType().name(), countMap.get(entity.getType().name()) + 1);
                    } else {
                        countMap.put(entity.getType().name(), 1);
                    }
                }
                final ValueComparator bvc = new ValueComparator(countMap);
                final TreeMap<String, Integer> sortedMap = new TreeMap<>(bvc);
                sortedMap.putAll(countMap);
                int counter = 0;
                sender.sendMessage(MessageFormat.format(Lang.AOE_TOP_AMOUNT.toString(), world.getName()));
                for (final Entry<String, Integer> entry : sortedMap.entrySet()) {
                    sender.sendMessage(MessageFormat.format(Lang.PRINT_FORMAT.toString(), counter++, entry.getKey(),
                            entry.getValue()));
                }
                sender.sendMessage(MessageFormat.format(Lang.PRINT_TOTAL.toString(), entities.size()));
            }

        }.runTaskAsynchronously(Main.getInstance());
    }

}
