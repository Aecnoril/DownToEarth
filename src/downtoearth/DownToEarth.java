package downtoearth;

import Items.Item;
import Items.TileItem;
import enums.Tooltype;
import java.io.File;
import Entities.NPC;
import enums.DirectionType;
import enums.MobType;
import gameUtil.AnimationManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

public class DownToEarth extends BasicGame {
    
    private static Item i;
    private static Item i2;
    private static Item i3;
    private static Item i4;
    private float rotate = 125;

    private NPC npc;
    private AnimationManager animationManager;
    //private Animation animation
    
    public DownToEarth(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        i = new TileItem("jopie", Tooltype.WOODENSWORD, 10, 10);
        i2 = new TileItem("jopie", Tooltype.STONESWORD, 10, 10);
        i3 = new TileItem("jopie", Tooltype.STEELSWORD, 10, 10);
        i4 = new TileItem("jopie", Tooltype.GEMSWORD, 10, 10);

        //npc = new NPC("bob", new Point(50,50), 100, MobType.Bird, "D:\\School\\J2\\S1\\Software\\PT\\Software\\DownToEarth\\Assets\\SpriteSheets\\TestCharSprite.png");
        animationManager = new AnimationManager("D:\\School\\J2\\S1\\Software\\PT\\Software\\DownToEarth\\Assets\\SpriteSheets\\TestCharSprite.png", 3, 31, 47, 0);
        animationManager.Animate();
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if(Mouse.isButtonDown(0)){
            rotate = 125;
        }
        
        if(Mouse.isButtonDown(1)){
            rotate = 45;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setBackground(Color.white);
        i3.render(gc.getScreenWidth()/2, gc.getScreenHeight()/2, 200);
        i2.render(gc.getScreenWidth()/2, gc.getScreenHeight()/2, rotate);
        i.render(gc.getScreenWidth()/2, gc.getScreenHeight()/2);

        animationManager.DrawAnimation(50, 50);
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        try {   
            AppGameContainer appgc;
            appgc = new AppGameContainer(new DownToEarth("DownToEarth"));
            appgc.setDisplayMode(1920, 1080, false);
            appgc = new AppGameContainer(new DownToEarth("Simple Slick Game"));
            appgc.setDisplayMode(600, 800, false);

            appgc.setTargetFrameRate(60);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(DownToEarth.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
