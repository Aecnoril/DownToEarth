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
import downtoearth.world.Tile;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

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
    
    private String blocked;
    private boolean collision;
    
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
    
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    
    public Rectangle getBounds(){
        return new Rectangle( 542, 362, 28, 28);
    }

    //</editor-fold>
    
    public Player(String name, Point location, int hitPoints, String path) throws SlickException {
        super(name, location, hitPoints, path);
        this.aManager = new AnimationManager(32 ,32);
        this.sManager = new SpriteManager("res/playerSprite.png");
        this.cam = new Camera(1080, 720);
        this.dir = DirectionType.NORTH;
        this.moving = false;
        this.coordinate = new Coordinate(540,360);
    }
    
    public void setSpawnPoint(int x, int y){
        this.coordinate.setX(x);
        this.coordinate.setY(y);
    }
    
    public void move(Input input, List<Tile> tiles){     
        
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
    }
    
    public void render(GameContainer con) throws SlickException{
        if(moving){
            moving = false;
            aManager.DrawAnimation(this.dir, con);
        }else{
            SpriteLocation pos = DirectionType.getStandingSprite(dir);
            sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), con.getWidth() / 2, con.getHeight() / 2);
        }
    }
    
    public boolean Collision(List<Tile> tiles){
        for(Tile t : tiles){
            if(t.getBounds().contains(this.getBounds().getMaxX() + SPEED, t.getBounds().getY())){
                
            }
        }
        return false;
    }
    
    public void useItem(Item item){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

