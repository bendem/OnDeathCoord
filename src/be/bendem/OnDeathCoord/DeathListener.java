package be.bendem.OnDeathCoord;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 *
 * @author Ben
 */
public class DeathListener implements Listener {
    
    @EventHandler
    public void death(PlayerDeathEvent event) {
        Location playerLocation = event.getEntity().getLocation();
        event.setDeathMessage(event.getDeathMessage() + " :  "
                + Math.round(playerLocation.getX()) + " / " 
                + Math.round(playerLocation.getY()) + " / "
                + Math.round(playerLocation.getZ()));
    }
}
