package dev.sakura.sakurahub.Libs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class WorldHandlers {
	public static WorldHandlers get() {
		return new WorldHandlers();
	}
	
	public boolean deleteWorld(File file) {
		   if (file.exists() && !file.getName().equals("session.lock")) {
		        File files[] = file.listFiles();
		        for (int i = 0; i < files.length; i++) {
		            if (files[i].isDirectory()) {
		                deleteWorld(files[i]);
		            } else {
		                files[i].delete();
		            }
		        }
		    }
		    return (file.delete());
	}
	
	public void copyWorld(File source, File target) {
	    try {
	        ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
	        if (!ignore.contains(source.getName())) {
	            if (source.isDirectory()) {
	                if (!target.exists()) target.mkdirs();
	                String files[] = source.list();
	                for (String file : files) {
	                    File srcFile = new File(source, file);
	                    File destFile = new File(target, file);
	                    copyWorld(srcFile, destFile);
	                }
	            } else {
	                InputStream in = new FileInputStream(source);
	                OutputStream out = new FileOutputStream(target);
	                byte[] buffer = new byte[1024];
	                int length;
	                while ((length = in.read(buffer)) > 0)
	                    out.write(buffer, 0, length);
	                in.close();
	                out.close();
	            }
	        }
	    } catch (IOException e) {

	    }
	}
}
