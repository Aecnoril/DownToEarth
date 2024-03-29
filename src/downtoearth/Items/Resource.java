/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items;

import downtoearth.entities.ItemEntity;
import downtoearth.enums.Tooltype;
import downtoearth.enums.SpriteLocation;
import downtoearth.gameUtil.Coordinate;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Demian
 */
public class Resource extends Item {

    public Resource(String name, byte type, double durability, double breakChange) throws SlickException {
        super(name, type, durability, breakChange);
    }

    @Override
    public void drop(Coordinate coord) throws SlickException {
        ItemEntity droppedEnt = new ItemEntity(name, coord, type, name, this);
    }
}
