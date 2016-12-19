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
import downtoearth.world.Tile;
import static downtoearth.world.Tile.SPEED;
import downtoearth.world.World;
import java.util.List;
import java.util.Random;
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
    private boolean moving;
    private World world;
    private SpriteManager sManager;
    private Rectangle colBox;
    public Rectangle getColBox(){
        return colBox;
    }

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
    
    public NPC(String name, Coordinate location, int hitPoints, MobType type, String path, World world) throws SlickException {
        super(name, location, hitPoints, path);
        this.count = 0;
        this.type = type;
        this.bounds = new Rectangle(location.getXint() + 2 , location.getYint() + 2, 28, 28);
        this.dir = DirectionType.SOUTH;
        this.sManager = new SpriteManager("res/playerSprite.png");
        this.moving = true;
        this.world = world;
    }
    
    public void move(Input input, List<Tile> tiles, List<NPC> entities) throws SlickException{
        int randomMoveStateChange = new Random().nextInt(250);
        if((!moving && randomMoveStateChange != 1) || (moving && randomMoveStateChange == 1)) {
            moving = !moving;
        }
        if(moving){
            int randomDirChange = new Random().nextInt(125);
            if(randomDirChange == 1) {
                int randomDir = new Random().nextInt(8) + 1;
                int difference = randomDir - (int) dir;
                if(difference < 0){
                    difference = difference * -1;
                }
                if(difference > 2){
                    randomDir += 2;
                    if(randomDir > 8){
                        randomDir -= 4;
                    }
                }
                dir = (byte) randomDir;
            }
            
            float xa = 0;
            float ya = 0;
        
            switch(dir){
                case DirectionType.NORTH:
                    ya = SPEED * -1; 
                    break;
                case DirectionType.NORTHEAST:
                    ya = SPEED * -1;
                    xa = SPEED;
                    break;
                case DirectionType.EAST:
                    xa = SPEED;
                    break;
                case DirectionType.SOUTHEAST:
                    ya = SPEED;
                    xa = SPEED;
                    break;
                case DirectionType.SOUTH:
                    ya = SPEED;
                    break;
                case DirectionType.SOUTHWEST:
                    ya = SPEED;
                    xa = SPEED * -1;
                    break;
                case DirectionType.WEST:
                    xa = SPEED * -1;
                    break;
                case DirectionType.NORTHWEST:
                    ya = SPEED * -1;
                    xa = SPEED * -1;
            }
            
            if(!collision(tiles, entities)){
                this.location.setX(this.location.getX() + xa);
                this.location.setY(this.location.getY() + ya);
                float newX = this.location.getX() + xa;
                if(xa != 0 && newX >= 0 && newX <= this.world.getMapSize().width){
                   this.location.setX(newX);
                }
                float newY = this.location.getY() + ya;
                if(ya != 0 && newY >= 0 && newY <= this.world.getMapSize().height){
                    this.location.setY(newX);
                }
            }
        }
    }
    
    public boolean collision(List<Tile> tiles, List<NPC> entities) throws SlickException{
        switch(dir){
             case DirectionType.NORTH:
                 colBox = new Rectangle(540-13, 360-14, 26, 1);
                 break;

             case DirectionType.EAST:
                 colBox = new Rectangle(540+14, 360-13, 1, 26);
                 break;

             case DirectionType.SOUTH:
                 colBox = new Rectangle(540-13, 360+14, 26, 1);
                 break;

             case DirectionType.WEST:
                 colBox = new Rectangle(540-16, 360-13, 1, 26);
                 break;
        }
        for(Tile tile : tiles){
            if(this.getColBox().intersects(tile.getBounds())){
                return true;
            }
        }
        for(NPC npc : entities)
        {
            if(this.getColBox().intersects(npc.getBounds())){
                return true;
            }
        }
        return false;
    }   

    public void draw(int posX, int posY) {    
        SpriteLocation pos = DirectionType.getStandingSprite(dir);
        bounds.setX(location.getX()+2 - posX);
        bounds.setY(location.getY()+2 - posY);
        sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), location.getXint() - posX -16, location.getYint() - posY -16);
    }
}
