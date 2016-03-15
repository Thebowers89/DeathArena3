package DeathArena3.Commands;

import DeathArena3.Managers.ArenaManager;
import DeathArena3.Utils.LocationUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                if (player.hasPermission("DeathArena.configArena")) {
                    if (ArenaManager.activeArenas.keySet().contains(args[0])) {
                        if (args[1].equalsIgnoreCase("lobby")) {
                            LocationUtils.setLocation(player.getLocation(), "DeathArena.Arenas." + args[0] + ".Lobby");
                            player.sendMessage("Lobby for " + args[0] + " has been configured!");
                            return true;
                        } else if (args[1].equalsIgnoreCase("boss")) {
                            LocationUtils.setLocation(player.getLocation(), "DeathArena.Arenas." + args[0] + ".Boss");
                            player.sendMessage("Boss spawn for " + args[0] + " has been configured!");
                            return true;
                        } else if (args[1].equalsIgnoreCase("spawn")) {
                            LocationUtils.setLocation(player.getLocation(), "DeathArena.Arenas." + args[0] + ".PlayerSpawn");
                            player.sendMessage("Player spawn point for " + args[0] + " has been configured!");
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Arena either is not active or does not exist!");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid Syntax!");
                    return true;
                }
            }
        }
        return false;
    }
}
