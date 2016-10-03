package downtoearth;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class DownToEarth extends BasicGame {

    public DownToEarth(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        File clipFile = new File("C:\\Users\\Ruud\\Documents\\NetBeansProjects\\DownToEarth\\src\\downtoearth\\Sounds\\Korg-01W-Tech-Bass-C2.wav");
        soundLogic.playClip(clipFile);
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new DownToEarth("Simple Slick Game"));
            appgc.setTargetFrameRate(60);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(DownToEarth.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
