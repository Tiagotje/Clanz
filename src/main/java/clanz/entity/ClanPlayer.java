package clanz.entity;

import java.util.Date;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import clanz.Clanz;

public class ClanPlayer {

	public ClanPlayer(YamlConfiguration yc, Clanz clanz) {
		p = clanz.getServer().getOfflinePlayer(UUID.fromString(yc.getString("UUID")));
		kills = yc.getInt("kills");
		deaths = yc.getInt("deaths");
		clan = clanz.getClanByName(yc.getString("clan"));
		lastPlayed = new Date(p.getLastPlayed());
	}
	
	public OfflinePlayer p;
	public int kills;
	public int deaths;
	public Clan clan;
	public Date lastPlayed;

	
	
}
