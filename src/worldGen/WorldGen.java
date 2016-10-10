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

    public float[][] map;

    public int[] getFlatMap() {

        int[] flatMap = new int[size.getXint() * size.getYint()];

        for (int i = 0; i < map.length; i++) {
            float[] row = map[i];
            for (int j = 0; j < row.length; j++) {
                float number = map[i][j];
                flatMap[i * row.length + j] = Math.round(number);
            }
        }
        return flatMap;
    }

    public WorldGen(Coordinate size) {
        this.size = size;
        this.generator = new SimplexNoise();
    }

    public void GenerateMatrix() {
        map = new float[size.getXint()][size.getYint()];
        SimplexNoise simpNoise = new SimplexNoise();
        double total = 0.0;
        float freq = 3.0f / size.getXint();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                total = simpNoise.noise(i * freq, j * freq);
                total = (total + 1) /2;

                map[i][j] = (float) total;
            }
        }
    }

    private float[][] generateGradient(float[][] radiatedMatrix) {

        Coordinate middle = size;
        size.x /= 2;
        size.y /= 2;

        for (int i = 0; i < radiatedMatrix.length; i++) {
            for (int j = 0; j < radiatedMatrix[i].length; j++) {

                int distance = Math.round(Coordinate.distance(Coordinate.Origin(), new Coordinate(i, j)));

            }
        }

        return radiatedMatrix;
    }
}
