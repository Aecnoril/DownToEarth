/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.items;

import downtoearth.entities.ItemEntity;
import downtoearth.enums.SpriteLocation;
import downtoearth.enums.Tooltype;
import shared.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Demian
 */
public abstract class Item{
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private final static String PATH = "res/TestItems.png";

    protected String name;
    protected byte type;
    protected double durability;
    protected double breakChange;
    protected SpriteManager manager;
    
    private int spritex = Integer.MIN_VALUE;
    private int spritey = Integer.MIN_VALUE;
    
    /**
     * Creates an item with the given parameters
     * @param name
     * @param type
     * @param durability
     * @param breakChange
     * @throws SlickException 
     */
    public Item(String name, byte type, double durability, double breakChange) throws SlickException{
        this.name = name;
        this.type = type;

        this.durability = durability;
        this.breakChange = breakChange;
        manager = new SpriteManager(PATH);    
    }
    
    public byte getType(){
        return type;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    //</editor-fold>
    
    /**
     * Draws the sprite at the given x and y position without scaling
     * @param xpos
     * @param ypos
     * @throws SlickException 
     */
    public void render(int xpos, int ypos) throws SlickException{
        if(spritex == Integer.MIN_VALUE){
            SpriteLocation sl = Tooltype.getSpriteLocation(type);
            spritex = sl.getSpriteX();
            spritey = sl.getSpriteY(); 
        }
        manager.drawSprite(spritex, spritey, xpos, ypos);
    }
    
    /**
     * Draws the sprite at the given x and y and scales it to the given size
     * @param xpos
     * @param ypos
     * @param size
     * @throws SlickException 
     */
    public void render(int xpos, int ypos, int size) throws SlickException{
        if(spritex == Integer.MIN_VALUE){
            SpriteLocation sl = Tooltype.getSpriteLocation(type);
            spritex = sl.getSpriteX();
            spritey = sl.getSpriteY(); 
        }
        manager.scaleSprite(spritex, spritey, xpos, ypos, size);
    }
    
    /**
     * drops the item at the given coordinate
     * @param coord
     * @return
     * @throws SlickException 
     */
    public abstract ItemEntity drop(Coordinate coord) throws SlickException;
}
