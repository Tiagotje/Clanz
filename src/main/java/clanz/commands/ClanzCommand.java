package clanz.commands;

import clanz.Clanz;
import clanz.entity.ClanPlayer;

public abstract class ClanzCommand {
	
	ClanPlayer p;
	String[] args;
	Clanz clanz;
	
	public ClanzCommand(ClanPlayer p, String[] args, Clanz clanz){
		this.p = p; this.args = args; this.clanz = clanz;
	}
	
	//Use if(!ArgCheck(this, args.length, MINARGS, MAXARGS, USAGE)) return;
	public boolean ArgCheck(ClanzCommand cc, int args, int min, int max, String usage){
		if(args < min){ NotEnoughArguments(usage); return false;}
		if(args > max){ TooManyArguments(usage); return false;}
		return true;
	}
	
	public void Error(String message){
		p.p.getPlayer().sendMessage("§c" + message);
	}
	
	public void Result(String message){
		p.p.getPlayer().sendMessage("§a" + message);
	}
	
	public void Message(String[] strings){
		p.p.getPlayer().sendMessage(strings);
	}
	
	public void Message(String message){
		p.p.getPlayer().sendMessage(message);
	}
	
	public void NotEnoughArguments(String usage){
		Error("Not enough arguments. Usage: " + usage);
	}
	
	public void TooManyArguments(String usage){
		Error("Too many arguments. Usage: " + usage);
	}
	
	
}
