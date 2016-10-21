/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.Items.Item;
import downtoearth.enums.DirectionType;
import downtoearth.enums.SpriteLocation;
import downtoearth.gameUtil.AnimationManager;
import downtoearth.gameUtil.Camera;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Demian
 */
public class Player extends LivingEntity{
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    
    public static final float SPEED = 0.3f;
    
    private int thirst;
    private int hunger;
    private byte dir;
    private Camera cam;
    private boolean moving;
    private Coordinate coordinate;
    
    private AnimationManager aManager;
    private SpriteManager sManager;

    /**
     * Get the value of thirst
     *
     * @return the value of thirst
     */
    public int getThirst() {
        return thirst;
    }
    
    /**
     * Get the value of hunger
     *
     * @return the value of hunger
     */
    public int getHunger() {
        return hunger;
    }
    
    public Camera getCamera(){
        return this.cam;
    }

    
    //</editor-fold>
    
    public Player(String name, Coordinate location, int hitPoints, String path) throws SlickException {
        super(name, location, hitPoints, path);
        this.aManager = new AnimationManager(32 ,32);
        this.sManager = new SpriteManager("res/playerSprite.png");
        this.cam = new Camera(0,0, location);
        this.dir = DirectionType.NORTH;
        this.moving = false;
    }
    
    public void move(Input input){     
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_W)){
            cam.getCoordinate().setY(cam.getCoordinate().getY() - SPEED);
            cam.getCoordinate().setX(cam.getCoordinate().getX() + SPEED);
            moving = true;
        }
        else if(input.isKeyDown(Input.KEY_W)){
            cam.getCoordinate().setY(cam.getCoordinate().getY() - SPEED);
            dir = DirectionType.NORTH;
            moving = true;
        }
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_S)){
            cam.getCoordinate().setY(cam.getCoordinate().getY() + SPEED);
            cam.getCoordinate().setX(cam.getCoordinate().getX() + SPEED);
            moving = true;
        }
        else if(input.isKeyDown(Input.KEY_D)){
            cam.getCoordinate().setX(cam.getCoordinate().getX() + SPEED);
            dir = DirectionType.EAST;
            moving = true;
        }
        
        if(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A)){
            cam.getCoordinate().setY(cam.getCoordinate().getY() + SPEED);
            cam.getCoordinate().setX(cam.getCoordinate().getX() - SPEED);
            moving = true;
        }
        else if(input.isKeyDown(Input.KEY_S)){
            cam.getCoordinate().setY(cam.getCoordinate().getY() + SPEED);
            dir = DirectionType.SOUTH;
            moving = true;
        }
        
        if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_W)){
            cam.getCoordinate().setY(cam.getCoordinate().getY() - SPEED);
            cam.getCoordinate().setX(cam.getCoordinate().getX() - SPEED);
            moving = true;
        }
        else if(input.isKeyDown(Input.KEY_A)){
            cam.getCoordinate().setX(cam.getCoordinate().getX() - SPEED);
            dir = DirectionType.WEST;
            moving = true;
        }  
        else{
            moving = false;
        }
        this.location = cam.getCoordinate();
        
        System.out.println(cam.getCoordinate().getXint() + ", " + cam.getCoordinate().getYint());
    }
    
    public void render(GameContainer con) throws SlickException{
        if(moving){
            moving = false;
            aManager.DrawAnimation(this.dir, con);
        }else{
            SpriteLocation pos = DirectionType.getStandingSprite(dir);
            sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), con.getWidth() / 2, con.getHeight() /2);
        }
    }
    
    public void useItem(Item item){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void attack(GameContainer gc)
    {
        
    }
 }

