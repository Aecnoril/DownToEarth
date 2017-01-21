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
public class Consumable extends Item{

    public Consumable(String name, byte type, double durability, double breakChange) throws SlickException {
        super(name, type, durability, breakChange);
    }

    @Override
    public ItemEntity drop(Coordinate coord) {
        return null; 
    }  
        
    public void eat(){
        //TODO: implement 
    }   
}
