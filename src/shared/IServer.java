package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote{

    public void clientJoin(IClient client) throws RemoteException;

    public void clientLeave(IClient client) throws RemoteException;
    
    public void updateTiles(String message) throws RemoteException;
    
    public void updateMobs(String message) throws RemoteException;
    
    public void updatePlayers() throws RemoteException;
    
    public void pickupItem(String message) throws RemoteException;
    
    public void dropItem(String message) throws RemoteException;
    
    public void attackPlayer(RemotePlayer player) throws RemoteException;
    
    public RemotePlayer spawnPlayer(IClient client) throws RemoteException;

    public IClient getClientByName(String clientName) throws RemoteException;

    public int getClientListIndex(String clientName) throws RemoteException;

}
