package downtoearth.states;

import downtoearth.Inventorys.Inventory;
import downtoearth.Inventorys.InventorySlot;
import downtoearth.Items.Item;
import downtoearth.Items.crafting.CraftingScreen;

import downtoearth.entities.ItemEntity;
import downtoearth.entities.NPC;
import downtoearth.gameUtil.Camera;
import downtoearth.gameUtil.Coordinate;

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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Demian
 */
public class GameState extends BasicGameState {

    public World w;
    private static Inventory inv;
    private CraftingScreen cs;
    private static int mapSize = 5012;
    private static WorldGen worldGen = new WorldGen(new Coordinate(mapSize, mapSize));
    
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
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        try {
            w.draw(gc.getWidth(), gc.getHeight(), gc, g);
        } catch (IOException ex) {
            Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawString("State 3: Game", 10, 30);
        for (Tile t : w.getTiles()) {
            if (t.getBounds().intersects(w.getPlayer().getColLine())) {
                w.getPlayer().collision();
            }
            if (t.getBounds().intersects(w.getPlayer().getAttackColLine()) && Mouse.isButtonDown(0)) {
                w.getPlayer().attack(t);
            }
        }
        for (NPC n : w.getMobs()) {
            if (w.getPlayer().getAttack() && n.getBounds().intersects(w.getPlayer().getAttackColLine())) {
                w.getPlayer().attack(n);
            }

        }

        for (ItemEntity i : w.itemEnts) {
            if (w.getPlayer().getBounds().intersects(i.getBounds())) {
                //inv.addItem(i.getItem());
                inv.generateInventory();
                System.out.println(i.getItem().getName());
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
        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            w.getPlayer().attackCollision();
        }
        inv.ePressed(gc);
        cs.setInventory(inv); 
        cs.cPressed(gc);    
//        for(InventorySlot is: inv.getInvSlots()){
//            if(is.getItem() != null)
//            System.out.println(((Item)is.getItem()).getName()); 
//        }
    }
    
    @Override
    public void mouseWheelMoved(int change) {
        double res = Math.floor(change * 0.1);
        cs.setScroll((float) res);
    }
}
