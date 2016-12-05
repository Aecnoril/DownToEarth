package downtoearth.gameUtil;

public class Camera {
    
    private Coordinate c;
    private int width, height;
    
    public Camera(int width, int height){
        this.width= width;
        this.height = height;
        c = new Coordinate(500,1050);
    }
    
    public Coordinate getCoordinate(){
        return this.c;
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