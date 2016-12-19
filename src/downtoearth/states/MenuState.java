package downtoearth.states;

import downtoearth.states.gui.Button;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {

    private Button play;
    private Button exit;
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
        
        backgroundImage = new Image("res/Backgrounds/MenuBackground.png");
        play = new Button(container.getWidth()/2, (container.getHeight() / 2) - 60, "res/Buttons/startbtn.png");
        exit = new Button(container.getWidth()/2, (container.getHeight() / 2) + 60, "res/Buttons/exitbtn.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("State 1: Menu", 0, 30);
        g.drawImage(backgroundImage, 0, 0);
        play.render(play.hover(container.getInput()));
        exit.render(exit.hover(container.getInput()));       
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(rebound == 5){
            if (play.clicked(container.getInput())) {
                game.enterState(3);
            }
            if (exit.clicked(container.getInput())) {
                container.exit();
            } 
        }
        else{
           rebound++; 
        }
        
    }
}
