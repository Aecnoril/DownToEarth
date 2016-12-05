/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruud
 */
public class Client extends UnicastRemoteObject implements IRemotePropertyListener {

    private IRemotePublisherForListener publisher;
    private Object[] gameChanges;
    private String host;

    public Object[] GetGameChanges() {
        return gameChanges;
    }

    public void SetGameChanges(Object[] gamechanges) {
        gameChanges = gamechanges;
    }

    public void SetHost(String host) {
        host = host;
    }

    public Client() throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            publisher = (IRemotePublisherForListener) registry.lookup("DownToEarthServer");
            publisher.subscribeRemoteListener((IRemotePropertyListener) this, "ServerChanges");
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        gameChanges = (Object[]) evt.getNewValue();
    }
}
