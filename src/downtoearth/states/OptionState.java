/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.states.gui.Button;
import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Demian
 */
public class OptionState extends BasicGameState {

    private static Button play;
    private static Button stop;

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        play = new Button(container.getWidth() / 2, container.getHeight() / 2 - 60, "res/startbtn.png");
        stop = new Button(container.getWidth() / 2, (container.getHeight() / 2) + 60, "res/stopbtn.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("State 2: Option", 10, 30);
        play.render(play.hover(container.getInput()));
        stop.render(stop.hover(container.getInput()));
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        if (play.clicked(gc.getInput())) {
            game.enterState(2);
        }

        if (stop.clicked(gc.getInput())) {
            game.enterState(2);
        }

    }

}
