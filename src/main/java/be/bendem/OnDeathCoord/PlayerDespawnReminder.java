package be.bendem.OnDeathCoord;

import be.bendem.chatformatter.ChatFormatter;

import org.bukkit.entity.Player;


public class PlayerDespawnReminder implements Runnable {

    public OnDeathCoord plugin;
    public Player player;

    PlayerDespawnReminder(OnDeathCoord plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void run() {
        if(!player.isOnline()) {
            return;
        }
        player.sendMessage(plugin.prefix(ChatFormatter.italic(plugin.getConfig().getString("despawn-message")), true));
    }
}
