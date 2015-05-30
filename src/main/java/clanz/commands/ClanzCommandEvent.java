package clanz.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ClanzCommandEvent extends Event{
    private static final HandlerList handlers = new HandlerList();
 
    CommandSender sender;
    String[] args;
    
    public ClanzCommandEvent(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }

    
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
