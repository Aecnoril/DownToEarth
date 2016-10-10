//package downtoearth;
//
//import Entities.NPC;
//import Items.Item;
//import Items.TileItem;
//import enums.Tooltype;
//import enums.DirectionType;
//import enums.ItemType;
//import enums.MobType;
//import java.io.File;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
//import org.lwjgl.input.Mouse;
//import org.newdawn.slick.AppGameContainer;
//import org.newdawn.slick.BasicGame;
//import org.newdawn.slick.Color;
//import org.newdawn.slick.GameContainer;
//import org.newdawn.slick.Graphics;
//import org.newdawn.slick.ScalableGame;
//import org.newdawn.slick.SlickException;
//import org.newdawn.slick.SpriteSheet;
//import org.newdawn.slick.geom.Point;
//
//public class DownToEarth extends BasicGame {
//
//    private static Item i;
//    private static Item i2;
//    private static ItemType type = ItemType.Steel;
//    private NPC npc;
//    
//    private static Item i;
//    private static Item i2;
//    private static Item i3;
//    private static Item i4;
//    private int rotateint = 40;
//    private float rotate = (float) Math.toRadians(rotateint);
//    
//    public DownToEarth(String gamename) {
//        super(gamename);
//    }
//
//    @Override
//    public void init(GameContainer gc) throws SlickException {
//        i = new TileItem("jopie", Tooltype.WOODENSWORD, 10, 10);
//        i2 = new TileItem("jopie", Tooltype.STONESWORD, 10, 10);
//        i3 = new TileItem("jopie", Tooltype.STEELSWORD, 10, 10);
//        i4 = new TileItem("jopie", Tooltype.GEMSWORD, 10, 10);
//    }
//
//    @Override
//    public void update(GameContainer gc, int i) throws SlickException {
//        if(Mouse.isButtonDown(0)){
//            rotateint += 40;
//            if(rotateint >= 180){
//                rotateint = 0;
//            }
//            rotate = (float) Math.toRadians(rotateint);
//        }
//        
//        if(Mouse.isButtonDown(1)){
//            rotateint -= 40;
//            if(rotateint <= 0){
//                rotateint = 180;
//            }
//            rotate = (float) -Math.toRadians(rotateint);
//        }
//    }
//
//    @Override
//    public void render(GameContainer gc, Graphics g) throws SlickException {
//        g.setBackground(Color.white);
//        i3.render(gc.getScreenWidth()/2, gc.getScreenHeight()/2, 200);
//        i2.render(gc.getScreenWidth()/2, gc.getScreenHeight()/2);
//        i.render(gc.getScreenWidth()/2, gc.getScreenHeight()/2);
//    }
//
//    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
//        try {   
//            AppGameContainer appgc;
//            appgc = new AppGameContainer(new DownToEarth("Simple Slick Game"));
//            appgc.setDisplayMode(1920, 1080, false);
//            appgc.setTargetFrameRate(60);
//            appgc.start();
//        } catch (SlickException ex) {
//            Logger.getLogger(DownToEarth.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//}

package downtoearth;

import Items.Item;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DownToEarth extends BasicGameState {

    private static final int number = 200;
    private boolean inventory = false;
    private List<Rect> rects = new ArrayList<Rect>();
    private List<Item> Items = new ArrayList<Item>();
    Circle mouseBall;
    boolean collides;

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        mouseBall = new Circle(0, 0, 5);
        this.generateInventory();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("State 2: Game", 0, 30);
        g.setBackground(Color.white);

        if (this.inventory == true) {
            int x = 200;
            int y = 400;
            g.setColor(new Color(122, 118, 118));
            g.fillRect(x, y, 1000, 500);
            for (Rect r : this.rects) {
                g.setColor(r.getColor());
                g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
            }
        }

        g.setColor(Color.lightGray);
        g.fill(mouseBall);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_P)) {
            if (this.inventory == true) {
                this.inventory = false;
            } else {
                this.inventory = true;
            }
        }

        mouseBall.setCenterX(gc.getInput().getMouseX());
        mouseBall.setCenterY(gc.getInput().getMouseY());
        collides = false;

        for (Rect rec : this.rects) {
            if (mouseBall.getCenterX() >= rec.getX() && mouseBall.getCenterX() <= rec.getX() + rec.getWidth()) {
                if (mouseBall.getCenterY() >= rec.getY() && mouseBall.getCenterY() <= rec.getY() + rec.getHeight()) {
                    collides = true;
                    if (Mouse.isButtonDown(0)) {
                        rec.setColor(Color.yellow);
                    }
                }
            }
        }
    }

    public void generateInventory() {
        int x = 200;
        int y = 400;

        for (int i = 0; i < 40; i++) {
            if (i == 10 || i == 20) {
                y += 100;
                x -= 1000;
            }
            if (i == 30) {
                y += 150;
                x -= 1000;
            }
            Rect r = new Rect(x + 12, y + 25, 75, 75, new Color(58, 55, 55));
            this.rects.add(r);

            x += 100;
        }
    }
}

