package clanz.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import clanz.Clanz;
import clanz.entity.ClanPlayer;

public class ClanzCommandRunner{


	
	public ClanzCommandRunner(Clanz clanz, ClanPlayer p, String[] args) {
		clanz.getServer().broadcastMessage("§bTEST");
		if(args.length == 0){ new CommandHelp(p, args, clanz); return;}
		switch(args[0]){
		case "?":
		case "help": new CommandHelp(p, args, clanz); break;
		case "create":
		case "new": new CommandNew(p, args, clanz); break;
		case "changename":
		case "name": new CommandName(p, args, clanz); break;
		case "info": new CommandInfo(p, args, clanz); break;
		default: {p.p.getPlayer().sendMessage("§cCommand not found. Type /c for help");}
		}
	}
	

}
