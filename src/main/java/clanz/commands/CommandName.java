package clanz.commands;

import clanz.Clanz;
import clanz.entity.Clan;
import clanz.entity.ClanPlayer;

public class CommandName extends ClanzCommand{
	
	public final String USAGE = "/c name <name>";
	public final int MINARGS = 2;
	public final int MAXARGS = 2;

	public CommandName(ClanPlayer p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(!ArgCheck(this, args.length, MINARGS, MAXARGS, USAGE)) return;
		if(p.clan == null){
			Error("You must be in a clan to change it's name"); return;
		}
		for(Clan c: clanz.Clans){
			if(c.name.equalsIgnoreCase(args[1])){ Error("Name already used"); return;}
		}
		if(args[1].length() < 4){ Error("Name too short!"); return;}
		if(args[1].length() > 10){ Error("Name too long!"); return;}
		p.clan.name = args[1];
		Result("Name successfully changed to " + args[1]);
	}

}
