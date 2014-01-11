package be.bendem.OnDeathCoord;

import be.bendem.chatformatter.ChatFormatter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements TabExecutor {

    OnDeathCoord plugin;

    CommandHandler(OnDeathCoord plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("odc")) {
            return false;
        }

        if(args[0].equalsIgnoreCase("reload") && args.length == 1) {
            plugin.reloadConfig();
            sender.sendMessage("Config reloaded...");
            return true;
        }

        if(args[0].equalsIgnoreCase("set") && args.length == 3) {
            if(!args[2].equalsIgnoreCase("true") && !args[2].equalsIgnoreCase("false")) {
                return false;
            }

            if(EditableParameters.contains(args[1])) {
                sender.sendMessage(args[1] + " setted to " + args[2]);
                plugin.reloadConfig();
                plugin.getConfig().set(args[1], args[2].equalsIgnoreCase("true"));
                plugin.saveConfig();
                return true;
            }
        }

        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        if (args.length == 0) {
            return list;
        }

        if(args.length == 1) {
            list.add("reload");
            list.add("set");
            return list;
        }
        if(args[0].equalsIgnoreCase("set")) {
            if(args.length == 2) {
                return EditableParameters.stringValues();
            }
            if(args.length == 3) {
                list.add("true");
                list.add("false");
                return list;
            }
        }

        return list;
    }

}
