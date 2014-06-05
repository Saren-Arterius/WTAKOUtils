package net.wtako.WTAKOUtils.EventHandlers;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Utils.Lang;
import net.wtako.WTAKOUtils.Utils.ScoreboardUtils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {

    private static HashMap<UUID, Long> playerDeathTimes = new HashMap<UUID, Long>();

    @EventHandler
    public static void onPlayerDeath(final PlayerDeathEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("DeathInfo.Show")) {
            return;
        }
        final Player victim = event.getEntity();
        if (ScoreboardUtils.noShowScoreboardPlayers.contains(victim.getUniqueId())) {
            return;
        }
        PlayerDeathListener.playerDeathTimes.put(victim.getUniqueId(), System.currentTimeMillis());
        final String deathMessage = PlayerDeathListener.getDeathScoreboardMessage(victim);

        ScoreboardUtils.showScoreboardMessage(Lang.DEATH_INFO_TITLE.toString(), deathMessage,
                Lang.DEATH_INFO_DELIMITER.toString(), victim,
                Main.getInstance().getConfig().getLong("DeathInfo.DelayTicks"),
                Main.getInstance().getConfig().getLong("DeathInfo.ShowTicks"));
    }

    @EventHandler
    public static void onPlayerRespawn(final PlayerRespawnEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("DeathInfo.Show")) {
            return;
        }
        final Player victim = event.getPlayer();
        if (ScoreboardUtils.noShowScoreboardPlayers.contains(victim.getUniqueId())) {
            return;
        }
        if (!PlayerDeathListener.playerDeathTimes.containsKey(victim.getUniqueId())) {
            return;
        }
        final Long millisReduction = System.currentTimeMillis()
                - PlayerDeathListener.playerDeathTimes.get(victim.getUniqueId());
        final Long ticksReduction = millisReduction / 50L;
        final String deathMessage = PlayerDeathListener.getDeathScoreboardMessage(victim);
        ScoreboardUtils.showScoreboardMessage(Lang.DEATH_INFO_TITLE.toString(), deathMessage,
                Lang.DEATH_INFO_DELIMITER.toString(), victim,
                Main.getInstance().getConfig().getLong("DeathInfo.DelayTicks"),
                Main.getInstance().getConfig().getLong("DeathInfo.ShowTicks") - ticksReduction);
    }

    public static String getDeathScoreboardMessage(Player victim) {
        final String locationMsg = MessageFormat.format(Lang.DEATH_INFO_LOCATION_FORMAT.toString(),
                Main.getHumanTranslation(victim.getLocation().getWorld().getName()), victim.getLocation().getBlockX(),
                victim.getLocation().getBlockY(), victim.getLocation().getBlockZ())
                + Lang.DEATH_INFO_DELIMITER.toString();
        String killerMsg = "";
        if (victim.getKiller() != null && victim.getKiller() instanceof Player) {
            killerMsg = Lang.DEATH_INFO_KILLER.toString() + ": " + victim.getKiller().getName()
                    + Lang.DEATH_INFO_DELIMITER.toString();
        } else if (victim.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            killerMsg = Lang.DEATH_INFO_KILLER.toString()
                    + ": "
                    + Main.getHumanTranslation(((EntityDamageByEntityEvent) victim.getLastDamageCause()).getDamager()
                            .getType().name()) + Lang.DEATH_INFO_DELIMITER.toString();
        }
        final Date deathDate = new Date();
        deathDate.setTime(PlayerDeathListener.playerDeathTimes.get(victim.getUniqueId()));
        final String timeMsg = Lang.DEATH_INFO_TIME.toString() + ": "
                + new SimpleDateFormat("HH:mm:ss").format(deathDate) + Lang.DEATH_INFO_DELIMITER.toString();
        String causeMsg;
        if (victim.getLastDamageCause() != null) {
            causeMsg = Lang.DEATH_INFO_CAUSE.toString() + ": "
                    + Main.getHumanTranslation(victim.getLastDamageCause().getCause().name())
                    + Lang.DEATH_INFO_DELIMITER.toString();
        } else {
            causeMsg = Lang.DEATH_INFO_CAUSE.toString() + ": " + Lang.DEATH_INFO_CAUSE_UNKNOWN.toString()
                    + Lang.DEATH_INFO_DELIMITER.toString();
        }
        final String deathMessage = MessageFormat.format(Lang.DEATH_INFO_FORMAT.toString(), locationMsg, killerMsg,
                timeMsg, causeMsg, Lang.DEATH_INFO_EXTRA_MSG.toString());
        return deathMessage;
    }
}
