package net.mov51.shiftablespawners.utils;

import net.mov51.shiftablespawners.ShiftableSpawners;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHelper {
    public static FileConfiguration c;
    public static int shiftCost = 0;
    public static boolean mendTool;
    public static void loadConfig(){
        c = ShiftableSpawners.plugin.getConfig();
        shiftCost = c.getInt("shift-cost");
        mendTool = c.getBoolean("mend-tool");
    }
}
