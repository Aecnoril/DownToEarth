package downtoearth;

import Items.Armor;
import Items.Item;
import Items.TileItem;
import enums.Tooltype;
import java.util.ArrayList;
import java.util.ListIterator;
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
    private ArrayList<inventorySlot> inventorySlots = new ArrayList<inventorySlot>();
    private ArrayList<Item> Items = new ArrayList<Item>();
    private inventorySlot selectedSlot = null;
    Circle mouseBall;

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
        Items.add(new TileItem("jopie", Tooltype.WOODENSWORD, 10, 10));
        Items.add(new TileItem("jopie", Tooltype.STONESWORD, 10, 10));
        Items.add(new TileItem("jopie", Tooltype.STEELSWORD, 10, 10));
        Items.add(new TileItem("jopie", Tooltype.GEMSWORD, 10, 10));
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
            g.fillRect(x, y, 1025, 500);
            for (inventorySlot r : this.inventorySlots) {
                g.setColor(r.getColor());
                g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
                if (r.getItem() != null) {
                    r.getItem().render(r.getX(), r.getY(), r.getWidth());
                }
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

        if (inventory == true) {
            for (inventorySlot is : this.inventorySlots) {
                if (is.detectMouse(mouseBall)) {
                    if (input.isMousePressed(input.MOUSE_RIGHT_BUTTON)) {
                        is.getItem().drop();
                        is.setItem(null);
                    } else if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
                        if (selectedSlot != null && is.getItem() != null) {
                            Item i = selectedSlot.getItem();
                            selectedSlot.setItem(is.getItem());
                            is.setItem(i);
                        } else if (selectedSlot != null) {
                            is.setItem(selectedSlot.getItem());
                            selectedSlot.setItem(null);
                        }
                        if (selectedSlot == null) {
                            selectedSlot = is;
                        } else {
                            selectedSlot = null;
                        }
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
                y += 180;
                x -= 1000;
            }
            int leftborder = 25;
            int topborder = 25;
            inventorySlot r = new inventorySlot(x + leftborder, y + topborder, 75, 75, new Color(58, 55, 55));
            this.inventorySlots.add(r);

            x += 100;
        }

        int i = 1;
        for (Item it : Items) {
            inventorySlot r = inventorySlots.get(inventorySlots.size() - i);
            if (r.getItem() == null) {
                r.setItem(it);
            }
            i++;
        }
    }
}
