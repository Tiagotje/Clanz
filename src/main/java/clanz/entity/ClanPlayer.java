package clanz.entity;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import clanz.Clanz;

public class ClanPlayer {

	public ClanPlayer(YamlConfiguration yc, Clanz clanz) {
		System.out.println("ADDING NEW CLANPLAYER WITH UUID " + yc.getString("UUID"));
		p = clanz.getServer().getOfflinePlayer(UUID.fromString(yc.getString("UUID")));
		uuid = UUID.fromString(yc.getString("UUID"));
		kills = yc.getInt("kills");
		deaths = yc.getInt("deaths");
		clan = clanz.getClanByName(yc.getString("clan"));
	}
	
	public ClanPlayer(Player player) {
		p = player;
		uuid = p.getUniqueId();
		kills = 0;
		deaths = 0;
		clan = null;
	}

	public OfflinePlayer p;
	public UUID uuid;
	public int kills;
	public int deaths;
	public Clan clan;
	
	
	public void saveToConfig(YamlConfiguration yc) {
		String clanUUID = "";
		if(clan != null && clan.uuid != null){ clanUUID = clan.uuid.toString();}
		yc.set("UUID", uuid.toString());
		yc.set("kills", kills);
		yc.set("deaths", deaths);
		yc.set("clan", clanUUID);
		
	}

	
	
}
