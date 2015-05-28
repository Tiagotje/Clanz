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
			if(!(sender instanceof Player)){noPlayer(sender); return true;}
			Player player = (Player) sender;
			Clan clan = plugin.getPlayerClan(player.getUniqueId());
			// clanz , clan , cl, c
			if(a.length == 0){showHelp(sender); return true;}
			// c help
			if(a[0].equals("help") && a.length == 1){
				showHelp(sender); return true;
			}
			// c save
			if(a[0].equals("save") && a.length == 1){
				plugin.ClanzFile.save(plugin.ClanzPath);
				sender.sendMessage(ChatColor.GREEN + "Sucessfully saved clanz.yml");
			}
			// c new *name*
			if(a[0].equalsIgnoreCase("new") && a.length == 2){
				if(!sender.hasPermission("clanz.new")){noPermissions(sender); return true;}
				if(!(sender instanceof Player)){ noPlayer(sender); return true;}
				plugin.Clans.add( new Clan(plugin, a[1], ((Player)sender).getUniqueId() ));
				sender.sendMessage(ChatColor.GREEN + "Sucessfully created the " + a[1] + " clan!");
				return true;
			}
			// c name *new name*
			if(a[0].equalsIgnoreCase("name") && a.length == 2){
				if(new ClanPermissions(plugin).CheckAccess(clan, player, 6)){
					clan.changeName(a[1], sender);
				}else{noPermissions(sender);}
			}
				
				
		}catch(Exception e){
			plugin.getLogger().severe(e.toString());
			e.printStackTrace();
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
