package be.bendem.OnDeathCoord;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class PlayerDespawnReminder implements Runnable {

    public Player player;
    public JavaPlugin plugin;

    public void PlayerDespawnReminder(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void run() {
        player.sendMessage("[" + plugin.pdfFile.getName() + "] " + plugin.getConfig().getString("despawn-message"));
    }
}
