package shared;

import java.io.Serializable;

/**
 * this represents the x and y coordinates within the gameworld
 * @author Vernoxius
 */
public class Coordinate implements Serializable{
    
    private float x;
    private float y;
    
    /**
     * creates a coordinate with the given x and y
     * @param x the x value of the coordinate
     * @param y the y value of the coordinate
     */
    public Coordinate (float x, float y) {
        this.x = x;
        this.y = y;
    }
    
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
    
    /**
     * @return the x as an integer
     */
    public int getXint(){
        return Math.round(x);
    }
    
    /**
     * @return the y as an integer
     */
    public int getYint(){
        return Math.round(y);
    }
    
    /**
     * calculates the distance between two coordinate points
     * @param pos1
     * @param pos2
     * @return 
     */
    public static float distance (Coordinate pos1, Coordinate pos2)
    {
        return (float)(Math.sqrt((pos1.x-pos2.x)*(pos1.x-pos2.x) + (pos1.y-pos2.y)*(pos1.y-pos2.y)));
    }
    
    /**
     * 
     * @return an new coordinate at point (0,0)
     */
    public static Coordinate origin() {
        return new Coordinate(0, 0);
    }
}
