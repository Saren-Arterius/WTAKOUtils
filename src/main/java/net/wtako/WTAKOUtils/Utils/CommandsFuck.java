package net.wtako.WTAKOUtils.Utils;

import net.wtako.WTAKOUtils.Commands.Fuck.MainCommand;
import net.wtako.WTAKOUtils.Main;

public enum CommandsFuck implements BaseCommands {

    MAIN_COMMAND(Lang.HELP_FUCK.toString(), MainCommand.class, Main.artifactId + ".admin");

    private String helpMessage;
    private Class<?> targetClass;
    private String permission;

    CommandsFuck(String helpMessage, Class<?> targetClass, String permission) {
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
