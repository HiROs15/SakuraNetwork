package dev.sakura;

import dev.sakura.Database.SakuraDB;
import dev.sakura.Hub.HubManager;

public class Managers {
	public static Managers get() {
		return new Managers();
	}
	
	public Managers() {
		new HubManager();
		
		//Internal Managers
		this.setupStaticManagers();
	}
	
	//Declare Managers
	public static SakuraDB sakuraDB;
	
	private void setupStaticManagers() {
		sakuraDB = new SakuraDB();
	}
}
