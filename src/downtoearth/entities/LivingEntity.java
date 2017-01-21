    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.items.Item;
import downtoearth.enums.DirectionType;
import shared.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import org.newdawn.slick.SlickException;

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
    protected boolean dead;
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

    public boolean isDead() {
        return dead;
    }
    

    //</editor-fold>
    public LivingEntity(String name, Coordinate location, int hitPoints, String path) throws SlickException {
        super(name, location, hitPoints, path);
        this.spriteManager = new SpriteManager(path, 30, 59, 1, 0);
    }

    public void loseHp(int damage) throws SlickException {
        this.hitPoints = this.hitPoints - damage;
        onDeath();
    }

    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void attack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
        
    public void onDeath() throws SlickException
    {
        if(this.hitPoints <= 0)
        {
            for(Item i : inventory)
            {
                i.drop(this.location);
            }
            this.dead = true;
        }
    }

}
