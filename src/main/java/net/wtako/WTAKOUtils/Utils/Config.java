package net.wtako.WTAKOUtils.Utils;

import java.util.List;

import net.wtako.WTAKOUtils.Main;

import org.bukkit.configuration.file.FileConfiguration;

public enum Config {

    PREVENT_RAIL_DUPLICATE("bug-fix.prevent-rail-duplicatre", true),
    BAN_ACC_SAY("ban.acc-say", true),
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

    public static void saveAll() {
        final FileConfiguration config = Main.getInstance().getConfig();
        for (final Config setting: Config.values()) {
            if (!config.contains(setting.getPath())) {
                config.set(setting.getPath(), setting.getValue());
            }
        }
        Main.getInstance().saveConfig();
    }

}