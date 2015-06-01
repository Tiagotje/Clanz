package clanz.commands;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;

import clanz.Clanz;
import clanz.entity.Clan;
import clanz.entity.ClanPlayer;

public class CommandInfo extends ClanzCommand{

	public CommandInfo(ClanPlayer p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(args.length < 2){ NotEnoughArguments("/c info <p/c> [player / clan]"); return;}
		if(args.length > 3){ TooManyArguments("/c info <p/c> [player / clan]"); return;}
		if(args[1].equalsIgnoreCase("p") || args[1].equalsIgnoreCase("player")){
			ClanPlayer info;
			if(args.length == 2){ info = p; }
			else info = clanz.getClanPlayer(args[2]);
			if(info == null){ Error("Player not found");}
			Message(new String[]{ 
				  "\n§3-----------------------",
					"§bName: " + info.p.getPlayer().getDisplayName(),
					"§bClan: " + info.clan.name,
					"§bKills: "+ info.kills,
					"§bDeaths: "+ info.deaths,
					"§bLast Date Online" +  info.p.getPlayer() != null ? "Now" :
						new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date(info.p.getLastPlayed())),
					"§3-----------------------"
								
			});
		}
	}

}
