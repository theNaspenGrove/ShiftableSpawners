package net.mov51.shiftablespawners.utils;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class CoreProtectHelper {
    Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");
    boolean isCoreProtectEnabled;
    CoreProtectAPI api;
    public CoreProtectHelper(){
        isCoreProtectEnabled = plugin instanceof CoreProtect;
        if(isCoreProtectEnabled){
            api = getCoreProtectAPI();
        }
    }
    public boolean isCoreProtectEnabled(){
        return isCoreProtectEnabled;
    }

    public CoreProtectAPI getApi() {
        if (api != null){ // Ensure we have access to the API
            return api;
        } else {
            //todo log error
            return null;
        }
    }
    public CoreProtectAPI getCoreProtectAPI() {
        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");

        // Check that CoreProtect is loaded
        if (!(plugin instanceof CoreProtect)) {
            return null;
        }

        // Check that the API is enabled
        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
        if (!CoreProtect.isEnabled()) {
            return null;
        }

        // Check that a compatible version of the API is loaded
        if (CoreProtect.APIVersion() < 9) {
            return null;
        }

        return CoreProtect;
    }
}

