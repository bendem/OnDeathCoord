package be.bendem.bukkit.ondeathcoord;

import org.bukkit.entity.Player;


public class PlayerDespawnReminder implements Runnable {

    public Player player;
    public String msg;

    PlayerDespawnReminder(Player player, String msg) {
        this.msg = msg;
        this.player = player;
    }

    public void run() {
        if(!player.isOnline()) {
            return;
        }
        player.sendMessage(msg);
    }
}
