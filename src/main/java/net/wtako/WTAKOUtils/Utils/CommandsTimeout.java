package net.wtako.WTAKOUtils.Utils;

import net.wtako.WTAKOUtils.Commands.Timeout.MainCommand;
import net.wtako.WTAKOUtils.Main;

public enum CommandsTimeout implements BaseCommands {

    MAIN_COMMAND(Lang.HELP_TIMEOUT.toString(), MainCommand.class, Main.artifactId + ".admin");

    private String helpMessage;
    private Class<?> targetClass;
    private String permission;

    CommandsTimeout(String helpMessage, Class<?> targetClass, String permission) {
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
