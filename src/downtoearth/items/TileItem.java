/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.items;

import downtoearth.entities.ItemEntity;
import shared.Coordinate;
import downtoearth.world.Tile;
import downtoearth.world.World;
import org.newdawn.slick.SlickException;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Demian
 */
public class TileItem extends Item{
    private Coordinate location;
    
    /**
     * creates a tile item with the given parameters
     * @param name
     * @param type
     * @param durability
     * @param breakChange
     * @throws SlickException 
     */
    public TileItem(String name, byte type, double durability, double breakChange) throws SlickException {
        super(name, type, durability, breakChange);
    }
    
<<<<<<< HEAD:src/downtoearth/items/TileItem.java
    /**
     * draws the tile item at the mouse location
     * @throws SlickException 
     */
    public void place() throws SlickException{
        Tile tile;
        location.setX(Mouse.getX());
        location.setY(Mouse.getY());
        
        tile = new Tile(location.getXint(), location.getYint(), this.type, this.name);
        World.placeTileItem(tile);
=======
    public Tile place() throws SlickException{
        Tile tile;
        location = GameState.w.getPlayer().getCoordinate();
        
        tile = new Tile((int)location.x, (int)location.y, this.type, this.name);
        return tile;
>>>>>>> endpoint:src/downtoearth/Items/TileItem.java
    }  
     
    @Override
    public ItemEntity drop(Coordinate coord) throws SlickException {
        ItemEntity droppedEnt = new ItemEntity(name, coord, type, "res/TestItems.png", this);
        
        return droppedEnt;
    }
}
