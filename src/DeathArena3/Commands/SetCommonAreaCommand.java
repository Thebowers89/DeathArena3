package DeathArena3.Commands;

import DeathArena3.Utils.LocationUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommonAreaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("DeathArena.setCommonArea") || player.isOp()) {
                LocationUtils.setLocation(player.getLocation(), "DeathArena.CommonArea");
                player.sendMessage("Common Area location set!");
                return true;
            }
         }
        return false;
    }
}
