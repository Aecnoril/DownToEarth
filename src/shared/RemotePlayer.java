/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Demian
 */
public class RemotePlayer implements Serializable{
    private String id;
    private Coordinate coords;
    private Rectangle bounds;
    
    private int health;
    public byte dir;

    public boolean moving;
    public boolean attack;
    public boolean dead;

    public String getId() {
        return id;
    }

    public Coordinate getCoords(){
        return this.coords;
    }
    
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
    
    public RemotePlayer(String id, Coordinate coords, int health){
        this.id = id;
        this.coords = coords;
        this.health = health;
        this.bounds = new Rectangle(coords.getXint() + 2 , coords.getYint() + 2, 28, 28);
    }
}
