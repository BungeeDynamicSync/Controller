package org.skunion.smallru8.BungeeDynamicSync.controller.util;

import org.skunion.smallru8.BungeeDynamicSync.controller.Controller;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class RedisUtil extends JedisPubSub{

	private static JedisPool REDIS_POOL;
	private static Jedis JEDIS;
	
	private Thread t;
	private JedisPubSub jedispubsub;
	
	public RedisUtil() {
		jedispubsub = this;
		REDIS_POOL = new JedisPool(Controller.REDIS_SETTING.getIP(), Controller.REDIS_SETTING.getPort());
		JEDIS = REDIS_POOL.getResource();
		JEDIS.auth(Controller.REDIS_SETTING.getPasswd());
    }
	
	public void start() {
		t = new Thread() {
			@Override
			public void run() {
				JEDIS.subscribe(jedispubsub, Controller.PUB_SUB_CHANNEL);
			}
		};
		t.start();
	}
	
	@Override
    public void onMessage(String channel, String message) {
		if(channel.equalsIgnoreCase(Controller.PUB_SUB_CHANNEL)) {
			String[] cmd = message.split(" ");
			int len = cmd.length;
			//TODO
		}
    }
	
	/**
	 * Add a new dynamic server(broadcast by master controller):| SERVER |ADD|<CONTAINER_NAME>| ip | port | motd |
	 * @param containerName
	 * @param ip
	 * @param port
	 * @param motd_type
	 */
	public void sendServerAddMassage(String containerName,String ip,String port,String motd_type) {
		publish("SERVER ADD "+containerName+" "+ip+" "+port+" "+motd_type);
	}
	
	public void publish(String message) {
		JEDIS.publish(Controller.PUB_SUB_CHANNEL,message);
	}
}
