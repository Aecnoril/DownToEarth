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
        c = new Coordinate(500,950);
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