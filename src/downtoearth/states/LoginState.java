/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.states.gui.Button;
import downtoearth.states.gui.PasswordTextField;
import downtoearth.database.ServerAPI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
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

    private String mode;
    private String tokenTF;
    private String tokenIdTF;
    
    private int rebound;
    
    private boolean registerb;
    
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
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        mode = "login";
        rebound = 5;
        registerb = false;
        
        login = new Button(gc.getWidth() / 2, 400, "res/loginbtn.png");
        register = new Button(gc.getWidth() / 2, 550, "res/registerbtn.png");
        
        Font awtfont = new Font("Arial", Font.PLAIN, 24);
        TrueTypeFont f = new TrueTypeFont(awtfont, false);
        
        loginUsername = new TextField(gc, f, (gc.getWidth() / 2) - 100, 200, 200, 28);
        initTextField(loginUsername);
        loginPassword = new PasswordTextField(gc, f, (gc.getWidth() / 2) - 100, 250, 200, 28);
        initTextField(loginPassword);
        registerEmail = new TextField(gc, f, (gc.getWidth() / 2) - 100, 150, 200, 28);
        initTextField(registerEmail);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if(registerb == true){
            g.drawString("Register was succesfull", (container.getWidth() / 2) - 25, 100);
        }
        
        if(mode == "login"){
            login.render(login.hover(container.getInput()));
            register.render(register.hover(container.getInput()));
            
            g.drawString("Username", loginUsername.getX(), loginUsername.getY() - 20);
            loginUsername.render(container, g);

            g.drawString("Password", loginPassword.getX(), loginPassword.getY() - 20);
            loginPassword.render(container, g);
        }
        else{
            login.render(login.hover(container.getInput()));
            register.render(register.hover(container.getInput()));
            
            g.drawString("Email", registerEmail.getX(), registerEmail.getY() - 20);
            registerEmail.render(container, g);

            g.drawString("Username", loginUsername.getX(), loginUsername.getY() - 20);
            loginUsername.render(container, g);

            g.drawString("Password", loginPassword.getX(), loginPassword.getY() - 20);
            loginPassword.render(container, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(rebound == 5){
            rebound = 0;
            if (login.clicked(container.getInput())) {
                registerb = false;
                if(mode == "register"){
                    mode = "login";
                }
                else{
                    login(game);
                    logout();
                } 
            }
        }
        else{
            rebound++;
        }
        
        if (register.clicked(container.getInput())) {
            registerb = false;
            if(mode == "register"){
                register();
                logout();
            }
            else{
                mode = "register";
            }
        }
        //game.enterState(5);
    }
    
    public void initTextField(TextField t){
        t.setBorderColor(Color.gray);
        t.setBackgroundColor(Color.white);
        t.setTextColor(Color.black);
    }

    private void register() {
        if(rebound == 5){
            ServerAPI.register(registerEmail.getText(), loginUsername.getText(), loginPassword.getText(), new ServerAPI.ResponseListener() {
                @Override
                public void onResponse(ServerAPI.Response response) {
                    if(response.isSuccess() && response.getStatusCode() == 200){
                        try {
                            JSONObject sessionInfo = response.getJSONObjectResponse();
                            tokenTF = sessionInfo.getString("token");
                            tokenIdTF = sessionInfo.getString("tokenId");
                        } catch (JSONException ex) {
                            Logger.getLogger(LoginState.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        registerb = true;
                        mode = "login";
                    } else {
                        System.out.println(response.getResponse());
                        System.out.println("Something went wrong..."); 
                    }
                }
            });
        }
    }

    private void login(final StateBasedGame game) {
        ServerAPI.login(loginUsername.getText(), loginPassword.getText(), new ServerAPI.ResponseListener() {
            @Override
            public void onResponse(ServerAPI.Response response) {
                if(response.isSuccess() && response.getStatusCode() == 200){
                    try {
                        JSONObject sessionInfo = response.getJSONObjectResponse();
                        tokenTF = sessionInfo.getString("token");
                        tokenIdTF = sessionInfo.getString("tokenId");
                    } catch (JSONException ex) {
                        Logger.getLogger(LoginState.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //game.enterState(1);
                    game.enterState(5);
                } else {
                    System.out.println(response.getResponse());
                    System.out.println("Something went wrong...");
                }
            }
        });
    }
    
    private void logout(){
        ServerAPI.logout(tokenTF, tokenIdTF, new ServerAPI.ResponseListener() {
            @Override
            public void onResponse(ServerAPI.Response response) {
                if(response.isSuccess() && response.getStatusCode() == 200){
                    tokenTF = "";
                    tokenIdTF = "";
                } else {
                    System.out.println("Logout went wrong...");
                }
            }
        });
    }
}
