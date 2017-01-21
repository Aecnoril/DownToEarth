package downtoearth.entities;

import downtoearth.Items.Item;
import shared.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


/**
 *
 * @author Demian
 */
public abstract class Entity{
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    

    protected String name;
    protected SpriteSheet sprite;
    protected Coordinate location;
    public int hitPoints;  
    protected List<Item> inventory;
    protected SpriteManager spriteManager;
    
    protected final int bagSize = 20;
    
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
    public Coordinate getLocation() {
        return location;
    }

    /**
     * Set the value of location
     *
     * @param location new value of location
     */
    public void setLocation(Coordinate location) {
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
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Set the value of inventory
     *
     * @param inventory new value of inventory
     */
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
    
    //</editor-fold>
    
    public Entity(String name, Coordinate location, int hitPoints, String path) throws SlickException{
        this.name=name;
        this.location = location;
        this.hitPoints = hitPoints;
        this.inventory = new ArrayList<>();
        this.spriteManager = new SpriteManager(path);
    }
}
