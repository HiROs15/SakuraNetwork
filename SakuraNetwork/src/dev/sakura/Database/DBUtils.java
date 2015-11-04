package dev.sakura.Database;

import java.sql.ResultSet;

public class DBUtils {
	public static DBUtils get() {
		return new DBUtils();
	}
	
	public int getRows(ResultSet rs) {
		int size = 0;
		if(rs != null) {
			try {
				rs.beforeFirst();
				rs.last();
				size = rs.getRow();
			} catch(Exception e) {}
		}
		return size;
	}
}
