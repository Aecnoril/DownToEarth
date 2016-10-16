/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.Items.crafting;

import downtoearth.Items.Item;
import java.util.ArrayList;

/**
 *
 * @author Sanko
 */
public class CraftingRecipe {
    
        private String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

        private ArrayList<Item> resources;

    /**
     * Get the value of resources
     *
     * @return the value of resources
     */
    public ArrayList<Item> getResources() {
        return resources;
    }

    /**
     * Set the value of resources
     *
     * @param resources new value of resources
     */
    public void setResources(ArrayList<Item> resources) {
        this.resources = resources;
    }

    
}
