/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.gameUtil;

import java.io.Serializable;
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
	
	public SpriteManager(String path, int width, int height, int spacing, int margin) throws SlickException{
        Image spritesheet = new Image(path);
        sprites = new SpriteSheet(spritesheet, width, height, spacing, margin);
    }
    
    public void drawSprite(int spriteX, int spriteY, int xpos, int ypos){
        sprites.startUse();
        sprites.getSubImage(spriteX, spriteY).drawEmbedded(xpos, ypos, this.size, this.size);
        sprites.endUse();
    }

    public void scaleSprite(int spriteX, int spriteY, int xpos, int ypos, int size){
        sprites.startUse();
        sprites.getSubImage(spriteX, spriteY).drawEmbedded(xpos, ypos, size, size);
        sprites.endUse();
    }
	
    public void getCharacterSprite(int x, int y, int xpos, int ypos){
        sprites.startUse();
        sprites.getSubImage(x, y).drawEmbedded(xpos, ypos, 29, 58);
        sprites.endUse();
    }
}
