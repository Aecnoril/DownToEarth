package downtoearth;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;


public class DownToEarth extends BasicGame {

    worldGen.WorldGen worldGen = new worldGen.WorldGen(new gameUtil.Coordinate(128, 128));
    
	public DownToEarth(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
            worldGen.GenerateMatrix();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
            
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
            
	}

	public static void main(String[] args) {
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