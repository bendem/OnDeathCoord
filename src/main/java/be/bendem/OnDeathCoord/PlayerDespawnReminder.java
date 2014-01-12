package be.bendem.OnDeathCoord;

import be.bendem.chatformatter.ChatFormatter;

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
