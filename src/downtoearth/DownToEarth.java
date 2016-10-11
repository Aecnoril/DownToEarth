package downtoearth;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class DownToEarth extends BasicGame {
    private static soundLogic sL;
    public DownToEarth(String gamename) throws SlickException {
        super(gamename);
        sL = new soundLogic();
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

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException, SlickException {
        File clipFile = new File("C:\\Users\\Tomt\\Documents\\GitHub\\DownToEarth\\src\\downtoearth\\Sounds\\treeFallingA.wav");
        soundLogic.readFile();
        File test;
        test = new File(soundLogic.paths.get(0));
        soundLogic.playClip(test);
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
