/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/**
 *
 * @author Sanko
 */
public class DirectionType {
    
    public static final byte NORTH      = 1;
    public static final byte NORTHEAST  = 2;
    public static final byte EAST       = 3;
    public static final byte SOUTHEAST  = 4;
    public static final byte SOUTH      = 5;
    public static final byte SOUTHWEST  = 6;
    public static final byte WEST       = 7;
    public static final byte NORTHWEST  = 8;
    
    public static SpriteLocation getStartingPos(byte dir){
        switch(dir){
            case NORTH:
                return new SpriteLocation(0,0);
            case NORTHEAST:
                return new SpriteLocation(0,3);
            case EAST:
                return new SpriteLocation(0,1);
            case SOUTHEAST:
                return new SpriteLocation(4,3);
            case SOUTH:
                return new SpriteLocation(4,0);
            case SOUTHWEST:
                return new SpriteLocation(0,2);
            case WEST:
                return new SpriteLocation(4,1);
            case NORTHWEST:
                return new SpriteLocation(4,2);
        }
        return null;
    }
    
    public static SpriteLocation getStandingSprite(byte dir){
        switch(dir){
             case NORTH:
                 return new SpriteLocation(0,0);
             case NORTHEAST:
                 return new SpriteLocation(2,1);
             case EAST:
                 return new SpriteLocation(1,0);
             case SOUTHEAST:
                 return new SpriteLocation(1,1);
             case SOUTH:
                 return new SpriteLocation(2,0);
             case SOUTHWEST:
                 return new SpriteLocation(0,1);
             case WEST:
                 return new SpriteLocation(3,0);
             case NORTHWEST:
                 return new SpriteLocation(3,1);
         }
         return null; 
    }
}
