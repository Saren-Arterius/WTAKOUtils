package net.wtako.WTAKOUtils.Commands.Wut;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Lang;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Random;

public class ArgRandomlyReduceEntity {

    public ArgRandomlyReduceEntity(CommandSender sender, String[] args) {
        if (args.length < 4) {
            sender.sendMessage(MessageFormat.format(Lang.HELP_RRE.toString(), args[1]));
            return;
        }

        int reduceProb;
        try {
            final int parseProb = Integer.parseInt(args[3]);
            reduceProb = parseProb > 100 ? 100 : parseProb < 0 ? 0 : parseProb;
        } catch (final Exception e) {
            sender.sendMessage(MessageFormat.format(Lang.HELP_RRE.toString(), args[1]));
            return;
        }

        final World world = Main.getInstance().getServer().getWorld(args[2]);
        if (world == null) {
            sender.sendMessage(MessageFormat.format(Lang.RRE_NO_SUCH_WORLD.toString(), args[2]));
            return;
        }

        EntityType entityType;
        try {
            entityType = EntityType.valueOf(args[1].toUpperCase());
        } catch (final IllegalArgumentException e) {
            sender.sendMessage(MessageFormat.format(Lang.COUNT_ENTITY_NO_SUCH_THING.toString(), args[1]));
            return;
        }

        final LinkedList<Entity> removingEntities = new LinkedList<>();
        int entityCount = 0;
        final Random rand = new Random();
        for (final Entity entity : world.getEntities()) {
            if (entityType != null && entity.getType() != entityType) {
                continue;
            }
            entityCount++;
            if (rand.nextInt(100) < reduceProb) {
                removingEntities.add(entity);
            }
        }
        final int removedEntityCount = removingEntities.size();
        removingEntities.forEach(org.bukkit.entity.Entity::remove);
        if (entityType != null) {
            sender.sendMessage(MessageFormat.format(Lang.RRE_REMOVED.toString(), removedEntityCount, entityType.name(),
                    entityCount, world.getName(), reduceProb));
        }
    }

}
