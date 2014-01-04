package be.bendem.OnDeathCoord;

import be.bendem.common.ChatFormatter;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DeathListener implements Listener {

    public JavaPlugin plugin;

    public void DeathListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

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

        if(plugin.getConfig().getBoolean("public-death")) {
            deathMessage = plugin.getConfig().getString(
                "public-death-messages." + player.getLastDamageCause().getCause().name().toLowerCase(),
                "%s died at %s"
            );
            // injecting informations
            deathMessage = String.format(deathMessage, ChatFormatter.darkGreen(player.getDisplayName()), coordinates);
            event.setDeathMessage(deathMessage);
        } else {
            deathMessage = plugin.getConfig().getString(
                "private-death-message",
                ChatFormatter.bold("You") + ChatFormatter.darkGray(" died at ") + "%s"
            );
            // injecting informations
            deathMessage = String.format(deathMessage, coordinates);
            playerMessage = deathMessage + " ";
        }

        if(plugin.getConfig().getBoolean("show-despawn-time") && event.getDrops().size() != 0) {
            playerMessage += plugin.getConfig().getString("despawn-time-message", "Your items will despawn at %s");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, plugin.getConfig().getInt("despawn-time", 300));

            // injection
            playerMessage = String.format(playerMessage, ChatFormatter.red(sdf.format(cal.getTime())));
        }
        player.sendMessage(playerMessage);

        if(plugin.getConfig().getBoolean("remind-at-despawn")) {
            // TODO Ajout du schedule de despawn message
            // http://jd.bukkit.org/beta/apidocs/index.html?org/bukkit/scheduler/BukkitScheduler.html
            // There are normally (I mean without lag) 20 ticks per seconds
            PlayerDespawnReminder psd = new PlayerDespawnReminder(plugin, player);
            plugin.getServer().getScheduler().runTaskLater(
                plugin,
                // new PlayerDespawnReminder(plugin, player),
                psd,
                20 * plugin.getConfig().getInt("despawn-time", 300)
            );
        }
    }
}
