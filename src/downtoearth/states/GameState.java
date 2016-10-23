/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.Items.Item;
import downtoearth.Items.TileItem;
import downtoearth.entities.NPC;
import downtoearth.enums.DirectionType;
import downtoearth.enums.Tooltype;
import downtoearth.gameUtil.Camera;
import downtoearth.gameUtil.Coordinate;
import downtoearth.inventorySlot;
import downtoearth.world.Tile;
import downtoearth.world.World;
import downtoearth.world.worldGen.WorldGen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.games.input.Component;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Demian
 */
public class GameState extends BasicGameState {

    private static final int number = 200;
    private boolean inventory = false;
    private ArrayList<inventorySlot> inventorySlots = new ArrayList<inventorySlot>();
    private ArrayList<Item> Items = new ArrayList<Item>();
    private inventorySlot selectedSlot = null;

    private boolean invOpen;

    private static World w;

    private static int mapSize = 5012;
    private static WorldGen worldGen = new WorldGen(new Coordinate(mapSize, mapSize));

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        w = new World(new Coordinate(mapSize, mapSize));
        this.generateInventory();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        try {
            w.draw(gc.getWidth(), gc.getHeight(), gc, g);
        } catch (IOException ex) {
            Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawString("State 3: Game", 10, 30);
        g.drawString("Mouse Position: " + Mouse.getX() + ", " + (720 - Mouse.getY()), 10, 60);
        for (Tile t : w.getTiles()) {
            if (t.getBounds().intersects(w.getPlayer().getColLine())) {
                w.getPlayer().collision();
            }
            if (t.getBounds().intersects(w.getPlayer().getAttackColLine())) {
                w.getPlayer().attack(t);
            }
        }
        for (NPC n : w.getMobs()) {
            if (w.getPlayer().getAttack()) {
                if (n.getBounds().intersects(w.getPlayer().getAttackColLine())) {
                    w.getPlayer().attack(n);
                }
            }

        }
        if (invOpen) {
            g.setColor(new Color(122, 118, 118));
            g.fillRect(200, 400, 1025, 500);
            for (inventorySlot r : this.inventorySlots) {
                g.setColor(r.getColor());
                g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
                if (r.getItem() != null) {
                    r.getItem().render(r.getX(), r.getY(), r.getWidth());
                }
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        w.update(gc.getInput());
        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            w.getPlayer().attackCollision();
        }
        if (gc.getInput().isKeyPressed(Input.KEY_E)) {
            if (invOpen) {
                invOpen = false;
            } else {
                invOpen = true;
            }
        }
    }

    public void generateInventory() {
        int x = 200;
        int y = 400;

        for (int i = 0; i < 40; i++) {
            if (i == 10 || i == 20) {
                y += 100;
                x -= 1000;
            }
            if (i == 30) {
                y += 180;
                x -= 1000;
            }
            int leftborder = 25;
            int topborder = 25;
            inventorySlot r = new inventorySlot(x + leftborder, y + topborder, 75, 75, new Color(58, 55, 55));
            this.inventorySlots.add(r);

            x += 100;
        }

        int i = 1;
        for (Item it : Items) {
            inventorySlot r = inventorySlots.get(inventorySlots.size() - i);
            if (r.getItem() == null) {
                r.setItem(it);
            }
            i++;
        }
    }

}
