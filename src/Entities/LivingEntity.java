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
    public LivingEntity(String name, Point location, int hitPoints, String path) throws SlickException {
        super(name, location, hitPoints, path);
        this.spriteManager = new SpriteManager(path, 30, 59, 1, 0);
    }

    @Override
    public void render(DirectionType direction) throws SlickException {
        switch (direction) {
            case North:
                spriteManager.getCharacterSprite(2, 0, (int)this.location.getX(), (int)this.location.getY());
                break;
            case NorthEast:
                break;
            case East:
                spriteManager.getCharacterSprite(1, 0, (int)this.location.getX(), (int)this.location.getY());
                break;
            case SouthEast:
                break;
            case South:
                spriteManager.getCharacterSprite(0, 0, (int)this.location.getX(), (int)this.location.getY());
                break;
            case SouthWest:
                break;
            case West:
                spriteManager.getCharacterSprite(3, 0, (int)this.location.getX(), (int)this.location.getY());
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
