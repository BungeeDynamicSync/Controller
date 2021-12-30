package org.skunion.smallru8.BungeeDynamicSync.controller.docker;

import java.util.ArrayList;

import org.json.JSONObject;
import org.skunion.smallru8.BungeeDynamicSync.controller.config.PortainerConfig;

//TODO
public class MainController {

	private ArrayList<EndPointController> epcs = new ArrayList<EndPointController>();
	private ArrayList<PortainerAuth> pas = new ArrayList<PortainerAuth>();
	private PortainerConfig pc = new PortainerConfig();
	
	public MainController() {
		//Load PortainerAuth
		for(int i=0;i<pc.getPortainerCount();i++) {
			PortainerAuth pa = new PortainerAuth(pc.getIp(i),pc.getPort(i),pc.getUsername(i),pc.getPasswd(i));
			pas.add(pa);
			//Load Endpoints
			for(int j=0;j<pc.getEndPointCount(i);j++) {
				JSONObject endpointInfo = new JSONObject(pa.getEndPointInfo(j));
				String URL_ip = endpointInfo.getString("URL");
				EndPointController epc;
				if(URL_ip.startsWith("tcp://")) {//endpoint is remote docker engine
					URL_ip = URL_ip.replace("tcp://", "").replace("/", "");
					URL_ip = URL_ip.split(":")[0];
					epc = new EndPointController(pa,pc.getEndPointId(i, j),pc.getEndPointMaxContainer(i, j),URL_ip);
				}else {//endpoint is local docker engine, ip same as portainer 
					epc = new EndPointController(pa,pc.getEndPointId(i, j),pc.getEndPointMaxContainer(i, j));
				}
				epcs.add(epc);
			}
		}
		
		
		
	}
	
}
