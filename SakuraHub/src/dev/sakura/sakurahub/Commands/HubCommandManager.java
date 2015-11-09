package dev.sakura.sakurahub.Commands;

import dev.sakura.Commands.CommandManager;
import dev.sakura.sakurahub.Hub.Commands.Command_sethub;

public class HubCommandManager {
	public static HubCommandManager get() {
		return new HubCommandManager();
	}
	
	public HubCommandManager() {
		CommandManager manager = CommandManager.get();
		manager.addCommand(new Command_sethub());
	}
}
