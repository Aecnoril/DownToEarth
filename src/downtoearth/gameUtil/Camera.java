/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.gameUtil;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Camera {
    
    private final float SPEED = 0.5f;
    
    private Coordinate c;
    private int width, height;
    
    public Camera(int width, int height){
        this.width= width;
        this.height = height;
        c = new Coordinate(500,950);
    }
    
    public void move(GameContainer gc){
        if(gc.getInput().isKeyDown(Input.KEY_DOWN)){
            c.setY(c.getY() + SPEED);
        }
        if(gc.getInput().isKeyDown(Input.KEY_RIGHT)){
            c.setX(c.getX() + SPEED);
        }
        if(gc.getInput().isKeyDown(Input.KEY_UP)){
            c.setY(c.getY() - SPEED);
        }
        if(gc.getInput().isKeyDown(Input.KEY_LEFT)){
            c.setX(c.getX() - SPEED);
        }
    }
    
    public Coordinate getCoordinate(){
        return this.c;
    }
    
    public int getX(){
        return (int)c.getX();
    }
    
    public int getY(){
        return (int)c.getY();
    }
    
    public int getCenterPosX(){
        return (int)c.getX() + width / 2;
    }
    
    public int getCenterPosY(){
        return (int)c.getY() + height / 2;
    }
    
    
    
}