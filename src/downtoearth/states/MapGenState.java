
import downtoearth.states.gui.Button;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MapGenState extends BasicGameState {

    
    private Image backgroundImage;
    
    private int rebound;

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        rebound = 0;
        Point middle = new Point(container.getWidth()/2, container.getHeight()/2);
        
        backgroundImage = new Image("res/Backgrounds/MenuBackground.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("State 1: Menu", 0, 30);
        backgroundImage.draw(0, 0, container.getWidth(), container.getHeight());
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(rebound == 5){

        }
        else{
           rebound++; 
        }
        
    }
}