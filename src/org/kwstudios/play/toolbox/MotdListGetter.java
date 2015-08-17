package org.kwstudios.play.toolbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.kwstudios.play.loader.PluginLoader;

public class MotdListGetter {
	
	private static List<String> motdList = new ArrayList<String>();
	
	public static void getMotdsFromFile(){
		File motdsFile = new File(PluginLoader.getInstance().getDataFolder().getPath() + "/messages/motds.kw");
		try(BufferedReader br = new BufferedReader(new FileReader(motdsFile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        // process the line.
		    	motdList.add(line.trim());
		    }
		}catch(Exception e){
			motdList = null;
			System.out.println("No motds.kw File was created yet. Skipping that...");
		}
	}

	public static List<String> getMotdList() {
		return motdList;
	}

}
