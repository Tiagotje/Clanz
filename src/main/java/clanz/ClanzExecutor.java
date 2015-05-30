package clanz;

import java.util.ArrayList;
import java.util.Arrays;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
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
			else if(a[0].equals("help") && a.length == 1){
				showHelp(sender);
			}
			// c save
			else if(a[0].equals("save") && a.length == 1){
				plugin.ClanzFile.save(plugin.ClanzPath);
				sender.sendMessage(ChatColor.GREEN + "Sucessfully saved clanz.yml");
			}
			// c new *name*
			else if(a[0].equalsIgnoreCase("new") && a.length == 2){
				if(!sender.hasPermission("clanz.new")){noPermissions(sender); return true;}
				if(!(sender instanceof Player)){ noPlayer(sender); return true;}
				plugin.Clans.add( new Clan(plugin, a[1], ((Player)sender).getUniqueId() ));
				sender.sendMessage(ChatColor.GREEN + "Sucessfully created the " + a[1] + " clan!");
			}
			// c name *new name*
			else if(a[0].equalsIgnoreCase("name") && a.length == 2){
				if(new ClanPermissions(plugin).CheckAccess(clan, player, 5)){
					clan.changeName(a[1], sender);
				}else{noPermissions(sender);}
			}
			// c desc *description*
			else if(a[0].equalsIgnoreCase("desc") && a.length > 1){
				String desc = ""; 
				for(String s: Arrays.copyOfRange(a, 1, a.length)){
					desc += (s + " ");
				}
				clan.description = desc.subSequence(0, desc.length() - 1).toString(); 
			}
			//c info (clan)
			else if(a[0].equalsIgnoreCase("info")){
				Clan c;
				if(a.length == 2) c = plugin.getClanByName(a[1]);
				else if(a.length == 1) c = clan; 
				else {invalid(sender, "the amount of arguments you entered"); return true;}
				if(c == null){invalid(sender, "the clan name you entered"); return true;}
				sender.sendMessage(new String[]{
						"§3_-_-_-_-_-_-_-_-_-_-_-_-_-_-",
						"§bClan name: " + c.name,
						"§bClan description: " + c.description,
						"§bClan leaders: " + c.leaders.toString(),
						"§bClan assistants: " + c.assistants.toString(),
						"§bClan members: " + c.members.toString(),
						"§3_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-"});
			}
			//c clearclans
			else if(a[0].equalsIgnoreCase("clearclans") && sender.isOp()){
				plugin.Clans.clear();
				plugin.ClanzFile = new YamlConfiguration();
			}
			else{ return false; }
				
				
		}catch(Exception e){
			plugin.getLogger().severe(e.toString());
			e.printStackTrace();
		}
		
		return true;
	}

	
	private void invalid(CommandSender sender, String what){
		sender.sendMessage(ChatColor.DARK_RED + "Sorry, but " + what + " is invalid.\nType \"/c help\"for help");
	}
	
	private void noPlayer(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command");
	}
	
	private void noPermissions(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_RED + "Sorry, but you don't have the required permissions for this command.");
	}
	
	private void showHelp(CommandSender sender){
		sender.sendMessage(new String[]{
				"§3_-_-_-_-_-_-_-_-_-_-_Clanz_-_-_-_-_-_-_-_-_-_-_",
	            "§b/c new <name>: Create a new clan named <name>",
	            "§b/c name <name>: Change the name of your clan",
	            "§b/c desc <desc>: Change the description of your clan",
	            "§b/c help: Shows this message"});
	}

}
