/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.Inventorys.Inventory;
import downtoearth.Items.crafting.CraftingScreen;
import downtoearth.Multiplayer.Contestant;
import downtoearth.Multiplayer.GameCommunicator;
import downtoearth.entities.ItemEntity;
import downtoearth.entities.Player;
import downtoearth.gameUtil.Coordinate;
import downtoearth.world.World;
import downtoearth.world.worldGen.WorldGen;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
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
    private static WorldGen worldGen = new WorldGen(new Coordinate(mapSize, mapSize));
    private String id = UUID.randomUUID().toString();
    
    private GameContainer container;
     
    private Contestant player;
    
    private GameCommunicator com;

    public static void main(String[] args) {
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        w = new World(new Coordinate(mapSize, mapSize), this, id);
        inv = new Inventory(25, 100, 1025, 500, new Color(122, 118, 118));
        cs = new CraftingScreen(25, 100, 1025, 500, new Color(122, 118, 118));
        this.player = new Contestant(this.id, w.getPlayer().getCoordinate().getXint(), w.getPlayer().getCoordinate().getYint(), w.getPlayer().getHitPoints());
        try {
            this.com = new GameCommunicator(this);
            com.connectToPublisher();
            com.register("players");
            com.subscribe("players");
        } catch (RemoteException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {

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
    
    public void setProperty(String property){
        com.register(property);
    }
    
    public void getProperties(){
        
    }
    
    public void updatePlayer(Coordinate coords){
        this.player.setX(coords.getXint());
        this.player.setY(coords.getYint());
        System.out.println("Update!");
        String property = "players";
        com.broadcast(property, player);
    }
    
    public void dataIn(Contestant data){
        System.out.println(data.getId());
        if(!data.getId().equalsIgnoreCase(this.id)){
            System.out.println("Data Recieved");
            if(checkPlayerList(data) && !data.isDead()){
                changePlayerValues(data);
            }
            else if(data.isDead())
            {
                for(Contestant o : w.opponents)
                {
                    if(o.getId() == null ? data.getId() == null : o.getId().equals(data.getId()))
                    {
                        w.opponents.remove(o);
                    }
                }
                System.out.println("Death has come!");
            }
            else{
                System.out.println("Opponents Added!");
                w.opponents.add(data);
            }
        }
        if(data.getId().equalsIgnoreCase(this.id))
        {
                 updateHealthValues(data);

        }
    }
    
    public boolean checkPlayerList(Contestant c){
        boolean exists = false;
        for(Contestant player : w.opponents){
            if(player.getId().equalsIgnoreCase(c.getId())){
                System.out.println("Opponents Found!");
                exists = true;
            }
        }
        return exists;
    }
    
    public void changePlayerValues(Contestant c){
        for(Contestant con : w.opponents){
            if(con.getId().equalsIgnoreCase(c.getId())){
                con = c;
                break;
            }
        }
    }
    public void attackOpponent(Contestant opponent) {
        System.out.println("Update attack!" + opponent.getId());
        String property = "players";
        com.broadcast(property, opponent);
    }

    private void updateHealthValues(Contestant data) {
            w.getPlayer().setHitPoints(data.getHealth());
            if(w.getPlayer().getHitPoints() == 0)
            {
                player.setDead(true);
                updatePlayer(w.getPlayer().getCoordinate());
                com.unsubscribe("players");
                game.enterState(3);
                //stop();
            }
            System.out.println(w.getPlayer().getHitPoints());
    }
    
    private void stop()
    {
        container.exit();
    }
}

