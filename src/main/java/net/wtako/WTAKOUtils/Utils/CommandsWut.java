package net.wtako.WTAKOUtils.Utils;

import net.wtako.WTAKOUtils.Commands.Wut.*;
import net.wtako.WTAKOUtils.Main;

public enum CommandsWut implements BaseCommands {

    MAIN_COMMAND(Lang.HELP_HELP.toString(), ArgHelp.class, Main.artifactId + ".use"),
    H(Lang.HELP_HELP.toString(), ArgHelp.class, Main.artifactId + ".use"),
    HELP(Lang.HELP_HELP.toString(), ArgHelp.class, Main.artifactId + ".use"),
    AOE(Lang.HELP_AOE.toString(), ArgAmountsOfEntities.class, Main.artifactId + ".admin"),
    CE(Lang.HELP_COUNT_ENTITY.toString(), ArgCountEntity.class, Main.artifactId + ".admin"),
    RRE(Lang.HELP_RRE.toString(), ArgRandomlyReduceEntity.class, Main.artifactId + ".admin"),
    RELOAD(Lang.HELP_RELOAD.toString(), ArgReload.class, Main.artifactId + ".reload");

    private String helpMessage;
    private Class<?> targetClass;
    private String permission;

    CommandsWut(String helpMessage, Class<?> targetClass, String permission) {
        this.helpMessage = helpMessage;
        this.targetClass = targetClass;
        this.permission = permission;
    }

    @Override
    public String getHelpMessage() {
        return helpMessage;
    }

    @Override
    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public String getRequiredPermission() {
        return permission;
    }
}
