package DeathArena3.Handlers;

import DeathArena3.Managers.ArenaManager;
import DeathArena3.Utils.LocationUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class JoinSignHandler implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = e.getClickedBlock();
            if (block.getType().equals(Material.WALL_SIGN)) {
                Sign sign = (Sign) block.getState();
                String[] lines = sign.getLines();
                if (lines[1].equals("[DeathArena]")) {
                    if (lines[2].equals("Tank") || lines[2].equals("Ranger") || lines[2].equals("Alchemist")) {

                    } else {
                        if (ArenaManager.activeArenas.containsKey(lines[2])) {
                            if (!(ArenaManager.activeBattle.contains(lines[2]))) {
                                ArenaManager.putPlayerInArena(player, lines[2]);
                            } else {
                                player.sendMessage(ChatColor.RED + "That arena is already in progress...");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "The arena you are trying to join does not exist or is not active!");
                        }
                    }
                }
            }
        }
    }

    private void putPlayerInArena(Player player, String arenaName) {
        ArrayList<String> names = ArenaManager.activeArenas.get(arenaName);
        names.add(player.getName());
        ArenaManager.activeArenas.put(arenaName, names);
        ArenaManager.playerArena.put(player.getName(), arenaName);
        player.teleport(LocationUtils.getLocation("DeathArena.Arenas." + arenaName + ".Lobby"));
        player.sendMessage(ChatColor.GOLD + "Joining " + arenaName + "!");
    }
}
