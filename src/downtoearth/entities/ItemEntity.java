/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.Items.Item;
import downtoearth.Items.TileItem;
import downtoearth.enums.DirectionType;
import downtoearth.enums.SpriteLocation;
import downtoearth.enums.Tooltype;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import static downtoearth.world.Tile.SPEED;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author koenv
 */
public class ItemEntity extends Entity {
    
    private SpriteManager sManager;
    private Rectangle bounds;
    private Item ti;
    
    public Rectangle getBounds() {
        return bounds;
    }
    
    public Item getItem(){
        return ti;
    }
    
    public ItemEntity(String name, Coordinate location, int hitPoints, String path, Item ti) throws SlickException {
        super(name, location, hitPoints, path);
        this.bounds = new Rectangle(location.getXint() + 2 , location.getYint() + 2, 28, 28);
        this.sManager = new SpriteManager("res/TestItems.png");
        this.ti = ti;
    }
    
    public void move(Input input){     
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_W)){
            super.location.setY(super.location.getY() + (SPEED * 4));
            super.location.setX(super.location.getX() - (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_W)){
            super.location.setY(super.location.getY() + (SPEED * 4));
        }
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_S)){
            super.location.setY(super.location.getY() - (SPEED * 4));
            super.location.setX(super.location.getX() - (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_D)){
            super.location.setX(super.location.getX() - (SPEED * 4));
        }
        
        if(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A)){
            super.location.setY(super.location.getY() - (SPEED * 4));
            super.location.setX(super.location.getX() + (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_S)){
            super.location.setY(super.location.getY() - (SPEED * 4));
        }
        
        if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_W)){
            super.location.setY(super.location.getY() + (SPEED * 4));
            super.location.setX(super.location.getX() + (SPEED * 4));
        }
        else if(input.isKeyDown(Input.KEY_A)){
            super.location.setX(super.location.getX() + (SPEED * 4));
        }
        bounds.setX(super.location.getX()+2);
        bounds.setY(super.location.getY()+2);
    }
    
    public void draw() {
        SpriteLocation pos = Tooltype.getSpriteLocation(ti.getType());
        sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), location.getXint(), location.getYint());
    }
    
    
    
}
