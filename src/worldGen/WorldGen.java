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
     
     private double[][] map;
     
     public WorldGen(Coordinate size){
         
         this.size = size;
         this.generator = new SimplexNoise();
     }
     
     public void GenerateMatrix()
     {
         map = new double[size.getXint()][size.getYint()];
         SimplexNoise simpNoise = new SimplexNoise();
         
         for(int i=0;i<size.getXint();i++){
        for(int j=0;j<size.getYint();j++){
            int x=(int)(0+i*((500-0)/size.getXint()));
            int y=(int)(0+j*((500-0)/size.getYint()));
            map[i][j]=0.5*(1+simpNoise.noise(x, y));
        }
    }
     }
    
}
