package clanz;

import java.io.File;
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
import org.bukkit.plugin.java.JavaPlugin;

import clanz.commands.ClanzCommandRunner;
import clanz.entity.Clan;
import clanz.entity.ClanPlayer;

public class Clanz extends JavaPlugin{
	
	public List<Clan> Clans = new ArrayList<>();
	public YamlConfiguration ClanzFile;
	public File ClanzPath;
	public List<ClanPlayer> ClanPlayers = new ArrayList<>(); //Every players ever joined :O
	
	@Override
	public void onEnable(){
		
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
				ClanPlayers.add(new ClanPlayer(yc, this));
			}
		}
		
		Clans.add(new Clan(this, "TEST", UUID.fromString("c8c62d04-d549-4dda-a33a-d6e2dfa542b8"), UUID.fromString("c8c62d04-d549-4dda-a33a-d6e2dfa542b9") ));
		
		getLogger().info("HAAI");
		//Set commandExecutor
		getCommand("clanz").setExecutor(new CommandExecutor(){
			public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
				if(arg1.getName().equalsIgnoreCase("clanz")){
					if(arg0 instanceof Player){
					new ClanzCommandRunner(Clanz.getPlugin(Clanz.class), getClanPlayer(arg0.getName()), arg3);
					}
				}return true; } } );
		
		
		//Make list of all clans
		AbstractSet<String> ClanSet = (AbstractSet<String>) ClanzFile.getKeys(false);
		for(String s: ClanSet){
			Clans.add(new Clan(this, ClanzFile.getConfigurationSection(s), s));
		}
	}
	
	@Override
	public void onDisable(){
		for(ClanPlayer c: ClanPlayers){
			if(c.lastPlayed.getTime() + 7884000000L < new Date().getTime()){
				new File(getDataFolder()+File.pathSeparator+"players", c.p.getUniqueId().toString() + ".yml").delete();
				ClanPlayers.remove(c);
			}
		}
		try {
			ClanzFile.save(ClanzPath);
		} catch (Exception e) {
			getLogger().severe("FATAL ERROR, COULD NOT SAVE clanz.yml ! \n " + e.getMessage());
		}
		Clans.clear();
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
		OfflinePlayer op = getServer().getOfflinePlayer(uuid);
		for(ClanPlayer cp: ClanPlayers){
			if(cp.p == op) return cp;
		}
		getLogger().severe("Error, could not find player with uuid: " + uuid.toString());
		return null;
	}
	
}
