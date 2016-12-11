package downtoearth.states;

import downtoearth.Inventorys.Inventory;
import downtoearth.Items.crafting.CraftingScreen;
import downtoearth.entities.ItemEntity;
import downtoearth.gameUtil.Coordinate;
import downtoearth.Inventory.Map;
import downtoearth.gameUtil.Camera;
import downtoearth.world.Tile;
import downtoearth.world.World;
import downtoearth.world.worldGen.WorldGen;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

    public World w;
    private Inventory inv;
    private CraftingScreen cs;
    private static int mapSize = 5012;
    private static WorldGen worldGen = new WorldGen(new Coordinate(mapSize, mapSize));
    private Map map;
    private Camera cam;

    public static void main(String[] args) {
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        w = new World(new Coordinate(mapSize, mapSize));
        inv = new Inventory(25, 100, 1025, 500, new Color(122, 118, 118));
        cs = new CraftingScreen(25, 100, 1025, 500, new Color(122, 118, 118));
        map = new Map(300,100);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.setBackground(new Color(12, 54, 94));
        
        try {
            w.draw(gc.getWidth(), gc.getHeight(), gc, g);
        } catch (IOException ex) {
            Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawString("State 3: Game", 10, 30);
        
        for(ItemEntity i : w.itemEnts){
            if(w.getPlayer().getBounds().intersects(i.getBounds())){
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
    }

    @Override
    public void mouseWheelMoved(int change) {          
        double res = Math.floor(change * 0.15);
        cs.setScroll((float)res);
        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            System.out.println("Attack");
            w.getPlayer().attackCollision();
        }      
    }
}
