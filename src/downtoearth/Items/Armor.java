/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items;

import downtoearth.entities.ItemEntity;
import shared.Coordinate;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Demian
 */
public class Armor extends Item{
    
    private final double DAMAGE_REDUCTION;
    
    public Armor(String name, byte type, double durability, double breakChange, double damageReduction) throws SlickException {
        super(name, type, durability, breakChange);
        this.DAMAGE_REDUCTION = damageReduction;
    }

    @Override
    public ItemEntity drop(Coordinate coord) {
      return null;   
    }
}
