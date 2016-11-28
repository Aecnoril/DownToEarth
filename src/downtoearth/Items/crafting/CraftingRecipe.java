/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items.crafting;

import downtoearth.Items.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Sanko
 */
public class CraftingRecipe {

    private final Item result;
    private final int nrOfItems;
    private final Object[] ingredients;

    public CraftingRecipe(Item result, int nrOfItems, Object[] ingredients) {
        this.result = result;
        this.nrOfItems = nrOfItems;
        this.ingredients = ingredients;
    }

    /**
     * Get the value of resources
     *
     * @return the value of resources
     */
    public Object[] getIngredients() {
        return ingredients;
    }

    /**
     * Get the value of Result
     *
     * @return
     */
    public Item getResult() {
        return result;
    }

}
