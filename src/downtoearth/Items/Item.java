/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items;

import downtoearth.entities.ItemEntity;
import downtoearth.enums.ResourceType;
import downtoearth.enums.SpriteLocation;
import downtoearth.enums.Type;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import java.io.Serializable;
import java.rmi.Remote;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Demian
 */
public abstract class Item implements Serializable, Remote{
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private final String PATH = "res/ToolAndResourceSprites.png";
    
    protected String name;
    protected byte type;
    protected double durability;
    protected double breakChange;
    protected SpriteManager manager;
    private int slot;
    
    private int spritex = Integer.MIN_VALUE, spritey = Integer.MIN_VALUE;
    
    public byte getType(){
        return type;
    }
    
    public int getSlot(){
        return slot;
    }
    
    public void setSlot(int slotnumber){
        this.slot = slotnumber;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    //</editor-fold>
    
    public Item(String name, byte type, double durability, double breakChange) throws SlickException{        
        this.slot = -1;
        this.name = name;
        this.type = type;

        this.durability = durability;
        this.breakChange = breakChange;
        manager = new SpriteManager(PATH);    
    }
    
    //normal draw
    public void render(int xpos, int ypos) throws SlickException{
        if(spritex == Integer.MIN_VALUE){
            SpriteLocation sl = Type.getSpriteLocation(type);
            spritex = sl.getSpriteX();
            spritey = sl.getSpriteY(); 
        }
        manager.drawSprite(spritex, spritey, xpos, ypos);
    }
    
    //scale draw
    public void render(int xpos, int ypos, int size) throws SlickException{
        if(spritex == Integer.MIN_VALUE){
            SpriteLocation sl = Type.getSpriteLocation(type);
            spritex = sl.getSpriteX();
            spritey = sl.getSpriteY(); 
        }
        manager.scaleSprite(spritex, spritey, xpos, ypos, size);
    }
    
    public abstract ItemEntity drop(Coordinate coord) throws SlickException;
}
