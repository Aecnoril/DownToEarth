/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.enums.DirectionType;
import downtoearth.enums.MobType;
import downtoearth.enums.SpriteLocation;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import static downtoearth.world.Tile.SPEED;
import downtoearth.world.World;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Demian
 * @author Sander
 */
public class NPC extends LivingEntity {
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private MobType type;
    private int count;
    private Rectangle bounds;
    private byte dir;
    private SpriteManager sManager;
    private World world;
    private NPC npc;
    private int nameint;

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
        this.count = 0;
        this.type = type;
        this.bounds = new Rectangle(location.getXint() + 2 , location.getYint() + 2, 28, 28);
        this.dir = DirectionType.SOUTH;
        this.sManager = new SpriteManager("res/playerSprite.png");
        nameint=0;
    }
    
    public void move(Input input){     
        
    }

    public void draw(int posX, int posY) {    
        SpriteLocation pos = DirectionType.getStandingSprite(dir);
        bounds.setX(location.getX()+2 - posX);
        bounds.setY(location.getY()+2 - posY);
        sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), location.getXint() - posX -16, location.getYint() - posY -16);
    }
}
