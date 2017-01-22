/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.Items.Item;
import downtoearth.enums.SpriteLocation;
import downtoearth.enums.Tooltype;
import shared.Coordinate;
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
    
    private final SpriteManager SMANAGER;
    private final Rectangle BOUNDS;
    private final Item TI;
    
    public Rectangle getBounds() {
        return BOUNDS;
    }
    
    public Item getItem(){
        return TI;
    }
    
    public ItemEntity(String name, Coordinate location, int hitPoints, String path, Item ti) throws SlickException {
        super(name, location, hitPoints, path);
        this.BOUNDS = new Rectangle(location.getXint() + 2 , location.getYint() + 2, 28, 28);
        this.SMANAGER = new SpriteManager("res/TestItems.png");
        this.TI = ti;
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
        BOUNDS.setX(super.location.getX()+2);
        BOUNDS.setY(super.location.getY()+2);
    }
    
    public void draw() {
        SpriteLocation pos = Tooltype.getSpriteLocation(TI.getType());
        SMANAGER.drawSprite(pos.getSpriteX(), pos.getSpriteY(), location.getXint(), location.getYint());
    }
    
    
    
}
