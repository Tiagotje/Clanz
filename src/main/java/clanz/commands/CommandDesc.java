package clanz.commands;

import java.util.Arrays;

import clanz.Clanz;
import clanz.entity.ClanPlayer;

public class CommandDesc extends ClanzCommand{

	public final String USAGE = "/c desc <description>";
	public final int MINARGS = 2;
	public final int MAXARGS = 50;
	
	public CommandDesc(ClanPlayer p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(!ArgCheck(this, args.length, MINARGS, MAXARGS, USAGE)) return;
		if(p.clan == null){ Error("You must be in a clan"); return;}
		String[] descArray = Arrays.copyOfRange(args, 1, args.length);
		String desc = Arrays.toString(descArray);
		desc = desc.substring(1, desc.length() -1);
		desc = desc.replaceAll(", ", " ");
		p.clan.description = desc;
		Result("Description successfully changed");
		
	}

}
