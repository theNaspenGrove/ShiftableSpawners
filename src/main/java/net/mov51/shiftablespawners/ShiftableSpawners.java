package net.mov51.shiftablespawners;

import net.mov51.shiftablespawners.events.BlockBreakEventListener;
import net.mov51.shiftablespawners.events.PlayerItemDamageEventListener;
import net.mov51.shiftablespawners.utils.CoreProtectHelper;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static net.mov51.shiftablespawners.utils.ConfigHelper.loadConfig;

public final class ShiftableSpawners extends JavaPlugin {
    public static Plugin plugin;

    public static CoreProtectHelper coreProtectHelper;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        coreProtectHelper = new CoreProtectHelper();
        loadConfig();
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemDamageEventListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
