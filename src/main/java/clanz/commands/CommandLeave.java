package clanz.commands;

import clanz.Clanz;
import clanz.entity.Clan;
import clanz.entity.ClanPlayer;
import clanz.entity.ClanRelation;

public class CommandLeave extends ClanzCommand{

	private final int MAXARGS = 1;
	private final String USAGE = "/c leave";
	private final int MINARGS = 1;

	public CommandLeave(ClanPlayer p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(!ArgCheck(this, args.length, MINARGS, MAXARGS, USAGE)) return;
		Clan c = p.clan;
		if(c.getPlayerList().size() == 1){
			Error("Type /c disband to disband a clan");
			return;
		}
		if(c.leaders.size() == 1 && c.checkRelation(p, ClanRelation.LEADER)){
			Error("You must promote someone else to leader to leave.");
			return;
		}
		//Remove clanplayer
		if(c.leaders.contains(p)){ c.leaders.remove(p); }
		if(c.assistants.contains(p)){ c.assistants.remove(p); }
		if(c.members.contains(p)){ c.members.remove(p); }
		p.clan = null;
	}

}
