package downtoearth;

import downtoearth.states.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
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
        appgc.start();
    }
    
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new MenuState());
        this.addState(new OptionState());
        this.addState(new GameState());
    }

//    public void generateInventory() {
//        int x = 200;
//        int y = 400;
//
//        for (int i = 0; i < 40; i++) {
//            if (i == 10 || i == 20) {
//                y += 100;
//                x -= 1000;
//            }
//            if (i == 30) {
//                y += 150;
//                x -= 1000;
//            }
//            Rect r = new Rect(x + 12, y + 25, 75, 75, new Color(58, 55, 55));
//            this.rects.add(r);
//
//            x += 100;
//        }
//    }
}

