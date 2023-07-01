package net.mov51.shiftablespawners.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.Random;

import static net.mov51.shiftablespawners.ShiftableSpawners.pushCost;
import static net.mov51.shiftablespawners.XPHelper.hasEnoughXP;

public class BlockBreakEventListener implements org.bukkit.event.Listener{
    static Random random = new Random();

    static RelativeLocation[] locations = {
            new RelativeLocation(0, 0, 1),
            new RelativeLocation(1, 0, 1),
            new RelativeLocation(-1, 0, 1),
            new RelativeLocation(0, 1, 1),
            new RelativeLocation(1, 1, 1),
            new RelativeLocation(-1, 1, 1),
            new RelativeLocation(0, -1, 1),
            new RelativeLocation(1, -1, 1),
            new RelativeLocation(-1, -1, 1),
            new RelativeLocation(0, 0, -1),
            new RelativeLocation(1, 0, -1),
            new RelativeLocation(-1, 0, -1),
            new RelativeLocation(0, 1, -1),
            new RelativeLocation(1, 1, -1),
            new RelativeLocation(-1, 1, -1),
            new RelativeLocation(0, -1, -1),
            new RelativeLocation(1, -1, -1),
            new RelativeLocation(-1, -1, -1),
            new RelativeLocation(1, 0, 0),
            new RelativeLocation(-1, 0, 0),
            new RelativeLocation(1, 1, 0),
            new RelativeLocation(-1, 1, 0),
            new RelativeLocation(1, -1, 0),
            new RelativeLocation(-1, -1, 0),
    };

    //implement BlockPistonEvent
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() != org.bukkit.Material.SPAWNER) {
            return;
        }
        if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.GOLDEN_PICKAXE) {
            return;
        }
        if (!event.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(org.bukkit.enchantments.Enchantment.SILK_TOUCH)) {
            return;
        }
        if(!hasEnoughXP(event.getPlayer(), pushCost)){
            return;
        }

        Location loc = rollLocation(block.getLocation());
        if (loc != null) {
            CreatureSpawner spawner = (CreatureSpawner) block.getState();
            //spawn spawner
            loc.getWorld().getBlockAt(loc).setType(Material.SPAWNER);
            //set spawner data
            loc.getWorld().getBlockAt(loc).setBlockData(spawner.getBlockData());
            BlockState state = loc.getWorld().getBlockAt(loc).getState();
            CreatureSpawner newSpawner = (CreatureSpawner) state;
            newSpawner.setSpawnedType(spawner.getSpawnedType());
            newSpawner.setDelay(spawner.getDelay());
            newSpawner.setMinSpawnDelay(spawner.getMinSpawnDelay());
            newSpawner.setMaxSpawnDelay(spawner.getMaxSpawnDelay());
            newSpawner.setSpawnCount(spawner.getSpawnCount());
            newSpawner.setMaxNearbyEntities(spawner.getMaxNearbyEntities());
            newSpawner.setRequiredPlayerRange(spawner.getRequiredPlayerRange());
            newSpawner.setSpawnRange(spawner.getSpawnRange());
            newSpawner.update();
            if(coreProtectHelper.isCoreProtectEnabled()){
                coreProtectHelper.getApi().logPlacement(event.getPlayer().getName(),loc,block.getType(),block.getBlockData());
            }

            if(pushCost != -1){
                event.getPlayer().giveExp(-pushCost);
            }else{
                event.getPlayer().giveExp(-event.getExpToDrop());
            }
            event.setExpToDrop(0);
        } else {
            //cancel event
            event.setCancelled(true);
        }
    }

    private static Location rollLocation(Location center) {
        ArrayList<RelativeLocation> checkedLocations = new ArrayList<>();
        if (center == null) {
            return null;
        }
        while (checkedLocations.size() < locations.length) {
            int x = random.nextInt(locations.length);
            while (checkedLocations.contains(locations[x])) {
                x = random.nextInt(locations.length);
            }
            checkedLocations.add(locations[x]);
            Location loc = center.clone().add(locations[x].x, locations[x].y, locations[x].z);
            if (loc.getBlock().getType() == Material.AIR && loc.getBlock().getRelative(0, -1, 0).getType().isSolid()) {
                return loc;
            }
        }
        return null;
    }
}

