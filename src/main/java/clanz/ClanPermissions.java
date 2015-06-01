package clanz;

import org.bukkit.entity.Player;

import clanz.entity.Clan;
import clanz.entity.ClanPlayer;

public class ClanPermissions {

	Clanz clanz;
	
	ClanPermissions(Clanz clanz){
		this.clanz = clanz;
	}
	

	//Calculates the relation between a player and a clan
	ClanRelation getRelation(Clan cl, ClanPlayer p){
		if(cl.leaders.contains(p)) return ClanRelation.Leader;
		if(cl.assistants.contains(p)) return ClanRelation.Assistant;
		if(cl.members.contains(p)) return ClanRelation.Member;
		if(!cl.Allies.isEmpty()){
			for(String c: cl.Allies){
				if(clanz.getClanByName(c) != null && c.equals(p.clan));
					return ClanRelation.Ally;
				}
		}if(!cl.Enemies.isEmpty()){
			for(String c: cl.Enemies){
				if(clanz.getClanByName(c) != null && c.equals(p.clan));
					return ClanRelation.Enemy;
				}
		}return ClanRelation.Stranger;
		
	}
	
	
	//Checks if a player has the required clan permission
	boolean CheckAccess(Clan cl, ClanPlayer p, int l){
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
