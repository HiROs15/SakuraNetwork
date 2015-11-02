package dev.sakura.Config;

public abstract class BaseConfigFile {
	private String name;
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract void setupConfig();
}
