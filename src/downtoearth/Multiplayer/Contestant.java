/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Multiplayer;

import java.io.Serializable;

/**
 *
 * @author Demian
 */
public class Contestant implements Serializable{
    private String id;
    private int x;
    private int y;
    private int health;

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
    
    public Contestant(String id, int x, int y, int health){
        this.id = id;
        this.x = x;
        this.y = y;
        this.health = health;
    }
}
