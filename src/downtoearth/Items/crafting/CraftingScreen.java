/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items.crafting;

import downtoearth.Items.crafting.CraftingSlot;
import downtoearth.Inventorys.Inventory;
import downtoearth.Inventorys.InventorySlot;
import downtoearth.Inventorys.Rectangle;
import downtoearth.Items.Item;
import downtoearth.Items.Resource;
import java.awt.Font;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private CraftingSlot selectedSlot = null;
    private boolean csOpen;
    private Rectangle rectangle;
    private float scroll;
    private ArrayList<Item> items = new ArrayList<>();
    private Inventory inv;
    private CraftingManager cm;

    public CraftingScreen(int x, int y, int width, int height, Color c) {
        cm = new CraftingManager();
        rectangle = new Rectangle(c, x, y, width, height);
    }

    public void setScroll(float x) {
        for (CraftingSlot is : Craftables) {
            if (is.craftable && is.getRectangle().getY() >= 90 - scroll && is.getRectangle().getY() <= 525 - scroll) {
                
                scroll += x;
            }
        }
    }

    public void setInventory(Inventory x) {
        inv = x;
    }

    public ArrayList<Item> getInventory() {
        return items;
    }

    public ArrayList<CraftingSlot> getCraftables() {
        return Craftables;
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
        selectedSlot = new CraftingSlot(500, 200, 250, 250, new Color(58, 55, 55));
        int temporaryX = rectangle.getX();
        int temporaryY = rectangle.getY();
        int leftborder = 25;
        int topborder = 25;
        for (InventorySlot is : inv.getInvSlots()) {
            if (is.getItem() != null) {
                System.out.println(((Item) is.getItem()).getName());
            }
        }
        for (CraftingRecipe cr : cm.getRecipes()) {
            CraftingSlot r = new CraftingSlot(temporaryX + leftborder, temporaryY + topborder, 300, 75, new Color(58, 55, 55));
            String recipeText = "";
            ArrayList<Object> receptenlijst = new ArrayList(Arrays.asList(cr.getIngredients()));
            boolean alltrue = false;
            for (int i = 0; i < (cr.getIngredients().length / 2); i += 2) {
                for (InventorySlot is : inv.getInvSlots()) {
                    if (is.getItem() != null) {
                        if (((Item) is.getItem()).getName() == ((Resource) receptenlijst.get(i)).getName() && is.getItemQuantity() >= (int) receptenlijst.get(i + 1)) {
                            if ((i == 0) || (i == 2 && alltrue) || (i == 4 && alltrue)) {
                                r.craftable = true;
                                alltrue = true;
                                break;
                            } else {
                                r.craftable = false;
                            }
                        } else {
                            r.craftable = false;
                        }
                    }
                }
            }

            recipeText += " " + cr.getResult().getName() + ": ";
            for (Object o : receptenlijst) {
                if (o instanceof Resource) {
                    recipeText += ((Resource) o).getName() + " ";
                }
            }
            r.setText(recipeText);
            r.setRecipe(cr);
            r.setItem(cr.getResult());
            this.Craftables.add(r);
            temporaryY += 100;
        }

    }

    public void render(Graphics g) {
        g.setColor(new Color(122, 118, 118));
        g.fillRect(25, 100, 1025, 500);

        for (CraftingSlot r : Craftables) {
            if (r.getRectangle().getY() >= 90 - scroll && r.getRectangle().getY() <= 525 - scroll) {
                g.setColor(r.getRectangle().getColor());
                g.fillRect(r.getRectangle().getX(), r.getRectangle().getY() + scroll, r.getRectangle().getWidth(), r.getRectangle().getHeight());
                if (r.craftable) {
                    g.setColor(Color.green);
                    g.drawRect(r.getRectangle().getX(), r.getRectangle().getY() + scroll, r.getRectangle().getWidth(), r.getRectangle().getHeight());
                }
                g.setColor(Color.black);
                Font awtfont = new Font("Arial", Font.PLAIN, 24);
                TrueTypeFont f = new TrueTypeFont(awtfont, false);
                g.setFont(f);
                g.drawString(r.text, r.getRectangle().getX(), r.getRectangle().getY() + scroll);
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
            if (is.craftable && is.getRectangle().getY() >= 90 - scroll && is.getRectangle().getY() <= 525 - scroll) {
                CraftingSlot is2 = new CraftingSlot(is.getRectangle().getX(), (int) (is.getRectangle().getY() + scroll), is.getRectangle().getWidth(), is.getRectangle().getHeight(), is.getRectangle().getColor());
                if (is2.detectMouse(gc.getInput())) {
                    if (gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
                        selectedSlot.setText(is.getItem().getName());
                        selectedSlot.setItem(is.getItem());
                        selectedSlot.setRecipe(is.getRecipe());
                    }
                }
            }
        }
        if (selectedSlot.detectMouse(gc.getInput())) {
            if (gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
                System.out.println("ItemCreated");
                selectedSlot.setText("Item Created!");
                inv.getItems().add(selectedSlot.getItem());
                for (Object o : selectedSlot.getRecipe().getIngredients()) {
                    for (Item i : inv.getItems()) {
                        if (o instanceof Resource) {
                            if (i.getName() == ((Resource) o).getName()) {
                                inv.getItems().remove(i);
                                break;
                            }
                        }
                    }
                }
                //inv.updateInv(inv);
                inv.generateInventory();
                generateCraftingScreen();
            }
        }
    }
}
