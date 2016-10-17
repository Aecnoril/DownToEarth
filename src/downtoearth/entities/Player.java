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
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Demian
 */
public class Player extends LivingEntity{
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    
    public static final float SPEED = 0.5f;
    
    private int thirst;
    private int hunger;
    private byte dir;
    private Camera cam;
    private boolean moving;
    
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
        this.aManager = new AnimationManager("res/playeranimation.png", 32 ,32);
        this.sManager = new SpriteManager("res/playerSprite.png");
        this.cam = new Camera(0,0);
        this.dir = DirectionType.NORTH;
        this.moving = false;
    }
    
    public void move(Input input){     
        
        if(input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_D)){
            cam.getCoordinate().setY(cam.getCoordinate().getY() - SPEED);
            cam.getCoordinate().setX(cam.getCoordinate().getX() + SPEED);
            dir = DirectionType.NORTHEAST;
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
            dir = DirectionType.SOUTHEAST;
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
            dir = DirectionType.SOUTHWEST;
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
            dir = DirectionType.NORTHWEST;
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
    }
    
    public void render(GameContainer con) throws SlickException{
        if(moving){
            aManager.DrawAnimation(this.dir, con);
        }else{
            SpriteLocation pos = DirectionType.getStandingSprite(dir);
            sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), con.getWidth() / 2, con.getHeight() / 2);
        }
    }
    
    public void useItem(Item item){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void attack(GameContainer gc)
    {
        switch(direction)
        {
            case North:
                
                break;
            case NorthEast:
                break;
            case East:
                spriteManager.getCharacterSprite(1, 0, (int)this.location.getX(), (int)this.location.getY());
                break;
            case SouthEast:
                break;
            case South:
                spriteManager.getCharacterSprite(0, 0, (int)this.location.getX(), (int)this.location.getY());
                break;
            case SouthWest:
                break;
            case West:
                spriteManager.getCharacterSprite(3, 0, (int)this.location.getX(), (int)this.location.getY());
                break;
            case NorthWest:
                break;
            default:
                System.out.println("Error no direction");
                break;
        }
    }
 }

