package be.bendem.OnDeathCoord;

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

    public PluginDescriptionFile pdfFile;
    public FileConfiguration config;
    public Logger logger;

    @Override
    public void onEnable() {
        logger = getLogger();
        config = getConfig();
        pdfFile = getDescription();
        getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");

        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info(pdfFile.getName() + " want you to have a nice day ;-)");
    }
}
