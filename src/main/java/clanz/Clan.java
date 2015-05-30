package clanz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;


public class Clan {
	
	Clanz clanz;
	UUID uuid;
	String name;
	String description;
	String motd;
	String tag;
	boolean requireInvite = false;
	//Players
	List<String> members = new ArrayList<>();
	List<String> assistants = new ArrayList<>();
	List<String> leaders = new ArrayList<>();
	List<Region> claimed = new ArrayList<>();
	//Political relations
	List<String> Enemies = new ArrayList<>();
	List<String> Allies = new ArrayList<>();
	

	public Clan(Clanz pl, String name, UUID leader, UUID id){
		this.uuid = id;
		this.name = name;
		this.clanz = pl;
		leaders.add(leader.toString());
		pl.ClanzFile.set(uuid.toString() + ".name", name);
		pl.ClanzFile.set(uuid.toString() + ".leaders" , leaders);
	}
	
	public Clan(Clanz plugin, String name, UUID leader){
		this(plugin, name, leader, UUID.randomUUID());
	}
	
	public Clan(Clanz plugin, ConfigurationSection s, String id){
		uuid = UUID.fromString(id);
		name = s.getString("name");
		clanz = plugin;
		description = 	s.contains("desc") ? s.getString("desc") : "";
		leaders 	=	s.getStringList("leaders");
		assistants 	= 	s.contains("assistants") ? s.getStringList("assistants") : new ArrayList<>();
		members 	= 	s.contains("members") ? s.getStringList("members") : new ArrayList<>();
		motd 		= 	s.contains("motd") ? s.getString("motd") : description;
		tag 		=	s.contains("tag") ? s.getString("tag") : name.substring(0, 4);
		
		
	}
	
	//Checks if a player is in this clan
	boolean containsPlayer(UUID uuid){
		String ID = uuid.toString();
		if(members.contains(ID)) return true;
		if(assistants.contains(ID)) return true;
		if(leaders.contains(ID)) return true;
		return false;
	}

	//Changes the name of this clan
	public void changeName(String name, CommandSender sender) {
		if(name.length() < 4){ sender.sendMessage(ChatColor.RED + "Name is not long enough");  return;}
		if(name.length() > 15){sender.sendMessage(ChatColor.RED + "Name is too long"); return; }
		for(Clan c: clanz.Clans){
			if(c.name == name){sender.sendMessage(ChatColor.RED + "Name already used!"); return;}
		}
		clanz.ClanzFile.set(uuid.toString() + ".name", name);
	} 
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof Clan)) return false;
		Clan c = (Clan) other;
		if(c.uuid == uuid && c.name == name){
			return true;
		}
		return false;
	}
	
}
