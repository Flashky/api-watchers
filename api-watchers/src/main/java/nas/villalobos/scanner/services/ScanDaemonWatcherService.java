package nas.villalobos.scanner.services;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import brv.commons.model.dto.ServerWatcher;
import brv.tools.daemons.ScanDaemon;
import brv.tools.daemons.ScanDaemonBuilder;
import brv.tools.daemons.exceptions.ScanDaemonNotFoundException;
import brv.tools.daemons.manager.ScanDaemonManager;
import brv.tools.model.ScanDaemonConfiguration;

@Service
public class ScanDaemonWatcherService implements WatcherService {

	@Value("${server-scanner.default-timeout:50}")
	private int defaultTimeoutMilliseconds;
	
	@Value("${server-scanner.default-refresh-time:1000}")
	private int defaultRefreshTimeMilliseconds;
	
	@Autowired
	private ScanDaemonManager scanDaemonManager;
	
	@Override
	public long create(ServerWatcher watcher) throws UnknownHostException {
		
		// First, validate the input data
		validate(watcher);
		
		// Build the daemon
		ScanDaemon daemon = buildScanDaemon(watcher);
		
		// Add it to the manager
		scanDaemonManager.add(daemon);
		
		return daemon.getId();
	}

	@Override
	public long modify(long watcherId, ServerWatcher watcher) throws UnknownHostException, ScanDaemonNotFoundException {
		
		// First, validate the input data
		validate(watcher);
		
		if(watcherId < 0)
			throw new IllegalArgumentException("Watcher id must be greater than 0.");
		
		// As daemons are immutable, remove the old one and add the new one.
		scanDaemonManager.remove(watcherId);
		ScanDaemon daemon = buildScanDaemon(watcher);
		scanDaemonManager.add(daemon);
		
		return daemon.getId();
	}

	private void validate(ServerWatcher watcher) {
		Objects.requireNonNull(watcher);
		Objects.requireNonNull(watcher.getProtocol());

		if(watcher.getPort() < 0) 
			throw new IllegalArgumentException("Port must be greater than 0.");
		
		if(watcher.getTimeoutMilliseconds() < 0)
			throw new IllegalArgumentException("Timeout must be in milliseconds and greater or equal to 0.");
		
		if(watcher.getRefreshSeconds() < 0)
			throw new IllegalArgumentException("Sleep time must be in seconds and greater or equal to 0.");
	}
	
	private ScanDaemon buildScanDaemon(ServerWatcher watcher) throws UnknownHostException {
		
		// The ScanDaemon will be created using the ServerWatcher data.
		// if no timeout or sleep time is specified (0), then the default values are used.
		ScanDaemonBuilder builder = new ScanDaemonBuilder(watcher.getProtocol());
		
		// Only use custom port if it is set and different to 0.
		if(watcher.getPort() > 0)
			builder.withPort(watcher.getPort());
		
		// Use default times if input is 0, otherwise, the input value.
		builder.withTimeout((watcher.getTimeoutMilliseconds() == 0) ? defaultTimeoutMilliseconds : watcher.getTimeoutMilliseconds())
				.withSleep((watcher.getRefreshSeconds() == 0) ? defaultRefreshTimeMilliseconds : watcher.getRefreshSeconds() * 1000);
		
		// Finally, build the daemon and add it to the manager.
		return builder.build();
	}
	
	@Override
	public ServerWatcher getWatcher(long watcherId) {
		
		ScanDaemonConfiguration config = scanDaemonManager.find(watcherId);
		return convertFrom(config);
	}

	@Override
	public List<ServerWatcher> getWatchers() {
		
		List<ServerWatcher> result = new LinkedList<>();
		List<ScanDaemonConfiguration> configs = scanDaemonManager.find();
		
		for(ScanDaemonConfiguration config : configs) {
			ServerWatcher watcher = convertFrom(config);
			if(watcher != null)
				result.add(watcher);
		}
		
		return result;
	}
	
	/**
	 * Converts a ScanDaemonConfiguration into a ServerWatcher.
	 * @param config the ScanDaemonConfiguration to convert.
	 * @return <code>ServerWatcher</code> - the converted server watcher.
	 */
	private ServerWatcher convertFrom(ScanDaemonConfiguration config) {
		
		ServerWatcher result = null;
		
		if(config != null) {
			result = new ServerWatcher();
			result.setId(config.getId());
			result.setProtocol(config.getProtocol());
			result.setPort(config.getPort());
			result.setTimeoutMilliseconds(config.getTimeout());
			result.setRefreshSeconds(config.getSleep() / 1000);
			result.setRunning(config.isRunning());
		}
		
		return result;
		
	}
	
	@Override
	public boolean remove(long watcherId) throws ScanDaemonNotFoundException {
		return scanDaemonManager.remove(watcherId);
	}

	@Override
	public boolean remove() {
		return scanDaemonManager.remove();
	}

}
