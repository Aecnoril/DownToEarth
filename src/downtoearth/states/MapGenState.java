package downtoearth.states;

import downtoearth.gameUtil.Coordinate;
import downtoearth.states.gui.Button;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MapGenState extends BasicGameState {

    
    private Image backgroundImage;
    private Image mapImage;
    private Coordinate imageCoords;
    
    private int rebound;
    

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        rebound = 0;
        Point middle = new Point(container.getWidth()/2, container.getHeight()/2);
        
        backgroundImage = new Image("res/Backgrounds/MenuBackground.png");
        mapImage = new Image("res/ColorMap.png");
        
        imageCoords = new Coordinate((container.getWidth() - container.getWidth()/3) - 64, 64);
        
        
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("State 1: Menu", 0, 30);
        backgroundImage.draw(0, 0, container.getWidth(), container.getHeight());
        g.setColor(Color.darkGray);
        g.setLineWidth(6);
        g.drawRect(imageCoords.getXint() - 3, imageCoords.getYint() - 3, container.getWidth()/3 + 6, container.getHeight()/3 + 6);
        mapImage.draw(imageCoords.getXint(), imageCoords.getYint(), container.getWidth()/3, container.getHeight()/3);
        //mapImage.draw(container.getWidth() - mapImage.getWidth()/4 , 128, container.getWidth()/4, container.getHeight()/4);
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