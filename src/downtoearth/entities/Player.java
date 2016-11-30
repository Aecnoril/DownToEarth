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
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Demian
 */
public class Player extends LivingEntity{
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    
    public float xa, ya;
    
    private int thirst;
    private int hunger;
    private byte dir;
    private Camera cam;
    private boolean moving;
    private Coordinate coordinate;
    private boolean attack;
    private Rectangle colBox;
    
    private AnimationManager aManager;
    private SpriteManager sManager;

    public int getThirst() {
        return thirst;
    }

    public int getHunger() {
        return hunger;
    }
    
    public Camera getCamera(){
        return this.cam;
    }
    
    public float getCamX(){
        return this.cam.getCoordinate().getX();
    }
    
    public float getCamY(){
        return this.cam.getCoordinate().getY();
    }
    
    public void setCamX(float value){
        cam.getCoordinate().setX(value);
    }
    
    public void setCamY(float value){
        cam.getCoordinate().setY(value);
    }
    
    public Coordinate getCoordinate(){
        return this.cam.getCoordinate();
    }
    
    public Rectangle getBounds(){
        return new Rectangle( 542 - 16, 362 - 16, 28, 28);
    }
    
    public Rectangle getColBox(){
        return colBox;
    }

    public byte getDir() {
        return dir;
    }
    
    public boolean setAttack(){
        return false;
    }
    
    public boolean getAttack(){
        return this.attack;
    }
    

    //</editor-fold>
    
    public Player(String name, Coordinate location, int hitPoints, String path) throws SlickException {
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
        moving = false;
        xa = 0;
        ya = 0;
        
        if(input.isKeyDown(Input.KEY_D)){ dir = DirectionType.EAST; xa = 1.3f; moving = true;}
        if(input.isKeyDown(Input.KEY_W)){ dir = DirectionType.NORTH; ya = -1.3f; moving = true;}
        if(input.isKeyDown(Input.KEY_S)){ dir = DirectionType.SOUTH; ya = 1.3f; moving = true;}
        if(input.isKeyDown(Input.KEY_A)){ dir = DirectionType.WEST; xa = -1.3f; moving = true;}
        
        if(!collision(this.getCamX() + xa, this.getCamY() + ya, tiles)){
            this.setCamX(this.getCamX() + xa);
            this.setCamY(this.getCamY() + ya);
            this.coordinate = cam.getCoordinate();
        }
        else{
            moving = false;
        }
    }
    
    public void render(GameContainer con) throws SlickException{
        if(moving){
            moving = false;
            aManager.DrawAnimation(this.dir, con);
        }else{
            SpriteLocation pos = DirectionType.getStandingSprite(dir);
            sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), (con.getWidth()/2)-16, (con.getHeight()/2)-16);
        }
    }

    public boolean collision(float x, float y, List<Tile> tiles){
       switch(dir){
            case DirectionType.NORTH:
                colBox = new Rectangle(540-13, 360-14, 26, 1);
                break;
               
            case DirectionType.EAST:
                colBox = new Rectangle(540+14, 360-13, 1, 26);
                break;
               
            case DirectionType.SOUTH:
                colBox = new Rectangle(540-13, 360+14, 26, 1);
                break;
               
            case DirectionType.WEST:
                colBox = new Rectangle(540-16, 360-13, 1, 26);
                break;
       }
       for(Tile tile : tiles){
           if(this.getColBox().intersects(tile.getBounds())){
               return true;
           }
       }
       return false;
    }
    
    public void attackCollision()
    {
        
    }
    
    public void attack(NPC n) throws SlickException
    {
        
    }
    
    public void attack(Tile t) throws SlickException
    {
        
    }
    
    public void useItem(Item item){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

