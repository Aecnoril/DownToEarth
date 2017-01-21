/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states.gui;

import shared.Coordinate;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Demian
 */
public class Button {
    
    private Coordinate point; 
    private Rectangle bounds;
    private Image sprite;
    
    public Button(int xpos, int ypos, int width, int height){
        this.point = new Coordinate(xpos, ypos);
        
        this.bounds = new Rectangle(xpos, ypos, width, height);
    }
    
    public Button(int xpos, int ypos, String path) throws SlickException{
        this.sprite = new Image(path);
        this.point = new Coordinate(xpos - (sprite.getWidth() / 2), ypos - (sprite.getHeight() / 2));    
        this.bounds = new Rectangle(xpos - (sprite.getWidth() / 2), ypos - (sprite.getHeight() / 2), sprite.getWidth(), sprite.getHeight());
    }
    
    public void setSprite(String path) throws SlickException{
        this.sprite = new Image(path);
        this.point = new Coordinate(point.getXint() - (sprite.getWidth() / 2), point.getYint() - (sprite.getHeight() / 2));
    }
    
    public boolean clicked(Input input){
        if(bounds.contains(input.getMouseX(), input.getMouseY())){
            if(Mouse.isButtonDown(0)){
                return true;
            }
        }
        return false;
    }
    
    public boolean hover(Input input){
        if(bounds.contains(input.getMouseX(), input.getMouseY())){
            return true;
        }
        return false;
    }
    
    public void render(boolean hover){
        if(hover)
            sprite.draw(point.getXint(), point.getYint(), new Color(1,1,1,0.4f));
        else
            sprite.draw(point.getXint(), point.getYint());
    }
    
}
