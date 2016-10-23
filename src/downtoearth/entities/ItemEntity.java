/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author koenv
 */
public class ItemEntity extends Entity {
    
    public ItemEntity(String name, Point location, int hitPoints, String path) throws SlickException {
        super(name, location, hitPoints, path);
    }
    
    
    
}
