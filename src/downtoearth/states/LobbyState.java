/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.states.gui.Button;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Tomt
 */
public class LobbyState extends BasicGameState {
    
    private Button join;
    private TextField ipBox;
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    @Override
    public int getID() {
       return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        join = new Button(container.getWidth()/2, (container.getHeight() / 2)-60, "res/Buttons/startbtn.png");
        
        Font awtfont = new Font("Arial", Font.PLAIN, 24);
        TrueTypeFont f = new TrueTypeFont(awtfont, false);
        
        ipBox = new TextField(container, f, (container.getWidth() / 2) - 100, 450, 200, 28);
        initTextField(ipBox);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        join.render(join.hover(container.getInput()));
        
        g.drawString("Type IP here:", ipBox.getX(), ipBox.getY() - 20);
            ipBox.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {
        if (join.clicked(container.getInput()))
        {
            //check ip
            game.enterState(1);
        }
    }
    
    public void initTextField(TextField t){
        t.setBorderColor(Color.gray);
        t.setBackgroundColor(Color.white);
        t.setTextColor(Color.black);
    }
}
