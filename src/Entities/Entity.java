package Entities;

import Items.Item;
<<<<<<< HEAD
import org.newdawn.slick.Graphics;
=======
import java.util.ArrayList;
>>>>>>> 4ab133069ed7c202a4695fe1fee87d3375df8e80
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;


/**
 *
 * @author Demian
 */
public abstract class Entity {
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    
    private String name;
    private SpriteSheet sprite;
    private Point location;
    private int hitPoints;  
    private ArrayList<Item> inventory;
    
    private final int maxBagSize = 20; 
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the value of sprite
     *
     * @return the value of sprite
     */
    public SpriteSheet getSprite() {
        return sprite;
    }

    /**
     * Set the value of sprite
     *
     * @param sprite new value of sprite
     */
    public void setSprite(SpriteSheet sprite) {
        this.sprite = sprite;
    }
    
        /**
     * Get the value of location
     *
     * @return the value of location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Set the value of location
     *
     * @param location new value of location
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * Get the value of hitPoints
     *
     * @return the value of hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Set the value of hitPoints
     *
     * @param hitPoints new value of hitPoints
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
    
        /**
     * Get the value of inventory
     *
     * @return the value of inventory
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Set the value of inventory
     *
     * @param inventory new value of inventory
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
    
    //</editor-fold>
    
    public Entity(String name, SpriteSheet sprite, Point location, int hitPoints){
        this.name=name;
        this.sprite = sprite;
        this.location = location;
        this.hitPoints = hitPoints;
        this.inventory = new ArrayList<Item>();
    }
    
    public abstract void render(Graphics g);
}
