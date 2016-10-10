/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Items.Item;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Demian
 */
public class Player extends LivingEntity{
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private int thirst;
    private int hunger;

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

    //</editor-fold>
    
    public Player(String name, Point location, int hitPoints, String path) throws SlickException {
        super(name, location, hitPoints, path);
    }
    
    public void useItem(Item item){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

