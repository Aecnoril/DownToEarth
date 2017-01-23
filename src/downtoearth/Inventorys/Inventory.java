/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Inventorys;

import downtoearth.Items.Item;
import downtoearth.Items.Resource;
import downtoearth.enums.ResourceType;
import downtoearth.enums.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ruud
 */
public class Inventory {

    private ArrayList<InventorySlot> inventorySlots = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private InventorySlot selectedSlot = null;
    private Rectangle rectangle;
    private Color color;
    private boolean invOpen;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param c
     */
    public Inventory(int x, int y, int width, int height, Color c) {
        rectangle = new Rectangle(c, x, y, width, height);
        this.invOpen = false;
        try {
            items.add(new Resource("Wood", Type.WOOD, 100, 0));
            items.add(new Resource("Wood", Type.WOOD, 100, 0));
            items.add(new Resource("Wood", Type.WOOD, 100, 0));
            items.add(new Resource("Wood", Type.WOOD, 100, 0));
            items.add(new Resource("Wood", Type.WOOD, 100, 0));
            items.add(new Resource("Wood", Type.WOOD, 100, 0));
            items.add(new Resource("Wood", Type.WOOD, 100, 0));
            items.add(new Resource("Wood", Type.WOOD, 100, 0));
            items.add(new Resource("Stick", Type.STICK, 100, 0));
            items.add(new Resource("Stick", Type.STICK, 100, 0));
            items.add(new Resource("Stick", Type.STICK, 100, 0));
            items.add(new Resource("Stick", Type.STICK, 100, 0));
            items.add(new Resource("Stick", Type.STICK, 100, 0));
            items.add(new Resource("Stick", Type.STICK, 100, 0));
            items.add(new Resource("Stick", Type.STICK, 100, 0));
            items.add(new Resource("Gravel", Type.GRAVEL, 100, 0));
            items.add(new Resource("Steel", Type.STEEL, 100, 0));
            items.add(new Resource("Steel", Type.STEEL, 100, 0));
        } catch (SlickException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public ArrayList<InventorySlot> getInvSlots() {
        return inventorySlots;
    }

    /**
     * Get the value of items
     *
     * @return the value of items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> x) {
        items = x;
    }

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
     * Get the value of invOpen
     *
     * @return the value of invOpen
     */
    public boolean isInvOpen() {
        return invOpen;
    }

    /**
     * Set the value of invOpen
     *
     * @param invOpen new value of invOpen
     */
    public void setInvOpen(boolean invOpen) {
        this.invOpen = invOpen;
    }
    //</editor-fold>

    /**
     * Generates the inventory screen
     */
    public void generateInventory() {
        int temporaryX = rectangle.getX();
        int temporaryY = rectangle.getY();
        int leftborder = 25;
        int topborder = 25;
        this.inventorySlots.clear();
        for (int i = 0; i < 40; i++) {
            if (i == 10 || i == 20) {
                temporaryY += 100;
                temporaryX -= rectangle.getWidth() - leftborder;
            }
            if (i == 30) {
                temporaryY += 180;
                temporaryX -= rectangle.getWidth() - leftborder;
            }
            InventorySlot r = new InventorySlot(temporaryX + leftborder, temporaryY + topborder, 75, 75, new Color(58, 55, 55));
            this.inventorySlots.add(r);

            temporaryX += r.getRectangle().getWidth() + leftborder;
        }
        int i = 1;
        for (Item it : items) {
            boolean dubbelItem = false;
            InventorySlot r = inventorySlots.get(inventorySlots.size() - i);
            for (InventorySlot is : inventorySlots) {
                if (is.getItem() != null) {
                    if (((Item) is.getItem()).getName() == it.getName()) {
                        is.addItemQuantity();
                        dubbelItem = true;
                    }
                }
            }
            if (r.getItem() == null && dubbelItem == false && it.getSlot() == -1) {
                r.setItem(it);
                ((Item) r.getItem()).setSlot(inventorySlots.indexOf(r));
                i++;
            } else if (dubbelItem == false) {
                if (it.getSlot() == -1) {
                    i++;
                    it.setSlot(inventorySlots.indexOf(inventorySlots.size() - i));
                }
                inventorySlots.get(it.getSlot()).setItem(it);
                i++;

            }
        }
    }

    /**
     *
     * @param g
     */
    public void render(Graphics g) {
        try {
            g.setColor(new Color(122, 118, 118));
            g.fillRect(rectangle.getX(), rectangle.getY(), 1025, 500);
            for (InventorySlot r : this.inventorySlots) {
                g.setColor(r.getRectangle().getColor());
                g.fillRect(r.getRectangle().getX(), r.getRectangle().getY(), r.getRectangle().getWidth(), r.getRectangle().getHeight());
                if (r.getItem() != null) {
                    if (r.getItem() instanceof Item) {
                        ((Item) r.getItem()).render(r.getRectangle().getX(), r.getRectangle().getY(), r.getRectangle().getWidth());
                        if (r.getItemQuantity() > 1) {
                            g.setColor(Color.black);
                            g.drawString(Integer.toString(r.getItemQuantity()), r.getRectangle().getX(), r.getRectangle().getY());
                        }
                    }
                }
            }
        } catch (SlickException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param gc
     */
    public void ePressed(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_E)) {
            if (this.invOpen) {
                this.invOpen = false;
            } else {
                this.invOpen = true;
                for (Item i : items) {
                    System.out.println(i.getName());
                }
                this.generateInventory();
            }
        }
        if (this.invOpen) {
            this.moveitem(gc);
        }
    }

    public void updateInv(Inventory inv) {
        items = inv.getItems();
    }

    /**
     *
     * @param gc
     */
    public void moveitem(GameContainer gc) {
        for (InventorySlot is : this.inventorySlots) {
            if (is.detectMouse(gc.getInput())) {
                if (gc.getInput().isMousePressed(gc.getInput().MOUSE_RIGHT_BUTTON)) {
                    if (is.getItemQuantity() == 1) {
                        items.remove(is.getItem());
                        is.setItem(null);
                    }
                    else{
                       items.remove(is.getItem());
                       is.setItemQuantity(is.getItemQuantity()-1);
                    }
                } else if (gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
                    if (selectedSlot != null && is.getItem() != null) {
                        Item i = (Item) selectedSlot.getItem();
                        int quantity = selectedSlot.getItemQuantity();
                        selectedSlot.setItem((Item) is.getItem());
                        selectedSlot.setItemQuantity(is.getItemQuantity());
                        if (i != null) {
                            i.setSlot(inventorySlots.indexOf(is));
                        }
                        ((Item) is.getItem()).setSlot(inventorySlots.indexOf(selectedSlot));
                        is.setItem(i);
                        is.setItemQuantity(quantity);
                    } else if (selectedSlot != null) {
                        is.setItem((Item) selectedSlot.getItem());
                        is.setItemQuantity(selectedSlot.getItemQuantity());
                        ((Item) selectedSlot.getItem()).setSlot(inventorySlots.indexOf(is));
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

    public void removeItem(Item i) {
        items.remove(i);
        for (InventorySlot is : inventorySlots) {
            if (is.getItem() == i) {
                is.setItem(null);
            }
        }
    }
}
