/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.enums.DirectionType;
import downtoearth.enums.MobType;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Demian
 */
public class NPC extends LivingEntity {
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private MobType type;

    /**
     * Gets the value of the mob type
     * @return returns the type as a MobType enum
     */
    public MobType getType() {
        return type;
    }
    
    //</editor-fold>
    
    public NPC(String name, Point location, int hitPoints, MobType type, String path) throws SlickException {
        super(name, location, hitPoints, path);
        this.type = type;
    }
}
