package clanz.commands;

import clanz.Clanz;
import clanz.entity.Clan;
import clanz.entity.ClanPlayer;

public class CommandTag extends ClanzCommand {

	public CommandTag(ClanPlayer p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(args.length == 1){ NotEnoughArguments("/clanz tag <tag>"); return;}
		if(args.length > 2){ TooManyArguments("/clanz tag <tag>"); return;}
		if(p.clan == null){ Error("You must be in a clan to change it's tag"); return;}
		Clan c = p.clan;
		c.tag = args[1];
		Result("Tag sucessfully changed to " + args[1]);
	}

}
