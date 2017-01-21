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
public class Type {

    //swords
    public static final byte WOODENSWORD = 1;
    public static final byte STONESWORD = 2;
    public static final byte STEELSWORD = 3;
    public static final byte GEMSWORD = 4;

    //shields
    public static final byte WOODENSHIELD = 5;
    public static final byte STONESHIELD = 6;
    public static final byte STEELSHIELD = 7;
    public static final byte GEMSHIELD = 8;

    //shovels
    public static final byte WOODENSHOVEL = 9;
    public static final byte STONESHOVEL = 10;
    public static final byte STEELSHOVEL = 11;
    public static final byte GEMSHOVEL = 12;

    //hatchets
    public static final byte WOODENHATCHET = 13;
    public static final byte STONEHATCHET = 14;
    public static final byte STEELHATCHET = 15;
    public static final byte GEMHATCHET = 16;

    //pickaxes
    public static final byte WOODENPICKAXE = 17;
    public static final byte STONEPICKAXE = 18;
    public static final byte STEELPICKAXE = 19;
    public static final byte GEMPICKAXE = 20;

    //recourses
    public static final byte COWHIDE = 21;
    public static final byte WOOL = 22;
    public static final byte GRAVEL = 23;
    public static final byte SAND = 24;
    public static final byte STONE = 25;
    public static final byte WOOD = 26;
    public static final byte COAL = 28;
    public static final byte STEEL = 29;
    public static final byte GEMSTONE = 30;
    public static final byte BUCKET = 31;
    public static final byte STICK = 32;
    public static final byte TREE = 33;

    //consumable
    public static final byte APPLE = 34;
    public static final byte BREAD = 35;
    public static final byte FISH = 36;
    public static final byte MANGO = 37;

    //armor
    public static final byte WOODENARMOR = 38;
    public static final byte STONEARMOR = 39;
    public static final byte STEELARMOR = 40;
    public static final byte GEMARMOR = 41;

    public static SpriteLocation getSpriteLocation(byte index) {
        switch (index) {    
            case WOODENSWORD:
                return new SpriteLocation(0, 0);
            case STONESWORD:
                return new SpriteLocation(1, 0);
            case STEELSWORD:
                return new SpriteLocation(2, 0);
            case GEMSWORD:
                return new SpriteLocation(3, 0);
            
            case WOODENSHIELD:
                return new SpriteLocation(4, 0);
            case STONESHIELD:
                return new SpriteLocation(5, 0);
            case STEELSHIELD:
                return new SpriteLocation(6, 0);
            case GEMSHIELD:
                return new SpriteLocation(7, 0);
            
            case WOODENSHOVEL:
                return new SpriteLocation(0, 1);
            case STONESHOVEL:
                return new SpriteLocation(1, 1);
            case STEELSHOVEL:
                return new SpriteLocation(2, 1);
            case GEMSHOVEL:
                return new SpriteLocation(3, 1);
            
            case WOODENHATCHET:
                return new SpriteLocation(4, 1);
            case STONEHATCHET:
                return new SpriteLocation(5, 1);
            case STEELHATCHET:
                return new SpriteLocation(6, 1);
            case GEMHATCHET:
                return new SpriteLocation(7, 1);
            
            case WOODENPICKAXE:
                return new SpriteLocation(0, 2);
            case STONEPICKAXE:
                return new SpriteLocation(1, 2);
            case STEELPICKAXE:
                return new SpriteLocation(2, 2);
            case GEMPICKAXE:
                return new SpriteLocation(3, 2);
           
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
            case TREE:
                return new SpriteLocation(7, 3);
           
            case APPLE:
                return new SpriteLocation(0, 4);
            case BREAD:
                return new SpriteLocation(1, 4);
            case FISH:
                return new SpriteLocation(2, 4);
            case MANGO:
                return new SpriteLocation(3, 4);
            
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
