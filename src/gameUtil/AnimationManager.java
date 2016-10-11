/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameUtil;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Vernoxius
 */
public class AnimationManager {

    private SpriteSheet spriteSheet;
    private Animation animation;
    private int nrOfFrames;
    private int startingSprite;
    private int currentFrame;

    public AnimationManager(String path, int nrOfFrames, int width, int height, int startingSprite) throws SlickException {
        this.nrOfFrames = nrOfFrames;
        this.spriteSheet = new SpriteSheet(path, width, height, 1);
        this.startingSprite = startingSprite;
        this.animation = new Animation();
    }

    public void Animate() throws SlickException {
        animation.setAutoUpdate(true);
        currentFrame = startingSprite;
        for (int frame = 0; frame < nrOfFrames; frame++) {
            animation.addFrame(spriteSheet.getSprite(currentFrame, 0), 200);
            currentFrame++;
        }
    }

    public void DrawAnimation(int width, int height) {
        animation.draw(width, height);
    }
}
