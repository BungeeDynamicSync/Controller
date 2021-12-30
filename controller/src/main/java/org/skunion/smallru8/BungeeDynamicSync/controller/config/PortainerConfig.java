package org.skunion.smallru8.BungeeDynamicSync.controller.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.skunion.smallru8.BungeeDynamicSync.controller.util.ReadFile;

public class PortainerConfig {

private JSONArray config;
	
	private File path;
	
	public PortainerConfig() {
		path = new File("config/portainer.json");
		if(!path.exists()) {
			InputStream is = getClass().getClassLoader().getResourceAsStream("portainer.json");
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
		}else {
			config = new JSONArray(ReadFile.READ_FILE(path));
		}
	}
	
	public int getPortainerCount() {
		return config.length();
	}
	
	public JSONArray getEndpoints(int portainer_index) {
		return config.getJSONObject(portainer_index).getJSONArray("endpoints");
	}
	
	public String getIp(int portainer_index) {
		return config.getJSONObject(portainer_index).getString("ip");
	}
	
	public int getPort(int portainer_index) {
		return config.getJSONObject(portainer_index).getInt("port");
	}
	
	public String getUsername(int portainer_index) {
		return config.getJSONObject(portainer_index).getString("username");
	}
	
	public String getPasswd(int portainer_index) {
		return config.getJSONObject(portainer_index).getString("passwd");
	}
	
	public int getEndPointCount(int portainer_index) {
		return config.getJSONObject(portainer_index).getJSONArray("endpoints").length();
	}
	
	public int getEndPointId(int portainer_index,int endpoint_index) {
		return config.getJSONObject(portainer_index).getJSONArray("endpoints").getJSONObject(endpoint_index).getInt("id");
	}
	
	public int getEndPointMaxContainer(int portainer_index,int endpoint_index) {
		return config.getJSONObject(portainer_index).getJSONArray("endpoints").getJSONObject(endpoint_index).getInt("maxContainer");
	}
}
