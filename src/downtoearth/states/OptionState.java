/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.states.gui.Button;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Demian
 */
public class OptionState extends BasicGameState {

    private Button play;
    private Button stop;
    private Image backgroundImage;

    public static void main(String[] args) {
    }

    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
        backgroundImage = new Image("res/Backgrounds/OptionsBackground.png");
        play = new Button(container.getWidth() / 2, (container.getHeight() / 2) - 60, "res/Buttons/startbtn.png");
        stop = new Button(container.getWidth() / 2, (container.getHeight() / 2) + 60, "res/Buttons/exitbtn.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        backgroundImage.draw(0, 0, container.getWidth(), container.getHeight());
        g.drawString("State 2: Option", 10, 30);       
        play.render(play.hover(container.getInput()));
        stop.render(stop.hover(container.getInput()));
        

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        if (play.clicked(gc.getInput())) {
            game.enterState(3);

            if (stop.clicked(gc.getInput())) {
                game.enterState(2);
            }

        }
    }
}
