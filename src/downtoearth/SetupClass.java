package downtoearth;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Demian
 */
public class SetupClass extends StateBasedGame{

    public SetupClass(String name) {
        super(name);
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer appgc = new AppGameContainer(new SetupClass("Creatures"));
        appgc.setDisplayMode(1920, 1080, false);
        appgc.setTargetFrameRate(120);
        appgc.start();
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new MenuState());
        this.addState(new DownToEarth());
    }    
}
