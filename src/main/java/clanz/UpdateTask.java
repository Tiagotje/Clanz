package clanz;

import java.io.File;
import java.util.Date;

import org.bukkit.scheduler.BukkitRunnable;

import clanz.entity.ClanPlayer;

public class UpdateTask extends BukkitRunnable {

	Clanz clanz;
	
	public UpdateTask(Clanz c){
		clanz = c;
	}
	
	@Override
	public void run() {
		
		
		//Delete old YML files
		for(ClanPlayer c: clanz.ClanPlayers){
			if(c.p.getLastPlayed() + 7884000000L < new Date().getTime()){
				new File(clanz.getDataFolder()+File.pathSeparator+"players", c.p.getUniqueId().toString() + ".yml").delete();
				clanz.ClanPlayers.remove(c);
			}
		}
		//Save All Clans
		try {
			clanz.ClanzFile.save(clanz.ClanzPath);
		} catch (Exception e) {
			clanz.getLogger().severe("FATAL ERROR, COULD NOT SAVE clanz.yml ! \n " + e.getMessage());
		}

	}

}
