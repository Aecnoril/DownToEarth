/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items.crafting;

import downtoearth.Inventorys.CraftingSlot;
import downtoearth.Inventorys.Inventory;
import downtoearth.Inventorys.Rectangle;
import downtoearth.Items.Item;
import downtoearth.Items.Resource;
import downtoearth.states.GameState;
import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ruud
 */
public class CraftingScreen {

    private ArrayList<CraftingSlot> Craftables = new ArrayList<>();
    private ArrayList<String> craftables = new ArrayList<>();
    private CraftingSlot selectedSlot = null;
    private boolean csOpen;
    private Rectangle rectangle;
    private float scroll;
    private ArrayList<Item> items = new ArrayList<>();
    private Inventory inv;

    public CraftingScreen(int x, int y, int width, int height, Color c) {
        rectangle = new Rectangle(c, x, y, width, height);

        this.craftables.add("Mes");
        this.craftables.add("Mes");
        this.craftables.add("Mes");
        this.craftables.add("Mes");
        this.craftables.add("Mes");

    }

    public void setScroll(float x) {
        scroll += x;
    }

    public void setInventory(Inventory x) {
        inv = x;
    }
    
    public ArrayList<Item> getInventory() {
        return items;
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
        CraftingManager cm = new CraftingManager();

        for (CraftingRecipe cr : cm.getRecipes()) {
            CraftingSlot r = new CraftingSlot(temporaryX + leftborder, temporaryY + topborder, 250, 75, new Color(58, 55, 55));
            String recipeText = "";

            for (Object o : cr.getIngredients()) { 
                
                if (o instanceof Resource) {
                    recipeText += " " + ((Resource) o).getName();
                }
                for (Item c : inv.getItems()) {
                    if (o instanceof Resource) {                        
                        if (c.getName() == ((Resource) o).getName()) {                       
                            r.craftable = true;
                        } 
                        else{
                            break;
                        }
                    }
                }
            }
            r.setText(recipeText);
            r.setItem(cr.getResult());            
            this.Craftables.add(r);
            temporaryY += 100;
        }

        selectedSlot = new CraftingSlot(500, 200, 250, 250, new Color(58, 55, 55));
    }

    public void render(Graphics g) {
        g.setColor(new Color(122, 118, 118));
        g.fillRect(25, 100, 1025, 500);

        for (CraftingSlot r : Craftables) {
            if (r.getRectangle().getY() >= 75 + scroll && r.getRectangle().getY() <= 525 + scroll) {
                g.setColor(r.getRectangle().getColor());
                g.fillRect(r.getRectangle().getX(), r.getRectangle().getY() - scroll, r.getRectangle().getWidth(), r.getRectangle().getHeight());
                if (r.craftable) {
                    g.setColor(Color.green);
                    g.drawRect(r.getRectangle().getX(), r.getRectangle().getY() - scroll, r.getRectangle().getWidth(), r.getRectangle().getHeight());
                }
                g.setColor(Color.black);
                Font awtfont = new Font("Arial", Font.PLAIN, 24);
                TrueTypeFont f = new TrueTypeFont(awtfont, false);
                g.setFont(f);
                g.drawString(r.text, r.getRectangle().getX(), r.getRectangle().getY() - scroll);
            }
        }

        g.setColor(selectedSlot.getRectangle().getColor());
        g.fillRect(selectedSlot.getRectangle().getX(), selectedSlot.getRectangle().getY(), selectedSlot.getRectangle().getWidth(), selectedSlot.getRectangle().getHeight());
        g.setColor(Color.black);

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
                this.generateCraftingScreen();
            }
        }
        if (this.csOpen) {
            this.selectedRecipe(gc);
        }
    }

    /**
     *
     * @param gc
     */
    public void selectedRecipe(GameContainer gc) {
        for (CraftingSlot is : Craftables) {
            CraftingSlot is2 = new CraftingSlot(is.getRectangle().getX(), (int) (is.getRectangle().getY() - scroll), is.getRectangle().getWidth(), is.getRectangle().getHeight(), is.getRectangle().getColor());
            if (is2.detectMouse(gc.getInput())) {
                if (gc.getInput().isMousePressed(gc.getInput().MOUSE_RIGHT_BUTTON)) {
                    selectedSlot.setText(is.getItem().getName());
                    selectedSlot.setItem(is.getItem());
                }
            }
        }        
        if (selectedSlot.detectMouse(gc.getInput())) {
                if (gc.getInput().isMousePressed(gc.getInput().MOUSE_RIGHT_BUTTON)) {
                    System.out.println("Test123");
                    inv.getItems().add(selectedSlot.getItem());
                    inv.updateInv(inv);                    
                }
            }
    }
}
