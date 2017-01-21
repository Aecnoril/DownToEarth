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
public class ArmorType {
    
    //armor
    public static final byte WOODENARMOR    = 1;
    public static final byte STONEARMOR     = 2;
    public static final byte STEELARMOR     = 3;
    public static final byte GEMARMOR       = 4;
    
    public static SpriteLocation getSpriteLocation(byte index){
        switch(index){
            case WOODENARMOR:
                return new SpriteLocation(4, 4);
            case STONEARMOR:
                return new SpriteLocation(5, 4);
            case STEELARMOR:
                return new SpriteLocation(6, 4);
            case GEMARMOR:
                return new SpriteLocation(7, 4);
        }
        return null;
    }
}
