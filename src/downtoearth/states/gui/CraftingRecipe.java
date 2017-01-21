/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states.gui;

import downtoearth.Items.Item;

/**
 *
 * @author Sanko
 */
public class CraftingRecipe {

    private final Item result;
    private final int nrOfItems;
    private final Object[] ingredients;

    /**
     * this class is for storing which items are needed to craft a certain item and how many of that item are created.
     * @param result
     * @param nrOfItems
     * @param ingredients 
     */
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
