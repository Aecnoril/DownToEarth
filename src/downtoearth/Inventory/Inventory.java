/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Inventory;

import downtoearth.Items.Item;
import downtoearth.Items.TileItem;
import downtoearth.enums.Tooltype;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Ruud
 */
public class Inventory {

    private ArrayList<inventorySlot> inventorySlots = new ArrayList<inventorySlot>();
    private ArrayList<Item> Items = new ArrayList<Item>();
    private Rectangle bounds;
    private inventorySlot selectedSlot = null;

    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private Color color;

    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the value of color
     *
     * @param color new value of color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    private int x;

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public int getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(int x) {
        this.x = x;
    }

    private int y;

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(int y) {
        this.y = y;
    }

    private int width;

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the value of width
     *
     * @param width new value of width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    private int height;

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    private boolean invOpen;

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

    public Inventory(int x, int y, int width, int height, Color c) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.invOpen = false;
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
        try {
            Items.add(new TileItem("jopie", Tooltype.WOODENSWORD, 10, 10));
            Items.add(new TileItem("jopie", Tooltype.STONESWORD, 10, 10));
            Items.add(new TileItem("jopie", Tooltype.STEELSWORD, 10, 10));
            Items.add(new TileItem("jopie", Tooltype.GEMSWORD, 10, 10));
        } catch (SlickException ex) {
        }
        this.generateInventory();
    }

    public void generateInventory() {
        int x = this.x;
        int y = this.y;
        int leftborder = 25;
        int topborder = 25;

        for (int i = 0; i < 40; i++) {
            if (i == 10 || i == 20) {
                y += 100;
                x -= this.width - leftborder;
            }
            if (i == 30) {
                y += 180;
                x -= this.width - leftborder;
            }
            inventorySlot r = new inventorySlot(x + leftborder, y + topborder, 75, 75, new Color(58, 55, 55));
            this.inventorySlots.add(r);

            x += r.getWidth() + leftborder;
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

    public void render(Graphics g) {
        try {
            g.setColor(new Color(122, 118, 118));
            g.fillRect(this.x, this.y, 1025, 500);
            for (inventorySlot r : this.inventorySlots) {
                g.setColor(r.getColor());
                g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
                if (r.getItem() != null) {
                    r.getItem().render(r.getX(), r.getY(), r.getWidth());
                }
            }
        } catch (SlickException ex) {
        }
    }

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

    public void moveitem(GameContainer gc) {
        if (invOpen == true) {
            for (inventorySlot is : this.inventorySlots) {
                if (is.detectMouse(gc.getInput())) {
                    if (gc.getInput().isMousePressed(gc.getInput().MOUSE_RIGHT_BUTTON)) {
                        is.getItem().drop();
                        is.setItem(null);
                    } else if (gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
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
