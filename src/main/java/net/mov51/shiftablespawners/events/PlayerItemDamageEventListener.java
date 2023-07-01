package net.mov51.shiftablespawners.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.mov51.shiftablespawners.utils.XPHelper.hasEnoughXP;

public class PlayerItemDamageEventListener implements Listener {
    public static List<UUID> playersToList = new ArrayList<>();
    @EventHandler
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {
        if(playersToList.contains(event.getPlayer().getUniqueId())){
            if(hasEnoughXP(event.getPlayer(), 1)){
                event.getPlayer().giveExp(-1);
                event.setCancelled(true);
                playersToList.remove(event.getPlayer().getUniqueId());
            }
        }
    }
}
