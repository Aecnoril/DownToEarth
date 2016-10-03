package downtoearth;

import Items.Item;
import Items.TileItem;
import enums.ItemType;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class DownToEarth extends BasicGame {
    
    private static Item i;
    private static Item i2;
    private static Item i3;
    private static Item i4;
    
    public DownToEarth(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        i = new TileItem("jopie", ItemType.WOODENSWORD, 10, 10);
        i2 = new TileItem("jopie", ItemType.STONESWORD, 10, 10);
        i3 = new TileItem("jopie", ItemType.STEELSWORD, 10, 10);
        i4 = new TileItem("jopie", ItemType.GEMSWORD, 10, 10);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setBackground(Color.white);
        i.render(200, 200);
        i2.render(264, 200);
        i3.render(200, 264);
        i4.render(264, 264);
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        try {   
            AppGameContainer appgc;
            appgc = new AppGameContainer(new DownToEarth("Simple Slick Game"));
            appgc.setDisplayMode(1080, 720, false);
            appgc.setTargetFrameRate(60);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(DownToEarth.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
