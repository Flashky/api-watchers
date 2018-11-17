package nas.villalobos.scanner.controllers;

import java.rmi.NoSuchObjectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nas.villalobos.scanner.services.WatcherActionService;

@RestController
@RequestMapping("/watchers")
public class WatcherActionController {
	
	@Autowired
	private WatcherActionService watcherActionService;

	
	@PutMapping("/{id}/resume/")
	public ResponseEntity<Void> resume(@PathVariable Integer id) {
		
		HttpStatus status;
		try {
			if(watcherActionService.resume(id))
				status = HttpStatus.NO_CONTENT;
			else
				status = HttpStatus.ACCEPTED;
		} catch (NoSuchObjectException e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(status);
		
	}
	
	@PutMapping("/{id}/stop/")
	public ResponseEntity<Void> stop(@PathVariable Integer id) {
		
		HttpStatus status;
		try {
			if(watcherActionService.stop(id))
				status = HttpStatus.NO_CONTENT;
			else
				status = HttpStatus.ACCEPTED;
		} catch (NoSuchObjectException e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(status);
	}
	
	
	@PutMapping("/{id}/force-stop/")
	public ResponseEntity<Void> interrupt(@PathVariable Integer id) {
		
		HttpStatus status;
		try {
			if(watcherActionService.interrupt(id))
				status = HttpStatus.NO_CONTENT;
			else
				status = HttpStatus.ACCEPTED;
		} catch (NoSuchObjectException e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(status);
		
	}
	
	@PutMapping("/resume/")
	public ResponseEntity<Void> resume() {
		
		HttpStatus status;

		if(watcherActionService.resume())
			status = HttpStatus.NO_CONTENT;
		else
			status = HttpStatus.ACCEPTED;

		
		return new ResponseEntity<>(status);
		
	}

	@PutMapping("/stop/")
	public ResponseEntity<Void> stop() {
		
		HttpStatus status;

		if(watcherActionService.stop())
			status = HttpStatus.NO_CONTENT;
		else
			status = HttpStatus.ACCEPTED;

		return new ResponseEntity<>(status);
	}
	
	@PutMapping("/force-stop/")
	public ResponseEntity<Void> interrupt() {
		
		HttpStatus status;

		if(watcherActionService.interrupt())
			status = HttpStatus.NO_CONTENT;
		else
			status = HttpStatus.ACCEPTED;

		
		return new ResponseEntity<>(status);
		
	}
	
}
