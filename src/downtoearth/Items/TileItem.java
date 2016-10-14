/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items;

import downtoearth.enums.Tooltype;
import downtoearth.enums.SpriteLocation;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Demian
 */
public class TileItem extends Item{
    
    public TileItem(String name, byte type, double durability, double breakChange) throws SlickException {
        super(name, type, durability, breakChange);
    }
    
    public void place(){
        //TODO: implement
    }  
    
    @Override
    public void drop(){
        //TODO: implement
    }
}
