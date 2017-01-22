package downtoearth.states;


import downtoearth.states.gui.Inventory;
import downtoearth.states.gui.CraftingScreen;
import downtoearth.entities.ItemEntity;
import shared.Coordinate;
import downtoearth.world.World;
import downtoearth.world.worldGen.WorldGen;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

<<<<<<< HEAD
    private World w;
=======
    public static World w;
>>>>>>> endpoint
    private Inventory inv;
    private CraftingScreen cs;
    private static final int mapSize = 5012;
    private static final NoiseGen noiseGen = new NoiseGen();
    private Map map;
    private Camera cam;

    public static void main(String[] args) {
        System.out.println("hoi");
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        noiseGen.resized(mapSize, mapSize);
        w = new World(new Coordinate(mapSize, mapSize));
        inv = new Inventory(25, 100, 1025, 500, new Color(122, 118, 118));
        cs = new CraftingScreen(25, 100, 1025, 500, new Color(122, 118, 118));
        map = new Map(300,100);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        
        g.setBackground(new Color(20, 19, 156));
        g.clear();
        
        try {
            g.setBackground(new Color(20, 19, 156));
            w.draw(gc.getWidth(), gc.getHeight(), gc, g);
        } catch (IOException ex) {
            Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawString("State 3: Game", 10, 30);
        
        for(ItemEntity i : w.getItemEnt()){
            if(w.getPlayer().getBounds().intersects(i.getBounds())){
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
    }

    @Override
    public void mouseWheelMoved(int change) {   
        if(cs.isCsOpen()){
            double res = Math.floor(change * 0.15);
            cs.setScroll((float)res); 
        }   
    }
}
