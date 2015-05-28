package clanz;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClanzExecutor implements CommandExecutor {

	private Clanz plugin;

	public ClanzExecutor(Clanz pl){
		this.plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] a){
		try{
		if(!(sender instanceof Player)){}
		if(a.length == 0){showHelp(sender); return true;}
		
		if(a.length == 1){
			if(a[0].equals("save")){plugin.ClanzFile.save(plugin.ClanzPath);}
			return true;
		}
		if(a.length == 2){
			if(a[0].equalsIgnoreCase("new")){
				if(!sender.hasPermission("clanz.new")){noPermissions(sender); return true;}
				if(!(sender instanceof Player)){ noPlayer(sender); return true;}
				String name = a[1];
				plugin.ClanzFile.set("clanz."+name, ((Player) sender).getName());
				sender.sendMessage(ChatColor.GREEN + "Sucessfully created the \"" + name + "\" clan!");
				return true;
			}
		}
		}catch(Exception e){
			plugin.getLogger().severe(e.getMessage());
		}
		
		return false;
	}

	
	private void noPlayer(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command");
	}
	
	private void noPermissions(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_RED + "Sorry, but you don't have the required permissions for this command.");
	}
	
	private void showHelp(CommandSender sender){
		sender.sendMessage(new String[]{ChatColor.DARK_AQUA + "_-_-_-_-_-_-_-_-_Clanz_-_-_-_-_-_-_-_-_",
	                                         ChatColor.AQUA + "/c new <name>: Create a new clan named <name>"});
	}

}
