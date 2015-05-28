package clanz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;


public class Clan {
	
	Clanz clanz;
	//Clan info
	UUID uuid;
	String name;
	String description;
	String motd;
	boolean requireInvite = false;
	//Players
	List<String> members = new ArrayList<>();
	List<String> assistents = new ArrayList<>();
	List<String> leaders = new ArrayList<>();
	List<Region> claimed = new ArrayList<>();
	//Political relations
	List<String> Enemies = new ArrayList<>();
	List<String> Allies = new ArrayList<>();
	
	public Clan(Clanz plugin, String name, UUID leader){
		this.uuid = UUID.randomUUID();
		this.name = name;
		this.clanz = plugin;
		leaders.add(leader.toString());
		plugin.ClanzFile.set(uuid.toString() + ".name", name);
		plugin.ClanzFile.set(uuid.toString() + ".leaders" , leaders);
	}
	
	boolean containsPlayer(UUID uuid){
		String ID = uuid.toString();
		if(members.contains(ID)) return true;
		if(assistents.contains(ID)) return true;
		if(leaders.contains(ID)) return true;
		return false;
	}

	public void changeName(String name, CommandSender sender) {
		if(name.length() < 4){ sender.sendMessage(ChatColor.RED + "Name is not long enough");  return;}
		if(name.length() > 15){sender.sendMessage(ChatColor.RED + "Name is too long"); return; }
		for(Clan c: clanz.Clans){
			if(c.name == name){sender.sendMessage(ChatColor.RED + "Name already used!"); return;}
		}
		clanz.ClanzFile.set(uuid.toString() + ".name", name);
	} 
	
}
