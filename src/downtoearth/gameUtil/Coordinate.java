/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.gameUtil;

/**
 * Stores a coordinate. X-Y or X-Y-X
 * @author koenv
 */
public class Coordinate {
    
    public float x;
    public float y;
    public float z;
    
        /**
     * @return the x
     */
    public float getX() {
        return x;
    }
    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }
    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }
    
    public int getXint(){
        return (int)Math.round(x);
    }
    
    public int getYint(){
        return (int)Math.round(y);
    }
    
    public Coordinate (float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public static float distance (Coordinate pos1, Coordinate pos2)
    {
        return (float)(Math.sqrt((pos1.x-pos2.x)*(pos1.x-pos2.x) + (pos1.y-pos2.y)*(pos1.y-pos2.y)));
    }
    
    public static Coordinate Origin() {
        return new Coordinate(0, 0);
    }


    
}
