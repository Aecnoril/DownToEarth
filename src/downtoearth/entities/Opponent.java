/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.enums.DirectionType;
import downtoearth.enums.SpriteLocation;
import downtoearth.gameUtil.AnimationManager;
import downtoearth.gameUtil.SpriteManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import shared.Coordinate;
import shared.RemotePlayer;

/**
 *
 * @author Demian
 */
public class Opponent {
    private final RemotePlayer PLAYER;
    private final SpriteManager SMANAGER;
    private AnimationManager aManager;
    private final Rectangle BOUNDS;
    
    public Coordinate getLocation(){
        return PLAYER.getCoords();
    }
    
    public String getName(){
        return PLAYER.getId();
    }
    
    public Rectangle getBounds(){
        return BOUNDS;
    }
    
    public RemotePlayer getPlayer(){
        return PLAYER;
    }
    
    public Opponent(RemotePlayer player) throws SlickException{
        this.PLAYER = player;
        this.BOUNDS = new Rectangle(getLocation().getXint() - 16 , getLocation().getYint() -16, 28, 28);
        this.SMANAGER = new SpriteManager("res/playersprite.png");
    }
    
    public void draw(int posX, int posY){
        BOUNDS.setX(getLocation().getX()-16 - posX);
        BOUNDS.setY(getLocation().getY() -14 - posY);
        try{
            if(PLAYER.moving){
                PLAYER.moving = false;
                aManager.DrawAnimation(PLAYER.dir, PLAYER.getCoords());
            }else{
                SpriteLocation pos = DirectionType.getStandingSprite(PLAYER.dir);
                SMANAGER.drawSprite(pos.getSpriteX(), pos.getSpriteY(), PLAYER.getCoords().getXint() - posX -16, PLAYER.getCoords().getYint() - posY -16);
            }

            if(PLAYER.attack){
                aManager.DrawAttack(PLAYER.dir, PLAYER.getCoords());
                PLAYER.attack = false;
            }
        }catch(Exception e){
            Logger.getLogger(Opponent.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
