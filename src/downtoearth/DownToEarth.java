package downtoearth;

import downtoearth.states.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DownToEarth extends StateBasedGame {

    public DownToEarth(String name) {
        super(name);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer appgc = new AppGameContainer(new DownToEarth("DownToEarth"));
        appgc.setDisplayMode(1080, 720, false);
        appgc.setTargetFrameRate(60);
        appgc.setShowFPS(false);
        appgc.start();
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {

        //this.addState(new MapGenState());
        this.addState(new LoginState());
        this.addState(new MenuState());
        this.addState(new OptionState());
        this.addState(new MultiplayerState());
        this.addState(new GameState());
        this.addState(new LobbyState());
    }
}
