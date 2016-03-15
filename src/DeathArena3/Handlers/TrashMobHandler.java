package DeathArena3.Handlers;

import DeathArena3.Managers.ArenaManager;
import DeathArena3.TrashMobs.TrashMobs;
import DeathArena3.Utils.LocationUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.UUID;

public class TrashMobHandler implements Listener{

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        Entity ent = e.getEntity();
        UUID uuid = ent.getUniqueId();
        if (ArenaManager.bossArena.keySet().contains(uuid)) {
            String arena = ArenaManager.bossArena.get(uuid);
            spawnTrash(arena);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Entity ent = e.getEntity();
        if (ent.getName().equals(ChatColor.RED + "Zombie")) {
            e.getDrops().clear();
            e.getDrops().add(new ItemStack(Material.BREAD));
            e.setDroppedExp(0);
        } else if (ent.getName().equals(ChatColor.RED + "Skeleton")) {
            e.getDrops().clear();
            e.setDroppedExp(0);
        } else if (ent.getName().equals(ChatColor.RED + "Witch")) {
            e.getDrops().clear();
            e.setDroppedExp(0);
        }
    }

    private void spawnTrash(String arenaName) {
        Location location = LocationUtils.getLocation("DeathArena.Arenas." + arenaName + ".Boss");
        Random rand = new Random();
        int number = rand.nextInt(4);
        if (number == 1 || number == 3) {
            number = rand.nextInt(4);
            if (number == 1) {
                TrashMobs.tZombie(location, arenaName);
            } else if (number == 2) {
                TrashMobs.tSkeleton(location, arenaName);
            } else if (number == 3) {
                TrashMobs.tWitch(location, arenaName);
            } else if (number == 4) {
                TrashMobs.tZombie(location, arenaName);
            }
        }
    }

}
