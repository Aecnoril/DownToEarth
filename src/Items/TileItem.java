/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import enums.ItemType;

/**
 *
 * @author Demian
 */
public class TileItem extends Item{
    
    public TileItem(String name, ItemType type, double durability, double breakChange) {
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
