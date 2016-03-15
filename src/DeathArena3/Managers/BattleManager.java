package DeathArena3.Managers;

import DeathArena3.Bosses.ShiaLabeouf;
import DeathArena3.ClassGear.RangerGear;
import DeathArena3.ClassGear.TankGear;
import DeathArena3.DeathArenaMain;
import DeathArena3.Utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BattleManager implements Listener {

    static DeathArenaMain plugin;
    public BattleManager(DeathArenaMain instance) {
        plugin = instance;
    }

    public static HashMap<String, Integer> playerCount = new HashMap<>();
    public static HashMap<String, ArrayList<String>> readyPlayers = new HashMap<>();

    public static ArrayList<String> tankList = new ArrayList<>();
    public static ArrayList<String> rangerList = new ArrayList<>();
    public static ArrayList<String> alchemistList = new ArrayList<>();
    public static final int playerCap = 3;

    public static void addToPlayerCount(String arenaName) {
        if (playerCount.containsKey(arenaName)) {
            int number = playerCount.get(arenaName) + 1;
            playerCount.put(arenaName, number);
        } else {
            playerCount.put(arenaName, 0);
            int number = playerCount.get(arenaName) + 1;
            playerCount.put(arenaName, number);
        }
    }

    public static void removeFromPlayerCount(String arenaName, Player player) {
        if (playerCount.containsKey(arenaName)) {
            int number = playerCount.get(arenaName) - 1;
            System.out.println(number);
            if (number == 0) {
                if (readyPlayers.containsKey(arenaName)) {
                    playerCount.remove(arenaName);
                    ArrayList<String> names = readyPlayers.get(arenaName);
                    names.remove(player.getName());
                    readyPlayers.put(arenaName, names);
                } else {
                    playerCount.remove(arenaName);
                }
            }
            if (readyPlayers.containsKey(arenaName)) {
                ArrayList<String> names = readyPlayers.get(arenaName);
                names.remove(player.getName());
                readyPlayers.put(arenaName, names);
            }
        }
    }

    public static void classCheck(String playerName) {
        if (tankList.contains(playerName)) {
            tankList.remove(playerName);
        } else if (rangerList.contains(playerName)) {
            rangerList.remove(playerName);
        } else if (alchemistList.contains(playerName)) {
            alchemistList.remove(playerName);
        }
    }

    public static void addReadyPlayer(Player player, String arenaName) {
        if (readyPlayers.containsKey(arenaName)) {
            ArrayList<String> names = readyPlayers.get(arenaName);
            if (!names.contains(player.getName())) {
                names.add(player.getName());
                readyPlayers.put(arenaName, names);
                if (names.size() == playerCount.get(arenaName)) {
                    preBattle(arenaName);
                }
            } else {
                player.sendMessage("I know you are ready, hold your horses for the other players.");
            }
        } else {
            readyPlayers.put(arenaName, new ArrayList<String>());
            ArrayList<String> names = readyPlayers.get(arenaName);
            names.add(player.getName());
            readyPlayers.put(arenaName, names);
            if (names.size() == playerCount.get(arenaName)) {
                preBattle(arenaName);
            }
        }
    }

    public static void preBattle(String arenaName) {
        ArenaManager.activeBattle.add(arenaName);
        playerCount.remove(arenaName);
        readyPlayers.remove(arenaName);
        ArrayList<String> names = ArenaManager.activeArenas.get(arenaName);
        for (String key : names) {
            if (tankList.contains(key)) {
                Player player = Bukkit.getServer().getPlayer(key);
                player.teleport(LocationUtils.getLocation("DeathArena.Arenas." + arenaName + ".PlayerSpawn"));
                addTankGear(player);

            } else if (rangerList.contains(key)) {
                Player player = Bukkit.getServer().getPlayer(key);
                player.teleport(LocationUtils.getLocation("DeathArena.Arenas." + arenaName + ".PlayerSpawn"));
                addRangerGear(player);

            } else if (alchemistList.contains(key)) {
                Player player = Bukkit.getServer().getPlayer(key);
                player.teleport(LocationUtils.getLocation("DeathArena.Arenas." + arenaName + ".PlayerSpawn"));
                addAlchemistGear(player);

            }
        }
        startBattle(arenaName);
    }

    private static void addTankGear(Player player) {
        Inventory i = player.getInventory();
        i.setItem(0, TankGear.sword());
        i.setItem(36, TankGear.boots());
        i.setItem(37, TankGear.leggings());
        i.setItem(38, TankGear.chestplate());
        i.setItem(39, TankGear.helmet());
    }

    private static void addRangerGear(Player player) {
        Inventory i = player.getInventory();
        i.setItem(0, RangerGear.bow());
        i.setItem(36, RangerGear.boot());
        i.setItem(37, RangerGear.leg());
        i.setItem(38, RangerGear.chest());
        i.setItem(39, RangerGear.helmet());
        i.setItem(10, new ItemStack(Material.ARROW));
    }

    private static void addAlchemistGear(Player player) {

    }

    public static void startBattle(String arenaName) {
        summonBoss(arenaName);
    }

    public static void endBattle(String arenaName) {
        ArenaManager.activeBattle.remove(arenaName);
        ArrayList<String> names = ArenaManager.activeArenas.get(arenaName);
        for (String key : names) {
            Player player = Bukkit.getServer().getPlayer(key);
            player.teleport(LocationUtils.getLocation("DeathArena.CommonArea"));
            player.sendMessage(ChatColor.GOLD + "Congratulations!\n  You have defeated Shia Labeouf");
            ArenaManager.playerArena.remove(key);
            player.getInventory().clear();
            player.getInventory().setItem(36, null);
            player.getInventory().setItem(37, null);
            player.getInventory().setItem(38, null);
            player.getInventory().setItem(39, null);
            player.setHealth(20.0);
            player.setFoodLevel(20);
            if (tankList.contains(key)) {
                tankList.remove(key);
            } else if (rangerList.contains(key)) {
                rangerList.remove(key);
            } else if (alchemistList.contains(key)) {
                alchemistList.remove(key);
            }
        }
        cleanTrash(arenaName);
        names.clear();
        ArenaManager.activeArenas.put(arenaName, names);
    }

    public static void summonBoss(String aName) {
        final String arenaName = aName;
        final ArrayList<String> names = ArenaManager.activeArenas.get(aName);
        for (String key : names) {
            Player player = Bukkit.getServer().getPlayer(key);
            player.sendMessage("The Boss will spawn in 5 seconds!");
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                ShiaLabeouf.shiaBoss(LocationUtils.getLocation("DeathArena.Arenas." + arenaName + ".Boss"), arenaName);
                for (String key : names) {
                    Player player = Bukkit.getServer().getPlayer(key);
                    player.sendMessage(ChatColor.GOLD + "The Boss has spawned!");
                }
            }
        }, 100L);
    }

    public static void cleanTrash(String arenaName) {
        Location spawn = LocationUtils.getLocation("DeathArena.Arenas." + arenaName + ".Boss");
        List<Entity> things = Bukkit.getServer().getWorld(spawn.getWorld().getName()).getEntities();
        for (Entity thing : things) {
            String name = thing.getName();
            if (name.equals(ChatColor.RED + "Zombie")) {
                thing.remove();
            } else if (name.equals(ChatColor.RED + "Witch")) {
                thing.remove();
            } else if (name.equals(ChatColor.RED + "Skeleton")) {
                thing.remove();
            }
        }
    }

    @EventHandler
    public void onBossDeath(EntityDeathEvent e) {
        UUID id = e.getEntity().getUniqueId();
        if (ArenaManager.bossArena.containsKey(id)) {
            e.getDrops().clear();
            e.setDroppedExp(0);
            String arenaName = ArenaManager.bossArena.get(id);
            endBattle(arenaName);
            ArenaManager.bossArena.remove(id);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        if (ArenaManager.activeBattle.contains(ArenaManager.playerArena.get(player.getName()))) {
            String arenaName = ArenaManager.playerArena.get(player.getName());
            e.setKeepInventory(true);
            player.setHealth(20.0);
            repairGear(player);
            player.teleport(LocationUtils.getLocation("DeathArena.Arenas." + arenaName + ".PlayerSpawn"));
        }
    }

    private void repairGear(Player player) {
        EntityEquipment eq = player.getEquipment();
        player.sendMessage(String.valueOf(eq.getHelmet().getDurability()) + "/" + eq.getHelmet().getType().getMaxDurability());
        eq.getHelmet().setDurability((short) 0);
        eq.getChestplate().setDurability((short) 0);
        eq.getLeggings().setDurability((short) 0);
        eq.getBoots().setDurability((short) 0);
        eq.getItemInHand().setDurability((short) 0);
    }

}
