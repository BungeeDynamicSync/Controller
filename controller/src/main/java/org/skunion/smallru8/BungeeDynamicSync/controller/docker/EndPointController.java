package org.skunion.smallru8.BungeeDynamicSync.controller.docker;

import java.util.Random;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
public class EndPointController {

	private PortainerAuth portainerAuth;
	private boolean LOCK = false;
	private HttpClient httpclient;
	private Random random = new Random();
	private int endPointId;
	private int maxContainer;
	private String endPointHostIP;
	
	public EndPointController(PortainerAuth portainerAuth,int endpointId,int max,String ip) {
		this.portainerAuth = portainerAuth;
		httpclient = HttpClientBuilder.create().build();
		endPointId = endpointId;
		maxContainer = max;
		endPointHostIP = ip;
	}
	public EndPointController(PortainerAuth portainerAuth,int endpointId,int max) {
		this.portainerAuth = portainerAuth;
		httpclient = HttpClientBuilder.create().build();
		endPointId = endpointId;
		maxContainer = max;
		endPointHostIP = portainerAuth.getIP();
	}
}
