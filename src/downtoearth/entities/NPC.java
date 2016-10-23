/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.enums.DirectionType;
import downtoearth.enums.MobType;
import downtoearth.enums.SpriteLocation;
import downtoearth.enums.TileType;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import static downtoearth.world.Tile.SPEED;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Demian
 */
public class NPC extends LivingEntity {
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private MobType type;
    
    private Rectangle bounds;
    private byte dir;
    private int spritex;
    private int spritey;
    private SpriteManager sManager;

    /**
     * Get the value of bounds
     *
     * @return the value of bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Gets the value of the mob type
     * @return returns the type as a MobType enum
     */
    public MobType getType() {
        return type;
    }
    
        public byte getDir() {
        return dir;
    }
    
    
    //</editor-fold>
    
    public NPC(String name, Coordinate location, int hitPoints, MobType type, String path) throws SlickException {
        super(name, location, hitPoints, path);
        this.type = type;
        this.bounds = new Rectangle(location.getXint() + 2 , location.getYint() + 2, 28, 28);
        this.dir = DirectionType.SOUTH;
        this.sManager = new SpriteManager("res/playerSprite.png");
    }
    
     public void move(Input input){     
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_W)){
            location.setY(location.getY() + (SPEED * 4));
            location.setX(location.getX() - (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_W)){
            location.setY(location.getY() + (SPEED * 4));
        }
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_S)){
            location.setY(location.getY() - (SPEED * 4));
            location.setX(location.getX() - (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_D)){
            location.setX(location.getX() - (SPEED * 4));
        }
        
        if(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A)){
            location.setY(location.getY() - (SPEED * 4));
            location.setX(location.getX() + (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_S)){
            location.setY(location.getY() - (SPEED * 4));
        }
        
        if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_W)){
            location.setY(location.getY() + (SPEED * 4));
            location.setX(location.getX() + (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_A)){
            location.setX(location.getX() + (SPEED * 4));
        }
        bounds.setX(location.getX()+2);
        bounds.setY(location.getY()+2);
    }

    public void draw() {
        SpriteLocation pos = DirectionType.getStandingSprite(dir);
        System.out.println(this.getName() + ": " + this.getLocation().getXint() + ", " + this.getLocation().getYint());
        sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), location.getXint(), location.getYint());
    }
}
