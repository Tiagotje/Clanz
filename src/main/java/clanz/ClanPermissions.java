package clanz;

import org.bukkit.entity.Player;

public class ClanPermissions {

	Clanz clanz;
	
	ClanPermissions(Clanz clanz){
		this.clanz = clanz;
	}
	

	//Calculates the relation between a player and a clan
	ClanRelation getRelation(Clan cl, Player p){
		String uuid = p.getUniqueId().toString();
		if(cl.leaders.contains(uuid)) return ClanRelation.Leader;
		if(cl.assistants.contains(uuid)) return ClanRelation.Assistant;
		if(cl.members.contains(uuid)) return ClanRelation.Member;
		if(!cl.Allies.isEmpty()){
			for(String c: cl.Allies){
				if(clanz.getClanByName(c) != null && c.equals(clanz.getPlayerClan(p.getUniqueId())));
					return ClanRelation.Ally;
				}
		}if(!cl.Enemies.isEmpty()){
			for(String c: cl.Enemies){
				if(clanz.getClanByName(c) != null && c.equals(clanz.getPlayerClan(p.getUniqueId())));
					return ClanRelation.Enemy;
				}
		}return ClanRelation.Stranger;
		
	}
	
	
	//Checks if a player has the required clan permission
	boolean CheckAccess(Clan cl, Player p, int l){
		ClanRelation r = getRelation(cl,p);
		if(r.i >= l) return true;
		return false;
	}

	
	//Possible Clan relations 
	enum ClanRelation{
		Enemy(0), Stranger(1),
		Ally(2), Member(3),
		Assistant(4), Leader(5);
		int i;
		
		ClanRelation(int i){
			this.i = i;
		}
	}
}
