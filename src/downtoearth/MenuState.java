//MenuState
package downtoearth;



import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {
    
    Image play;
    Rectangle playBox;
    Image stop;
    Rectangle stopBox;
    Circle mouseBall;
    boolean collides;
    
    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        play = new Image("resources/startbtn.png");
        stop = new Image("resources/stopbtn.png");
        mouseBall = new Circle(0, 0, 5);
        playBox = new Rectangle((container.getWidth() / 2) - (play.getWidth() / 2), (container.getHeight() / 2) - (play.getHeight() / 2), play.getWidth(), play.getHeight());
        stopBox = new Rectangle((container.getWidth() / 2) - (stop.getWidth() / 2), (container.getHeight() / 2) - (stop.getHeight() / 2) + (stop.getHeight() + 20), stop.getWidth(), stop.getHeight());
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //TODO: implement
        g.drawString(Integer.toString(Mouse.getX()), 0, 45);
        g.drawString(Integer.toString(Mouse.getY()), 0, 60);
        g.drawString("State 1: Menu", 0, 30);
        g.drawImage(play, (container.getWidth() / 2) - (play.getWidth() / 2) , (container.getHeight() / 2) - (play.getHeight() / 2));
        g.draw(playBox);
        g.drawImage(stop, (container.getWidth() / 2) - (stop.getWidth() / 2), (container.getHeight() / 2) - (stop.getHeight() / 2) + (stop.getHeight() + 20)); 
        g.draw(stopBox);
        g.setColor(Color.lightGray);
        g.fill(mouseBall);
        if(collides){
            g.setColor(Color.yellow);
            g.drawString("collision", 200, 200);
        }
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        mouseBall.setCenterX(container.getInput().getMouseX());
        mouseBall.setCenterY(container.getInput().getMouseY());
        
        collides = false;
        
        if(mouseBall.getCenterX() >= playBox.getX() && mouseBall.getCenterX() <= playBox.getX() + playBox.getWidth()){
            if(mouseBall.getCenterY() >= playBox.getY() && mouseBall.getCenterY() <= playBox.getY() + playBox.getHeight()){
                collides = true;
                if(Mouse.isButtonDown(0)){
                    game.enterState(1);
                }
            }  
        }
        
        if(mouseBall.getCenterX() >= stopBox.getX() && mouseBall.getCenterX() <= stopBox.getX() + stopBox.getWidth()){
            if(mouseBall.getCenterY() >= stopBox.getY() && mouseBall.getCenterY() <= stopBox.getY() + stopBox.getHeight()){
                collides = true;
                if(Mouse.isButtonDown(0)){
                    container.exit();
                }
            }  
        }
    }
    
}
