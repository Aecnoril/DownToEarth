package downtoearth.world;

import downtoearth.entities.Player;
import downtoearth.Items.*;
import downtoearth.entities.LivingEntity;
import downtoearth.entities.NPC;
import downtoearth.enums.*;
import downtoearth.gameUtil.Coordinate;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public class World implements Serializable {
    
    private final int zoom = 4;
    private final float shaderTrans = 0.4f;
    private final Player p;
    private final List<LivingEntity> Mobs;
    
    float[][] heightMap;
    
    int[][] colorMap;
    
    Coordinate size;
    Image map;
    Image shader;
    
    public Player getPlayer(){
        return this.p;
    }
    
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
        this.Mobs = new ArrayList();
        map = new Image("res/ColorMap.png");
        if(map != null){
         System.out.println("image found!: " + map.getHeight());           
        }
        
        shader = new Image("res/HeightMap.png");
        if(shader != null){
         System.out.println("image found!: " + shader.getHeight());           
        }
        
        p = new Player("henk", new Coordinate(540,360), 100, "Assets/SpriteSheets/NinjaBob2.png");
        Mobs.add(new NPC("henk", new Coordinate(520,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
        Mobs.add(new NPC("henk", new Coordinate(420,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
        Mobs.add(new NPC("henk", new Coordinate(320,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
        Mobs.add(new NPC("henk", new Coordinate(220,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
        Mobs.add(new NPC("henk", new Coordinate(120,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
        
        

    }
    
    public World(Coordinate size) throws SlickException{
        this.size = size;
        this.Mobs = new ArrayList();
        map = new Image("res/ColorMap.png");
        if(map != null){
         System.out.println("image found!: " + map.getHeight());           
        }
        
        shader = new Image("res/HeightMap.png");
        if(shader != null){
         System.out.println("image found!: " + shader.getHeight());           
        }
        
        p = new Player("henk", new Coordinate(540,360), 100, "Assets/SpriteSheets/NinjaBob2.png");
        Mobs.add(new NPC("henk", new Coordinate(520,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
        Mobs.add(new NPC("henk", new Coordinate(420,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
        Mobs.add(new NPC("henk", new Coordinate(320,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
        Mobs.add(new NPC("henk", new Coordinate(220,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
        Mobs.add(new NPC("henk", new Coordinate(120,360), 100,MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));
    }
   
    public void draw(int width, int height, GameContainer con, Graphics g) throws IOException, SlickException{
        Color myFilter = new Color(1f, 1f, 1f, 0.5f);   //50%
        Image img = map.getSubImage(p.getCamera().getX(), p.getCamera().getY(), width / zoom, height / zoom);
        Image shd = shader.getSubImage(p.getCamera().getX(), p.getCamera().getY(), width / zoom, height / zoom);
        img.setFilter(Image.FILTER_NEAREST);
        img.getScaledCopy(width, height).draw(0, 0);
        shd.getScaledCopy(width, height).draw(0, 0, new Color(1,1,1,shaderTrans));
        p.render(con, g);
        for(LivingEntity npc : Mobs)
        {
            if(npc instanceof NPC)
            {
               ((NPC)npc).render(con);
            }

        }
    }
}