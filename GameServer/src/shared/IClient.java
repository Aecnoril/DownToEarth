package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface for interacting with clients
 * @author Vernoxius
 */
public interface IClient extends Remote {

    /**
     * exits the server
     * @throws RemoteException 
     */
    public void exitServer() throws RemoteException;

    /**
     * @return the name of the client
     * @throws RemoteException 
     */
    public String getClientName() throws RemoteException;
 
    /**
     * @return all th information of an enemy player
     * @throws RemoteException 
     */
    public RemotePlayer getPlayer() throws RemoteException;
    
    /**
     * updates all the opponent players position health and action
     * @param opponents 
     * @throws java.rmi.RemoteException 
     */
    public void updatePlayers(List<RemotePlayer> opponents) throws RemoteException;

    /**
     * sends a message
     * @param message
     * @throws RemoteException 
     */
    public void sendMessage(String message) throws RemoteException;
}
