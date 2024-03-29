/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldGen;

import downtoearth.gameUtil.Coordinate;
import downtoearth.world.worldGen.SimplexNoise;

/**
 *
 * @author koenv
 */
public class WorldGen {

    private Coordinate size;
    private SimplexNoise noise;

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
        this.noise = new SimplexNoise();
    }

    public void GenerateMatrix() {
        map = new float[size.getXint()][size.getYint()];
        double total = 0.0;
        int octaves = 8;
        float roughness = 0.4f;
        float layerFreq = 0.003f;
        float layerWeight = 1;
        float weightSum = 0;

        for (int octave = 0; octave < octaves; octave++) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] += noise.noise(i * layerFreq, j * layerFreq) * layerWeight;
                }
            }
        layerFreq *= 2;
        weightSum += layerWeight;
        layerWeight *= roughness;
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
