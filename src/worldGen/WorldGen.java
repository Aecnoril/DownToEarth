/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldGen;

import gameUtil.Coordinate;
import worldGen.OpenSimplexNoise;

/**
 *
 * @author koenv
 */
public class WorldGen {
    
     private Coordinate size;
     private OpenSimplexNoise generator;
     
     private int[][] map;
     
     public WorldGen(Coordinate size){
         
         this.size = size;
         this.generator = new OpenSimplexNoise();
     }
     
     public void GenerateMatrix()
     {
         map = new int[size.getXint()][size.getYint()];
         
     }
    
}
