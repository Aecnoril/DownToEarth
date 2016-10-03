/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Items.Tool;
import Items.Item;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import enums.*;
import Items.*;

/**
 *
 * @author Demian
 */
public class Player extends Entity{
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private int thirst;
    private int hunger;
    private Item rightHand;
    private Item leftHand;
    private Item armorSet;

    /**
     * Get the value of thirst
     *
     * @return the value of thirst
     */
    public int getThirst() {
        return thirst;
    }
    
    /**
     * Get the value of hunger
     *
     * @return the value of hunger
     */
    public int getHunger() {
        return hunger;
    }
    
    /**
     * Get the value of hunger
     *
     * @return the value of hunger
     */
    public Item getRightHand() {
        return rightHand;
    }
    
    /**
     * Get the value of hunger
     *
     * @return the value of hunger
     */
    public Item getLeftHand() {
        return leftHand;
    }
    
    /**
     * Get the value of hunger
     *
     * @return the object of armorSet
     */
    public Item getArmorSet() {
        return armorSet;
    }

    //</editor-fold>
    
    public Player(String name, SpriteSheet sprite, Point location, int hitPoints) {
        super(name, sprite, location, hitPoints);
    }
    
    public void move(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void attack(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void useItem(Item item){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void loseHP(int damage){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
