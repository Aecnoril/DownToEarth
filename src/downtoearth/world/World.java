package downtoearth.world;

import downtoearth.entities.Player;
import downtoearth.Items.*;
import downtoearth.enums.*;
import downtoearth.gameUtil.Coordinate;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public class World implements Serializable {
    
    private final int zoom = 4;
    private final float shaderTrans = 0.4f;
    private final Player testPlayer;
    
    float[][] heightMap;
    
    int[][] colorMap;
    
    Coordinate size;
    Image map;
    Image shader;
    
    public Coordinate getCoordinate() {
        return size;
    }
    
    public float[][] getHeightMap() {
        return heightMap;
    }
    
    public int[][] getColorMap() {
        return colorMap;
    }
    
    public static boolean checkMap() throws SlickException{
        Image test = new Image("src/resources/ColorMap.png");
        if(test != null){
            return true;
        }else{
            return false;
        }
    }
    
    public World(float[][] heightMap, int[][] colorMap, Coordinate size) throws SlickException{
        
        this.heightMap = heightMap;
        this.colorMap = colorMap;
        this.size = size;
        map = new Image("res/ColorMap.png");
        if(map != null){
         System.out.println("image found!: " + map.getHeight());           
        }
        
        shader = new Image("res/HeightMap.png");
        if(shader != null){
         System.out.println("image found!: " + shader.getHeight());           
        }
        
        testPlayer = new Player("henk", new Point(540,360), 100, "Assets/SpriteSheets/NinjaBob2.png");

    }
    
    public World(Coordinate size) throws SlickException{
        this.size = size;
        map = new Image("res/ColorMap.png");
        if(map != null){
         System.out.println("image found!: " + map.getHeight());           
        }
        
        shader = new Image("res/HeightMap.png");
        if(shader != null){
         System.out.println("image found!: " + shader.getHeight());           
        }
        
        testPlayer = new Player("henk", new Point(540,360), 100, "Assets/SpriteSheets/NinjaBob2.png");

    }
    
    public void draw(Coordinate c, int width, int height, Graphics g) throws IOException, SlickException{
        Color myFilter = new Color(1f, 1f, 1f, 0.5f);   //50%
        Image img = map.getSubImage(c.getXint(), c.getYint(), width / zoom, height / zoom);
        Image shd = shader.getSubImage(c.getXint(), c.getYint(), width / zoom, height / zoom);
        img.setFilter(Image.FILTER_NEAREST);
        img.getScaledCopy(width, height).draw(0, 0);
        shd.getScaledCopy(width, height).draw(0, 0, new Color(1,1,1,shaderTrans));
        testPlayer.render(DirectionType.South);
    }
}