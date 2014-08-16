package net.wtako.WTAKOUtils.Utils;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Commands.Wut.ArgAmountsOfEntities;
import net.wtako.WTAKOUtils.Commands.Wut.ArgCountEntity;
import net.wtako.WTAKOUtils.Commands.Wut.ArgHelp;
import net.wtako.WTAKOUtils.Commands.Wut.ArgReload;

public enum Commands {

    MAIN_COMMAND(Lang.HELP_HELP.toString(), ArgHelp.class, Main.artifactId + ".use"),
    H(Lang.HELP_HELP.toString(), ArgHelp.class, Main.artifactId + ".use"),
    HELP(Lang.HELP_HELP.toString(), ArgHelp.class, Main.artifactId + ".use"),
    AOE(Lang.HELP_AOE.toString(), ArgAmountsOfEntities.class, Main.artifactId + ".admin"),
    CE(Lang.HELP_COUNT_ENTITY.toString(), ArgCountEntity.class, Main.artifactId + ".admin"),
    RRE(Lang.HELP_RRE.toString(), ArgCountEntity.class, Main.artifactId + ".admin"),
    RELOAD(Lang.HELP_RELOAD.toString(), ArgReload.class, Main.artifactId + ".reload");

    private String   helpMessage;
    private Class<?> targetClass;
    private String   permission;

    private Commands(String helpMessage, Class<?> targetClass, String permission) {
        this.helpMessage = helpMessage;
        this.targetClass = targetClass;
        this.permission = permission;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public String getRequiredPermission() {
        return permission;
    }
}
