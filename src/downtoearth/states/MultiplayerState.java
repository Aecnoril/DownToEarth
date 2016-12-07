/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.Inventorys.Inventory;
import downtoearth.Items.crafting.CraftingScreen;
import downtoearth.Multiplayer.GameCommunicator;
import downtoearth.entities.ItemEntity;
import downtoearth.entities.Player;
import downtoearth.gameUtil.Coordinate;
import downtoearth.world.World;
import downtoearth.world.worldGen.WorldGen;
import java.io.IOException;
import java.rmi.RemoteException;
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
    private static int mapSize = 5012;
    private static WorldGen worldGen = new WorldGen(new Coordinate(mapSize, mapSize));
    
    private GameCommunicator com;

    public static void main(String[] args) {
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        w = new World(new Coordinate(mapSize, mapSize), this);
        inv = new Inventory(25, 100, 1025, 500, new Color(122, 118, 118));
        cs = new CraftingScreen(25, 100, 1025, 500, new Color(122, 118, 118));
        try {
            this.com = new GameCommunicator(this);
            com.connectToPublisher();
            com.register("player");
            com.subscribe("player");
        } catch (RemoteException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.setBackground(new Color(12, 54, 94));

        try {
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
    
    public void updatePlayer(Coordinate coords){
        System.out.println("Updated Player");
        System.out.println("X: " + coords.getXint() + ", Y: " + coords.getYint());
        String property = "player";
        com.broadcast(property, coords);
    }
    
    public void dataIn(String property, Coordinate coords){
        System.out.println("Data In: X - " + coords.getXint() + ", Y - " + coords.getYint());
    }
}

