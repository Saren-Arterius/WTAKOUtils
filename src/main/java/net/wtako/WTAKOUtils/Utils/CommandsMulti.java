package net.wtako.WTAKOUtils.Utils;

import net.wtako.WTAKOUtils.Main;
import net.wtako.WTAKOUtils.Commands.Multi.MainCommand;

public enum CommandsMulti implements BaseCommands {

    MAIN_COMMAND(Lang.HELP_MULTI.toString(), MainCommand.class, Main.artifactId + ".admin");

    private String   helpMessage;
    private Class<?> targetClass;
    private String   permission;

    private CommandsMulti(String helpMessage, Class<?> targetClass, String permission) {
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
