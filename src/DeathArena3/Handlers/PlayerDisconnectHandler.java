package DeathArena3.Handlers;

import DeathArena3.Managers.ArenaManager;
import DeathArena3.Managers.LobbyManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDisconnectHandler implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (ArenaManager.playerArena.keySet().contains(player.getName())) {
            ArenaManager.removePlayerFromArena(player);
            LobbyManager.removePlayerFromClass(player);
        }
    }

}
