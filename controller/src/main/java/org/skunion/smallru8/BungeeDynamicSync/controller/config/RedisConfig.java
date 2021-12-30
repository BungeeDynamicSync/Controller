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

public class RedisConfig {

	private String ip;
	private int port = 6379;
	private String passwd;
	
	private File path;
	
	public RedisConfig() {
		path = new File("config/dynamicServer.json");
		if(!path.exists()) {
			InputStream is = getClass().getClassLoader().getResourceAsStream("redis.json");
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
		JSONObject config = new JSONObject(ReadFile.READ_FILE(path));
		ip = config.getString("ip");
		port = config.getInt("port");
		passwd = config.getString("passwd");
	}
	
	public String getIP() {
		return ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getPasswd() {
		return passwd;
	}
}
