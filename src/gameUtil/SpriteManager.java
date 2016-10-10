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
    private final int width = 32, height = 32;
    private SpriteSheet sprites;
    
    public SpriteManager(String path) throws SlickException{
        Image spritesheet = new Image(path);
        sprites = new SpriteSheet(spritesheet, 32, 32);
    }
    
    public SpriteManager(String path, int width, int height, int spacing, int margin) throws SlickException{
        Image spritesheet = new Image(path);
        sprites = new SpriteSheet(spritesheet, width, height, spacing, margin);
    }
    
    public void getSprite(int x, int y, int xpos, int ypos){
        sprites.startUse();
        sprites.getSubImage(x, y).drawEmbedded(xpos, ypos, width, height);
        sprites.endUse();
    }
    
    public void getCharacterSprite(int x, int y, int xpos, int ypos){
        sprites.startUse();
        sprites.getSubImage(x, y).drawEmbedded(xpos, ypos, 29, 58);
        sprites.endUse();
    }
}
