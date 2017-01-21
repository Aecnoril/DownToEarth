package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for interaction with the server
 * @author Vernoxius
 */
public interface IServer extends Remote{

    /**
     * adds the given client to the list of connected clients
     * @param client
     * @throws RemoteException 
     */
    public void clientJoin(IClient client) throws RemoteException;

    /**
     * removes the given client to the list of connected clients
     * @param client
     * @throws RemoteException 
     */
    public void clientLeave(IClient client) throws RemoteException;
    
    /**
     * sends a message containing the information of a change in a tile to all the clients so they can update
     * @param message
     * @throws RemoteException 
     */
    public void updateTiles(String message) throws RemoteException;
    
    /**
     * 
     * sends a message containing the information of a change in a mob to all the clients so they can update
     * @param message
     * @throws RemoteException 
     */
    public void updateMobs(String message) throws RemoteException;
    
    /**
     * updates the list of opponents on all the connected clients
     * @throws RemoteException 
     */
    public void updatePlayers() throws RemoteException;
    
    /**
     * 
     * sends a message containing the information of an item pickup to all the clients so they can update
     * @param message
     * @throws RemoteException 
     */
    public void pickupItem(String message) throws RemoteException;
    
    /**
     * sends a message containing the information of an item drop to all the clients so they can update
     * @param message
     * @throws RemoteException 
     */
    public void dropItem(String message) throws RemoteException;
    
    /**
     * grabs the next spawnpoint in the list and adds this as spawnpoint then it updates all players
     * @param client
     * @return
     * @throws RemoteException 
     */
    public RemotePlayer spawnPlayer(IClient client) throws RemoteException;

    /**
     * @param clientName
     * @return the client with the given name
     * @throws RemoteException 
     */
    public IClient getClientByName(String clientName) throws RemoteException;

    /**
     * @param clientName
     * @return the index of the given client in the server list
     * @throws RemoteException 
     */
    public int getClientListIndex(String clientName) throws RemoteException;

}