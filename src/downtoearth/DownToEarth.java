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

    private static final int number = 200;
    private boolean inventory = false;
    private ArrayList<inventorySlot> inventorySlots = new ArrayList<inventorySlot>();
    private ArrayList<Item> Items = new ArrayList<Item>();
    private inventorySlot selectedSlot = null;

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
//                y += 180;
//                x -= 1000;
//            }
//            int leftborder = 25;
//            int topborder = 25;
//            inventorySlot r = new inventorySlot(x + leftborder, y + topborder, 75, 75, new Color(58, 55, 55));
//            this.inventorySlots.add(r);
//
//            x += 100;
//        }
//
//        int i = 1;
//        for (Item it : Items) {
//            inventorySlot r = inventorySlots.get(inventorySlots.size() - i);
//            if (r.getItem() == null) {
//                r.setItem(it);
//            }
//            i++;
//        }
//    }
}
