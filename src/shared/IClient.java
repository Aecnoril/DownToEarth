package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
     * @return all the information of an enemy player
     * @throws RemoteException 
     */
    public RemotePlayer getPlayer() throws RemoteException;
    
    /**
     * updates all the opponent players position health and action
     * @param opponents 
     * @throws java.rmi.RemoteException 
     */    
    public void updatePlayers(CopyOnWriteArrayList<RemotePlayer> opponents) throws RemoteException;
    
    public void attackPlayer(RemotePlayer p) throws RemoteException;
    
    public void recieveDamage() throws RemoteException;

    /**
     * sends a message
     * @param message
     * @throws RemoteException 
     */
    public void sendMessage(String message) throws RemoteException;
}
