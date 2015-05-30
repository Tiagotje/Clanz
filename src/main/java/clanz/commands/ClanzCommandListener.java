package clanz.commands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import clanz.Clanz;

public class ClanzCommandListener implements Listener {

	Clanz clanz;
	
	public ClanzCommandListener(Clanz clanz) {
		this.clanz = clanz;
	}
	
	@EventHandler
	public void onCommandEvent(ClanzCommandEvent e){
		switch(e.args[0]){
		case "new": new CommandNew(e.sender, e.args, this);
		}
	}

}
