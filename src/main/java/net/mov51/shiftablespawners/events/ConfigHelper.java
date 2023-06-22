package net.mov51.shiftablespawners.events;

import net.mov51.shiftablespawners.ShiftableSpawners;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHelper {
    public static FileConfiguration c;
    static int shiftCost = 0;
    public static void loadConfig(){
        c = ShiftableSpawners.plugin.getConfig();
        shiftCost = (c.getInt("shift-cost") != 0 ? c.getInt("shift-cost") : -1);
    }
}
