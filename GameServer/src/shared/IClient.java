package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.CopyOnWriteArrayList;

public interface IClient extends Remote {

    public void exitServer() throws RemoteException;

    public String getClientName() throws RemoteException;
 
    public RemotePlayer getPlayer() throws RemoteException;
    
    public void updatePlayers(CopyOnWriteArrayList<RemotePlayer> opponents) throws RemoteException;

    public void sendMessage(String message) throws RemoteException;
}