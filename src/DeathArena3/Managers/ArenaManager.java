package DeathArena3.Managers;

import DeathArena3.Utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ArenaManager {

    //This is to quickly find which arena a player is in <PlayerName, ArenaName>
    public static HashMap<String, String> playerArena = new HashMap<>();
    //This is to tell which boss is in which arena
    public static HashMap<UUID, String> bossArena = new HashMap<>();
    //This is a list of all arenas and lists for player names
    public static HashMap<String, ArrayList<String>> activeArenas = new HashMap<>();
    //This is to tell when a battle is in progress
    public static ArrayList<String> activeBattle = new ArrayList<>();

    public static void registerArenas() {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena3").getDataFolder() + "/Arena Configurations.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        if (myFile.contains("DeathArena.Arenas")) {
            for (String key : myFile.getConfigurationSection("DeathArena.Arenas").getKeys(false)) {
                String path = "DeathArena.Arenas." + key;
                activeArenas.put(myFile.getString(path + ".Name"), new ArrayList<String>());
            }
        }
    }

    public static void reloadArenas() {
        activeArenas.clear();
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena3").getDataFolder() + "/Arena Configurations.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        for (String key : myFile.getConfigurationSection("DeathArena.Arenas").getKeys(false)) {
            String path = "DeathArena.Arenas." + key;
            activeArenas.put(myFile.getString(path + ".Name"), new ArrayList<String>());
        }
        for (String key : playerArena.keySet()) {
            String arena = playerArena.get(key);
            ArrayList<String> names = activeArenas.get(arena);
            names.add(key);
            activeArenas.put(arena, names);
        }
    }

    public static void removePlayerFromArena(Player player) {
        sendMessage("Leave", player.getName());
        String arena = playerArena.get(player.getName());
        ArrayList<String> names = activeArenas.get(arena);
        names.remove(player.getName());
        playerArena.remove(player.getName());
        activeArenas.put(arena, names);
        BattleManager.removeFromPlayerCount(arena, player);
        BattleManager.classCheck(player.getName());
        if (BattleManager.readyPlayers.containsKey(arena)) {
            ArrayList<String> name = BattleManager.readyPlayers.get(arena);
            if (name.contains(player.getName())) {
                name.remove(player.getName());
                BattleManager.readyPlayers.put(arena, name);
            }
        }
        player.teleport(LocationUtils.getLocation("DeathArena.CommonArea"));
    }

    public static void putPlayerInArena(Player player, String arenaName) {
        if (getPlayerAmount(arenaName) <= BattleManager.playerCap) {
            ArrayList<String> names = ArenaManager.activeArenas.get(arenaName);
            names.add(player.getName());
            ArenaManager.activeArenas.put(arenaName, names);
            ArenaManager.playerArena.put(player.getName(), arenaName);
            BattleManager.addToPlayerCount(arenaName);
            player.teleport(LocationUtils.getLocation("DeathArena.Arenas." + arenaName + ".Lobby"));
            sendMessage("Join", player.getName());
        } else {
            player.sendMessage("This arena is full!");
        }
    }

    private static void sendMessage(String type, String playerName) {
        String arena = playerArena.get(playerName);
        ArrayList<String> names = activeArenas.get(arena);
        if (type.equals("Join")) {
            for (String key : names) {
                Player player = Bukkit.getServer().getPlayer(key);
                player.sendMessage(ChatColor.AQUA + playerName + ChatColor.YELLOW + " has joined the arena!");
            }
        } else if (type.equals("Leave")) {
            for (String key : names) {
                Player player = Bukkit.getServer().getPlayer(key);
                player.sendMessage(ChatColor.AQUA + playerName + ChatColor.YELLOW + " has left the arena!");
            }
        }
    }

    public static void serverShuttingDown() {
        Location spawn = LocationUtils.getLocation("DeathArena.CommonArea");
        for (String key : playerArena.keySet()) {
            Player player = Bukkit.getServer().getPlayer(key);
            player.getInventory().clear();
            player.getInventory().setItem(36, null);
            player.getInventory().setItem(37, null);
            player.getInventory().setItem(38, null);
            player.getInventory().setItem(39, null);
            player.teleport(spawn);
            player.sendMessage("Server is shutting down!\n  Returning you to the common area.");
        }
        List<Entity> things = Bukkit.getServer().getWorld(spawn.getWorld().getName()).getEntities();
        for (Entity thing : things) {
            if (bossArena.keySet().contains(thing.getUniqueId())) {
                thing.remove();
            }
            if (thing.getName().equals(ChatColor.RED + "Zombie")) {
                thing.remove();
            } else if (thing.getName().equals(ChatColor.RED + "Skeleton")) {
                thing.remove();
            } else if (thing.getName().equals(ChatColor.RED + "Witch")) {
                thing.remove();
            }
        }
    }

    public static String getPlayerArena(Player player) {
        return playerArena.get(player.getName());
    }

    public static int getPlayerAmount(String arenaName) {
        if (BattleManager.playerCount.containsKey(arenaName)) {
            return BattleManager.playerCount.get(arenaName);
        } else {
            return 0;
        }
    }

}
