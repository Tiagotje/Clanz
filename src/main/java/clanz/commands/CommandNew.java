package clanz.commands;

import org.bukkit.entity.Player;

import clanz.*;

public class CommandNew extends ClanzCommand{

	public CommandNew(Player p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(args.length == 1){  NotEnoughArguments("/c new <name>"); return;}
		if(args.length > 2){ TooManyArguments("/c new <name>"); return; }
		if(args.length == 2){
			if(clanz.getPlayerClan(p.getUniqueId()) != null){
				Error("You must first leave your current clan to make an other");
				return;
			}
			for(Clan c: clanz.Clans){
				if(c.name.equalsIgnoreCase(args[1])){
					Error("There already exists a clan with that name!");
					return;
				}
			}
			if(args[1].length() < 4){ Error("Name too short!"); return;}
			if(args[1].length() > 10){ Error("Name too long!"); return;}
			clanz.Clans.add(new Clan(clanz, args[1], p.getUniqueId()));
			Result("Successfully created the " + args[1] + " clan!");
			Result("See *SITE* for further instructions!");
				
				
		}
	}

}
