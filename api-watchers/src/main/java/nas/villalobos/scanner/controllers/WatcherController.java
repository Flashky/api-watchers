package nas.villalobos.scanner.controllers;

import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import brv.tools.daemons.exceptions.ScanDaemonNotFoundException;
import brv.tools.model.dto.ServerWatcher;
import nas.villalobos.scanner.services.WatcherService;

@RestController
@RequestMapping("/watchers")
public class WatcherController {

	@Autowired
	private WatcherService watcherService;
	
	@PostMapping("/")
	public ResponseEntity<Void> create(@RequestBody ServerWatcher watcher)  {
		final HttpHeaders headers = new HttpHeaders();
		long watcherId;
		HttpStatus status;
		try {
			watcherId = watcherService.create(watcher);
			final URI location = ServletUriComponentsBuilder
								.fromCurrentServletMapping().path("/watchers/{id}").build()
								.expand(watcherId).toUri();
	
			
		  	headers.setLocation(location);
		  	status = HttpStatus.CREATED;
		} catch (UnknownHostException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
	  	return new ResponseEntity<>(headers, status);
		  
	}
	
	@DeleteMapping("/{id}/")
	public ResponseEntity<Void> remove(@PathVariable Integer id) {
		
		HttpStatus status;
		try {
			watcherService.remove(id);
			status = HttpStatus.NO_CONTENT;
		} catch (ScanDaemonNotFoundException e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(status);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<Void> remove() {
		
		HttpStatus status;
		
		watcherService.remove();
		status = HttpStatus.NO_CONTENT;
		
		return new ResponseEntity<>(status);
	}
	
	@GetMapping("/{id}/")
	public ResponseEntity<ServerWatcher> findWatcher(@PathVariable Integer id) {
		ResponseEntity<ServerWatcher> result;
		ServerWatcher watcher = watcherService.getWatcher(id);
		
		if(watcher != null)
			result = new ResponseEntity<>(watcher, HttpStatus.OK);
		else
			result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return result;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ServerWatcher>> findWatchers() {
		
		List<ServerWatcher> watchers = watcherService.getWatchers();
		return new ResponseEntity<>(watchers, HttpStatus.OK);
	}
	

}
