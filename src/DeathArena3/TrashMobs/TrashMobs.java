package DeathArena3.TrashMobs;

import DeathArena3.Managers.ArenaManager;
import DeathArena3.Managers.BattleManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;

import java.util.ArrayList;

public class TrashMobs {

    public static Zombie tZombie(Location location, String arenaName) {
        Zombie z = location.getWorld().spawn(location, Zombie.class);
        z.setCustomName(ChatColor.RED + "Zombie");
        if (!(getTank(arenaName) == null)) {
            z.setTarget(getTank(arenaName));
        }
        return z;
    }

    public static Skeleton tSkeleton(Location location, String arenaName) {
        Skeleton s = location.getWorld().spawn(location, Skeleton.class);
        s.setCustomName(ChatColor.RED + "Skeleton");
        if (!(getTank(arenaName) == null)) {
            s.setTarget(getTank(arenaName));
        }
        return s;
    }

    public static Witch tWitch(Location location, String arenaName) {
        Witch w = location.getWorld().spawn(location, Witch.class);
        w.setCustomName(ChatColor.RED + "Witch");
        if (!(getTank(arenaName) == null)) {
            w.setTarget(getTank(arenaName));
        }
        return w;
    }

    private static Player getTank(String arenaName) {
        ArrayList<String> names = ArenaManager.activeArenas.get(arenaName);
        Player player = null;
        for (String key : names) {
            if (BattleManager.tankList.contains(key)) {
                player = Bukkit.getServer().getPlayer(key);
            }
        }
        return player;
    }

}
