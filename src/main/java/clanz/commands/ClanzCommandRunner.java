package clanz.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import clanz.Clanz;

public class ClanzCommandRunner{


	
	public ClanzCommandRunner(Clanz clanz, Player p, String[] args) {
		if(args.length == 0){ new CommandHelp(p, args, clanz); return;}
		switch(args[0]){
		case "?":
		case "help": new CommandHelp(p, args, clanz); break;
		case "create":
		case "new": new CommandNew(p, args, clanz); break;
		case "changename":
		case "name": new CommandName(p, args, clanz); break;
		default: {p.sendMessage("§cCommand not found. Type /c for help");}
		}
	}
	

}
