/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.Multiplayer.Contestant;
import downtoearth.enums.DirectionType;
import downtoearth.enums.MobType;
import downtoearth.enums.SpriteLocation;
import shared.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import downtoearth.world.Tile;
import static downtoearth.world.Tile.SPEED;
import downtoearth.world.World;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Demian
 * @author Sander
 */
public class NPC extends LivingEntity {
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private MobType type;
    private int count;
    
    private long startedMovingBasedOnPlayer = System.currentTimeMillis();
    private int timeToMoveBasedOnPlayer = 10000;
    private boolean shouldAttack = true;
    private int viewingAngle = 178; //Max 178 degrees
    private int viewingDistance = 1000000;
    
    private Rectangle bounds;
    private byte dir;
    private boolean moving;
    private World world;
    private SpriteManager sManager;
    private Rectangle colBox;
    public Rectangle getColBox(){
        return colBox;
    }

    /**
     * Get the value of bounds
     *
     * @return the value of bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Gets the value of the mob type
     * @return returns the type as a MobType enum
     */
    public MobType getType() {
        return type;
    }
    
    public byte getDir() {
        return dir;
    }
    
    
    //</editor-fold>
    
    public NPC(String name, Coordinate location, int hitPoints, MobType type, String path, World world) throws SlickException {
        super(name, location, hitPoints, path);
        this.count = 0;
        this.type = type;
        this.bounds = new Rectangle(location.getXint() + 2 , location.getYint() + 2, 28, 28);
        this.dir = DirectionType.SOUTH;
        this.sManager = new SpriteManager("res/playerSprite.png");
        this.moving = true;
        this.world = world;
    }
    
    private void setRandomMovingDirection() {
        int randomMoveStateChange = new Random().nextInt(250);
        if((!moving && randomMoveStateChange != 1) || (moving && randomMoveStateChange == 1)) {
            moving = !moving;
        }
        int randomDirChange = new Random().nextInt(125);
            if(randomDirChange == 1) {
                int randomDir = new Random().nextInt(8) + 1;
                int difference = randomDir - (int) dir;
                if(difference < 0){
                    difference = difference * -1;
                }
                if(difference > 2){
                    randomDir += 2;
                    if(randomDir > 8){
                        randomDir -= 4;
                    }
                }
                dir = (byte) randomDir;
            }
    }
    
    private boolean isInRange(double playerX, double playerY) {
        if(viewingAngle > 178) {
            viewingAngle = 178;
        }
        float ownX = this.location.getX();
        float ownY = this.location.getY();
        
        
        if(dir == DirectionType.NORTH || dir == DirectionType.NORTHEAST || dir == DirectionType.NORTHWEST) {
            double alpha = viewingAngle / 2;
            double x = Math.sin(alpha) * viewingDistance;
            double y = Math.cos(alpha) * viewingDistance;
            double leftYX = y / (x * -1);
            double rightYX = y / x;
            
            if(playerY <= ownY + (playerX - ownX) * leftYX &&
                    playerY <= ownY + (playerX - ownX) * rightYX) {
                double distance = Math.sqrt(Math.pow(Math.abs(ownX - playerX), 2) + Math.pow(Math.abs(ownY - playerY), 2));
                if(distance <= viewingDistance) {
//                    System.out.println("Player in view! (North)");
                    return true;
                }
            }
        } else if(dir == DirectionType.SOUTH || dir == DirectionType.SOUTHEAST || dir == DirectionType.SOUTHWEST) {
            double alpha = viewingAngle / 2;
            double x = Math.sin(alpha) * viewingDistance;
            double y = Math.cos(alpha) * viewingDistance;
            double leftYX = (y * -1) / (x * -1);
            double rightYX = (y * -1) / x;
            
            if(playerY >= ownY + (playerX - ownX) * leftYX &&
                    playerY >= ownY + (playerX - ownX) * rightYX) {
                double distance = Math.sqrt(Math.pow(Math.abs(ownX - playerX), 2) + Math.pow(Math.abs(ownY - playerY), 2));
                if(distance <= viewingDistance) {
//                    System.out.println("Player in view! (South)");
                    return true;
                }
            }
        } else if(dir == DirectionType.WEST) {
            double alpha = viewingAngle / 2;
            double y = Math.sin(alpha) * viewingDistance;
            double x = Math.cos(alpha) * viewingDistance;
            double bottomXY = (x * -1) / (y * -1);
            double topXY = x / (y * -1);
            
            if(playerX <= ownX + (playerY - ownY) * bottomXY && 
                    playerX <= ownX + (playerY - ownY) * topXY) {
                double distance = Math.sqrt(Math.pow(Math.abs(ownX - playerX), 2) + Math.pow(Math.abs(ownY - playerY), 2));
                if(distance <= viewingDistance) {
//                    System.out.println("Player in view! (West)");
                    return true;
                }
            }
        } else if(dir == DirectionType.EAST) {
            double alpha = viewingAngle / 2;
            double y = Math.sin(alpha) * viewingDistance;
            double x = Math.cos(alpha) * viewingDistance;
            double topXY = (x * -1) / (y * -1);
            double bottomXY = x / (y * -1);
            
            if(playerX >= ownX + (playerY - ownY) * bottomXY && 
                    playerX >= ownX + (playerY - ownY) * topXY) {
                double distance = Math.sqrt(Math.pow(Math.abs(ownX - playerX), 2) + Math.pow(Math.abs(ownY - playerY), 2));
                if(distance <= viewingDistance) {
//                    System.out.println("Player in view! (East)");
                    return true;
                }
            }
        }
        return false;
    }
    
    private Opponent getContestantInRange(List<Opponent> opponents) {
        for(Opponent opponent : opponents) {
            if(isInRange(opponent.getLocation().getX(), opponent.getLocation().getY())) {
                return opponent;
            }
        }
        return null;
    }
    
    private Player getPlayerInRange(Player player) {
        if(isInRange(player.getCamX(), player.getCamY())) {
            return player;
        }
        return null;
    }
    
    public void move(Input input, List<Tile> tiles, List<NPC> entities, List<Opponent> opponents) throws SlickException {
        double inRangePlayerX = -1;
        double inRangePlayerY = -1;
        Player inRangePlayer = getPlayerInRange(world.getPlayer());
        if(inRangePlayer == null && opponents != null) {
            Opponent inRangeContestant = getContestantInRange(opponents);
            if(inRangeContestant != null) {
                inRangePlayerX = inRangeContestant.getLocation().getX();
                inRangePlayerY = inRangeContestant.getLocation().getY();
            }
        } else if(inRangePlayer != null) {
            inRangePlayerX = inRangePlayer.getCamX();
            inRangePlayerY = inRangePlayer.getCamY();
        }
        
        if(inRangePlayerX != -1 && 
                ((
                startedMovingBasedOnPlayer > 0 
                && (System.currentTimeMillis() - startedMovingBasedOnPlayer) < timeToMoveBasedOnPlayer)
                ||
                (startedMovingBasedOnPlayer < 0 
                && (System.currentTimeMillis() - -startedMovingBasedOnPlayer) > (timeToMoveBasedOnPlayer * 0.1)))
                ) { 
            if(startedMovingBasedOnPlayer < 0) {
                System.out.println("Started moving based on player");
                startedMovingBasedOnPlayer = System.currentTimeMillis();
            }
            moving = true;
            float ownX = this.location.getX();
            float ownY = this.location.getY();
            if(shouldAttack) {
                if((inRangePlayerX - ownX) > 2) {
                    dir = DirectionType.EAST;
                    if((inRangePlayerY - ownY) > 2) {
                        dir = DirectionType.SOUTHEAST;
                    } else if((inRangePlayerY - ownY) < -2) {
                        dir = DirectionType.NORTHEAST;
                    }
                } else if((inRangePlayerX - ownX) < -2) {
                    dir = DirectionType.WEST;
                    if((inRangePlayerY - ownY) > 2) {
                        dir = DirectionType.SOUTHWEST;
                    } else if((inRangePlayerY - ownY) < -2) {
                        dir = DirectionType.NORTHWEST;
                    }
                } else if((inRangePlayerY - ownY) > 2) {
                    dir = DirectionType.SOUTH;
                } else{
                    dir = DirectionType.NORTH;
                }
            } else {
                if((inRangePlayerX - ownX) > 2) {
                    dir = DirectionType.WEST;
                    if((inRangePlayerY - ownY) > 2) {
                        dir = DirectionType.NORTHWEST;
                    } else if((inRangePlayerY - ownY) < -2) {
                        dir = DirectionType.SOUTHWEST;
                    }
                } else if((inRangePlayerX - ownX) < -2) {
                    dir = DirectionType.EAST;
                    if((inRangePlayerY - ownY) > 2) {
                        dir = DirectionType.NORTHEAST;
                    } else if((inRangePlayerY - ownY) < -2) {
                        dir = DirectionType.SOUTHEAST;
                    }
                } else if((inRangePlayerY - ownY) > 2) {
                    dir = DirectionType.NORTH;
                } else {
                    dir = DirectionType.SOUTH;
                }
            }
        } else {
            if(startedMovingBasedOnPlayer > 0) {
                System.out.println("Stoped moving based on player");
                startedMovingBasedOnPlayer = -System.currentTimeMillis();
            }
            setRandomMovingDirection();
        }
        
        if(moving){
            float xa = 0;
            float ya = 0;
        
            switch(dir){
                case DirectionType.NORTH:
                    ya = SPEED * -1; 
                    break;
                case DirectionType.NORTHEAST:
                    ya = SPEED * -1;
                    xa = SPEED;
                    break;
                case DirectionType.EAST:
                    xa = SPEED;
                    break;
                case DirectionType.SOUTHEAST:
                    ya = SPEED;
                    xa = SPEED;
                    break;
                case DirectionType.SOUTH:
                    ya = SPEED;
                    break;
                case DirectionType.SOUTHWEST:
                    ya = SPEED;
                    xa = SPEED * -1;
                    break;
                case DirectionType.WEST:
                    xa = SPEED * -1;
                    break;
                case DirectionType.NORTHWEST:
                    ya = SPEED * -1;
                    xa = SPEED * -1;
            }
            
            if(!collision(tiles, entities)){
                float newX = this.location.getX() + xa;
                if(xa != 0 && newX >= 0 && newX <= this.world.getMapSize().width){
                   this.location.setX(newX);
                }
                float newY = this.location.getY() + ya;
                if(ya != 0 && newY >= 0 && newY <= this.world.getMapSize().height){
                    this.location.setY(newY);
                }
            }
        }
    }
    
    public boolean collision(List<Tile> tiles, List<NPC> entities) throws SlickException{
        switch(dir){
             case DirectionType.NORTH:
             case DirectionType.NORTHEAST:
             case DirectionType.NORTHWEST:
                 colBox = new Rectangle(540-13, 360-14, 26, 1);
                 break;

             case DirectionType.EAST:
                 colBox = new Rectangle(540+14, 360-13, 1, 26);
                 break;

             case DirectionType.SOUTH:
             case DirectionType.SOUTHEAST:
             case DirectionType.SOUTHWEST:
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
        return false;
    }   

    public void draw(int posX, int posY) {    
        SpriteLocation pos = DirectionType.getStandingSprite(dir);
        bounds.setX(location.getX()+2 - posX);
        bounds.setY(location.getY()+2 - posY);
        sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), location.getXint() - posX -16, location.getYint() - posY -16);
    }
}
