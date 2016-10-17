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
import java.awt.event.KeyEvent;

/**
 *
 * @author Tomt
 */
public class Player extends Entity{
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private int thirst;
    private int hunger;
    private Item rightHand;
    private Item leftHand;
    private Item armorSet;
    
    private int dx;
    private int dy;
    private int y;
    private int x;

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public int getX() {
        return x;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public int getY() {
        return y;
    }


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
        x+= dx;
        y+=dy;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key==KeyEvent.VK_W)
        {
            dy = -1;
        }
        if (key==KeyEvent.VK_S)
        {
            dy=1;
        }
        if(key==KeyEvent.VK_A)
        {
            dx = -1;
        }
        if (key==KeyEvent.VK_D)
        {
            dx=1;
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key==KeyEvent.VK_W)
        {
            dy = 0;
        }
        if (key==KeyEvent.VK_S)
        {
            dy=0;
        }
        if(key==KeyEvent.VK_A)
        {
            dx = 0;
        }
        if (key==KeyEvent.VK_D)
        {
            dx=0;
        }
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
