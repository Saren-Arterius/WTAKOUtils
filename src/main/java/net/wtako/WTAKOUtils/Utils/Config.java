package net.wtako.WTAKOUtils.Utils;

import net.wtako.WTAKOUtils.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Config {

    PREVENT_RAIL_DUPLICATE("bug-fix.prevent-rail-duplicate", true),
    FIX_WORLDGUARD_LAVA("bug-fix.fix-worldguard-lava", true),
    BANNED_COMMANDS("banned-commands.commands", Arrays.asList("testban", "dangercommand")),
    BANNED_COMMANDS_EXEMPTS("banned-commands.exempt-players", new ArrayList<String>()),
    FUCK_MAX_POWER("fuck.max-power", 20),
    FUCK_MAX_TIMES("fuck.max-times", 300),
    TIMEOUT_MAX_TICKS("timeout.max-ticks", 1200);

    private String path;
    private Object value;

    Config(String path, Object var) {
        this.path = path;
        final FileConfiguration config = Main.getInstance().getConfig();
        if (config.contains(path)) {
            value = config.get(path);
        } else {
            value = var;
        }
    }

    public static void saveAll() {
        final FileConfiguration config = Main.getInstance().getConfig();
        for (final Config setting : Config.values()) {
            if (!config.contains(setting.getPath())) {
                config.set(setting.getPath(), setting.getValue());
            }
        }
        Main.getInstance().saveConfig();
    }

    public Object getValue() {
        return value;
    }

    public boolean getBoolean() {
        return (boolean) value;
    }

    public String getString() {
        return (String) value;
    }

    public int getInt() {
        return (int) value;
    }

    public long getLong() {
        return Integer.valueOf(getInt()).longValue();
    }

    public double getDouble() {
        return (double) value;
    }

    public String getPath() {
        return path;
    }

    @SuppressWarnings("unchecked")
    public List<Object> getValues() {
        return (List<Object>) value;
    }

    @SuppressWarnings("unchecked")
    public List<String> getStrings() {
        return (List<String>) value;
    }

}