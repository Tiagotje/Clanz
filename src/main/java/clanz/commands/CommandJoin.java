package clanz.commands;

import clanz.Clanz;
import clanz.entity.Clan;
import clanz.entity.ClanPlayer;

public class CommandJoin extends ClanzCommand {

	public final String USAGE = "/c join <clan>";
	public final int MINARGS = 2;
	public final int MAXARGS = 2;
	
	public CommandJoin(ClanPlayer p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(!ArgCheck(this, args.length, MINARGS, MAXARGS, USAGE)) return;
		if(p.clan != null){Error("You must first leave your current clan to join an other"); return;}
		Clan c = clanz.getClanByName(args[1]);
		if(c == null){ Error("Could not find clan " + args[1]); return;}
		if(!c.requireAcceptance){
			p.clan = c;
			c.members.add(p);
			Result("Sucessfully joined the " + c.name + " clan!");
			return;
		}
		c.joinRequests.put(p, System.currentTimeMillis());
		
	}

}
