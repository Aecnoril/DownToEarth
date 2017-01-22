/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.Inventory.Map;
import downtoearth.Multiplayer.Contestant;
import downtoearth.gameUtil.SoundManager;
import downtoearth.Multiplayer.Client;
import downtoearth.Multiplayer.GameCommunicator;
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
    private Map map; 
    private GameCommunicator com; 
    private SoundManager sm;   
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
            this.client = new Client(Integer.toString(random.nextInt(100)), "localhost", w.getPlayer());
            w.getPlayer().setSpawnPoint(client.getPlayer().getCoords().getXint(), client.getPlayer().getCoords().getYint());
        } catch (RemoteException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }
        map = new Map(300,100);
        sm = new SoundManager();    
        this.w.getPlayer().setClient(client);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.setBackground(new Color(12, 54, 94));


        
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
    
    public void setProperty(String property){
        com.register(property);
    }
    
    public void getProperties(){
        
    }
    
    public void updatePlayer(Coordinate coords){
        this.player.setCoords(coords.getXint(), coords.getYint());
        //System.out.println("Update!");
        String property = "players";
        //com.broadcast(property, player);
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
                /*for(Contestant o : w.opponents)
                {
                    if(o.getId() == null ? data.getId() == null : o.getId().equals(data.getId()))
                    {
                        w.opponents.remove(o);
                    }
                }*/
                System.out.println("Death has come!");
            }
            else{
                System.out.println("Opponents Added!");
                //w.opponents.add(data);
            }
        }
        if(data.getId().equalsIgnoreCase(this.id))
        {
                 updateHealthValues(data);

        }
    }
    
    public boolean checkPlayerList(Contestant c){
        boolean exists = false;
        /*for(Contestant player : w.opponents){
            if(player.getId().equalsIgnoreCase(c.getId())){
                System.out.println("Opponents Found!");
                exists = true;
            }
        }*/
        return exists;
    }
    
    public void changePlayerValues(Contestant c){
        /*for(Contestant con : w.opponents){
            if(con.getId().equalsIgnoreCase(c.getId())){
                con.setX(c.getX());
                con.setY(c.getY());
                break;
            }
        }*/
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
                sm.playSound("manDyingA.wav", false);
                //player.setDead(true);
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

