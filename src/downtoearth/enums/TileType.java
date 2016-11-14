/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.enums;

/**
 *
 * @author Ruud
 */
public class TileType {
    
    public static final byte STONE          = 21;
    public static final byte COAL           = 22;
    public static final byte GEMSTONE       = 23;
    public static final byte TREE           = 24;
    
    public static SpriteLocation getSpritePosition(byte type){
        switch(type){
            case STONE:
                return new SpriteLocation(0,0);
            case COAL:
                return new SpriteLocation(1,0);
            case GEMSTONE:
                return new SpriteLocation(2,0);
            case TREE:
                return new SpriteLocation(1,1);
        }
        return null;
    }
}
