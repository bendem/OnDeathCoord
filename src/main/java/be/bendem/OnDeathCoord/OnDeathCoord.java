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

    PluginDescriptionFile pdfFile;
    public static FileConfiguration config;
    public static Logger logger;

    @Override
    public void onEnable() {
        this.logger = this.getLogger();
        this.config = this.getConfig();
        this.pdfFile = this.getDescription();
        this.getLogger().info(this.pdfFile.getName() + " version " + this.pdfFile.getVersion() + " is enabled!");

        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new DeathListener(), this);
    }

    @Override
    public void onDisable() {
        this.getLogger().info(this.pdfFile.getName() + " want you to have a nice day ;-)");
    }
}
