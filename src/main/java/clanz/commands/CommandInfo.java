package clanz.commands;

import org.bukkit.entity.Player;

import clanz.Clan;
import clanz.Clanz;

public class CommandInfo extends ClanzCommand{

	public CommandInfo(Player p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(args.length < 2){ NotEnoughArguments("/c info <p/c> [player / clan]"); return;}
		if(args.length > 3){ TooManyArguments("/c info <p/c> [player / clan]"); return;}
		if(args[1].equalsIgnoreCase("p") || args[1].equalsIgnoreCase("player")){
			Player info;
			if(args.length == 2){ info = p; }
			else{ info = clanz.getServer().getPlayer(args[1]);}
			if(info == null){ Error("Player not found");}
			Clan ic = clanz.getPlayerClan(p.getUniqueId());
			Message(new String[]{
				  "\n§3-----------------------",
					"§bName: " + info.getDisplayName(),
					"§bClan: " + ic != null ? ic.name : "None",
					"§bKills: " +
			});
		}
	}

}
