package clanz.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import clanz.Clanz;
import clanz.entity.Clan;
import clanz.entity.ClanPlayer;

public class CommandInfo extends ClanzCommand{

	public final String USAGE = "/c info <p/c> [player/clan]";
	public final int MINARGS = 2;
	public final int MAXARGS = 3;
	
	public CommandInfo(ClanPlayer p, String[] args, Clanz clanz) {
		super(p, args, clanz);
		if(!ArgCheck(this, args.length, MINARGS, MAXARGS, USAGE)) return;
		if(args[1].equalsIgnoreCase("p") || args[1].equalsIgnoreCase("player")){
			ClanPlayer info;
			if(args.length == 2){ info = p; }
			else info = clanz.getClanPlayer(args[2]);
			if(info == null){ Error("Player not found"); return;}
			String LastOnline = "Now";
			if(!info.p.isOnline()){ LastOnline = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(info.p.getLastPlayed()));}
			Message(new String[]{ 
				  "\n§3-----------------------",
					"§bName: " + info.p.getPlayer().getName(),
					"§bClan: " + (info.clan == null ? "none" : info.clan.name),
					"§bKills: "+ info.kills,
					"§bDeaths: "+ info.deaths,
					"§bLast Online: " + LastOnline,
					"§3-----------------------"
								
			});
			return;
		}
		if(args[1].equals("c") || args[1].equals("clan")){
			Clan info;
			if(args.length == 2){
					info = clanz.getClanPlayer(p.p.getUniqueId()).clan; 
					if(info==null){Error("You aren't in a clan"); return;}
			}
			else{	info = clanz.getClanByName(args[2]);
					if(info == null){ Error("Could not find clan"); return;}
			}
			String Motd = "§cSecret";
			if(p.clan == info || info.Allies.contains(p.clan)){
				Motd = info.motd;
			}
			Message(new String[]{
				"\n§3------------------",
				  "§bName: " + info.name,
				  "§bDesc: " + info.description,
				  "§bTag: §f[" + info.tag + "§f]",
				  "§bMotd: " + Motd,
				  "§bMembers: " + CPListToString(info.members),
				  "§bAssistants: " + CPListToString(info.assistants),
				  "§bLeaders: " + CPListToString(info.leaders),
				  "§bAllies: §a" + ClanListToString(info.Allies),
				  "§bEnemies: §c" + ClanListToString(info.Enemies),
				  "§3------------------"
			});
			return;
		}
		Error("Usage: " + USAGE);
	}
	
	private String ClanListToString(List <Clan> l){
		String s = "";
		if(l.size() == 0) return "none";
		for(Clan c: l){
			s+= c.name + " ";
		}
		s = s.substring(0, s.length()-1);
		return s;
	}
	
	private String CPListToString(List<ClanPlayer> l){
		String s = "[";
		if(l.size() == 0) return "none";
		for(ClanPlayer cp: l){
			s+=cp.p.getName() + " ";
		}
		s = s.substring(0, s.length()-1);
		s += "]";
		return s;
	}

}
