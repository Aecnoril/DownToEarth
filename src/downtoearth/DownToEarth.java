package downtoearth;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class DownToEarth extends BasicGame {

    worldGen.WorldGen worldGen = new worldGen.WorldGen(new gameUtil.Coordinate(1024, 1024));
    BufferedImage bImage = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);

    public DownToEarth(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        worldGen.GenerateMatrix();

        try {
            for (int i = 0; i < worldGen.map.length; i++) {
                for (int j = 0; j < worldGen.map.length; j++) {
                    int a = Math.round(worldGen.map[i][j] * 100);
                    Color newColor = new Color(a, a, a);
                    bImage.setRGB(j, i, newColor.getRGB());
                }
            }
            File output = new File("HeightMap.png");
            ImageIO.write(bImage, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < worldGen.map.length; i++) {
                for (int j = 0; j < worldGen.map.length; j++) {
                    float a = worldGen.map[i][j];
                    Color sea = new Color(20, 90, 156);
                    Color shore = new Color(36, 119, 170);
                    Color beach = new Color(226, 198, 83);
                    Color land = new Color(95, 150, 40);
                    Color rock = new Color(116, 113, 116);
                    Color mountain = new Color(148, 149, 151);
                    Color top = new Color(225, 226, 225);
                    
                    if (a < 0.45f) {
                        bImage.setRGB(j, i, sea.getRGB());
                    }
                    if (a > 0.45f) {
                        bImage.setRGB(j, i, shore.getRGB());
                    }
                    if (a > 0.5f) {
                        bImage.setRGB(j, i, beach.getRGB());
                    }
                    if (a > 0.55f) {
                        bImage.setRGB(j, i, land.getRGB());
                    }
                    if (a > 0.8f) {
                        bImage.setRGB(j, i, rock.getRGB());
                    }
                    if (a > 0.82f) {
                        bImage.setRGB(j, i, mountain.getRGB());
                    }
                    if (a > 0.9f) {
                        bImage.setRGB(j, i, top.getRGB());
                    }
                    

                }
            }
            File output = new File("ColorMap.png");
            ImageIO.write(bImage, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new DownToEarth("Simple Slick Game"));
            appgc.setTargetFrameRate(60);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(DownToEarth.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
