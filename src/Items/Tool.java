/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Items.Item;
import enums.ItemType;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Demian
 */
public class Tool extends Item{
    
    private int damage;
    private int environmentDamage;
    private int attackSpeed;
    
    public int getDamage(){
        return damage;
    }
    
    public int getEnvironmentDamage(){
        return environmentDamage;
    }
    
    public int getAttackSpeed(){
        return attackSpeed;
    }
    
    public Tool(String name, byte type, double durability, double breakChange, int damage, int environdamage, int attackSpeed) throws SlickException {
        super(name, type, durability, breakChange);
        this.damage = damage;
        this.environmentDamage = environmentDamage;
        this.attackSpeed = attackSpeed;
    }
    
    @Override
    public void drop(){
        //TODO: implement
    }
}
