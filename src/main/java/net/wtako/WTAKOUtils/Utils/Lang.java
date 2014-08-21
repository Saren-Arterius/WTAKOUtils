package net.wtako.WTAKOUtils.Utils;

import net.wtako.WTAKOUtils.Main;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * An enum for requesting strings from the language file.
 * 
 * @author gomeow
 */
public enum Lang {

    TITLE("[" + Main.getInstance().getName() + "]"),

    AOE_TOP_AMOUNT("&aTop amount of entities in {0}:"),

    COUNT_ENTITY_NO_SUCH_THING("&eThere is no entity type named <&f{0}&e>."),
    COUNT_ENTITY_TOP_CHUNKS("&aTop {0} chunks in {1} with most {2}(s):"),
    COUNT_ENTITY_ENTITIES("entities"),
    RRE_NO_SUCH_WORLD("&eThere is no such world named <&f{0}&e>."),
    RRE_REMOVED("&aRemoved {0} {1} out of {2} in {3} by {4}%."),
    PRINT_TOTAL("&aTotal: {0}"),
    PRINT_FORMAT("{0}. {1}: {2}"),
    COMMAND_HELP_SEPERATOR("&6 | &a"),
    COMMAND_ARG_IN_USE("&e{0}&a"),
    SUB_COMMAND("Sub-command: &e{0}"),
    HELP_COUNT_ENTITY("Type &b/" + Main.getInstance().getProperty("mainCommand")
            + " &a{0}&f <EntityType = all> <WorldName = yours> <ListItems = 10>&f "
            + "to count top <ListItems> chunks with greatest amount of <EntityType> in <WorldName>."),
    HELP_AOE("Type &b/" + Main.getInstance().getProperty("mainCommand")
            + " &a{0}&f <WorldName = yours> &f to view amounts of entities in <WorldName>."),
    HELP_RRE("Type &b/" + Main.getInstance().getProperty("mainCommand")
            + " &a{0}&f <EntityType> <WorldName = yours> <Probability>&f "
            + "to randomly remove <Probability>% of <EntityType> in <WorldName>."),
    HELP_HELP("Type &b/" + Main.getInstance().getProperty("mainCommand") + " &a{0}&f to show help (this message)."),
    HELP_RELOAD("Type &b/" + Main.getInstance().getProperty("mainCommand") + " &a{0}&f to reload the plugin."),
    NO_PERMISSION_HELP(" (&cno permission&f)"),
    PLUGIN_RELOADED("&aPlugin reloaded."),
    NO_PERMISSION_COMMAND("&cYou are not allowed to use this command.");

    private String                   path;
    private String                   def;
    private static YamlConfiguration LANG;

    /**
     * Lang enum constructor.
     * 
     * @param path
     *            The string path.
     * @param start
     *            The default string.
     */
    Lang(String start) {
        path = name().toLowerCase().replace("_", "-");
        def = start;
    }

    /**
     * Set the {@code YamlConfiguration} to use.
     * 
     * @param config
     *            The config to set.
     */
    public static void setFile(YamlConfiguration config) {
        Lang.LANG = config;
    }

    @Override
    public String toString() {
        if (this == TITLE) {
            return ChatColor.translateAlternateColorCodes('&', Lang.LANG.getString(path, def)) + " ";
        }
        return ChatColor.translateAlternateColorCodes('&', Lang.LANG.getString(path, def));
    }

    /**
     * Get the default value of the path.
     * 
     * @return The default value of the path.
     */
    public String getDefault() {
        return def;
    }

    /**
     * Get the path to the string.
     * 
     * @return The path to the string.
     */
    public String getPath() {
        return path;
    }
}