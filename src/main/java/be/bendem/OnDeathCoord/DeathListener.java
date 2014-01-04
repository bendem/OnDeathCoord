package be.bendem.OnDeathCoord;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player   player = event.getEntity();
        Location playerLocation = player.getLocation();
        String   deathMsg, coordinates;

        // TODO Adding colors
        // player
        // ChatColor.COLOR_CHAR + ChatColor.BOLD
        // message
        // ChatColor.COLOR_CHAR + ChatColor.RESET
        // ChatColor.COLOR_CHAR + ChatColor.DARK_GREY
        // coordinates
        // ChatColor.COLOR_CHAR + ChatColor.BOLD

        coordinates = Math.round(playerLocation.getX()) + " - "
                    + Math.round(playerLocation.getY()) + " - "
                    + Math.round(playerLocation.getZ());

        if(OnDeathCoord.config.getBoolean("public")) {
            deathMsg = OnDeathCoord.config.getString(
                "public-messages." + player.getLastDamageCause().getCause().name().toLowerCase(),
                "%s died at %s"
            );
            deathMsg = String.format(deathMsg, player.getDisplayName(), coordinates);
            OnDeathCoord.logger.info("Death message set to '" + deathMsg + "'");
            event.setDeathMessage(deathMsg);
        } else {
            deathMsg = OnDeathCoord.config.getString("private-message", "%s died at %s");
            deathMsg = String.format(deathMsg, coordinates);
            player.sendMessage(deathMsg);
        }


        OnDeathCoord.logger.info(deathMsg);
    }
}
