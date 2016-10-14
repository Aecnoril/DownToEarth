package downtoearth;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {

    Image play;
    Image stop;
    Circle mouseBall;
    boolean collides;
    Button playbutton;
    Button stopbutton;
    private Image gbg;

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
        gbg = new Image("resources/gamebg.png");
        play = new Image("resources/startbtn.png");
        stop = new Image("resources/stopbtn.png");
        mouseBall = new Circle(0, 0, 5);
        playbutton = new Button((container.getWidth() / 2) - (play.getWidth() / 2), (container.getHeight() / 2) - (play.getHeight() / 2), play.getWidth(), play.getHeight(), new Image("resources/startbtn.png"));
        stopbutton = new Button((container.getWidth() / 2) - (stop.getWidth() / 2), (container.getHeight() / 2) - (stop.getHeight() / 2) + (stop.getHeight() + 20), stop.getWidth(), stop.getHeight(), new Image("resources/stopbtn.png"));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //TODO: implement
        g.drawImage(gbg, 0, 0);
        g.drawString(Integer.toString(Mouse.getX()), 0, 45);
        g.drawString(Integer.toString(Mouse.getY()), 0, 60);
        g.drawString("State 1: Menu", 0, 30);
        playbutton.render(g);
        stopbutton.render(g);
        g.setColor(Color.lightGray);
        g.fill(mouseBall);
        if (collides) {
            g.setColor(Color.yellow);
            g.drawString("collision", 200, 200);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        mouseBall.setCenterX(container.getInput().getMouseX());
        mouseBall.setCenterY(container.getInput().getMouseY());

        if (playbutton.detectMouse(mouseBall)) {
            game.enterState(1);
        }
        if (stopbutton.detectMouse(mouseBall)) {
            container.exit();
        }
    }
}
