/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Multiplayer;

import downtoearth.enums.DirectionType;
import downtoearth.enums.SpriteLocation;
import downtoearth.gameUtil.SpriteManager;
import java.io.Serializable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Demian
 */
public class Contestant implements Serializable{
    private String id;
    private int x;
    private int y;
    private int health;
    private Rectangle bounds;
    private boolean dead;

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public Rectangle getBounds(){
        return this.bounds;
    }
    
    public void setBounds(int posX, int posY)
    {
        bounds.setX(getX()+2 - posX);
        bounds.setY(getY()+2 - posY);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
    
    
    
    public Contestant(String id, int x, int y, int health){
        this.id = id;
        this.x = x;
        this.y = y;
        this.health = health;
        this.bounds = new Rectangle(getX() + 2 , getY() + 2, 28, 28);
    }
        
}
