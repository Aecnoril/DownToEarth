/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.world;

import downtoearth.enums.SpriteLocation;
import downtoearth.enums.TileType;
import downtoearth.enums.Tooltype;
import downtoearth.gameUtil.Camera;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Sanko
 */
public class Tile {
    private final int size = 32;
    private int spritex, spritey;
    
    private Coordinate position;
    private byte type;
    private SpriteManager manager;
    
    public Coordinate getPosition(){
        return position;
    }
    
    public Tile(int xpos, int ypos, byte type) throws SlickException{
        this.position = new Coordinate(xpos, ypos);
        this.manager = new SpriteManager("res/tiles.png");
        this.type = type;
    }
    
    public void draw(Camera c){
        if(spritex == Integer.MIN_VALUE){
            SpriteLocation sl = TileType.getSpritePosition(this.type);
            spritex = sl.getSpriteX();
            spritey = sl.getSpriteY(); 
        }
        manager.drawSprite(spritex, spritey, this.position.getXint() - c.getX(), this.position.getYint() - c.getY());
    }
}
