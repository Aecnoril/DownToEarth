package downtoearth.gameUtil;

import shared.Coordinate;

public class Camera {
    
    private Coordinate c;
    private final int width, height;
    
    public Camera(int width, int height, Coordinate c){
        this.width= width;
        this.height = height;
        this.c = c;
    }
    
    public Coordinate getCoordinate(){
        return this.c;
    }
    
    public void setCoordinate(Coordinate c){
        this.c = c;
    }
    
    public int getX(){
        return c.getXint() - width/2;
    }
    
    public int getY(){
        return c.getYint() - height/2;
    }
    
    public int getCenterPosX(){
        return c.getXint();
    }
    
    public int getCenterPosY(){
        return c.getYint();
    }
    
    public int getXX(){
        return c.getXint() + width /2;
    }
    
    public int getYY(){
        return c.getYint() + height /2;
    }
}