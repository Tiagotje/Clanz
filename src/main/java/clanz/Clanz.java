package clanz;

import java.io.File;
import java.io.IOException;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import clanz.commands.ClanzCommandRunner;
import clanz.entity.Clan;
import clanz.entity.ClanPlayer;
import clanz.listener.PlayerListener;

public class Clanz extends JavaPlugin{
	
	public List<Clan> Clans = new ArrayList<>();
	public YamlConfiguration ClanzFile;
	public File ClanzPath;
	public List<ClanPlayer> ClanPlayers = new ArrayList<>(); //Every players ever joined :O
	public WorldGuardPlugin WGP;
	
	@Override
	public void onEnable(){
		
		//Get WorldGuard
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
		 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	    	getLogger().severe("Could not find WorldGuard");
	    }
	    else{
	    	WGP = (WorldGuardPlugin) plugin;
	    	getLogger().info("Found WorldGuard");
	    }
		
		
		//Get ClanzFile
		ClanzPath  = new File(getDataFolder(), "clanz.yml");
		if(!ClanzPath.exists()){
			try{
				getDataFolder().mkdirs();
				ClanzPath.createNewFile();
			} catch (Exception e) {
				getLogger().severe(e.getMessage());
		}}
		ClanzFile = YamlConfiguration.loadConfiguration(ClanzPath);
		
		//Get Player Files
		File PlayerFolder = new File(getDataFolder(), "players");
		if(!PlayerFolder.exists()) PlayerFolder.mkdirs();
		for(File f: PlayerFolder.listFiles()){
			if(f.isFile() && f.getName().endsWith(".yml")){
				YamlConfiguration yc = YamlConfiguration.loadConfiguration(f);
				System.out.println("FOUND PLAYER WITH UUID:" + yc.getString("UUID"));
				ClanPlayers.add(new ClanPlayer(yc, this));
			}
		}
		
		//Tempoary test clan
		Clans.add(new Clan(this, "TEST", UUID.fromString("00000000-0000-0000-0000-000000000000"), UUID.fromString("c8c62d04-d549-4dda-a33a-d6e2dfa542b8") ));
		
		
		//Set commandExecutor
		getCommand("clanz").setExecutor(new CommandExecutor(){
			public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
				if(arg1.getName().equalsIgnoreCase("clanz")){
					if(arg0 instanceof Player){
					new ClanzCommandRunner(Clanz.getPlugin(Clanz.class), getClanPlayer(((Player) arg0).getUniqueId()), arg3);
					}
				}return true; } } );
		
		
		//Make list of all clans
		AbstractSet<String> ClanSet = (AbstractSet<String>) ClanzFile.getKeys(false);
		for(String s: ClanSet){
			Clans.add(new Clan(this, ClanzFile.getConfigurationSection(s), s));
		}
		
		//Events
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		
		
	}
	
	@Override
	public void onDisable(){
		
		for(Clan c: Clans){
			c.save(ClanzFile);
		}
		try {
			ClanzFile.save(ClanzPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Saving ClanPlayer
		for(ClanPlayer cp: ClanPlayers){
			System.out.println("Saving PLAYER DATA FOR " + cp.p.getUniqueId());
			try {
				File f = new File(getDataFolder() + File.separator + "players", cp.p.getUniqueId().toString() + ".yml");
				if(!f.exists()){ f.createNewFile(); System.out.println("Creating file " + cp.p.getUniqueId().toString());}
				YamlConfiguration yc = new YamlConfiguration();
				cp.saveToConfig(yc);
				yc.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Garbage collection
		Clans.clear();
		ClanPlayers.clear();
		ClanzFile = new YamlConfiguration();
		
	}
	
	
	public Clan getClanByName(String name){
		for(Clan c: Clans){
			if(c.name.equalsIgnoreCase(name)) return c;
		}
		return null;
	}
	
	public ClanPlayer getClanPlayer(String name){
		Player p = getServer().getPlayer(name);
		if(p == null) return null;
		return getClanPlayer(p.getUniqueId());
	}
	
	public ClanPlayer getClanPlayer(UUID uuid){
		for(ClanPlayer cp: ClanPlayers){
			if(cp.uuid.compareTo(uuid) == 0) return cp;
		}
		getLogger().severe("Error, could not find player with uuid: " + uuid.toString());
		return null;
	}
	
}
