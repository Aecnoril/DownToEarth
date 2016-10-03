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
import org.newdawn.slick.Graphics;

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
     * Set the value of thirst
     *
     * @param thirst new value of thirst
     */
    public void setThirst(int thirst) {
        this.thirst = thirst;
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
     * Set the value of hunger
     *
     * @param hunger new value of hunger
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
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
     * Set the value of hunger
     *
     * @param hunger new value of hunger
     */
    public boolean setRightHand(Item item) {
        if(item instanceof Tool){
            this.rightHand = item;
            return true;
        }
        else{
            return false;
        }                
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
     * Set the value of leftHand
     *
     * @param item new object of leftHand
     */
    public boolean setLeftHand(Item item) {
        if(item instanceof Tool){
            this.leftHand = item;
            return true;
        }
        else{
            return false;
        } 
    }
    
    /**
     * Get the value of hunger
     *
     * @return the object of armorSet
     */
    public Item getArmorSet() {
        return armorSet;
    }

    /**
     * Set the armorSet of hunger
     *
     * @param item new object of armorSet
     */
    public boolean setArmorSet(Item item) {
        if(item instanceof Armor){
            this.armorSet = item;
            return true;
        }
        else{
            return false;
        } 
    }

    //</editor-fold>
    
    public Player(String name, SpriteSheet sprite, Point location, int hitPoints) {
        super(name, sprite, location, hitPoints);
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

