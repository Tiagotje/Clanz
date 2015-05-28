package clanz;

import org.bukkit.entity.Player;

public class ClanPermissions {

	Clanz clanz;
	
	ClanPermissions(Clanz clanz){
		this.clanz = clanz;
	}
	

	
	ClanRelation getRelation(Clan cl, Player p){
		String uuid = p.getUniqueId().toString();
		if(cl.leaders.contains(uuid)) return ClanRelation.Leader;
		if(cl.assistents.contains(uuid)) return ClanRelation.Assistant;
		if(cl.members.contains(uuid)) return ClanRelation.Member;
		if(!cl.Allies.isEmpty()){
			for(String c: cl.Allies){
				if(clanz.getClanName(c) != null && clanz.getClanName(c).containsPlayer(p.getUniqueId()))
					return ClanRelation.Ally;
			}
		}
		if(!cl.Enemies.isEmpty()){
			for(String c: cl.Enemies){
				if(clanz.getClanName(c) != null && clanz.getClanName(c).containsPlayer(p.getUniqueId()))
					return ClanRelation.Enemy;
			}
		}
		return ClanRelation.Stranger;
		
	}
	
	boolean CheckAccess(Clan cl, Player p, int l){
		return CheckAccess(cl, p, l, false);
	}
	
	boolean CheckAccess(Clan cl, Player p, int l, boolean required){
		ClanRelation r = getRelation(cl,p);
		if(r.i >= l) return true;
		return false;
	}
	
	
	enum ClanRelation{
		Enemy(0, "an enemy"), Stranger(1, "a stranger"),
		Ally(2, "an ally"), Member(3, "a member"),
		Assistant(4, "an assistant"), Leader(5, "a leader");
		int i;
		
		ClanRelation(int i, String name){
			this.i = i;
		}
	}
}
