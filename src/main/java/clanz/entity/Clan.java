package clanz.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import clanz.Clanz;
import clanz.protection.Region;


public class Clan {
	
	Clanz clanz;
	public UUID uuid;
	public String name = "";
	public String description = "";
	public String motd = "";
	public String tag = "";
	boolean requireInvite = false;
	//Players
	public List<ClanPlayer> members = new ArrayList<>();
	public List<ClanPlayer> assistants = new ArrayList<>();
	public List<ClanPlayer> leaders = new ArrayList<>();
	public List<Region> claimed = new ArrayList<>();
	//Political relations
	public List<String> Enemies = new ArrayList<>();
	public List<String> Allies = new ArrayList<>();
	

	public Clan(Clanz pl, String name, UUID leader, UUID id){
		this.uuid = id;
		this.name = name;
		this.clanz = pl;
		leaders.add(clanz.getClanPlayer(leader));
		pl.ClanzFile.set(uuid.toString() + ".name", name);
		pl.ClanzFile.set(uuid.toString() + ".leaders" , toStringList(leaders));
	}
	
	public Clan(Clanz plugin, String name, UUID leader){
		this(plugin, name, leader, UUID.randomUUID());
	}
	
	public Clan(Clanz plugin, ConfigurationSection s, String id){
		uuid = UUID.fromString(id);
		name = s.getString("name");
		if(name == null){clanz.getLogger().severe("ERROR NULL");}
		clanz = plugin;
		description = 	s.contains("desc") ? s.getString("desc") : "";
		leaders 	=	toClanPlayerList(s.getStringList("leaders"));
		assistants 	= 	s.contains("assistants") ? toClanPlayerList(s.getStringList("assistants")) : new ArrayList<>();
		members 	= 	s.contains("members") ? toClanPlayerList(s.getStringList("members")) : new ArrayList<>();
		motd 		= 	s.contains("motd") ? s.getString("motd") : description;
		
		
	}
	
	//converts ClanPlayer list into String list
	List<String> toStringList(List<ClanPlayer> l){
		List<String> result = new ArrayList<>();
		for(ClanPlayer c: l){
			result.add(c.p.getUniqueId().toString());
		}
		return result;
	}
	
	//converts String list into ClanPlayer list
	List<ClanPlayer> toClanPlayerList(List<String> l){
		List<ClanPlayer> result = new ArrayList<>();
		for(String s: l){
			result.add(clanz.getClanPlayer(UUID.fromString(s)));
		}
		return result;
	}
	
	//Checks if a player is in this clan
	public boolean containsPlayer(UUID uuid){
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
	
	//Gets the total number of players in this clan
	public int getPlayerCount(){
		return leaders.size() + assistants.size() + members.size();
	}
	
	
	//Get amount of players online players
	public int getOnlinePlayerCount(){
		int tot = 0;
		for(ClanPlayer s: getPlayerList()){
			if(s.p.isOnline()){
				tot++;
			}
		}
		return tot;
	}
	
	//get a list of all players in this clan
	public List<ClanPlayer> getPlayerList(){
		ArrayList<ClanPlayer> res = new ArrayList<>();
		res.addAll(members);
		res.addAll(assistants);
		res.addAll(leaders);
		return res;
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof Clan)) return false;
		Clan c = (Clan) other;
		if(c.uuid == uuid){
			return true;
		}
		return false;
	}
	
}
