package clanz.commands;

import clanz.Clanz;
import clanz.entity.ClanPlayer;

public class CommandHelp extends ClanzCommand{
	
	public final String USAGE = "/c help";
	public final int MINARGS = 0;
	public final int MAXARGS = 1;
	
	final String[] Page1 = new String[]{"\n",
			"§3_-_-_-_-_-_-_-_-_-_-_Clanz_-_-_-_-_-_-_-_-_-_-_",
            "§b/c list: List all clans",
            "§b/c info c [clan]: Show info on your clan or [clan]",
            "§b/c info p [player]: Shows info on you or [player]",
            "§b/c join <clan>: Join clan <clan> or send an invite",
            "§b/c leave: Leave your current clan",
            "§b/c map: [on/off]: Shows a map or turn it on / off",
            "§b/c new <name>: Create a new clan named <name>",
            "§3-_-_-_-_-_-_-_-_-_-_Page 1/6_-_-_-_-_-_-_-_-_-_-"
    };
	
	final String[] Page2 = new String[]{"\n",
			"§3_-_-_-_-_-_-_-_-_-_-_Clanz_-_-_-_-_-_-_-_-_-_-_",
            "§b/c name <name>: Change the name of your clan",
            "§b/c desc <desc>: Change the description of your clan",
            "§b/c motd <motd>: Change the message of the day",
            "§b/c title <player> <title>: Give a player a title",
            "§b/c invite <true/false>: Toggles if invite is required",
            "§b/c accept <player>: Accept a player join request",
            "§b/c deny <player>: Deny a players join request.",
            "§3-_-_-_-_-_-_-_-_-_-_Page 2/6_-_-_-_-_-_-_-_-_-_-"
    };
	
	final String[] Page3 = new String[]{ "\n",
			"§3_-_-_-_-_-_-_-_-_-_-_Clanz_-_-_-_-_-_-_-_-_-_-_",
            "§b/c sethome: Set the clan's home to your location",
            "§b/c home: Teleport to your clan's home",
            "§b/c promote <player>: Promote a player",
            "§b/c demote <player>: Demote a player",
            "§b/c kick <player>: Kick a player from your clan",
            "§b/c ban <player>:  Ban a player from your clan",
            "§b/c unban <player>: Unban a player from your clan",
            "§3-_-_-_-_-_-_-_-_-_-_Page 3/6_-_-_-_-_-_-_-_-_-_-"
    };
	
	final String[] Page4 = new String[]{ "\n",
			"§3_-_-_-_-_-_-_-_-_-_-_Clanz_-_-_-_-_-_-_-_-_-_-_",
            "§b/c select: Get the area selection tool ",
            "§b/c region <name> add: Add your selection to a region",
            "§b/c region <name> remove: Remove your selection from a region",
            "§b/c region <name> rename <name>: Rename a region",
            "§b/c region <name> settings: Shows advanced region settings",
            "§b/c region <name> del: Delete a region",
            "§3-_-_-_-_-_-_-_-_-_-_Page 4/6_-_-_-_-_-_-_-_-_-_-"
    };
	
	final String[] Page5 = new String[]{ "\n",
			"§3_-_-_-_-_-_-_-_-_-_-_Clanz_-_-_-_-_-_-_-_-_-_-_",
            "§b/c money [clan]: Shows the amount of a clan",
            "§b/c money deposit <amount>: Deposit money to your clan ",
            "§b/c money withdraw <amount>: Withdraw money from your clan",
            "§b/c money transfer <clan>: Transfer money to a clan",
            "§b/c tax: Shows tax info",
            "§b/c tax <amount>: Sets tax amount",
            "§b/c tax help: Shows help on advanced tax settings",
            "§3-_-_-_-_-_-_-_-_-_-_Page 5/6_-_-_-_-_-_-_-_-_-_-"
    };
	
	final String[] Page6 = new String[]{ "\n",
			"§3_-_-_-_-_-_-_-_-_-_-_Clanz_-_-_-_-_-_-_-_-_-_-_",
            "§b/c ally <clan>: Send ally request to a clan",
            "§b/c enemy <clan>: Make a clan your enemy",
            "§b/c war <clan>: Start a war with a clan",
            "§b/c money transfer <clan>: Transfer money to a clan",
            "§b/c tax: Shows tax info",
            "§b/c tax <amount>: Sets tax amount",
            "§3-_-_-_-_-_-_-_-_-_-_Page 6/6_-_-_-_-_-_-_-_-_-_-"
    };
	

	public CommandHelp(ClanPlayer p, String[] args, Clanz clanz){
		super(p, args, clanz);
		Message("§bGo to *SITE* for help on the Clanz plugin");
		
		
	}

}
