/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.Multiplayer.Client;
import downtoearth.states.gui.Inventory;
import downtoearth.states.gui.CraftingScreen;
import shared.RemotePlayer;
import downtoearth.entities.ItemEntity;
import shared.Coordinate;
import downtoearth.world.World;
import downtoearth.world.worldGen.WorldGen;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MultiplayerState extends BasicGameState{

    private World w;
    private Inventory inv;
    private CraftingScreen cs;
    private StateBasedGame game;
    private static int mapSize = 5012;
    private static WorldGen worldGen = new WorldGen(new Coordinate(mapSize, mapSize));
     
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
            this.client = new Client(Integer.toString(random.nextInt(100)), "145.93.84.202");
            w.getPlayer().setSpawnPoint(client.getPlayer().getCoords().getXint(), client.getPlayer().getCoords().getYint());
        } catch (RemoteException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {

        try {
            w.setOpponent(client.getOpponents());
            w.draw(gc.getWidth(), gc.getHeight(), gc, g);       
        } catch (IOException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawString("State 3: MultiplayerGame", 10, 30);

        for (ItemEntity i : w.getItemEnt()) {
            if (w.getPlayer().getBounds().intersects(i.getBounds())) {
                inv.generateInventory();
                w.removeFromItemEntities(i);
                break;
            }
        }

        if (this.inv.isInvOpen()) {
            this.inv.render(g);
        }
        if (this.cs.isCsOpen()) {
            this.cs.render(g);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        w.update(gc.getInput());
        inv.ePressed(gc);
        cs.cPressed(gc);
    }

    @Override
    public void mouseWheelMoved(int change) {
        double res = Math.floor(change * 0.15);
        cs.setScroll((float) res);
    }
}

