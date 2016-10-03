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
    private final int resolution = 16;
    private final int size = 64;
    private SpriteSheet sprites;
    
    public SpriteManager(String path) throws SlickException{
        Image spritesheet = new Image(path); 
        sprites = new SpriteSheet(spritesheet, resolution, resolution);
    }
    
    public void getSprite(int x, int y, int xpos, int ypos){
        sprites.startUse();
        sprites.getSubImage(x, y).drawEmbedded(xpos, ypos, size, size);
        sprites.endUse();
    }
}
