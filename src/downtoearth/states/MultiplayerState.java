/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.Inventory.Map;
import downtoearth.Multiplayer.Client;
import downtoearth.Multiplayer.GameCommunicator;
import downtoearth.states.gui.Inventory;
import downtoearth.states.gui.CraftingScreen;
import shared.RemotePlayer;
import downtoearth.entities.ItemEntity;
import shared.Coordinate;
import downtoearth.world.World;
import downtoearth.world.worldGen.NoiseGen;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MultiplayerState extends BasicGameState{

    public World w;
    private Inventory inv;
    private CraftingScreen cs;
    private StateBasedGame game;
    private static int mapSize = 5012;
    private static NoiseGen noiseGen = new NoiseGen();
    private String id = UUID.randomUUID().toString();
    
    private GameContainer container;
    private Map map;
    
    private GameCommunicator com;
     
    private RemotePlayer player;
    private Random random;
    private Client client;

    public static void main(String[] args) {
       
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.random = new Random();
        this.game = game;
        w = new World(new Coordinate(mapSize, mapSize));
        inv = new Inventory(25, 100, 1025, 500, new Color(122, 118, 118));
        cs = new CraftingScreen(25, 100, 1025, 500, new Color(122, 118, 118));
        
        try {
            this.client = new Client(Integer.toString(random.nextInt(100)), "localhost", w.getPlayer(), game);
            w.getPlayer().setSpawnPoint(client.getPlayer().getCoords().getXint(), client.getPlayer().getCoords().getYint());
        } catch (RemoteException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }
        map = new Map(300,100);
        
        this.w.getPlayer().setClient(client);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {


        
        try {
            w.opponents = client.opponents;
            w.draw(gc.getWidth(), gc.getHeight(), gc, g);       
        } catch (IOException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawString("State 3: MultiplayerGame", 10, 30);

        for (ItemEntity i : w.itemEnts) {
            if (w.getPlayer().getBounds().intersects(i.getBounds())) {
                inv.generateInventory();
                w.itemEnts.remove(i);
                break;
            }
        }

        if (this.inv.isInvOpen()) {
            this.inv.render(g);
        }
        if (this.cs.isCsOpen()) {
            this.cs.render(g);
        }
        
        if (this.map.isMapOpen())
        {
            this.map.render(g);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        w.update(gc.getInput());
        inv.ePressed(gc);
        cs.cPressed(gc);
        map.mPressed(gc);  
        map.setCamera(w.getPlayer().getCamera());
        client.movePlayer(w.getPlayer());
    }

    @Override
    public void mouseWheelMoved(int change) {
        double res = Math.floor(change * 0.15);
        cs.setScroll((float) res);
    }
}

