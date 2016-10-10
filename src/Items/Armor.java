/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import enums.Tooltype;
import enums.SpriteLocation;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Demian
 */
public class Armor extends Item{
    
    private double damageReduction;
    
    public Armor(String name, byte type, double durability, double breakChange, double damageReduction) throws SlickException {
        super(name, type, durability, breakChange);
        this.damageReduction = damageReduction;
    }

    @Override
    public void drop() {
        //TODO: implement
    }
}
