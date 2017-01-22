/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items;

import downtoearth.entities.ItemEntity;
import shared.Coordinate;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Demian
 */
public class Tool extends Item{
    
    private final int DAMAGE;
    private final int ENVIRONMENTDAMAGE;
    private final int ATTACKSPEED;
    
    public int getDamage(){
        return DAMAGE;
    }
    
    public int getEnvironmentDamage(){
        return ENVIRONMENTDAMAGE;
    }
    
    public int getAttackSpeed(){
        return ATTACKSPEED;
    }
    
    public Tool(String name, byte type, double durability, double breakChange, int damage, int environDamage, int attackSpeed) throws SlickException {
        super(name, type, durability, breakChange);
        this.DAMAGE = damage;
        this.ENVIRONMENTDAMAGE = environDamage;
        this.ATTACKSPEED = attackSpeed;
    }
    
    @Override
    public ItemEntity drop(Coordinate coord) {
        return null;
    }
}
