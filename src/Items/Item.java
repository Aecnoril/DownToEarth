/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import enums.*;

/**
 *
 * @author Demian
 */
public abstract class Item{
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private String name;
    private ItemType type;
    private double durability;
    private double breakChange;
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public ItemType getType(){
        return type;
    }
    
    public double getDurability(){
        return durability;
    }
    
    public double getBreakChange(){
        return breakChange;
    }
    
    //</editor-fold>
    
    public Item(String name, ItemType type, double durability, double breakChange){
        this.name = name;
        this.type = type;
        this.durability = durability;
        this.breakChange = breakChange;
    }
    
    public abstract void drop();
}
