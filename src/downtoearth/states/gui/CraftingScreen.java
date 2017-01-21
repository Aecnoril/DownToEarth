/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states.gui;

import downtoearth.states.gui.Inventory;
import downtoearth.states.gui.InventorySlot;
import downtoearth.states.gui.Rectangle;
import downtoearth.Items.Item;
import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ruud
 */
public class CraftingScreen {

    private ArrayList<InventorySlot> Craftables = new ArrayList<>();
    private ArrayList<String> craftables = new ArrayList<>();
    private InventorySlot selectedSlot = null;
    private boolean csOpen;
    private Rectangle rectangle;
    private float txtPosition;
    private float scroll;

    public CraftingScreen(int x, int y, int width, int height, Color c) {
        rectangle = new Rectangle(c, x, y, width, height);

        this.craftables.add("Mes");
        this.craftables.add("Mes");
        this.craftables.add("Mes");
        this.craftables.add("Mes");
        this.craftables.add("Mes");
        this.generateCraftingScreen();
    }

    public void setScroll(float x) {
        scroll += x;
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * Get the value of rectangle
     *
     * @return the value of rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Set the value of rectangle
     *
     * @param rectangle new value of rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Get the value of crftscrnOpen
     *
     * @return the value of crftscrnOpen
     */
    public boolean isCsOpen() {
        return csOpen;
    }

    /**
     * Set the value of crftscrnOpen
     *
     * @param invOpen new value of crftscrnOpen
     */
    public void setCsOpen(boolean invOpen) {
        this.csOpen = invOpen;
    }
    //</editor-fold>

    public void generateCraftingScreen() {
        int temporaryX = rectangle.getX();
        int temporaryY = rectangle.getY();
        int leftborder = 25;
        int topborder = 25;

        for (int i = 0; i < 40; i++) {
            InventorySlot r = new InventorySlot(temporaryX + leftborder, temporaryY + topborder, 250, 75, new Color(58, 55, 55));
            r.setText("Mes" + i);
            this.Craftables.add(r);
            temporaryY += 100;
        }

        selectedSlot = new InventorySlot(500, 100, 250, 250, new Color(58, 55, 55));
    }

    public void render(Graphics g) {
        g.setColor(new Color(122, 118, 118));
        g.fillRect(25, 100, 1025, 500);

        //g.drawString("Test", 30, 100);
//        for (String r : this.craftables) {
//
//            g.drawString(r, 100, txtPosition);
//            txtPosition += 20;
//        }
        for (InventorySlot r : Craftables) {
            if (r.getRectangle().getY() >= 75 + scroll && r.getRectangle().getY() <= 525 + scroll) {
                g.setColor(r.getRectangle().getColor());
                g.fillRect(r.getRectangle().getX(), r.getRectangle().getY() - scroll, r.getRectangle().getWidth(), r.getRectangle().getHeight());
                g.setColor(Color.black);
                Font awtfont = new Font("Arial", Font.PLAIN, 24);
                TrueTypeFont f = new TrueTypeFont(awtfont, false);
                g.setFont(f);
                g.drawString(r.text, r.getRectangle().getX(), r.getRectangle().getY() - scroll);
            }
        }

        g.setColor(selectedSlot.getRectangle().getColor());
        g.fillRect(selectedSlot.getRectangle().getX(), selectedSlot.getRectangle().getY() - scroll, selectedSlot.getRectangle().getWidth(), selectedSlot.getRectangle().getHeight());
        g.setColor(Color.black);
        Font awtfont = new Font("Arial", Font.PLAIN, 24);
        TrueTypeFont f = new TrueTypeFont(awtfont, false);
        g.setFont(f);
        if (selectedSlot.text != null) {
            g.drawString(selectedSlot.text, selectedSlot.getRectangle().getX(), selectedSlot.getRectangle().getY());
        }
    }

    /**
     *
     * @param gc
     */
    public void cPressed(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_C)) {
            if (this.csOpen) {
                this.csOpen = false;
            } else {
                this.csOpen = true;
            }
        }
    }

    /**
     *
     * @param gc
     */
    public void selectedRecipe(GameContainer gc) {
        if (csOpen) {
            for (InventorySlot is : Craftables) {
                if (is.detectMouse(gc.getInput())) {
                    if (is.detectMouse(gc.getInput()) && gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
                        if (selectedSlot == null) {
                            selectedSlot.setText(is.text);
                        }
                    }
                }
            }
        }
    }
}
