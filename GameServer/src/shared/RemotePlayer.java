/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;
import org.newdawn.slick.geom.Rectangle;

/**
 * represents a player character for the other players
 * @author Demian
 */
public class RemotePlayer implements Serializable{
    private String id;
    private Coordinate coords;
    private Rectangle bounds;
    
    private int health;
    private byte dir;

    private boolean moving;
    private boolean attack;
    private boolean dead;

    /**
     * Creates a new opponent player
     * @param id
     * @param coords
     * @param health 
     */
    public RemotePlayer(String id, Coordinate coords, int health){
        this.id = id;
        this.coords = coords;
        this.health = health;
        this.bounds = new Rectangle(coords.getXint() + 2 , coords.getYint() + 2, 28, 28);
        this.dir = DirectionType.NORTH;
    }
    
    public String getId() {
        return id;
    }

    public Coordinate getCoords(){
        return this.coords;
    }
    
    /**
     * sets the x and y of the coordinates
     * @param x
     * @param y 
     */
    public void setCoords(int x, int y){
        this.coords.setX(x);
        this.coords.setY(y);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(coords.getXint() + 2 , coords.getYint() + 2, 28, 28);
    }
    
    public byte getDir(){
        return this.dir;
    }
    
    public void setDir(byte b){
        this.dir = b;
    }
    
    public boolean getMoving(){
        return this.moving;
    }
    
    public void setMoving(boolean moving){
        this.moving = moving;
    }
    
    public boolean getAttacking(){
        return this.attack;
    }
    
    public void setAttacking(boolean attacking){
        this.attack = attacking;
    }
    
    public boolean getDead(){
        return this.dead;
    }
    
    
}
