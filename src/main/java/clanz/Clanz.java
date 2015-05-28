package clanz;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Clanz extends JavaPlugin{
	
	
	public YamlConfiguration ClanzFile;
	public File ClanzPath;
	public List<Clan> Clans;
	
	@Override
	public void onEnable(){
		getCommand("clanz").setExecutor(new ClanzExecutor(this));
		ClanzPath  = new File(getDataFolder(), "clanz.yml");
		if(!ClanzPath.exists()){
			getDataFolder().mkdirs();
			try {
				ClanzPath.createNewFile();
			} catch (Exception e) {
				getLogger().severe(e.getMessage());
		}}
		ClanzFile = YamlConfiguration.loadConfiguration(ClanzPath);
	}
	
	@Override
	public void onDisable(){
		
	}
	
}
