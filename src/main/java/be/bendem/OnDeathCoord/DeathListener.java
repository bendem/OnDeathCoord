package be.bendem.OnDeathCoord;

import be.bendem.chatformatter.ChatFormatter;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DeathListener implements Listener {

    private OnDeathCoord plugin;
    private String player_name;
    private String you;
    private String coordinates;
    private String x, y, z;
    private String despawn_time;

    DeathListener(OnDeathCoord plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player   player = event.getEntity();
        String   deathMessage, playerMessage = new String();

        tagInitialisation(player);
        applyColorsToTags();

        if(plugin.getConfig().getBoolean("public-death", true)) {
            deathMessage = plugin.getConfig().getString(
                "public-death-messages." + player.getLastDamageCause().getCause().name().toLowerCase(),
                "{player} died at {coordinates}"
            );

            deathMessage = injectInformations(deathMessage);
            event.setDeathMessage(plugin.prefix(deathMessage, false));
        } else if(player.hasPermission("odc.view-messages.private-coordinates")) {
            deathMessage = plugin.getConfig().getString("private-death-message", "{you} died at {coordinates}");

            deathMessage = injectInformations(deathMessage);
            playerMessage = deathMessage + " ";
        }

        if(plugin.getConfig().getBoolean("show-despawn-time", true)
                && player.hasPermission("odc.view-messages.item-despawn")
                && event.getDrops().size() != 0) {
            playerMessage += plugin.getConfig().getString("despawn-time-message", "Your items will despawn at {despawn_time}");
            playerMessage = injectInformations(playerMessage);
        }

        if(!playerMessage.isEmpty()) {
            player.sendMessage(plugin.prefix(playerMessage, true));
        }

        if(plugin.getConfig().getBoolean("remind-at-despawn", true)
                && player.hasPermission("odc.view-messages.item-despawn.reminder")
                && event.getDrops().size() != 0) {
            // There are normally (I mean without lag) 20 ticks per seconds
            plugin.getServer().getScheduler().runTaskLater(
                plugin,
                new PlayerDespawnReminder(
                    player,
                    plugin.prefix(ChatFormatter.italic(plugin.getConfig().getString(
                        "despawn-message",
                        "Your items just despawned...")), true)),
                20 * plugin.getConfig().getInt("despawn-time", 300));
        }
    }

    private void tagInitialisation(Player player) {
        Location playerLocation = player.getLocation();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        player_name = player.getDisplayName();
        you = plugin.getConfig().getString("you-traduction", "you");
        x = Integer.toString(playerLocation.getBlockX());
        y = Integer.toString(playerLocation.getBlockY());
        z = Integer.toString(playerLocation.getBlockZ());
        coordinates = x + ", " + y + ", " + z;

        // Despawn time calculation
        cal.add(Calendar.SECOND, plugin.getConfig().getInt("despawn-time", 300));
        despawn_time = sdf.format(cal.getTime());
    }

    private void applyColorsToTags() {
        ConfigurationSection tagConfig = plugin.getConfig().getConfigurationSection("tag-colors");

        for (String key : tagConfig.getKeys(true)) {
            for (String color : tagConfig.getStringList(key)) {
                color = color.toUpperCase();
                if(key.equalsIgnoreCase("player")) {
                    player_name = ChatFormatter.format(player_name, ChatColor.valueOf(color));
                } else if(key.equalsIgnoreCase("you")) {
                    you = ChatFormatter.format(you, ChatColor.valueOf(color));
                } else if(key.equalsIgnoreCase("despawn_time")) {
                    despawn_time = ChatFormatter.format(despawn_time, ChatColor.valueOf(color));
                } else if(key.equalsIgnoreCase("coordinates")) {
                    coordinates = ChatFormatter.format(coordinates, ChatColor.valueOf(color));
                } else if(key.equalsIgnoreCase("x")) {
                    x = ChatFormatter.format(x, ChatColor.valueOf(color));
                } else if(key.equalsIgnoreCase("y")) {
                    y = ChatFormatter.format(y, ChatColor.valueOf(color));
                } else if(key.equalsIgnoreCase("z")) {
                    z = ChatFormatter.format(z, ChatColor.valueOf(color));
                }
            }
        }
    }

    private String injectInformations(String msg) {
        return msg
            .replace("{player}", player_name)
            .replace("{you}", you)
            .replace("{despawn_time}", despawn_time)
            .replace("{coordinates}", coordinates)
            .replace("{x}", x)
            .replace("{y}", y)
            .replace("{z}", z);
    }

}
