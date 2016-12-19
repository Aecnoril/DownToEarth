/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items;

import downtoearth.entities.ItemEntity;
import shared.Coordinate;
import downtoearth.states.GameState;
import downtoearth.world.Tile;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Demian
 */
public class TileItem extends Item{
    private Coordinate location;
    
    public TileItem(String name, byte type, double durability, double breakChange) throws SlickException {
        super(name, type, durability, breakChange);
    }
    
    public void place() throws SlickException{
        Tile tile;
        location.x = Mouse.getX();
        location.y = Mouse.getY();
        
        tile = new Tile((int)location.x, (int)location.y, this.type, this.name);
        //GameState.w.tiles.add(tile);
    }  
     
    @Override
    public ItemEntity drop(Coordinate coord) throws SlickException {
        ItemEntity droppedEnt = new ItemEntity(name, coord, type, "res/TestItems.png", this);
        
        return droppedEnt;
    }
}
