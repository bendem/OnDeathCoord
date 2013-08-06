package be.bendem.OnDeathCoord;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * OnDeathCoord for Bukkit
 *
 * @author bendem
 */
public class OnDeathCoord extends JavaPlugin {

	PluginDescriptionFile pdfFile;

	@Override
	public void onEnable() {
		this.pdfFile = this.getDescription();
		getLogger().info(this.pdfFile.getName() + " version " + this.pdfFile.getVersion() + " is enabled!");

		getServer().getPluginManager().registerEvents(new DeathListener(), this);
	}

	@Override
	public void onDisable() {
		getLogger().info(this.pdfFile.getName() + " want you to have a nice day ;-)");
	}
}
