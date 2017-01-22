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
    private  AnimationManager aManager;
    private Rectangle bounds;

    public Coordinate getLocation() {
        return PLAYER.getCoords();
    }

    public String getName() {
        return PLAYER.getId();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public RemotePlayer getPlayer() {
        return PLAYER;
    }

    public Opponent(RemotePlayer player) throws SlickException {
        this.PLAYER = player;
        this.bounds = new Rectangle(player.getCoords().getXint() + 2, player.getCoords().getYint() + 2, 28, 28);
        this.SMANAGER = new SpriteManager("res/PLAYERSprite.png");
    }

    public void draw(int posX, int posY) {
        bounds.setX(PLAYER.getCoords().getX() + 2 - posX);
        bounds.setY(PLAYER.getCoords().getY() + 2 - posY);
        try {
            if (PLAYER.getMoving()) {
                PLAYER.setMoving(false);
                aManager.DrawAnimation(PLAYER.getDir(), PLAYER.getCoords());
            } else {
                SpriteLocation pos = DirectionType.getStandingSprite(PLAYER.getDir());
                SMANAGER.drawSprite(pos.getSpriteX(), pos.getSpriteY(), PLAYER.getCoords().getXint() - posX - 16, PLAYER.getCoords().getYint() - posY - 16);
            }

            if (PLAYER.getAttacking()) {
                aManager.DrawAttack(PLAYER.getDir(), PLAYER.getCoords());
                PLAYER.setAttacking(false);
                if (PLAYER.getMoving()) {
                    PLAYER.setMoving(false);
                    aManager.DrawAnimation(PLAYER.getDir(), PLAYER.getCoords());
                } else {
                    SpriteLocation pos = DirectionType.getStandingSprite(PLAYER.getDir());
                    SMANAGER.drawSprite(pos.getSpriteX(), pos.getSpriteY(), PLAYER.getCoords().getXint() - posX - 16, PLAYER.getCoords().getYint() - posY - 16);
                }

                if (PLAYER.getAttacking()) {
                    aManager.DrawAttack(PLAYER.getDir(), PLAYER.getCoords());
                    PLAYER.setAttacking(false);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Opponent.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
