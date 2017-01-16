  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.Items.Item;
import downtoearth.Multiplayer.Client;
import shared.RemotePlayer;
import downtoearth.enums.DirectionType;
import downtoearth.enums.SpriteLocation;
import downtoearth.gameUtil.AnimationManager;
import downtoearth.gameUtil.Camera;
import shared.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import downtoearth.interfaces.Observer;
import downtoearth.world.Tile;
import downtoearth.world.World;
import java.util.ArrayList;
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
    private List<Observer> observers;
    
    private int thirst;
    private int hunger;
    private byte dir;
    private Camera cam;
    private Client c;
    private boolean moving;
    private Coordinate coordinate;
    private boolean attack;
    private Rectangle colBox;
    private Rectangle attBox;
    private World w;
    
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
    
    public Rectangle getAttackBox(){
        return attBox;
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
    
    public Player(String name, Coordinate location, int hitPoints, String path, World w) throws SlickException {
        super(name, location, hitPoints, path);
        this.observers = new ArrayList<Observer>();
        this.aManager = new AnimationManager(32 ,32);
        this.sManager = new SpriteManager("res/playerSprite.png");
        this.dir = DirectionType.NORTH; 
        this.moving = false;
        this.coordinate = new Coordinate(540,360);
        this.cam = new Camera(1080, 720, this.coordinate);
        this.w = w;
    }
    
    public void setSpawnPoint(int x, int y){
        this.coordinate.setX(x);
        this.coordinate.setY(y);
    }
    
    public void setClient(Client c){
        this.c = c;
    }

    public void move(Input input, List<Tile> tiles, List<NPC> entities, List<Opponent> opponents) throws SlickException{   
        moving = false;
        xa = 0;
        ya = 0;
        
        if(input.isKeyDown(Input.KEY_D)){ dir = DirectionType.EAST; xa = 1.3f; moving = true;}
        if(input.isKeyDown(Input.KEY_W)){ dir = DirectionType.NORTH; ya = -1.3f; moving = true;}
        if(input.isKeyDown(Input.KEY_S)){ dir = DirectionType.SOUTH; ya = 1.3f; moving = true;}
        if(input.isKeyDown(Input.KEY_A)){ dir = DirectionType.WEST; xa = -1.3f; moving = true;}
        
        if(!collision(tiles, entities, opponents)){
            if(xa != 0){
                this.coordinate.setX(this.coordinate.getXint()+ xa);
            }
            if(ya != 0){
                this.coordinate.setY(this.coordinate.getYint() + ya);
            }

            cam.setCoordinate(this.coordinate);
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
        
        if(attack){
            aManager.DrawAttack(this.dir, con);
            attack = false;
        }
    }

    public boolean collision(List<Tile> tiles, List<NPC> entities, List<Opponent> opponents) throws SlickException{
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
        for(NPC npc : entities)
        {
            if(this.getColBox().intersects(npc.getBounds())){
                return true;
            }
        }      
        for(Opponent opponent: opponents){
            if(this.colBox.intersects(opponent.getBounds())){
                return true;
            }
        }
        return false;
    }   
    
    public void attackCollision(List<Tile> tiles, List<NPC> entities, List<Opponent> opponents, Input input) throws SlickException
    {
        final int RANGE = 10;
        switch(dir){
             case DirectionType.NORTH:
                 attBox = new Rectangle(540-13, 360-24, 26, RANGE);
                 break;

             case DirectionType.EAST:
                 attBox = new Rectangle(540+14, 360-13, RANGE, 26);
                 break;

             case DirectionType.SOUTH:
                 attBox = new Rectangle(540-13, 360+14, 26, RANGE);
                 break;

             case DirectionType.WEST:
                 attBox = new Rectangle(540-23, 360-13, RANGE, 26);
                 break;
        } 

        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            attack = true;
             for(Tile tile : tiles){
                 if(this.getAttackBox().intersects(tile.getBounds())){
                     tile.Destroy();
                     tiles.remove(tile);
                     break;
                 }
             }
             for(NPC npc : entities)
             {
                 if(this.getAttackBox().intersects(npc.getBounds()) && "Test".equals(npc.name)){
                     npc.loseHp(10);
                     if(npc.isDead()){
                         entities.remove(npc);
                         break;
                     }
                 }
             }
             for(Opponent o : opponents)
             {
                 if(this.getAttackBox().intersects(o.getBounds()) && (o.getName()== null ? this.name != null : !o.getName().equals(this.name))){
                     this.attackOpponent(o.getPlayer());
                     break;
                 }
             }
        }
    }
    
    public void attack(List<Tile> tiles, List<NPC> entities, List<Opponent> opponents, Input input) throws SlickException
    {
        attackCollision(tiles, entities, opponents, input);
    }
    
    public void useItem(Item item){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void attackOpponent(RemotePlayer opponent)
    {
        c.attackPlayer(opponent);
        opponent.setHealth(opponent.getHealth() - 10);
        System.out.println("player attacked: " + opponent.getId());
    }
}

