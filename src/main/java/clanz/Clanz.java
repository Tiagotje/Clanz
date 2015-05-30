package clanz;

import java.io.File;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import clanz.commands.ClanzCommandEvent;
import clanz.commands.ClanzCommandListener;

public class Clanz extends JavaPlugin{
	
	public List<Clan> Clans = new ArrayList<>();
	public YamlConfiguration ClanzFile;
	public File ClanzPath;
	
	@Override
	public void onEnable(){
		ClanzPath  = new File(getDataFolder(), "clanz.yml");
		if(!ClanzPath.exists()){
			getDataFolder().mkdirs();
			try {
				ClanzPath.createNewFile();
			} catch (Exception e) {
				getLogger().severe(e.getMessage());
		}}
		ClanzFile = YamlConfiguration.loadConfiguration(ClanzPath);
		//Tempoary test clan
		Clans.add(new Clan(this, "TEST", UUID.fromString("c8c62d04-d549-4dda-a33a-d6e2dfa542b9"), UUID.fromString("c8c62d04-d549-4dda-a33a-d6e2dfa542b9") ));
		
		getCommand("clanz").setExecutor(new CommandExecutor(){
			public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
				if(arg1.getName().equalsIgnoreCase("clanz")){
					getServer().getPluginManager().callEvent(new ClanzCommandEvent(arg0, arg3));
				}return true; } } );
		
		
		getServer().getPluginManager().registerEvents(new ClanzCommandListener(this), this);
		
		AbstractSet<String> ClanSet = (AbstractSet<String>) ClanzFile.getKeys(false);
		for(String s: ClanSet){
			Clans.add(new Clan(this, ClanzFile.getConfigurationSection(s), s));
		}
	}
	
	@Override
	public void onDisable(){
		try {
			ClanzFile.save(ClanzPath);
		} catch (Exception e) {
			getLogger().severe("FATAL ERROR, COULD NOT SAVE clanz.yml ! \n " + e.getMessage());
		}
		Clans.clear();
		ClanzFile = new YamlConfiguration();
	}
	
	
	Clan getClanByName(String name){
		for(Clan c: Clans){
			if(c.name.equalsIgnoreCase(name)) return c;
		}
		return null;
	}
	
	Clan getPlayerClan(UUID uuid){
		for(Clan c: Clans){
			if(c.containsPlayer(uuid)) return c;
		}
		return null;
	}
	
}
