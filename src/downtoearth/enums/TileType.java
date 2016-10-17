/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.enums;

import java.util.Random;

/**
 *
 * @author Ruud
 */
public class TileType {
    private static Random random;
    
    public static final byte STONE = 0;
    public static final byte COAL = 1;
    public static final byte GEMSTONE = 2;
    public static final byte TREE = 3;
    
    public static SpriteLocation getSpritePosition(byte type){
        switch(type){
            case STONE:
                return new SpriteLocation(0,0);
            case COAL:
                return new SpriteLocation(1,0);
            case GEMSTONE:
                return new SpriteLocation(2,0);
            case TREE:
                random = new Random();
                int num = random.nextInt(1);
                switch(num){
                    case 1:
                        return new SpriteLocation(0,1);
                    case 2:
                        return new SpriteLocation(1,1);
                }
        }
        return null;
    }
}
