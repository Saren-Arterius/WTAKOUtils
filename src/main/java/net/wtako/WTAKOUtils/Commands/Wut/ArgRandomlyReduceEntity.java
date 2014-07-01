package net.wtako.WTAKOUtils.Commands.Wut;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Random;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Lang;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ArgRandomlyReduceEntity {

    public ArgRandomlyReduceEntity(CommandSender sender, String[] args) {
        if (!sender.hasPermission(Main.getInstance().getProperty("artifactId") + ".admin")) {
            sender.sendMessage(Lang.NO_PERMISSION_COMMAND.toString());
            return;
        }
        if (args.length < 4) {
            sender.sendMessage(Lang.HELP_RRE.toString());
            return;
        }

        int reduceProb;
        try {
            final int parseProb = Integer.parseInt(args[3]);
            reduceProb = parseProb > 100 ? 100 : parseProb < 0 ? 0 : parseProb;
        } catch (final Exception e) {
            sender.sendMessage(Lang.HELP_RRE.toString());
            return;
        }

        World world = sender instanceof Player ? ((Player) sender).getWorld() : Main.getInstance().getServer()
                .getWorlds().get(0);
        final World parseWorld = Main.getInstance().getServer().getWorld(args[2]);
        world = parseWorld != null ? parseWorld : world;

        EntityType entityType;
        try {
            entityType = EntityType.valueOf(args[1].toUpperCase());
        } catch (final IllegalArgumentException e) {
            sender.sendMessage(MessageFormat.format(Lang.COUNT_ENTITY_NO_SUCH_THING.toString(), args[1]));
            return;
        }

        final LinkedList<Entity> removingEntities = new LinkedList<Entity>();
        int entityCount = 0;
        final Random rand = new Random();
        for (final Entity entity: world.getEntities()) {
            if (entityType != null && entity.getType() != entityType) {
                continue;
            }
            entityCount++;
            if (rand.nextInt(100) < reduceProb) {
                removingEntities.add(entity);
            }
        }
        final int removedEntityCount = removingEntities.size();
        for (final Entity entity: removingEntities) {
            entity.remove();
        }
        sender.sendMessage(MessageFormat.format(ChatColor.GREEN + "Removed {0} {1} out of {2} in {3} by {4}%.",
                removedEntityCount, entityType.name(), entityCount, world.getName(), reduceProb));
    }

}
