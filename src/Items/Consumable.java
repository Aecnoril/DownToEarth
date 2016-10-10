/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import enums.ItemType;
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
    public void drop() {
        //TODO: implement
    }    
        
    public void eat(){
        //TODO: implement 
    }
    
    @Override
    public void render(int xpos, int ypos) {
        //TODO: implement
    }

    
    
}
