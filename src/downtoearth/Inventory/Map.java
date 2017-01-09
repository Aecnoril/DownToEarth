/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Inventory;

import downtoearth.entities.Player;
import downtoearth.gameUtil.Camera;
import downtoearth.states.GameState;
import downtoearth.world.World;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 *
 * @author Tomt
 */
public class Map {
    private World world;
    private int x;
    private int y;
    private Camera cam;
    private int width;

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the value of width
     *
     * @param width new value of width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    private int height;

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    private Image map;

    private boolean mapOpen;

    /**
     * Get the value of mapOpen
     *
     * @return the value of mapOpen
     */
    public boolean isMapOpen() {
        return mapOpen;
    }

    /**
     * Set the value of mapOpen
     *
     * @param mapOpen new value of mapOpen
     */
    public void setMapOpen(boolean mapOpen) {
        this.mapOpen = mapOpen;
    }


    
    public Map(int width, int height) throws SlickException
    {
        cam = new Camera(1080,720);
        this.width = width;
        this.height = height;
        this.mapOpen = false;
        try {
            map = new Image("res/ColorMap.png");
        } catch (SlickException ex) {   
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mPressed(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_M)) {
            if (mapOpen==false)
            {
                //x= world.getPlayer().getCamera().getX();
                //y= world.getPlayer().getCamera().getY();
                this.x = cam.getX();
                this.y = cam.getY();
                System.out.println(x+" "+y);
                mapOpen = true;
            }
            else {
                mapOpen = false;
            }
        }
    }
    
    public void render(Graphics g)
    {
        if (map!=null)
        {
            map.getScaledCopy(0.1f).draw(width,height);
            g.fill(new Circle((float) (cam.getX()*0.1)+350, (float) (cam.getY()*0.1+135),5));
        }
    }
    
    public void setCamera(Camera camera)
    {
        cam = camera;
    }
}