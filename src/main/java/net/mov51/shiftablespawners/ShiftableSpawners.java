package net.mov51.shiftablespawners;

import net.mov51.shiftablespawners.events.BlockBreakEventListener;
import net.mov51.shiftablespawners.utils.CoreProtectHelper;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShiftableSpawners extends JavaPlugin {
    public static Plugin plugin;
    public static int pushCost = 75;
    public static CoreProtectHelper coreProtectHelper;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        coreProtectHelper = new CoreProtectHelper();
        getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
