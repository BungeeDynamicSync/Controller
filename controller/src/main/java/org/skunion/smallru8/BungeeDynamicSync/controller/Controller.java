package org.skunion.smallru8.BungeeDynamicSync.controller;

import java.io.File;

import org.skunion.smallru8.BungeeDynamicSync.controller.config.RedisConfig;
import org.skunion.smallru8.BungeeDynamicSync.controller.config.ServerConfig;
import org.skunion.smallru8.BungeeDynamicSync.controller.docker.MainController;

public class Controller {
	
	public static ServerConfig SERVER_CONFIG;
	public static RedisConfig REDIS_SETTING;
	public static MainController MAIN_CONTROLLER;
	
	public static void main( String[] args )
    {
		checkDir();
		SERVER_CONFIG = new ServerConfig();
		REDIS_SETTING = new RedisConfig();
		MAIN_CONTROLLER = new MainController();
    }
	
	private static void checkDir() {
		File f = new File("config");
		if(!f.exists())
			f.mkdir();
		f = new File(f,"containerScript");
		if(!f.exists())
			f.mkdir();
	}
}
