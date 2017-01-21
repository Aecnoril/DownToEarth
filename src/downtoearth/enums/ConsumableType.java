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
public class ConsumableType {
    
    //consumable
    public static final byte APPLE          = 1;
    public static final byte BREAD          = 2;
    public static final byte FISH           = 3;
    public static final byte MANGO          = 4;
    
    public static SpriteLocation getSpriteLocation(byte index){
        switch(index){
            case APPLE:
                return new SpriteLocation(2,4);
            case BREAD:
                return new SpriteLocation(3,4);
            case FISH:
                return new SpriteLocation(4,4);
            case MANGO:
                return new SpriteLocation(5,4); 
        }
        return null;
    }
}
