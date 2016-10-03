/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
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
    protected SpriteManager sprite;
    
    
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
        sprite = new SpriteManager(PATH);    
    }
    
    public void render(int xpos, int ypos) throws SlickException{
        SpriteLocation sl = ItemType.getSpriteLocation(type);
        int spritex = sl.getSpriteX();
        int spritey = sl.getSpriteY();
        sprite.drawSprite(spritex, spritey, xpos, ypos);
    }
    
    public abstract void drop();
}
