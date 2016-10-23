/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.states.gui.Button;
import downtoearth.states.gui.PasswordTextField;
import java.awt.Font;
import java.util.ArrayList;
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
 * @author Ruud
 */
public class LoginState extends BasicGameState {

    private Button login;
    private Button register;
    private TextField loginUsername;
    private PasswordTextField loginPassword;
    private TextField registerEmail;
    private TextField registerUsername;
    private PasswordTextField registerPassword;
    private ArrayList<TextField> textfields = new ArrayList<TextField>();

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
        
        login = new Button(200, 350, "res/startbtn.png");
        register = new Button(600, 350, "res/stopbtn.png");
        Font awtfont = new Font("Arial", Font.PLAIN, 24);
        TrueTypeFont f = new TrueTypeFont(awtfont, false);
        textfields.add(loginUsername = new TextField(container, f, 200, 200, 200, 28));
        textfields.add(loginPassword = new PasswordTextField(container, f, 200, 250, 200, 28));
        textfields.add(registerEmail = new TextField(container, f, 600, 200, 200, 28));
        textfields.add(registerUsername = new TextField(container, f, 600, 250, 200, 28));
        textfields.add(registerPassword = new PasswordTextField(container, f, 600, 300, 200, 28));

        for (TextField t : this.textfields) {
            t.setBorderColor(Color.gray);
            t.setBackgroundColor(Color.white);
            t.setTextColor(Color.black);
            t.setCursorVisible(false);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //TODO: implement
        g.drawString("State 1: Menu", 0, 30);
        login.render(login.hover(container.getInput()));
        register.render(register.hover(container.getInput()));

        g.drawString("Username", loginUsername.getX(), loginUsername.getY() - (float)20);
        loginUsername.render(container, g);

        g.drawString("Password", loginPassword.getX(), loginPassword.getY() - (float)20);
        loginPassword.render(container, g);

        g.drawString("Email", registerEmail.getX(), registerEmail.getY() - (float)20);
        registerEmail.render(container, g);

        g.drawString("Username", registerUsername.getX(), registerUsername.getY() - (float)20);
        registerUsername.render(container, g);

        g.drawString("Password", registerPassword.getX(), registerPassword.getY() - (float)20);
        registerPassword.render(container, g);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (login.clicked(container.getInput())) {
            game.enterState(1);
        }
        if (register.clicked(container.getInput())) {
            //Registreren
        }
    }
}
