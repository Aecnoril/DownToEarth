package downtoearth.states;

import downtoearth.Inventory.Inventory;
import downtoearth.Items.Item;

import downtoearth.entities.ItemEntity;
import downtoearth.entities.NPC;

import downtoearth.gameUtil.Coordinate;

import downtoearth.world.Tile;
import downtoearth.world.World;

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
    private Inventory inv;
    private final int mapSize = 5012;

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
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        w.update(gc.getInput());
        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            w.getPlayer().attackCollision();
        }
        inv.ePressed(gc);
    }
}
