package org.skunion.smallru8.BungeeDynamicSync.controller;

import java.io.File;

import org.skunion.smallru8.BungeeDynamicSync.controller.config.RedisConfig;
import org.skunion.smallru8.BungeeDynamicSync.controller.config.ServerConfig;
import org.skunion.smallru8.BungeeDynamicSync.controller.docker.MainController;
import org.skunion.smallru8.BungeeDynamicSync.controller.util.RedisUtil;

public class Controller {
	
	public static final String PUB_SUB_CHANNEL = "BDS_MESSAGE";
	
	public static ServerConfig SERVER_CONFIG;
	public static RedisConfig REDIS_SETTING;
	public static MainController MAIN_CONTROLLER;
	public static RedisUtil REDISUTIL;

	
	public static void main( String[] args )
    {
		checkDir();
		SERVER_CONFIG = new ServerConfig();
		REDIS_SETTING = new RedisConfig();
		REDISUTIL = new RedisUtil();
		MAIN_CONTROLLER = new MainController();
		REDISUTIL.start();
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
