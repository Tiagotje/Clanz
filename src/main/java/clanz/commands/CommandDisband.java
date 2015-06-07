package clanz.commands;

import clanz.Clanz;
import clanz.entity.Clan;
import clanz.entity.ClanPlayer;
import clanz.entity.ClanRelation;

public class CommandDisband extends ClanzCommand{

	public final String USAGE = "/c join <clan>";
	public final int MINARGS = 1;
	public final int MAXARGS = 2;
	
	public CommandDisband(ClanPlayer p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(!ArgCheck(this, args.length, MINARGS, MAXARGS, USAGE)) return;
		Clan c = p.clan;
		if(!c.checkRelation(p, ClanRelation.LEADER)){ Error("You must be a leader to disband a clan"); return;}
		if(args.length == 1){
			Message("§bAre you sure you want to disband your clan?");
			Message("§bType §c\"/c disband confirm\" §bto disband it.");
			return;
		}
		if(args[1].equals("confirm")){
			for(ClanPlayer cp: c.getPlayerList()){
				cp.clan = null;
			}
			clanz.Clans.remove(c);
			clanz.ClanzFile.set(c.uuid.toString(), null);
			Result("Sucessfully disbanded the " + c.name + " clan!");
			return;
		}
		Error("Usage: /c disband");
		
	}

}
