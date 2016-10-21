/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.enums.DirectionType;
import downtoearth.enums.MobType;
import downtoearth.enums.SpriteLocation;
import downtoearth.gameUtil.AnimationManager;
import downtoearth.gameUtil.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Demian
 */
public class NPC extends LivingEntity {

    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private MobType type;
    private AnimationManager aManager;
    private SpriteManager sManager;
    private boolean moving;

    /**
     * Gets the value of the mob type
     *
     * @return returns the type as a MobType enum
     */
    public MobType getType() {
        return type;
    }

    //</editor-fold>
    public NPC(String name, Coordinate location, int hitPoints, MobType type, String path) throws SlickException {
        super(name, location, hitPoints, path);
        this.type = type;
        this.aManager = new AnimationManager(32, 32);
        this.sManager = new SpriteManager("res/playerSprite.png");
        dir = DirectionType.SOUTH;
        moving = false;
    }

    public void render(GameContainer con, int cameraX, int cameraY) throws SlickException {
        SpriteLocation pos = DirectionType.getStandingSprite(dir);
        int x = (int)getLocation().x - cameraX;
        int y = (int)getLocation().y - cameraY;
        System.out.println(x + ", " + y);
        sManager.drawSprite(pos.getSpriteX(), pos.getSpriteY(), x, y);
    }
}
