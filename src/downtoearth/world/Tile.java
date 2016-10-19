/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.world;

import static downtoearth.entities.Player.SPEED;
import downtoearth.enums.DirectionType;
import downtoearth.enums.SpriteLocation;
import downtoearth.enums.TileType;
import downtoearth.enums.Tooltype;
import downtoearth.gameUtil.Camera;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import java.util.Random;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Sanko
 */
public class Tile {
    public static final float SPEED = 0.3f;
    private int spritex, spritey;
    private Random random;
    
    private Coordinate position;
    private byte type;
    private SpriteManager manager;
    
    public Coordinate getPosition(){
        return position;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(position.getXint() + 2, position.getYint() + 2, 28, 28);
    }
    
    public Tile(int xpos, int ypos, byte type) throws SlickException{
        this.random = new Random();
        this.position = new Coordinate(xpos, ypos);
        this.manager = new SpriteManager("res/tiles.png");
        this.type = type;
    }
    
    public void move(Input input){     
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_W)){
            position.setY(position.getY() + (SPEED * 4));
            position.setX(position.getX() - (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_W)){
            position.setY(position.getY() + (SPEED * 4));
        }
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_S)){
            position.setY(position.getY() - (SPEED * 4));
            position.setX(position.getX() - (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_D)){
            position.setX(position.getX() - (SPEED * 4));
        }
        
        if(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A)){
            position.setY(position.getY() - (SPEED * 4));
            position.setX(position.getX() + (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_S)){
            position.setY(position.getY() - (SPEED * 4));
        }
        
        if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_W)){
            position.setY(position.getY() + (SPEED * 4));
            position.setX(position.getX() + (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_A)){
            position.setX(position.getX() + (SPEED * 4));
        }  
    }
    
    public void draw(Camera c){
        SpriteLocation sl = TileType.getSpritePosition(this.type);
        spritex = sl.getSpriteX();
        spritey = sl.getSpriteY();
        manager.drawSprite(spritex, spritey, position.getXint(), position.getYint());
    }
}
