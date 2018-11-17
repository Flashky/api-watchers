package nas.villalobos.scanner.services;

import java.rmi.NoSuchObjectException;

/**
 * Defines the contract for a watcher control service to be implemented.
 * <p>
 * Responsability of a WatcherActionService is to give control on already created watchers like:
 * </p>
 * <ul>
 * 	<li>Starting watching over a server.</li>
 * 	<li>Stop watching over a server.</li>
 * 	<li>Interrupt watching over a server.</li>
 * </ul>
 * @author flash
 *
 */
public interface WatcherActionService {
	
	/**
	 * Resumes the specified monitorization over a server.
	 * @param watcherId
	 * @return <code>true</code> - if it has resumed.
	 * @throws NoSuchObjectException if there is no watcher having that id.
	 */
	boolean resume(long watcherId) throws NoSuchObjectException;
	
	/**
	 * Stops the the specified monitorization over a server.
	 * @param watcherId
	 * @return <code>true</code> - if it has stopped.
	 * @throws NoSuchObjectException if there is no watcher having that id.
	 */
	boolean stop(long watcherId) throws NoSuchObjectException;
	
	/**
	 * Interrupts the the specified monitorization over a server.
	 * @param watcherId
	 * @return <code>true</code> - if it has stopped.
	 * @throws NoSuchObjectException if there is no watcher having that id.
	 */
	boolean interrupt(long watcherId) throws NoSuchObjectException;
	
	/**
	 * Resumes all existing monitorization over any server.
	 * @param watcherId
	 * @return <code>true</code> - if any monitorization has resumed.
	 * @throws NoSuchObjectException if there is no watcher having that id.
	 */
	boolean resume();
	
	/**
	 * Stops all existing monitorization over any server.
	 * @param watcherId
	 * @return <code>true</code> - if any monitorization has stopped.
	 * @throws NoSuchObjectException if there is no watcher having that id.
	 */
	boolean stop();
	
	/**
	 * Interrupts all existing monitorization over any server.
	 * @param watcherId
	 * @return <code>true</code> - if any monitorization has stopped.
	 * @throws NoSuchObjectException if there is no watcher having that id.
	 */
	boolean interrupt();
}
