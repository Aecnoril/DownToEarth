/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.RemotePublisher;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 *
 * @author Ruud
 */
public class Server implements IRemotePublisherForDomain {

    private RemotePublisher publisher;
    private Object[] gameChanges;

    public Object[] GetGameChanges() {
        return gameChanges;
    }

    public void SetGameChanges(Object[] gamechanges) {
        gameChanges = gamechanges;
    }

    public Server() throws RemoteException {
        publisher = new RemotePublisher();
        publisher.registerProperty("GameChanges");
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("ClientChanges", publisher);
    }

    @Override
    public void registerProperty(String property) throws RemoteException {
        publisher.registerProperty(property);
    }

    @Override
    public void unregisterProperty(String property) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inform(String property, Object oldValue, Object newValue) throws RemoteException {
        publisher.inform(property, oldValue, newValue);
    }

    @Override
    public List<String> getProperties() throws RemoteException {
        return publisher.getProperties();
    }

}
