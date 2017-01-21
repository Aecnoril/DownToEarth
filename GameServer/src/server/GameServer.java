package server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.*;

/**
 * Game server for Down to Earth
 * @author Vernoxius
 */
public class GameServer extends UnicastRemoteObject implements IServer {

    private static int portNumber = 1099;
    private static String bindingName = "DownToEarth";
    private ArrayList<IClient> clients;
    private static ArrayList<Coordinate> spawnpoints = new ArrayList<>();
    
    private int count = 0;
    
    /**
     * Adds the clients that want to connect  as the server is created to the server
     * @throws RemoteException 
     */
    public GameServer() throws RemoteException {
        clients = new ArrayList<>();
    }
    
    /**
     * main class to start the server
     * @param args
     * @throws UnknownHostException 
     */
    public static void main(String[] args) throws UnknownHostException {
        Registry registry;
        
        try{
            GameServer rmiServer = new GameServer();
            registry = LocateRegistry.createRegistry(portNumber);
            registry.rebind(bindingName, rmiServer);
            Logger.getLogger("RMI server runs...");
        } catch (RemoteException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        InetAddress localhost = InetAddress.getLocalHost();
        Logger.getLogger("Server: IP Address: " + localhost.getHostAddress());
    }

    @Override
    public void clientJoin(IClient client) throws RemoteException {
        this.clients.add(client);
        updatePlayers();
        Logger.getLogger(client.getClientName() + " joined the server!");
    }

    @Override
    public void clientLeave(IClient client) throws RemoteException {
        this.clients.remove(client);
        Logger.getLogger(client.getClientName() + " left the server!");
    }

    @Override
    public IClient getClientByName(String clientName) throws RemoteException {
        for(IClient client : this.clients) {
            if(client.getClientName().equalsIgnoreCase(clientName)){
                return client;
            }
        }
        return null;
    }

    @Override
    public int getClientListIndex(String clientName) throws RemoteException {
        for(int i = 0; i < this.clients.size(); i++) {
            if(this.clients.get(i).getClientName().equals(clientName)) {
                return i;
            }
        }
        
        return 0;
    }

    @Override
    public RemotePlayer spawnPlayer(IClient client) throws RemoteException {
        spawnpoints = new ArrayList<>();
        spawnpoints.add(new Coordinate(780,1541));
        spawnpoints.add(new Coordinate(1137,2985));
        spawnpoints.add(new Coordinate(2291,2483));
        spawnpoints.add(new Coordinate(3962,2392));
        spawnpoints.add(new Coordinate(4205,3623));
        
        RemotePlayer player = new RemotePlayer(client.getClientName(), spawnpoints.get(count), 100);
        count++;
        updatePlayers();
        return player;
    }

    @Override
    public void updateTiles(String message) throws RemoteException {
        for(IClient client : this.clients) {
            updateTiles(message);
        }
    }

    public synchronized void updatePlayers() throws RemoteException {
        for(IClient client : this.clients) {
            ArrayList<RemotePlayer> opponents = new ArrayList<>();
            for(IClient c : this.clients){ 
                if(!client.getClientName().equalsIgnoreCase(c.getClientName())){
                    opponents.add(c.getPlayer());
                }
            }
            client.updatePlayers(opponents);
        }
    }

    @Override
    public void updateMobs(String message) throws RemoteException {
        for(IClient client : this.clients) {
            
        }
    }

    @Override
    public void pickupItem(String message) throws RemoteException {
        for(IClient client : this.clients) {
            
        }
    }

    @Override
    public void dropItem(String message) throws RemoteException {
        for(IClient client : this.clients) {
            
        }
    }
}
