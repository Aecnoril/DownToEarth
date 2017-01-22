package downtoearth.states;

import downtoearth.database.DatabaseAPI;
import downtoearth.database.ServerAPI;
import downtoearth.states.gui.Button;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {

    private Button play;
    private Button join;
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
        Point middle = new Point(container.getWidth()/2, container.getHeight()/2);
        
        backgroundImage = new Image("res/Backgrounds/MenuBackground.png");
        play = new Button(container.getWidth()/2, (container.getHeight() / 2) - 60, "res/Buttons/startbtn.png");
        exit = new Button(container.getWidth()/2, (container.getHeight() / 2) + 60, "res/Buttons/exitbtn.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("State 1: Menu", 0, 30);
        backgroundImage.draw(0, 0, container.getWidth(), container.getHeight());
        play.render(play.hover(container.getInput()));
        exit.render(exit.hover(container.getInput()));       
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(rebound == 5){
            rebound = 0;
            if (play.clicked(container.getInput())) {
                game.enterState(3);
            }
            if (exit.clicked(container.getInput())) {
                logout(container);
            } 
        }
        else{
           rebound++; 
        }
        
    }
    
    private void logout(final GameContainer container){
        JSONObject user = DatabaseAPI.getInstance().getUser();
        try {
            ServerAPI.logout(user.getString("token"), user.getString("tokenId"), new ServerAPI.ResponseListener() {
                @Override
                public void onResponse(ServerAPI.Response response) {
                    if(response.isSuccess() && response.getStatusCode() == 200){
                        DatabaseAPI.getInstance().removeUser();
                    } else {
                        System.out.println("Logout went wrong...");
                    }
                    container.exit();
                }
            });
        } catch (JSONException ex) {
            Logger.getLogger(LoginState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
