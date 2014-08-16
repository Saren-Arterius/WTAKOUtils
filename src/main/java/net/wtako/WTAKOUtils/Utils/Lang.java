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

    TITLE("title", "[" + Main.getInstance().getName() + "]"),

    AOE_TOP_AMOUNT("aoe-top-amount", "&aTop amount of entities in {0}:"),

    COUNT_ENTITY_NO_SUCH_THING("count-entity-no-such-thing", "&eThere is no entity type named <&f{0}&e>."),
    COUNT_ENTITY_TOP_CHUNKS("count-entity-top-chunks", "&aTop {0} chunks in {1} with most {2}(s):"),
    COUNT_ENTITY_ENTITIES("count-entity-entities", "entities"),
    RRE_NO_SUCH_WORLD("rre-no-such-world", "&eThere is no such world named <&f{0}&e>."),
    RRE_REMOVED("rre-removed", "&aRemoved {0} {1} out of {2} in {3} by {4}%."),
    PRINT_TOTAL("print-total", "&aTotal: {0}"),
    PRINT_FORMAT("print-format", "{0}. {1}: {2}"),
    COMMAND_HELP_SEPERATOR("command-help-seperator", "&6 | &a"),
    HELP_COUNT_ENTITY(
            "help-count-entity",
            "Type &b/"
                    + Main.getInstance().getProperty("mainCommand")
                    + " &a{0}&f <EntityType = all> <WorldName = yours> <ListItems = 10>&f to count top <ListItems> chunks with greatest amount of <EntityType> in <WorldName>. {1}"),
    HELP_AOE("help-aoe", "Type &b/" + Main.getInstance().getProperty("mainCommand")
            + " &a{0}&f <WorldName = yours> &f to view amounts of entities in <WorldName>. {1}"),
    HELP_RRE(
            "help-rre",
            "Type &b/"
                    + Main.getInstance().getProperty("mainCommand")
                    + " &a{0}&f <EntityType> <WorldName = yours> <Probability>&f to randomly remove <Probability>% of <EntityType> in <WorldName>. {1}"),
    HELP_HELP("help-help", "Type &b/" + Main.getInstance().getProperty("mainCommand")
            + " &a{0}&f to show help (this message). {1}"),
    HELP_RELOAD("help-reload", "Type &b/" + Main.getInstance().getProperty("mainCommand")
            + " &a{0}&f to reload the plugin. {1}"),
    NO_PERMISSION_HELP("no-permission-help", "(&cno permission&f)"),
    PLUGIN_RELOADED("plugin-reloaded", "&aPlugin reloaded."),
    NO_PERMISSION_COMMAND("no-permission-command", "&cYou are not allowed to use this command.");

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
    Lang(String path, String start) {
        this.path = path;
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