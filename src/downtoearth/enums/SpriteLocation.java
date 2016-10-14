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
public class SpriteLocation {
    private int spriteX;
    private int spriteY;
    
    public SpriteLocation(int x, int y){
        this.spriteX = x;
        this.spriteY = y;
    }
    
    public int getSpriteX(){
        return this.spriteX;
    }
    
    public int getSpriteY(){
        return this.spriteY;
    }
}
