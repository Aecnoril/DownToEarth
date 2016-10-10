/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author Demian
 */
public class Tooltype {
    
    //swords
    public static final byte WOODENSWORD    = 1;
    public static final byte STONESWORD     = 2;
    public static final byte STEELSWORD     = 3;
    public static final byte GEMSWORD       = 4;
    
    //shields
    public static final byte WOODENSHIELD   = 5;
    public static final byte STONESHIELD    = 6;
    public static final byte STEELSHIELD    = 7;
    public static final byte GEMSHIELD      = 8;
    
    //shovels
    public static final byte WOODENSHOVEL   = 9;
    public static final byte STONESHOVEL    = 10;
    public static final byte STEELSHOVEL    = 11;
    public static final byte GEMSHOVEL      = 12;
    
    //hatchets
    public static final byte WOODENHATCHET  = 13;
    public static final byte STONEHATCHET   = 14;
    public static final byte STEELHATCHET   = 15;
    public static final byte GEMHATCHET     = 16;
    
    //pickaxes
    public static final byte WOODENPICKAXE  = 17;
    public static final byte STONEPICKAXE   = 18;
    public static final byte STEELPICKAXE   = 19;
    public static final byte GEMPICKAXE     = 20;

    public static SpriteLocation getSpriteLocation(byte index){
        switch(index){
            case WOODENSWORD:
                return new SpriteLocation(0,0);
            case STONESWORD:
                return new SpriteLocation(1,0);
            case STEELSWORD:
                return new SpriteLocation(2,0);
            case GEMSWORD:
                return new SpriteLocation(3,0);
            case WOODENSHIELD:
                return new SpriteLocation(4,0);
            case STONESHIELD:
                return new SpriteLocation(5,0);
            case STEELSHIELD:
                return new SpriteLocation(6,0);
            case GEMSHIELD:
                return new SpriteLocation(7,0);
            case WOODENSHOVEL:
                return new SpriteLocation(0,1);
            case STONESHOVEL:
                return new SpriteLocation(1,1);
            case STEELSHOVEL:
                return new SpriteLocation(2,1);
            case GEMSHOVEL:
                return new SpriteLocation(3,1);
            case WOODENHATCHET:
                return new SpriteLocation(4,1);
            case STONEHATCHET:
                return new SpriteLocation(5,1);
            case STEELHATCHET:
                return new SpriteLocation(6,1);
            case GEMHATCHET:
                return new SpriteLocation(7,1);
            case WOODENPICKAXE:
                return new SpriteLocation(0,2);
            case STONEPICKAXE:
                return new SpriteLocation(1,2);
            case STEELPICKAXE:
                return new SpriteLocation(2,2);
            case GEMPICKAXE:
                return new SpriteLocation(3,2);     
        }
        return null;
    }
}
