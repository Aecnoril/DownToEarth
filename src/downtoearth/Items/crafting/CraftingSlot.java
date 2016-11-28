package downtoearth.Inventorys;

import downtoearth.Items.Item;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ruud
 */
public class CraftingSlot {

    private Rectangle bounds;
    private Item item;
    private downtoearth.Inventorys.Rectangle rectangle;
    public String text;
    public boolean craftable;
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param c
     */
    public CraftingSlot(int x, int y, int width, int height, Color c) {
        rectangle = new downtoearth.Inventorys.Rectangle(c, x, y, width, height);
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void setText(String x){
        text = x;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * Get the value of rectangle
     *
     * @return the value of rectangle
     */
    public downtoearth.Inventorys.Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Set the value of rectangle
     *
     * @param rectangle new value of rectangle
     */
    public void setRectangle(downtoearth.Inventorys.Rectangle rectangle) {
        this.rectangle = rectangle;
    }

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
    //</editor-fold>

    /**
     *
     * @param input
     * @return
     */
    public boolean detectMouse(Input input) {
        if (bounds.contains(input.getMouseX(), input.getMouseY())) {
            return true;
        }
        return false;
    }
}
