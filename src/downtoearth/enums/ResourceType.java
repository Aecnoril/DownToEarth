    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.enums;

/**
 *
 * @author Demian
 */
public class ResourceType {
    
    //recourses
    public static final byte COWHIDE        = 1;
    public static final byte WOOL           = 2;
    public static final byte GRAVEL         = 3;
    public static final byte SAND           = 4;
    public static final byte STONE          = 5;
    public static final byte WOOD           = 6;
    public static final byte COAL           = 7;
    public static final byte STEEL          = 8;
    public static final byte GEMSTONE       = 9;
    public static final byte BUCKET         = 10;
    public static final byte STICK          = 11;
    
    public static SpriteLocation getSpriteLocation(byte index){
        switch(index){
            case COWHIDE:
                return new SpriteLocation(4, 2);
            case WOOL:
                return new SpriteLocation(5, 2);
            case GRAVEL:
                return new SpriteLocation(6, 2);
            case SAND:
                return new SpriteLocation(7, 2);
            case STONE:
                return new SpriteLocation(0, 3);
            case WOOD:
                return new SpriteLocation(1, 3);
            case COAL:
                return new SpriteLocation(2, 3);
            case STEEL:
                return new SpriteLocation(3, 3);
            case GEMSTONE:
                return new SpriteLocation(4, 3);
            case BUCKET:
                return new SpriteLocation(5, 3);
            case STICK:
                return new SpriteLocation(6, 3);
        }
        return null;
    }
}
