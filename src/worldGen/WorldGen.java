/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldGen;

import gameUtil.Coordinate;
import worldGen.SimplexNoise;

/**
 *
 * @author koenv
 */
public class WorldGen {
    
     private Coordinate size;
     private SimplexNoise generator;
     
     private int[][] map;
     
     public WorldGen(Coordinate size){
         
         this.size = size;
         this.generator = new SimplexNoise();
     }
     
     public void GenerateMatrix()
     {
         map = new int[size.getXint()][size.getYint()];
         
     }
    
}
