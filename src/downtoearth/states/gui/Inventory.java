/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states.gui;

import downtoearth.items.Item;
import downtoearth.items.TileItem;
import downtoearth.enums.Tooltype;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ruud
 */
public class Inventory {

    private ArrayList<InventorySlot> inventorySlots = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private InventorySlot selectedSlot = null;
    private boolean invOpen;
    private Rectangle rectangle;
    private Color color;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param c
     */
    public Inventory(int x, int y, int width, int height, Color c) throws SlickException {
        rectangle = new Rectangle(c, x, y, width, height);
        this.invOpen = false;

        items.add(new TileItem("item1", Tooltype.WOODENSWORD, 10, 10));
        items.add(new TileItem("item2", Tooltype.STONESWORD, 10, 10));
        items.add(new TileItem("item3", Tooltype.STEELSWORD, 10, 10));
        items.add(new TileItem("item4", Tooltype.GEMSWORD, 10, 10));
        this.generateInventory();
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * Get the value of rectangle
     *
     * @return the value of rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Set the value of rectangle
     *
     * @param rectangle new value of rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Get the value of invOpen
     *
     * @return the value of invOpen
     */
    public boolean isInvOpen() {
        return invOpen;
    }

    /**
     * Set the value of invOpen
     *
     * @param invOpen new value of invOpen
     */
    public void setInvOpen(boolean invOpen) {
        this.invOpen = invOpen;
    }
    //</editor-fold>

    /**
     * Generates the inventory
     */
    public void generateInventory() {
        int temporaryX = rectangle.getX();
        int temporaryY = rectangle.getY();
        int leftborder = 25;
        int topborder = 25;

        for (int i = 0; i < 40; i++) {
            if (i == 10 || i == 20) {
                temporaryY += 100;
                temporaryX -= rectangle.getWidth() - leftborder;
            }
            if (i == 30) {
                temporaryY += 180;
                temporaryX -= rectangle.getWidth() - leftborder;
            }
            InventorySlot r = new InventorySlot(temporaryX + leftborder, temporaryY + topborder, 75, 75, new Color(58, 55, 55));
            this.inventorySlots.add(r);

            temporaryX += r.getRectangle().getWidth() + leftborder;
        }

        int i = 1;
        for (Item it : items) {
            InventorySlot r = inventorySlots.get(inventorySlots.size() - i);
            if (r.getItem() == null) {
                r.setItem(it);
            }
            i++;
        }
    }

    /**
     *
     * @param g
     */
    public void render(Graphics g) {
        try {
            g.setColor(new Color(122, 118, 118));
            g.fillRect(rectangle.getX(), rectangle.getY(), 1025, 500);
            for (InventorySlot r : this.inventorySlots) {
                g.setColor(r.getRectangle().getColor());
                g.fillRect(r.getRectangle().getX(), r.getRectangle().getY(), r.getRectangle().getWidth(), r.getRectangle().getHeight());
                if (r.getItem() != null) {
                    r.getItem().render(r.getRectangle().getX(), r.getRectangle().getY(), r.getRectangle().getWidth());
                }
            }
        } catch (SlickException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param gc
     */
    public void ePressed(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_E)) {
            if (this.invOpen) {
                this.invOpen = false;
            } else {
                this.invOpen = true;
            }
        }
        if (this.invOpen) {
            this.moveitem(gc);
        }
    }

    /**
     *
     * @param gc
     */
    public void moveitem(GameContainer gc) {
        if (invOpen) {
            for (InventorySlot is : this.inventorySlots) {
                if (is.detectMouse(gc.getInput())) {
                    if (is.detectMouse(gc.getInput()) && gc.getInput().isMousePressed(gc.getInput().MOUSE_RIGHT_BUTTON)) {
                        is.setItem(null);
                    } else if (is.detectMouse(gc.getInput()) && gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
                        if (selectedSlot != null && is.getItem() != null) {
                            Item i = selectedSlot.getItem();
                            selectedSlot.setItem(is.getItem());
                            is.setItem(i);
                        } else if (selectedSlot != null) {
                            is.setItem(selectedSlot.getItem());
                            selectedSlot.setItem(null);
                        }
                        if (selectedSlot == null) {
                            selectedSlot = is;
                        } else {
                            selectedSlot = null;
                        }
                    }
                }
            }
        }
    }
}
