package DeathArena3.Managers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class LobbyManager implements Listener {

    @EventHandler
    public void onBlockClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = e.getClickedBlock();
            if (block.getType().equals(Material.STAINED_CLAY)) {
                if (ArenaManager.playerArena.containsKey(player.getName())) {
                    if (block.getData() == 14) {
                        String arenaName = ArenaManager.getPlayerArena(player);
                        if (!ArenaManager.activeBattle.contains(arenaName)) {
                            ArenaManager.removePlayerFromArena(player);
                            removePlayerFromClass(player);
                        }
                    } else if (block.getData() == 5) {
                        String aName = ArenaManager.getPlayerArena(player);
                        if (!ArenaManager.activeBattle.contains(aName)) {
                            if (classCheck(player.getName())) {
                                String arenaName = ArenaManager.playerArena.get(player.getName());
                                BattleManager.addReadyPlayer(player, arenaName);
                            } else {
                                player.sendMessage(ChatColor.RED + "You have not selected a class yet!");
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = e.getClickedBlock();
            if (block.getType().equals(Material.WALL_SIGN)) {
                Sign sign = (Sign) block.getState();
                String[] lines = sign.getLines();
                if (lines[1].equals("[DeathArena]")) {
                    if (lines[2].equals("Tank")) {
                        if (BattleManager.alchemistList.contains(player.getName())) {
                            BattleManager.alchemistList.remove(player.getName());
                            BattleManager.tankList.add(player.getName());
                            player.sendMessage(ChatColor.AQUA + "You have selected " + lines[2]);
                        } else if (BattleManager.rangerList.contains(player.getName())) {
                            BattleManager.rangerList.remove(player.getName());
                            BattleManager.tankList.add(player.getName());
                            player.sendMessage(ChatColor.AQUA + "You have selected " + lines[2]);
                        } else {
                            BattleManager.tankList.add(player.getName());
                            player.sendMessage(ChatColor.AQUA + "You have selected " + lines[2]);
                        }
                    } else if (lines[2].equals("Ranger")) {
                        if (BattleManager.tankList.contains(player.getName())) {
                            BattleManager.tankList.remove(player.getName());
                            BattleManager.rangerList.add(player.getName());
                            player.sendMessage(ChatColor.AQUA + "You have selected " + lines[2]);
                        } else if (BattleManager.alchemistList.contains(player.getName())) {
                            BattleManager.alchemistList.remove(player.getName());
                            BattleManager.rangerList.add(player.getName());
                            player.sendMessage(ChatColor.AQUA + "You have selected " + lines[2]);
                        } else {
                            BattleManager.rangerList.add(player.getName());
                            player.sendMessage(ChatColor.AQUA + "You have selected " + lines[2]);
                        }
                    } else if (lines[2].equals("Alchemist")) {
                        if (BattleManager.tankList.contains(player.getName())) {
                            BattleManager.tankList.remove(player.getName());
                            BattleManager.alchemistList.add(player.getName());
                            player.sendMessage(ChatColor.AQUA + "You have selected " + lines[2]);
                        } else if (BattleManager.rangerList.contains(player.getName())) {
                            BattleManager.rangerList.remove(player.getName());
                            BattleManager.alchemistList.add(player.getName());
                            player.sendMessage(ChatColor.AQUA + "You have selected " + lines[2]);
                        } else {
                            BattleManager.alchemistList.add(player.getName());
                            player.sendMessage(ChatColor.AQUA + "You have selected " + lines[2]);
                        }
                    }
                }
            }
        }
    }

    public static void removePlayerFromClass(Player player) {
        String name = player.getName();
        if (BattleManager.alchemistList.contains(name)) {
            BattleManager.alchemistList.remove(name);
        } else if (BattleManager.rangerList.contains(name)) {
            BattleManager.rangerList.remove(name);
        } else if (BattleManager.tankList.contains(name)) {
            BattleManager.tankList.remove(name);
        }
    }

    private static boolean classCheck(String name) {
        if (BattleManager.tankList.contains(name)) {
            return true;
        } else if (BattleManager.rangerList.contains(name)) {
            return true;
        } else if (BattleManager.alchemistList.contains(name)) {
            return true;
        }
        return false;
    }

}
