package downtoearth.states;

import downtoearth.states.gui.Button;
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
    
    private Button play;
    private Button stop;

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        //Achtergrond en buttons
        play = new Button(container.getWidth()/2, container.getHeight() / 2, "res/startbtn.png");
        stop = new Button(container.getWidth()/2, (container.getHeight() / 2) + 60, "res/stopbtn.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //TODO: implement
        g.drawString("State 1: Menu", 0, 30);
        play.render(play.hover(container.getInput()));
        stop.render(stop.hover(container.getInput()));
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        if (play.clicked(container.getInput())) {
            game.enterState(1);
        }
        if (stop.clicked(container.getInput())) {
            container.exit();
        }
    }
}
