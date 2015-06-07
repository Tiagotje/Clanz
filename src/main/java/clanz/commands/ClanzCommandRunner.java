package clanz.commands;

import clanz.Clanz;
import clanz.entity.ClanPlayer;

public class ClanzCommandRunner{


	
	public ClanzCommandRunner(Clanz clanz, ClanPlayer p, String[] args) {
		if(args.length == 0){ new CommandHelp(p, args, clanz); return;}
		switch(args[0].toLowerCase()){
		case "delete":
		case "disband": new CommandDisband(p, args, clanz); break;
		case "leave":
		case "l": new CommandLeave(p, args, clanz); break;
		case "enter":
		case "j":
		case "join": new CommandJoin(p, args, clanz); break;
		case "tag": new CommandTag(p, args, clanz); break;
		case "desc":
		case "description": new CommandDesc(p, args, clanz); break;
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
