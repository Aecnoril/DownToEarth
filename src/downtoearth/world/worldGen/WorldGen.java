/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.world.worldGen;

import downtoearth.database.ServerAPI;
import shared.Coordinate;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.newdawn.slick.SlickException;
import downtoearth.world.World;
import downtoearth.world.worldGen.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author koenv
 */
public class WorldGen {

    private Coordinate size;
    private SimplexNoise noise;

    public float[][] map;
    public int[][] colorMap;

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

    public World GenerateWorld() throws SlickException {
        map = new float[size.getXint()][size.getYint()];
        double total = 0.0;
        int octaves = 8;
        float roughness = 0.4f;
        float layerFreq = 0.00025f;
        float layerWeight = 1;
        float weightSum = 0;

        for (int octave = 0; octave < octaves; octave++) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] += noise.noise((i * layerFreq) + (3900 * layerFreq), (j * layerFreq) + (1700 * layerFreq)) * layerWeight;
                }
            }
            layerFreq *= 2;
            weightSum += layerWeight;
            layerWeight *= roughness;
        }
        return saveWorld();
    }

    private float[][] generateGradient(float[][] radiatedMatrix) {
        Coordinate middle = size;
        size.setX(size.getX() / 2);
        size.setY(size.getY() / 2);

        for (int i = 0; i < radiatedMatrix.length; i++) {
            for (int j = 0; j < radiatedMatrix[i].length; j++) {

                int distance = Math.round(Coordinate.distance(Coordinate.origin(), new Coordinate(i, j)));

            }
        }

        return radiatedMatrix;
    }

    private World saveWorld() throws SlickException {

        BufferedImage bImage = new BufferedImage(size.getXint(), size.getYint(), BufferedImage.TYPE_INT_RGB);
        colorMap = new int[size.getXint()][size.getYint()];
        
        try {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    int grey = Math.round(((map[i][j])) * 100);
                    if (grey < 0) {
                        grey = 0;
                    }

                    try {
                        Color newColor = new Color(grey, grey, grey);
                        bImage.setRGB(j, i, newColor.getRGB());
                    } catch (IllegalArgumentException iEx) {
                        Logger.getLogger(WorldGen.class.getName()).log(Level.SEVERE, null, iEx);
                        System.out.println(map[i][j]);
                        System.out.println(grey);
                        return null;
                    }
                }
            }
            File output = new File("res/HeightMap.png");
            ImageIO.write(bImage, "png", output);
        } catch (Exception e) {
            Logger.getLogger(WorldGen.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    float height = map[i][j];
                    Color sea = new Color(20, 90, 156);
                    Color shore = new Color(36, 119, 170);
                    Color beach = new Color(226, 198, 83);
                    Color land = new Color(95, 150, 40);
                    Color rock = new Color(116, 113, 116);
                    Color mountain = new Color(148, 149, 151);
                    Color top = new Color(225, 226, 225);
                    boolean pixelSet = false;

                    if (height > 0.95f && !pixelSet) {
                        colorMap[j][i] = top.getRGB();
                        bImage.setRGB(j, i, top.getRGB());
                        pixelSet = true;
                    }
                    if (height > 0.85f && !pixelSet) {
                        colorMap[j][i] = top.getRGB();
                        bImage.setRGB(j, i, mountain.getRGB());
                        pixelSet = true;
                    }
                    if (height > 0.82f && !pixelSet) {
                        colorMap[j][i] = top.getRGB();
                        bImage.setRGB(j, i, rock.getRGB());
                        pixelSet = true;
                    }
                    if (height > 0.55f && !pixelSet) {
                        colorMap[j][i] = top.getRGB();
                        bImage.setRGB(j, i, land.getRGB());
                        pixelSet = true;
                    }
                    if (height > 0.5f && !pixelSet) {
                        colorMap[j][i] = top.getRGB();
                        bImage.setRGB(j, i, beach.getRGB());
                        pixelSet = true;
                    }
                    if (height > 0.45f && !pixelSet) {
                        colorMap[j][i] = top.getRGB();
                        bImage.setRGB(j, i, shore.getRGB());
                        pixelSet = true;
                    }
                    if (height < 0.45f && !pixelSet) {
                        colorMap[j][i] = top.getRGB();
                        bImage.setRGB(j, i, sea.hashCode());
                        pixelSet = true;
                    }
                }
            }
            File output = new File("res/ColorMap.png");
            ImageIO.write(bImage, "png", output);
        } catch (Exception e) {
            Logger.getLogger(WorldGen.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        
        return new World(map, colorMap, size);
    }
}
