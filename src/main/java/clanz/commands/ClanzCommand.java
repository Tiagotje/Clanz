package clanz.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import clanz.Clanz;
import clanz.entity.ClanPlayer;

public class ClanzCommand {

	ClanPlayer p;
	String[] args;
	Clanz clanz;
	
	public ClanzCommand(ClanPlayer p, String[] args, Clanz clanz){
		this.p = p; this.args = args; this.clanz = clanz;
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
