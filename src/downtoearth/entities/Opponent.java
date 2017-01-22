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
import org.newdawn.slick.geom.Rectangle;
import shared.Coordinate;
import shared.RemotePlayer;

/**
 *
 * @author Demian
 */
public class Opponent {
    private RemotePlayer player;
    private SpriteManager sManager;
    private AnimationManager aManager;
    private Rectangle bounds;
    
    public Coordinate getLocation(){
        return player.getCoords();
    }
    
    public String getName(){
        return player.getId();
    }
    
    public Rectangle getBounds(){
        return bounds;
    }
    
    public RemotePlayer getPlayer(){
        return player;
    }
    
    public Opponent(RemotePlayer player){
        this.player = player;
        this.bounds = new Rectangle(player.getCoords().getXint() + 2 , player.getCoords().getYint() + 2, 28, 28);
    }
    
    public void draw(int posX, int posY){
        bounds.setX(player.getCoords().getX()+2 - posX);
        bounds.setY(player.getCoords().getY()+2 - posY);
        try{
            if(player.getMoving()){
                player.setMoving(false);
                aManager.DrawAnimation(player.getDir(), player.getCoords());
            }else{
                SpriteLocation pos = DirectionType.getStandingSprite(player.getDir());
                sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), player.getCoords().getXint() - posX -16, player.getCoords().getYint() - posY -16);
            }

            if(player.getAttacking()){
                aManager.DrawAttack(player.getDir(), player.getCoords());
                player.setAttacking(false);
=======
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
>>>>>>> endpoint
            }
        }catch(Exception e){
            Logger.getLogger(Opponent.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
