package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClient extends Remote {

    public void exitServer() throws RemoteException;

    public String getClientName() throws RemoteException;
 
    public RemotePlayer getPlayer() throws RemoteException;
    
    public void updatePlayers(ArrayList<RemotePlayer> opponents);

    public void sendMessage(String message) throws RemoteException;
}