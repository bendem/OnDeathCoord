package be.bendem.OnDeathCoord;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * OnDeathCoord for Bukkit
 *
 * @author bendem
 */
public class OnDeathCoord extends JavaPlugin {

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");

		getServer().getPluginManager().registerEvents(new DeathListener(), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("Goodbye world!");
	}
}
