package nas.villalobos.scanner.services;

import java.rmi.NoSuchObjectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brv.tools.daemons.exceptions.ScanDaemonNotFoundException;
import brv.tools.daemons.manager.ScanDaemonManager;

@Service
public class ScanDaemonWatcherActionService implements WatcherActionService {

	@Autowired
	private ScanDaemonManager scanDaemonManager;
	
	@Override
	public boolean resume(long watcherId) throws NoSuchObjectException {
		
		try {
			return scanDaemonManager.resume(watcherId);
		} catch (ScanDaemonNotFoundException e) {
			throw new NoSuchObjectException(e.getMessage());
		}
	}

	@Override
	public boolean stop(long watcherId) throws NoSuchObjectException {

		try {
			return scanDaemonManager.stop(watcherId);
		} catch (ScanDaemonNotFoundException e) {
			throw new NoSuchObjectException(e.getMessage());
		}
	}

	@Override
	public boolean interrupt(long watcherId) throws NoSuchObjectException {
		try {
			return scanDaemonManager.interrupt(watcherId);
		} catch (ScanDaemonNotFoundException e) {
			throw new NoSuchObjectException(e.getMessage());
		}
	}

	@Override
	public boolean resume() {
		return scanDaemonManager.resume();
	}

	@Override
	public boolean stop() {
		return scanDaemonManager.stop();
	}

	@Override
	public boolean interrupt() {
		return scanDaemonManager.interrupt();
	}

}
