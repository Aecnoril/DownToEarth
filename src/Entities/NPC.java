/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Items.Item;
import enums.MobType;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Demian
 */
public class NPC extends Entity {
    
    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private MobType type;
    private Item leftHand;
    private Item rightHand;
    private Item armorSet;

    public NPC(String name, SpriteSheet sprite, Point location, int hitPoints, MobType type) {
        super(name, sprite, location, hitPoints);
        this.type = type;
    }

    /**
     * Gets the value of the mob type
     * @return returns the type as a MobType enum
     */
    public MobType getType() {
        return type;
    }

    /**
     * gets the item currently equipped in the left hand
     * @return returns the item in the left hand as an Item
     */
    public Item getLeftHand() {
        return leftHand;
    }

    /**
     * gets the item currently equipped in the right hand
     * @return returns the item in the right hand as an Item
     */
    public Item getRightHand() {
        return rightHand;
    }

    /**
     * gets the currently equipped armour set
     * @return return the equipped armour set as Item
     */
    public Item getArmorSet() {
        return armorSet;
    }
    
    //</editor-fold>
    
    public void move(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void attack(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void loseHp(int damage){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
