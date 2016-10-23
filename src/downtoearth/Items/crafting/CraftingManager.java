/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items.crafting;

import downtoearth.Items.Armor;
import downtoearth.Items.Item;
import downtoearth.Items.Resource;
import downtoearth.Items.TileItem;
import downtoearth.Items.Tool;
import downtoearth.enums.ArmorType;
import downtoearth.enums.ResourceType;
import downtoearth.enums.TileType;
import downtoearth.enums.Tooltype;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
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

    private CraftingManager(){
        try {
            this.recipes.add(new CraftingRecipe(new Resource("Stick", ResourceType.STICK, 100, 0), 2, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Resource("Steel", ResourceType.STEEL, 100, 0), 1, new Object[]{new Resource("Coal", ResourceType.COAL, 100, 0), 1, new Resource("Stone", ResourceType.STONE, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Resource("Bucket", ResourceType.BUCKET, 100, 0), 2, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Armor("Wood Armor", ArmorType.WOODENARMOR, 100, 10, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 4}));
            this.recipes.add(new CraftingRecipe(new Armor("StoneArmor", ArmorType.STONEARMOR, 100, 0, 2), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 4}));
            this.recipes.add(new CraftingRecipe(new Armor("Steel Armor", ArmorType.STEELARMOR, 100, 10, 3), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 4}));
            this.recipes.add(new CraftingRecipe(new Armor("Gem Armor", ArmorType.GEMARMOR, 100, 10, 3), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 4}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Sword", Tooltype.WOODENSWORD, 100, 10, 1, 0, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Sword", Tooltype.STONESWORD, 100, 10, 2, 0, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Sword", Tooltype.STEELSWORD, 100, 10, 3, 0, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Sword", Tooltype.GEMSWORD, 100, 10, 4, 0, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Armor("Wooden Shield", Tooltype.WOODENSHIELD, 100, 10, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 4, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Armor("Stone Shield", Tooltype.STONESHIELD, 100, 10, 2), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 4, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Armor("Steel Shield", Tooltype.STEELSHIELD, 100, 10, 3), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 4, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Armor("Gem Shield", Tooltype.GEMSHIELD, 100, 10, 4), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 4, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Hatchet", Tooltype.WOODENHATCHET, 100, 10, 0, 1, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Hatchet", Tooltype.STONEHATCHET, 100, 10, 0, 2, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Hatchet", Tooltype.STEELHATCHET, 100, 10, 0, 3, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Hatchet", Tooltype.GEMHATCHET, 100, 10, 0, 4, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 3, new Resource("Stick", ResourceType.STICK, 100, 0), 1}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Shovel", Tooltype.WOODENSHOVEL, 100, 10, 0, 1, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Shovel", Tooltype.STONESHOVEL, 100, 10, 0, 2, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Shovel", Tooltype.STEELSHOVEL, 100, 10, 0, 3, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Shovel", Tooltype.GEMSHOVEL, 100, 10, 0, 4, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Shovel", Tooltype.WOODENSHOVEL, 100, 10, 0, 1, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Shovel", Tooltype.STONESHOVEL, 100, 10, 0, 2, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Shovel", Tooltype.STEELSHOVEL, 100, 10, 0, 3, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Shovel", Tooltype.GEMSHOVEL, 100, 10, 0, 4, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 1, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Wooden Pickaxe", Tooltype.WOODENPICKAXE, 100, 10, 0, 1, 1), 1, new Object[]{new Resource("Wood", ResourceType.WOOD, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Stone Pickaxe", Tooltype.STONEPICKAXE, 100, 10, 0, 2, 1), 1, new Object[]{new Resource("Stone", ResourceType.STONE, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Steel Pickaxe", Tooltype.STEELPICKAXE, 100, 10, 0, 3, 2), 1, new Object[]{new Resource("Steel", ResourceType.STEEL, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
            this.recipes.add(new CraftingRecipe(new Tool("Gem Pickaxe", Tooltype.GEMPICKAXE, 100, 10, 0, 4, 2), 1, new Object[]{new Resource("Gemstone", ResourceType.GEMSTONE, 100, 0), 2, new Resource("Stick", ResourceType.STICK, 100, 0), 2}));
        } catch (SlickException ex) {
            Logger.getLogger(CraftingManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Item craft(List<Item> items, Item result) {
        CraftingRecipe recipe = new CraftingRecipe(null, 0, null);
        List<Item> itemsneeded = new ArrayList<Item>();
        
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
