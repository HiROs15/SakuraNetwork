package dev.sakura.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dev.sakura.Main;

public class SakuraDB {
	//Setup vars
	String host = "192.243.111.23";
	String username = "HiROs15";
	String password = "dfK2cm";
	String database = "data";
	
	private MySQL db = new MySQL(Main.plugin, host, "3306", database, username, password);
	Connection c = null;
	
	public SakuraDB() {
		try {
			c = db.openConnection();
		} catch(Exception e) {
			System.out.println("ERROR: "+e.getMessage());
		}
	}
	
	public ResultSet query(String q) {
		Statement st;
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(q);
			return rs;
		} catch(Exception e) {}
		return null;
	}
	
	public void update(String q) {
		Statement st;
		try {
			st = c.createStatement();
			st.executeUpdate(q);
		} catch(Exception e) {}
	}
}
