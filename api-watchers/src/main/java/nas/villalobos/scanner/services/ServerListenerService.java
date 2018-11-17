package nas.villalobos.scanner.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import brv.tools.listeners.ServerRemovedListener;
import brv.tools.listeners.ServerUpdatedListener;
import brv.tools.model.ScanResult;
import brv.tools.model.dto.WebServer;

@Service
public class ServerListenerService implements ServerRemovedListener, ServerUpdatedListener {
	
	//private Logger logger = Logger.getLogger(WebServerCheckupService.class.getName());
	
	private String getBaseUrl() {
		return "http://localhost:8888";
	}
	
	@Override
	public void serverUpdated(ScanResult server) {
		
		// Se añade o actualiza el servidor Web
		RestTemplate restTemplate = new RestTemplate();
		String url = getBaseUrl() + "/" + server.getIp();
		WebServer object = new WebServer(server.getIp(), server.getHostname(), 80);
		restTemplate.put(url, object);
		
	}

	@Override
	public void serverRemoved(ScanResult server) {
		
		// Se borra el Servidor Web
		RestTemplate restTemplate = new RestTemplate();
		String url = getBaseUrl() + "/" + server.getIp();
		restTemplate.delete(url);
		
	}

	

}
