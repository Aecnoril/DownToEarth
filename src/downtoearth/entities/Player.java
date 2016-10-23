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
import java.util.List;
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
    private Line attackColLine;
    private Camera cam;
    private boolean moving;
    private Coordinate coordinate;
    private CollisionCheck cCheck;
    private boolean attack;
    
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
        return new Rectangle( 542 - 16, 362 - 16, 28, 28);
    }
    
    public Line getColLine(){
        return this.colLine;
    }
    
    public Line getAttackColLine(){
        return this.attackColLine;
    }
    
    public boolean setAttack(){
        return false;
    }
    public boolean getAttack(){
        return this.attack;
    }

    public byte getDir() {
        return dir;
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
        this.attackColLine = null;
        this.location = new Coordinate(540,360);
        this.cCheck = new CollisionCheck();
    }
    
    public void setSpawnPoint(int x, int y){
        this.coordinate.setX(x);
        this.coordinate.setY(y);
    }
    
    public void move(Input input){     
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_W) && !cCheck.isNorthEastCol()){
            this.colLine = new Line(540, 360, 540 + 20, 360 - 20);
            cam.getCoordinate().setY(cam.getCoordinate().getY() - SPEED);
            cam.getCoordinate().setX(cam.getCoordinate().getX() + SPEED);
            moving = true;
        }
        else if(input.isKeyDown(Input.KEY_W) && !cCheck.isNorthCol()){
            this.colLine = new Line(540, 360, 540, 360 - 20);
            cam.getCoordinate().setY(cam.getCoordinate().getY() - SPEED);
            dir = DirectionType.NORTH;
            moving = true;
        }

        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_S) && !cCheck.isSouthEastCol()){
            this.colLine = new Line(540, 360, 540 + 20, 360 + 20);
            cam.getCoordinate().setY(cam.getCoordinate().getY() + SPEED);
            cam.getCoordinate().setX(cam.getCoordinate().getX() + SPEED);
            moving = true;
        }
        else if(input.isKeyDown(Input.KEY_D) && !cCheck.isEastCol()){
            this.colLine = new Line(540, 360, 540 + 20, 360);
            cam.getCoordinate().setX(cam.getCoordinate().getX() + SPEED);
            dir = DirectionType.EAST;
            moving = true;
        }

        if(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A) && !cCheck.isSouthWestCol()){
            this.colLine = new Line(540, 360, 540 - 20, 360 + 20);
            cam.getCoordinate().setY(cam.getCoordinate().getY() + SPEED);
            cam.getCoordinate().setX(cam.getCoordinate().getX() - SPEED);
            moving = true;
        }
        else if(input.isKeyDown(Input.KEY_S) && !cCheck.isSouthCol()){
            this.colLine = new Line(540, 360, 540, 360 + 20);
            cam.getCoordinate().setY(cam.getCoordinate().getY() + SPEED);
            dir = DirectionType.SOUTH;
            moving = true;
        }

        if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_W) && !cCheck.isNorthEastCol()){
            this.colLine = new Line(540, 360, 540 - 20, 360 - 20);
            cam.getCoordinate().setY(cam.getCoordinate().getY() - SPEED);
            cam.getCoordinate().setX(cam.getCoordinate().getX() - SPEED);
            moving = true;
        }
        else if(input.isKeyDown(Input.KEY_A) && !cCheck.isEastCol()){
            this.colLine = new Line(540, 360, 540 - 20, 360);
            cam.getCoordinate().setX(cam.getCoordinate().getX() - SPEED);
            dir = DirectionType.WEST;
            moving = true;
        } 
        //cCheck.clearCol();
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
                    }
                case DirectionType.NORTHEAST:
                    {
                        System.out.println("NorthEast Collision");
                        cCheck.setNorthEastCol(true);
                    }
                case DirectionType.EAST:
                    {
                        System.out.println("East Collision");
                        cCheck.setEastCol(true);
                    }
                case DirectionType.SOUTHEAST:
                    {
                        System.out.println("SouthEast Collision");
                        cCheck.setSouthEastCol(true);
                    }
                case DirectionType.SOUTH:
                    {
                        System.out.println("South Collision");
                        cCheck.setSouthCol(true);
                    }
                case DirectionType.SOUTHWEST:
                    {
                        System.out.println("SouthWest Collision");
                        cCheck.setSouthWestCol(true);
                    }
                case DirectionType.WEST:
                    {
                        System.out.println("West Collision");
                        cCheck.setWestCol(true);
                    }
                case DirectionType.NORTHWEST:
                    {
                        System.out.println("NorthWest Collision");
                        cCheck.setNorthWestCol(true);
                    }
            }
        }
    
    public void attackCollision()
    {
        attack = true;
       // cCheck.clearCol();
            switch(dir){
                case DirectionType.NORTH:
                    {
                        this.attackColLine = new Line(540, 360, 540, 360 - 40);
                        
                    }
                case DirectionType.NORTHEAST:
                    {
                        this.attackColLine = new Line(540, 360, 540 + 40, 360 - 40);
                        
                    }
                case DirectionType.EAST:
                    {
                        this.attackColLine = new Line(540, 360, 540 + 40, 360);
                        
                        
                    }
                case DirectionType.SOUTHEAST:
                    {
                        this.attackColLine = new Line(540, 360, 540 + 40, 360 + 40);
                        
                        
                    }
                case DirectionType.SOUTH:
                    {
                        this.attackColLine = new Line(540, 360, 540, 360 + 40);
                        
                        
                    }
                case DirectionType.SOUTHWEST:
                    {
                        this.attackColLine = new Line(540, 360, 540 - 40, 360 + 40);
                        
                        
                    }
                case DirectionType.WEST:
                    {
                        this.attackColLine = new Line(540, 360, 540 - 40, 360);
                        
                        
                    }
                case DirectionType.NORTHWEST:
                    {
                        this.attackColLine = new Line(540, 360, 540 - 40, 360 - 40);
                        
                    }
            }
            this.attackColLine = new Line(540, 360);
        }
    
    public void attack(NPC n)
    {
        if(attack)
        {
                    n.setHitPoints(n.hitPoints - 10);
        System.out.println(n.hitPoints);
                attack = false;
        }


    }
    
    public void attack(Tile t) throws SlickException
    {
        switch(t.getType())
        {
            case TileType.COAL:
            {
                Resource coal = new Resource("Coal", ResourceType.COAL, 100, 0);
                this.inventory.add(coal);
                System.out.println("Added Coal");
            }
            case TileType.GEMSTONE:
            {   
                Resource gem = new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0);
                this.inventory.add(gem);
            }
            case TileType.STONE:
            {
                Resource stone = new Resource("Stone", ResourceType.STONE, 100, 0);
                this.inventory.add(stone);
            }
            case TileType.TREE:
            {
                Resource wood = new Resource("Wood", ResourceType.WOOD, 100, 0);
                this.inventory.add(wood);
            }
        }
    }
    public void useItem(Item item){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

