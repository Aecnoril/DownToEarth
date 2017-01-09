package downtoearth.Multiplayer;

import downtoearth.entities.Opponent;
import downtoearth.entities.Player;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import shared.*;

public class Client extends UnicastRemoteObject implements IClient {

    private IServer server;
    private RemotePlayer player;
    private List<RemotePlayer> otherClients;
    public List<Opponent> opponents;
    private String name;

    public Client(String name, String ip) throws RemoteException {

        this.name = name;

        try {
            String lookUpName = "rmi://" + ip + "/DownToEarth";
            this.server = (IServer) Naming.lookup(lookUpName);
            this.opponents = new ArrayList<Opponent>();
            this.otherClients = new ArrayList<RemotePlayer>();
            this.server.clientJoin(this);
            this.player = server.spawnPlayer(this);
            this.server.updatePlayers();
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void exitServer() throws RemoteException {
        this.server.clientLeave(this);
    }

    @Override
    public String getClientName() throws RemoteException {
        return this.name;
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RemotePlayer getPlayer() throws RemoteException {
        return this.player;
    }

    @Override
    public void updatePlayers(ArrayList<RemotePlayer> otherClients) {
        this.otherClients = otherClients;
        if (opponents.size() != 0) {
            this.opponents.clear();
        }
        for (RemotePlayer p : this.otherClients) {
            try {
                opponents.add(new Opponent(p));
            } catch (SlickException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void movePlayer(Player player) {
               this.player.setCoords(player.getCoordinate().getXint(), player.getCoordinate().getYint());
               this.player.dir = player.getDir();
        try {
            this.server.updatePlayers();
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
