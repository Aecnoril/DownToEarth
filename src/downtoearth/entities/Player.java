  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.Items.Item;
import downtoearth.Items.Resource;
import downtoearth.enums.DirectionType;
import downtoearth.enums.ResourceType;
import downtoearth.enums.SpriteLocation;
import downtoearth.enums.TileType;
import downtoearth.gameUtil.AnimationManager;
import downtoearth.gameUtil.Camera;
import downtoearth.gameUtil.CollisionCheck;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import downtoearth.world.Tile;
import downtoearth.world.World;
import java.util.List;
import net.java.games.input.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
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
    private Line colLine;
    private Camera cam;
    private boolean moving;
    private Coordinate coordinate;
    private CollisionCheck cCheck;
    private Line attackColLine;
    private boolean attack;
    
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
        return new Rectangle( 542 - 16, 362 - 16, 28, 28);
    }
    
    public Line getColLine(){
        return this.colLine;
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
    
    public Line getAttackColLine(){
        return this.attackColLine;
    }
    

    //</editor-fold>
    
    public Player(String name, Coordinate location, int hitPoints, String path) throws SlickException {
        super(name, location, hitPoints, path);
        this.aManager = new AnimationManager(32 ,32);
        this.sManager = new SpriteManager("res/playerSprite.png");
        this.cam = new Camera(1080, 720);
        this.dir = DirectionType.NORTH;
        this.moving = false;
        this.colLine = new Line(540, 360, 540, 360 + 20);
        this.coordinate = new Coordinate(540,360);
        this.cCheck = new CollisionCheck();
    }
    
    public void setSpawnPoint(int x, int y){
        this.coordinate.setX(x);
        this.coordinate.setY(y);
    }
    
    public void move(Input input, List<Tile> tiles){     
        if(input.isKeyDown(Input.KEY_W)){ cam.getCoordinate().setY(cam.getCoordinate().getY() - SPEED); dir = DirectionType.NORTH;}
        if(input.isKeyDown(Input.KEY_D)){ cam.getCoordinate().setX(cam.getCoordinate().getX() + SPEED); dir = DirectionType.EAST;}
        if(input.isKeyDown(Input.KEY_S)){ cam.getCoordinate().setY(cam.getCoordinate().getY() + SPEED); dir = DirectionType.SOUTH;}
        if(input.isKeyDown(Input.KEY_A)){ cam.getCoordinate().setX(cam.getCoordinate().getX() - SPEED); dir = DirectionType.WEST;}
    }
    
    public void render(GameContainer con) throws SlickException{
        if(moving){
            moving = false;
            aManager.DrawAnimation(this.dir, con);
        }else{
            SpriteLocation pos = DirectionType.getStandingSprite(dir);
            sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), con.getWidth() / 2 - 16, con.getHeight() / 2 - 16);
        }
    }
    
    public void collision(){
        cCheck.clearCol();
            switch(dir){
                case DirectionType.NORTH:
                    {
                        System.out.println("North Collision");
                        cCheck.setNorthCol(true);
                        break;
                    }
                case DirectionType.NORTHEAST:
                    {
                        System.out.println("NorthEast Collision");
                        cCheck.setNorthEastCol(true);
                        break;
                    }
                case DirectionType.EAST:
                    {
                        System.out.println("East Collision");
                        cCheck.setEastCol(true);
                        break;
                    }
                case DirectionType.SOUTHEAST:
                    {
                        System.out.println("SouthEast Collision");
                        cCheck.setSouthEastCol(true);
                        break;
                    }
                case DirectionType.SOUTH:
                    {
                        System.out.println("South Collision");
                        cCheck.setSouthCol(true);
                        break;
                    }
                case DirectionType.SOUTHWEST:
                    {
                        System.out.println("SouthWest Collision");
                        cCheck.setSouthWestCol(true);
                        break;
                    }
                case DirectionType.WEST:
                    {
                        System.out.println("West Collision");
                        cCheck.setWestCol(true);
                        break;
                    }
                case DirectionType.NORTHWEST:
                    {
                        System.out.println("NorthWest Collision");
                        cCheck.setNorthWestCol(true);
                        break;
                    }
            }
        }
    
    public void attackCollision()
    {
        attack = true;
            switch(dir){
                case DirectionType.NORTH:
                    {
                        this.attackColLine = new Line(540, 360, 540, 360 - 40);
                        break;
                        
                    }
                case DirectionType.NORTHEAST:
                    {
                        this.attackColLine = new Line(540, 360, 540 + 40, 360 - 40);
                        break;
                        
                    }
                case DirectionType.EAST:
                    {
                        this.attackColLine = new Line(540, 360, 540 + 40, 360);
                        break;
                        
                        
                    }
                case DirectionType.SOUTHEAST:
                    {
                        this.attackColLine = new Line(540, 360, 540 + 40, 360 + 40);
                        break;
                        
                    }
                case DirectionType.SOUTH:
                    {
                        this.attackColLine = new Line(540, 360, 540, 360 + 40);
                        break;
                        
                    }
                case DirectionType.SOUTHWEST:
                    {
                        this.attackColLine = new Line(540, 360, 540 - 40, 360 + 40);
                        break;
                        
                    }
                case DirectionType.WEST:
                    {
                        this.attackColLine = new Line(540, 360, 540 - 40, 360);
                        break;
                        
                    }
                case DirectionType.NORTHWEST:
                    {
                        this.attackColLine = new Line(540, 360, 540 - 40, 360 - 40);
                        break;
                    }
            }
            //this.attackColLine = new Line(540, 360);
        }
    
    public void attack(NPC n) throws SlickException
    {
        if(attack)
        {
            n.setHitPoints(n.hitPoints - 10);
            System.out.println(n.hitPoints);
            attack = false;
            n.onDeath();
        }
        
        this.attackColLine = new Line(540, 360);


    }
    
    public void attack(Tile t) throws SlickException
    {
        if(attack)
        {
            switch(t.getType())
        {
            case TileType.COAL:
            {
                Resource coal = new Resource("Coal", ResourceType.COAL, 100, 0);
                this.inventory.add(coal);
                System.out.println("Added Coal");
                break;
            }
            case TileType.GEMSTONE:
            {   
                Resource gem = new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0);
                this.inventory.add(gem);
                break;
            }
            case TileType.STONE:
            {
                Resource stone = new Resource("Stone", ResourceType.STONE, 100, 0);
                this.inventory.add(stone);
                break;
            }
            case TileType.TREE:
            {
                Resource wood = new Resource("Wood", ResourceType.WOOD, 100, 0);
                this.inventory.add(wood);
                break;
            }
        }
        attack = false;
        }
        t.setDestroyed(true);
        this.attackColLine = new Line(540, 360);
        
    }
    public void useItem(Item item){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

