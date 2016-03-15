package DeathArena3.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationUtils {

    public static void setLocation(Location location, String string) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena3").getDataFolder() + "/Arena Configurations.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        myFile.set(string + ".World", location.getWorld().getName());
        myFile.set(string + ".X", location.getX());
        myFile.set(string + ".Y", location.getY());
        myFile.set(string + ".Z", location.getZ());
        myFile.set(string + ".Yaw", location.getYaw());
        myFile.set(string + ".Pitch", location.getPitch());
        try {
            myFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Location getLocation(String string) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena3").getDataFolder() + "/Arena Configurations.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        World world = Bukkit.getServer().getWorld(myFile.getString(string + ".World"));
        double x = myFile.getDouble(string + ".X");
        double y = myFile.getDouble(string + ".Y");
        double z = myFile.getDouble(string + ".Z");
        float yaw = (float) myFile.getDouble(string + ".Yaw");
        float pitch = (float) myFile.getDouble(string + ".Pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

}
