package org.skunion.smallru8.BungeeDynamicSync.controller.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.skunion.smallru8.BungeeDynamicSync.controller.util.ReadFile;

public class ServerConfig {
	
	private JSONObject config;
	
	private File path;
	
	public ServerConfig() {
		path = new File("config/dynamicServer.json");
		if(!path.exists()) {
			InputStream is = getClass().getClassLoader().getResourceAsStream("dynamicServer.json");
			InputStreamReader sr = new InputStreamReader(is, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(sr);
			FileWriter fw;
			try {
				fw = new FileWriter(path);
				String line = "";
				while((line = br.readLine())!=null) {
					fw.write(line+"\n");
				}
				fw.flush();
				fw.close();
				br.close();
				sr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = new JSONObject(ReadFile.READ_FILE(path));
	}
	
	public int getServerTypeCount() {
		return config.length();
	}
	
	public JSONObject getServerSetting(String type) {
		return config.getJSONObject(type);
	}
	
	public int getMinRoom(String type) {
		return config.getJSONObject(type).getInt("minRoom");
	}
	
	public int getMaxRoom(String type) {
		return config.getJSONObject(type).getInt("maxRoom");
	}
	
	public String getContainerCreateScript(String type) {
		return config.getJSONObject(type).getString("ContainerCreateScript");
	}
	
}
