/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.gameUtil;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Camera {
    
    private Coordinate c;
    private int width, height;
    
    public Camera(int width, int height){
        this.width= width;
        this.height = height;
        c = new Coordinate(500,300);
    }
    
    public Coordinate getCoordinate(){
        return this.c;
    }
    
    public int getX(){
        return c.getXint();
    }
    
    public int getY(){
        return c.getYint();
    }
    
    public int getCenterPosX(){
        return c.getXint() + width / 2;
    }
    
    public int getCenterPosY(){
        return c.getYint() + height / 2;
    }
    
    public int getXX(){
        return c.getXint() + width;
    }
    
    public int getYY(){
        return c.getYint() + height;
    }
}