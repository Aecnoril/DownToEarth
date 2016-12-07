package downtoearth.Multiplayer;

import downtoearth.entities.Player;
import downtoearth.gameUtil.Coordinate;
import downtoearth.states.MultiplayerState;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.IRemotePublisherForListener;
import java.beans.PropertyChangeEvent;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameCommunicator extends UnicastRemoteObject implements IRemotePropertyListener {

    private final MultiplayerState state;
    
    // Remote publisher
    private IRemotePublisherForDomain publisherForDomain;
    private IRemotePublisherForListener publisherForListener;
    private static int portNumber = 1099;
    private static String bindingName = "publisher";
    private boolean connected = false;
    
    // Thread pool
    private final int nrThreads = 10;
    private ExecutorService threadPool = null;
    
    public GameCommunicator(MultiplayerState state)throws RemoteException{
        this.state = state;
        threadPool = Executors.newFixedThreadPool(nrThreads);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        String property = "player";
        Coordinate coords = (Coordinate) evt.getNewValue();
        state.dataIn(property,coords);
    }
    
    /**
     * Establish connection with remote publisher.
     */
    public void connectToPublisher() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", portNumber);
            publisherForDomain = (IRemotePublisherForDomain) registry.lookup(bindingName);
            publisherForListener = (IRemotePublisherForListener) registry.lookup(bindingName);
            connected = true;
            System.out.println("Connection with remote publisher established");
        } catch (RemoteException | NotBoundException re) {
            connected = false;
            System.err.println("Cannot establish connection to remote publisher");
            System.err.println("Run WhiteBoardServer to start remote publisher");
        }
    }
    
    /**
     * Register property at remote publisher.
     * @param property  property to be registered
     */
    public void register(String property) {
        if (connected) {
            try {
                // Nothing changes at remote publisher in case property was already registered
                publisherForDomain.registerProperty(property);
            } catch (RemoteException ex) {
                Logger.getLogger(GameCommunicator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Subscribe to property.
     * @param property property to subscribe to
     */
    public void subscribe(final String property) {
        if (connected) {
            final IRemotePropertyListener listener = this;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        publisherForListener.subscribeRemoteListener(listener, property);
                    } catch (RemoteException ex) {
                        Logger.getLogger(GameCommunicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
    
    /**
     * Unsubscribe to property.
     * @param property property to unsubscribe to
     */
    public void unsubscribe(final String property) {
        if (connected) {
            final IRemotePropertyListener listener = this;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        publisherForListener.unsubscribeRemoteListener(listener, property);
                    } catch (RemoteException ex) {
                        Logger.getLogger(GameCommunicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
    
    /**
     * Broadcast draw event.
     * @param property  color of draw event
     * @param drawEvent draw event
     */
    public void broadcast(final String property, final Coordinate c) {
        if (connected) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        publisherForDomain.inform(property, null, c);
                    } catch (RemoteException ex) {
                        Logger.getLogger(GameCommunicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
    
    /**
     * Stop communicator.
     */
    public void stop() {
        if (connected) {
            try {
                publisherForListener.unsubscribeRemoteListener(this, null);
            } catch (RemoteException ex) {
                Logger.getLogger(GameCommunicator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } catch (NoSuchObjectException ex) {
            Logger.getLogger(GameCommunicator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}