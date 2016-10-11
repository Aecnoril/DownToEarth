/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameUtil;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Demian
 */
public class SpriteManager {
    private final int resolution = 32;
    private final int size = 32;
    private SpriteSheet sprites;
    
    public SpriteManager(String path) throws SlickException{
        Image spritesheet = new Image(path); 
        sprites = new SpriteSheet(spritesheet, resolution, resolution);
    }
    
    public void drawSprite(int spriteX, int spriteY, float xpos, float ypos){
        sprites.startUse();
        System.out.println("posX: " + xpos + " posY: " + ypos);
        sprites.getSubImage(spriteX, spriteY).drawEmbedded(xpos, ypos, this.size, this.size);
        sprites.endUse();
    }
    
    public void scaleSprite(int spriteX, int spriteY, float xpos, float ypos, int size){
        sprites.startUse();
        System.out.println("posX: " + xpos + " posY: " + ypos);
        sprites.getSubImage(spriteX, spriteY).drawEmbedded(xpos, ypos, size, size);
        sprites.endUse();
    }
    
    public void rotateSprite(int spriteX, int spriteY, float xpos, float ypos, int rotation){
        sprites.startUse();
        Image i = sprites.getSubImage(spriteX, spriteY);
        i.setCenterOfRotation((int)i.getWidth() / 2, (int)i.getHeight() / 2);
        i.setRotation((int)rotation);
        System.out.println("posX: " + xpos + " posY: " + ypos);
        i.draw((int)xpos, (int)ypos);
        i.setRotation((int)-rotation);
        sprites.endUse();
    }
}
