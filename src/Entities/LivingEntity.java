/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Items.Item;
import enums.DirectionType;
import gameUtil.SpriteManager;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Vernoxius
 */
public abstract class LivingEntity extends Entity {

    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    protected Item leftHand;
    protected Item rightHand;
    protected Item armorSet;
    protected DirectionType direction;

    /**
     * gets the item currently equipped in the left hand
     *
     * @return returns the item in the left hand as an Item
     */
    public Item getLeftHand() {
        return leftHand;
    }

    /**
     * gets the item currently equipped in the right hand
     *
     * @return returns the item in the right hand as an Item
     */
    public Item getRightHand() {
        return rightHand;
    }

    /**
     * gets the currently equipped armour set
     *
     * @return return the equipped armour set as Item
     */
    public Item getArmorSet() {
        return armorSet;
    }

    //</editor-fold>
    public LivingEntity(String name, SpriteSheet sprite, Point location, int hitPoints, String path) throws SlickException {
        super(name, sprite, location, hitPoints, path);
    }

    @Override
    public void render() throws SlickException {
        switch (direction) {
            case North:
                spriteManager.getSprite(64, 11, 50, 50);
                break;
            case NorthEast:
                break;
            case East:
                spriteManager.getSprite(31, 0, 50, 50);
                break;
            case SouthEast:
                break;
            case South:
                spriteManager.getSprite(0, 0, 50, 50);
                break;
            case SouthWest:
                break;
            case West:
                spriteManager.getSprite(96, 11, 50, 50);
                break;
            case NorthWest:
                break;
            default:
                System.out.println("Error no direction");
                break;
        }
    }

    public void loseHp(int damage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void attack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
