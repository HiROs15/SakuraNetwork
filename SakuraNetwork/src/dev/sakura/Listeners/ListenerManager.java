package dev.sakura.Listeners;

import java.util.ArrayList;

import dev.sakura.MOTD_Listener;
import dev.sakura.Main;
import dev.sakura.Commands.CommandListener;
import dev.sakura.Hub.Events.HubEvents;
import dev.sakura.Setup.SetupListeners;

public class ListenerManager {
	public static ListenerManager get() {
		return new ListenerManager();
	}
	
	private ArrayList<SakuraListener> listeners = new ArrayList<SakuraListener>();
	
	public ListenerManager() {
		//Add listeners
		listeners.add(new MOTD_Listener());
		listeners.add(new SetupListeners());
		listeners.add(new CommandListener());
		listeners.add(new HubEvents());
		
		for(SakuraListener l : listeners) {
			Main.plugin.getServer().getPluginManager().registerEvents(l.getInstance(), Main.plugin);
		}
	}
}
