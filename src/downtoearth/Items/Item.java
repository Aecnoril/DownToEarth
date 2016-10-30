/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items;

import downtoearth.enums.SpriteLocation;
import downtoearth.enums.Tooltype;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Demian
 */
public abstract class Item{
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private final String PATH = "res/TestItems.png";
    
    protected String name;
    protected byte type;
    protected double durability;
    protected double breakChange;
    protected SpriteManager manager;
    
    private int spritex = Integer.MIN_VALUE, spritey = Integer.MIN_VALUE;
    
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
    
    public abstract void drop(Coordinate coord) throws SlickException;
}
