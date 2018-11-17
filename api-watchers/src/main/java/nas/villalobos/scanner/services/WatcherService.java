package nas.villalobos.scanner.services;

import java.net.UnknownHostException;
import java.util.List;

import brv.tools.daemons.exceptions.ScanDaemonNotFoundException;
import brv.tools.model.dto.ServerWatcher;

/**
 * Defines the contract for a watcher service to be implemented.
 * <p>
 * WatcherService main responsability is CRUD related, defining methods like:
 * </p>
 * <ul>
 * 	<li>Creating a watcher.</li>
 * 	<li>Obtaining information from previously created watcher or watchers.</li>
 * 	<li>Removing a previously created watcher or watchers.</li>
 * </ul>
 * @author flash
 *
 */
public interface WatcherService {

	/**
	 * Creates a server watcher.
	 * @param watcher - the data needed to create a new watcher.
	 * @return <code>long</code> - the id of the created watcher.
	 * @throws UnknownHostException 
	 */
	long create(ServerWatcher watcher) throws UnknownHostException;
	
	/**
	 * Modifies an existing server watcher updating it with the provided data.
	 * @param <code>watcherId</code> - the watcher to be updated id.
	 * @param <code>ServerWatcher</code> - contains the data to be updated.
	 * @return the <code>watcher id</code>, this is useful in case that the implementation forces a change on the id.
	 * @throws UnknownHostException 
	 * @throws ScanDaemonNotFoundException 
	 */
	long modify(long watcherId, ServerWatcher watcher) throws UnknownHostException, ScanDaemonNotFoundException;
	
	/** 
	 * Obtains the data from a previously created server watcher.
	 * @param <code>watcherId</code> - the watcher to be found id.
	 * @return <code>ServerWatcher</code> - the server watcher specified by the id. <code>null</code> if no server watcher has been found.
	 */
	ServerWatcher getWatcher(long watcherId);
	
	/**
	 * Obtains all currently existing watchers.
	 * @return <code>List&lt;ServerWatcher&gt;</code> - A list of existing server watchers. An empty list if there are no watchers.
	 */
	List<ServerWatcher> getWatchers();
	
	/**
	 * Removes a server watcher.
	 * @param <code>watcherId</code> - the watcher to be found id.
	 * @return
	 * @throws ScanDaemonNotFoundException 
	 */
	boolean remove(long watcherId) throws ScanDaemonNotFoundException;
	
	/**
	 * Removes all existing server watchers.
	 * @param <code>watcherId</code> - the watcher to be found id.
	 * @return
	 */
	boolean remove();

}
