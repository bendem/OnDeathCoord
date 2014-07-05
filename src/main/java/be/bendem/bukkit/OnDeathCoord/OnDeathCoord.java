package be.bendem.bukkit.ondeathcoord;

import be.bendem.bukkit.chatformatter.ChatFormatter;
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

        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getCommand("odc").setExecutor(new CommandHandler(this));
        getCommand("odc").setTabCompleter(new CommandHandler(this));
    }

    public String prefix(String msg, Boolean isMsgPrivate) {
        if(isMsgPrivate && getConfig().getBoolean("prefix-private-messages")
                || !isMsgPrivate && getConfig().getBoolean("prefix-public-messages")) {
            msg = "[" + ChatFormatter.green(getDescription().getName()) + "] " + msg;
        }
        return msg;
    }

}
