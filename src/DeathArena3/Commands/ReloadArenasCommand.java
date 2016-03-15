package DeathArena3.Commands;

import DeathArena3.Managers.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadArenasCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("DeathArena.reloadArenas") || player.isOp()) {
                ArenaManager.reloadArenas();
                player.sendMessage("All arenas reloaded!");
                return true;
            }
        }
        return false;
    }
}
