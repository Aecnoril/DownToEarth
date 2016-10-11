/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import enums.*;
import gameUtil.SpriteManager;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Demian
 */
public abstract class Item{
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private final String PATH = "resources/TestItems.png";
    
    protected String name;
    protected byte type;
    protected double durability;
    protected double breakChange;
    protected SpriteManager manager;
    
    private int spritex = Integer.MIN_VALUE, spritey = Integer.MIN_VALUE;
    
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    //</editor-fold>
    
    public Item(String name, byte type, double durability, double breakChange) throws SlickException{
        this.name = name;
        this.type = type;

        this.durability = durability;
        this.breakChange = breakChange;
        manager = new SpriteManager(PATH);    
    }
    
    //normal draw
    public void render(int xpos, int ypos) throws SlickException{
        if(spritex == Integer.MIN_VALUE){
            SpriteLocation sl = Tooltype.getSpriteLocation(type);
            spritex = sl.getSpriteX();
            spritey = sl.getSpriteY(); 
        }
        manager.drawSprite(spritex, spritey, xpos, ypos);
    }
    
    //scale draw
    public void render(int xpos, int ypos, int size) throws SlickException{
        if(spritex == Integer.MIN_VALUE){
            SpriteLocation sl = Tooltype.getSpriteLocation(type);
            spritex = sl.getSpriteX();
            spritey = sl.getSpriteY(); 
        }
        manager.scaleSprite(spritex, spritey, xpos, ypos, size);
    }
    
    //rotate draw
    public void render(float xpos, float ypos, float rotation) throws SlickException{
        if(spritex == Integer.MIN_VALUE){
            SpriteLocation sl = Tooltype.getSpriteLocation(type);
            spritex = sl.getSpriteX();
            spritey = sl.getSpriteY(); 
        }
        
        manager.rotateSprite(spritex, spritey, xpos, ypos, (int)rotation);
    }
    
    public abstract void drop();
}
