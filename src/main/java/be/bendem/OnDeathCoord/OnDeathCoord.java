package be.bendem.OnDeathCoord;

import be.bendem.chatformatter.ChatFormatter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * OnDeathCoord for Bukkit
 *
 * @author bendem
 */
public class OnDeathCoord extends JavaPlugin {

    public Logger logger;

    @Override
    public void onEnable() {
        logger = getLogger();
        logger.info(getDescription().getName() + " version " + getDescription().getVersion() + " is enabled!");

        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getCommand("odc").setExecutor(new CommandHandler(this));
        getCommand("odc").setTabCompleter(new CommandHandler(this));
    }

    @Override
    public void onDisable() {
        logger.info(getDescription().getName() + " want you to have a nice day ;-)");
    }

    public String prefix(String msg, Boolean isMsgPrivate) {
        if( isMsgPrivate && getConfig().getBoolean("prefix-private-messages")
            || !isMsgPrivate && getConfig().getBoolean("prefix-public-messages")) {
            msg = "[" + ChatFormatter.green(getDescription().getName()) + "] " + msg;
        }
        return msg;
    }

}
