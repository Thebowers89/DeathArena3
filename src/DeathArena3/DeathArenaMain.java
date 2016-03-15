package DeathArena3;

import DeathArena3.Commands.AddArenaCommand;
import DeathArena3.Commands.ConfigArenaCommand;
import DeathArena3.Commands.ReloadArenasCommand;
import DeathArena3.Commands.SetCommonAreaCommand;
import DeathArena3.Handlers.JoinSignHandler;
import DeathArena3.Handlers.PlayerDisconnectHandler;
import DeathArena3.Handlers.TrashMobHandler;
import DeathArena3.Managers.ArenaManager;
import DeathArena3.Managers.BattleManager;
import DeathArena3.Managers.LobbyManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathArenaMain extends JavaPlugin {

    public void onEnable() {
        registerCommands();
        registerEvents();
        registerArenas();
    }

    public void onDisable() {
        ArenaManager.serverShuttingDown();
    }

    private void registerCommands() {
        getCommand("addarena").setExecutor(new AddArenaCommand());
        getCommand("configarena").setExecutor(new ConfigArenaCommand());
        getCommand("reloadarenas").setExecutor(new ReloadArenasCommand());
        getCommand("setcommonarea").setExecutor(new SetCommonAreaCommand());
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinSignHandler(), this);
        pm.registerEvents(new LobbyManager(), this);
        pm.registerEvents(new PlayerDisconnectHandler(), this);
        pm.registerEvents(new BattleManager(this), this);
        pm.registerEvents(new TrashMobHandler(), this);
    }

    private void registerArenas() {
        ArenaManager.registerArenas();
    }

}