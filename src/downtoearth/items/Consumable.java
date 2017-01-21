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
public class Consumable extends Item{

    /**
     * Creates a consumable with the given parameters
     * @param name
     * @param type
     * @param durability
     * @param breakChange
     * @throws SlickException 
     */
    public Consumable(String name, byte type, double durability, double breakChange) throws SlickException {
        super(name, type, durability, breakChange);
    }

    @Override
    public ItemEntity drop(Coordinate coord) {
        return null; 
    } 
}
