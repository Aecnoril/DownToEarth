/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldGen;

import gameUtil.Coordinate;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import worldGen.SimplexNoise;

/**
 *
 * @author koenv
 */
public class WorldGen {

    private Coordinate size;
    private SimplexNoise generator;

    private int[][] map;

    public WorldGen(Coordinate size) {

        this.size = size;
        this.generator = new SimplexNoise();
    }

    public void GenerateMatrix() {
        map = new int[size.getXint()][size.getYint()];

    }

    public void render(Graphics g) {
        float[][] matrix = new float[100][100];
        Color[][] kleurmatrix = new Color[100][100];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0.0 && matrix[i][j] <= 0.2) {
                    //kleurmatrix[i][j] = new Color(1.0,1.0,1.0);
                } else if (matrix[i][j] > 0.2 && matrix[i][j] <= 0.4) {

                } else if (matrix[i][j] > 0.4 && matrix[i][j] <= 0.1) {

                }
            }
        }

    }
}
