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
    
    public Opponent(RemotePlayer player) throws SlickException{
        this.player = player;
        this.bounds = new Rectangle(player.getCoords().getXint() + 2 , player.getCoords().getYint() + 2, 28, 28);
        this.sManager = new SpriteManager("res/playerSprite.png");
    }
    
    public void draw(int posX, int posY){
        bounds.setX(player.getCoords().getX()+2 - posX);
        bounds.setY(player.getCoords().getY()+2 - posY);
        try{
            if(player.moving){
                player.moving = false;
                aManager.DrawAnimation(player.dir, player.getCoords());
            }else{
                SpriteLocation pos = DirectionType.getStandingSprite(player.dir);
                sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), player.getCoords().getXint() - posX -16, player.getCoords().getYint() - posY -16);
            }

            if(player.attack){
                aManager.DrawAttack(player.dir, player.getCoords());
                player.attack = false;
            }
        }catch(Exception e){
            Logger.getLogger(Opponent.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
