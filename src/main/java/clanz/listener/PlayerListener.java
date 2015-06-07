package clanz.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import clanz.Clanz;
import clanz.entity.ClanPlayer;

public class PlayerListener implements Listener{
	
	Clanz clanz;
	
	public PlayerListener(Clanz c){
		clanz = c;
	}
	
	
	@EventHandler
	public void newClanPlayerLogin(PlayerLoginEvent e){
		if(clanz.getClanPlayer(e.getPlayer().getUniqueId()) == null){
			clanz.ClanPlayers.add(new ClanPlayer(e.getPlayer()));
			System.out.println("Adding New Player WITH UUID: " + e.getPlayer().getUniqueId().toString());
		}
	}
	
	@EventHandler
	public void playerKillEvent(PlayerDeathEvent e){
		ClanPlayer victim = clanz.getClanPlayer(e.getEntity().getUniqueId());
		victim.deaths += 1;
		if(e.getEntity().getKiller() != null){
			ClanPlayer killer = clanz.getClanPlayer(e.getEntity().getKiller().getUniqueId());
			killer.kills += 1;
		}
	}
	

	
}
