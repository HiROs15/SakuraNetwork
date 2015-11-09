package dev.sakura.sakurahub.Listeners;

import dev.sakura.Listeners.ListenerManager;
import dev.sakura.sakurahub.Hub.Events.HubEvents;

public class HubListenerManager {
	public HubListenerManager() {
		ListenerManager manager = ListenerManager.get();
		manager.addListener(new HubEvents());
	}
}
