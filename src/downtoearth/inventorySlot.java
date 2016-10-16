package downtoearth;

import downtoearth.Items.Item;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Circle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ruud
 */
public class inventorySlot {

    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private Item item;

    /**
     * Get the value of item
     *
     * @return the value of item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Set the value of item
     *
     * @param hasItem new value of item
     */
    public void setItem(Item hasItem) {
        this.item = hasItem;
    }

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
    //</editor-fold>

    public inventorySlot(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public boolean detectMouse(Circle mouseBall) {
        if (mouseBall.getCenterX() >= this.getX() && mouseBall.getCenterX() <= this.getX() + this.getWidth()) {
            if (mouseBall.getCenterY() >= this.getY() && mouseBall.getCenterY() <= this.getY() + this.getHeight()) {
                return true;
            }
        }
        return false;
    }

}
