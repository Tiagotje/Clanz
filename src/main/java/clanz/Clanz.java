package clanz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Clanz extends JavaPlugin{
	
	public List<Clan> Clans = new ArrayList<>();
	public YamlConfiguration ClanzFile;
	public File ClanzPath;
	
	@Override
	public void onEnable(){
		//Temporary
		ClanzPath  = new File(getDataFolder(), "clanz.yml");
		if(!ClanzPath.exists()){
			getDataFolder().mkdirs();
			try {
				ClanzPath.createNewFile();
			} catch (Exception e) {
				getLogger().severe(e.getMessage());
		}}
		ClanzFile = YamlConfiguration.loadConfiguration(ClanzPath);
		Clans.add(new Clan(this, "TEMP", UUID.fromString("c8c62d04-d549-4dda-a33a-d6e2dfa542b9")));
		getCommand("clanz").setExecutor(new ClanzExecutor(this));
	}
	
	@Override
	public void onDisable(){
		try {
			ClanzFile.save(ClanzPath);
		} catch (Exception e) {
			getLogger().severe("FATAL ERROR, COULD NOT SAVE clanz.yml ! \n " + e.getMessage());
		}
	}
	
	
	Clan getClanName(String name){
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
