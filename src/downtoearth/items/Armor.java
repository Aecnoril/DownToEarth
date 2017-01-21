/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.items;

import downtoearth.entities.ItemEntity;
import shared.Coordinate;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Demian
 */
public class Armor extends Item{
    
    private double damageReduction;
    
    /**
     * Creates a new piece of armour with the given parameters
     * @param name
     * @param type
     * @param durability
     * @param breakChange
     * @param damageReduction
     * @throws SlickException 
     */
    public Armor(String name, byte type, double durability, double breakChange, double damageReduction) throws SlickException {
        super(name, type, durability, breakChange);
        this.damageReduction = damageReduction;
    }

    @Override
    public ItemEntity drop(Coordinate coord) {
      return null;   
    }
    
    public double getDamageReduction(){
        return this.damageReduction;
    }
}
