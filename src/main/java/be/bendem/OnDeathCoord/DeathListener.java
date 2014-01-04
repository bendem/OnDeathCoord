package be.bendem.OnDeathCoord;

import be.bendem.common.ChatFormatter;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player   player = event.getEntity();
        Location playerLocation = player.getLocation();
        String   deathMessage, coordinates, playerMessage = new String();

        coordinates = ChatFormatter.darkGray(
              Math.round(playerLocation.getX()) + ", "
            + Math.round(playerLocation.getY()) + ", "
            + Math.round(playerLocation.getZ())
        );

        if(OnDeathCoord.config.getBoolean("public-death")) {
            deathMessage = OnDeathCoord.config.getString(
                "public-death-messages." + player.getLastDamageCause().getCause().name().toLowerCase(),
                "%s died at %s"
            );
            // injecting informations
            deathMessage = String.format(deathMessage, ChatFormatter.darkGreen(player.getDisplayName()), coordinates);
            event.setDeathMessage(deathMessage);
        } else {
            deathMessage = OnDeathCoord.config.getString(
                "private-death-message",
                ChatFormatter.bold("You") + ChatFormatter.darkGray(" died at ") + "%s"
            );
            // injecting informations
            deathMessage = String.format(deathMessage, coordinates);
            playerMessage = deathMessage + " ";
        }

        if(OnDeathCoord.config.getBoolean("show-despawn-time") && event.getDrops().size() != 0) {
            playerMessage += OnDeathCoord.config.getString("despawn-message", "Your items will despawn at %s");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, OnDeathCoord.config.getInt("despawn-time", 300));

            // injection
            playerMessage = String.format(playerMessage, ChatFormatter.red(sdf.format(cal.getTime())));
        }
        player.sendMessage(playerMessage);

        if(OnDeathCoord.config.getBoolean("remind-at-despawn")) {
            // TODO Ajout du schedule de despawn message
            // http://jd.bukkit.org/beta/apidocs/index.html?org/bukkit/scheduler/BukkitScheduler.html
        }
    }
}
