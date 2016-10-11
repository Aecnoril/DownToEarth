package downtoearth;

import Entities.NPC;
import enums.DirectionType;
import enums.MobType;
import gameUtil.AnimationManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

public class DownToEarth extends BasicGame {
    
    private NPC npc;
    private AnimationManager animationManager;
    //private Animation animation
    
    public DownToEarth(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        //npc = new NPC("bob", new Point(50,50), 100, MobType.Bird, "D:\\School\\J2\\S1\\Software\\PT\\Software\\DownToEarth\\Assets\\SpriteSheets\\TestCharSprite.png");
        animationManager = new AnimationManager("D:\\School\\J2\\S1\\Software\\PT\\Software\\DownToEarth\\Assets\\SpriteSheets\\TestCharSprite.png", 3, 31, 47, 0);
        animationManager.Animate();
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        animationManager.DrawAnimation(50, 50);
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        try {   
            AppGameContainer appgc;
            appgc = new AppGameContainer(new DownToEarth("Simple Slick Game"));
            appgc.setDisplayMode(600, 800, false);
            appgc.setTargetFrameRate(60);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(DownToEarth.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
