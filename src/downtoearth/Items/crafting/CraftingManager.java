/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items.crafting;

import downtoearth.Items.Armor;
import downtoearth.Items.Item;
import downtoearth.Items.Resource;
import downtoearth.Items.Tool;
import downtoearth.enums.ArmorType;
import downtoearth.enums.ResourceType;
import downtoearth.enums.Tooltype;
import downtoearth.enums.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Sanko
 */
public class CraftingManager {

    private static final CraftingManager CRAFTING_MANAGER = new CraftingManager();
    private List<CraftingRecipe> recipes = new ArrayList<>();
    
    public List<CraftingRecipe> getRecipes(){
        return recipes;
    }

    CraftingManager(){
        try {
            this.recipes.add(new CraftingRecipe(new Resource("Stick", Type.STICK, 100, 0), 2, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Resource("Steel", Type.STEEL, 100, 0), 1, new Object[]{new Resource("Coal", ResourceType.COAL, 100, 0), 1, new Resource("Stone", ResourceType.STONE, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Resource("Bucket", Type.BUCKET, 100, 0), 2, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Armor("Wood Armor", Type.WOODENARMOR, 100, 10, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 4}));
            this.recipes.add(new CraftingRecipe(new Armor("StoneArmor", Type.STONEARMOR, 100, 0, 2), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 4}));
            this.recipes.add(new CraftingRecipe(new Armor("Steel Armor", Type.STEELARMOR, 100, 10, 3), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 4}));
            this.recipes.add(new CraftingRecipe(new Armor("Gem Armor", Type.GEMARMOR, 100, 10, 3), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 4}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Sword", Type.WOODENSWORD, 100, 10, 1, 0, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Sword", Type.STONESWORD, 100, 10, 2, 0, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Sword", Type.STEELSWORD, 100, 10, 3, 0, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Sword", Type.GEMSWORD, 100, 10, 4, 0, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Armor("Wooden Shield", Type.WOODENSHIELD, 100, 10, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 4, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Armor("Stone Shield", Type.STONESHIELD, 100, 10, 2), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 4, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Armor("Steel Shield", Type.STEELSHIELD, 100, 10, 3), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 4, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Armor("Gem Shield", Type.GEMSHIELD, 100, 10, 4), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 4, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Hatchet", Type.WOODENHATCHET, 100, 10, 0, 1, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Hatchet", Type.STONEHATCHET, 100, 10, 0, 2, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Hatchet", Type.STEELHATCHET, 100, 10, 0, 3, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Hatchet", Type.GEMHATCHET, 100, 10, 0, 4, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Shovel", Type.WOODENSHOVEL, 100, 10, 0, 1, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Shovel", Type.STONESHOVEL, 100, 10, 0, 2, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Shovel", Type.STEELSHOVEL, 100, 10, 0, 3, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Shovel", Type.GEMSHOVEL, 100, 10, 0, 4, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Shovel", Type.WOODENSHOVEL, 100, 10, 0, 1, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Shovel", Type.STONESHOVEL, 100, 10, 0, 2, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Shovel", Type.STEELSHOVEL, 100, 10, 0, 3, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Shovel", Type.GEMSHOVEL, 100, 10, 0, 4, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Pickaxe", Type.WOODENPICKAXE, 100, 10, 0, 1, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Pickaxe", Type.STONEPICKAXE, 100, 10, 0, 2, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Pickaxe", Type.STEELPICKAXE, 100, 10, 0, 3, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Pickaxe", Type.GEMPICKAXE, 100, 10, 0, 4, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
        } catch (SlickException ex) {
            Logger.getLogger(CraftingManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * checks if the player has the required items for crafting and then creates the item if he does and returns this item.
     * @param items the items in the players inventory
     * @param result the item the player wants to craft
     * @return returns the item if the crafting succeeded or null if it failed
     */
    public Item craft(List<Item> items, Item result) {
        CraftingRecipe recipe = null;
        List<Item> itemsneeded = null;
        
        for(CraftingRecipe r : this.recipes){
            if(r.getResult() == result){
                recipe = r;
            }
        }
        Object[] recipeItems = recipe.getIngredients();
        
        for (int i = 0; i < recipeItems.length; i++) {
            Item item = (Item)recipeItems[i];
            i++;
            for (int j = 0; j < i; j++) {
                itemsneeded.add(item);
            }
        }
        
        for(Item i : itemsneeded){
            if(!items.contains(i)){
                return null;
            }
        }
        
        for(Item i : itemsneeded){
            items.remove(i);
        }
        return result;
    }
}