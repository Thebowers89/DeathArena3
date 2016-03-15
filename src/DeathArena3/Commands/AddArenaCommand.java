package DeathArena3.Commands;

import DeathArena3.Managers.ArenaManager;
import DeathArena3.Utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class AddArenaCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("DeathArena.addArena") || player.isOp()) {
                if (args.length == 1) {
                    File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena3").getDataFolder() + "/Arena Configurations.yml");
                    YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
                    String string = args[0];
                    myFile.set("DeathArena.Arenas." + string + ".Name", string);
                    try {
                        myFile.save(file);
                        player.sendMessage(ChatColor.GOLD + "Successfully created " + string + "!\n     Now configure it with /configarena.");
                        ArenaManager.reloadArenas();
                    } catch (IOException e) {
                        e.printStackTrace();
                        player.sendMessage(ChatColor.RED + "Error creating arena!");
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid Syntax!");
                    return true;
                }
            }
        }
        return false;
    }
}
