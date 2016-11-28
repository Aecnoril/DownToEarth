package downtoearth.world;

import downtoearth.enums.SpriteLocation;
import downtoearth.enums.TileType;
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
    public static final float SPEED = 1.3f;
    private int spritex, spritey;
    private Random random;
    private Rectangle bounds;
    private Coordinate position;
    private byte type;
    private SpriteManager manager;
    private String name;
    private boolean destroyed;
    
    public Coordinate getPosition(){
        return position;
    }
    
    public String getName(){
        return name;
    }
    
    public Rectangle getBounds(){
        return this.bounds;
    }
    
    public byte getType(){
        return this.type;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    
    public Tile(int xpos, int ypos, byte type, String name) throws SlickException{
        this.random = new Random();
        this.position = new Coordinate(xpos, ypos);
        this.manager = new SpriteManager("res/tiles.png");
        this.type = type;
        this.name = name;
        this.bounds = new Rectangle(position.getXint() + 2 , position.getYint() + 2, 28, 28);
    }
    
    public void move(Input input){     
        
//        if(input.isKeyDown(Input.KEY_W)){ this.position.setY(this.position.getY() + (SPEED));}
//        if(input.isKeyDown(Input.KEY_D)){ this.position.setX(this.position.getX() - (SPEED));}
//        if(input.isKeyDown(Input.KEY_S)){ this.position.setY(this.position.getY() - (SPEED));}
//        if(input.isKeyDown(Input.KEY_A)){ this.position.setX(this.position.getX() + (SPEED));}
//        
//        bounds.setX(position.getX()+2);
//        bounds.setY(position.getY()+2);
    }
    
    public void draw(int portX, int portY){
        SpriteLocation sl = TileType.getSpritePosition(this.type);
        spritex = sl.getSpriteX();
        spritey = sl.getSpriteY();
        bounds.setX(position.getX()+2);
        bounds.setY(position.getY()+2);
        manager.drawSprite(spritex, spritey, (position.getXint()) - (portX ), (position.getYint()) - (portY));
    }
}
