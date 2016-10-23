package downtoearth;

import downtoearth.states.*;
import org.newdawn.slick.AppGameContainer;
import downtoearth.Items.Item;
import java.util.ArrayList;
import java.util.ListIterator;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DownToEarth extends StateBasedGame { 
    
    public DownToEarth(String name){
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
        this.addState(new MenuState());
        this.addState(new OptionState());
        this.addState(new GameState());
    }
}
