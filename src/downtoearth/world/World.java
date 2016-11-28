package downtoearth.world;

import downtoearth.entities.Player;
import downtoearth.Items.*;
import downtoearth.entities.ItemEntity;
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
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World implements Serializable {
    
    private final int zoom = 4;
    private final float shaderTrans = 0.4f;
    private final Player p;
    public List<Tile> tiles;
    private List<Tile> removeTiles;
    private List<NPC> mobs;
    private List<NPC> removeMobs;
    public ArrayList<ItemEntity> itemEnts;
    
    float[][] heightMap;
    
    int[][] colorMap;
    
    Coordinate size;
    Image map;
    Image shader;
    
    public List<Tile> getTiles(){
        return tiles;
    }
    
    public Tile getTile(int x, int y){
        for(Tile tile : tiles){
            if(tile.getBounds().contains(x, y)){
                return tile;
            }
        }
        return null;
    }
    
    public List<NPC> getMobs()
    {
        return mobs;
    }
    
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
    
    public void addDrop(ItemEntity item){
        this.itemEnts.add(item);
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
        
        this.tiles = new ArrayList<Tile>();
        this.mobs = new ArrayList<NPC>();
        this.removeTiles = new ArrayList<Tile>();
        this.removeMobs = new ArrayList<NPC>();
        this.itemEnts = new ArrayList<ItemEntity>();
        tiles.add(new Tile(600, 900, TileType.STONE, "stone"));
        tiles.add(new Tile(580, 870, TileType.COAL, "coal"));
        tiles.add(new Tile(580, 920, TileType.GEMSTONE, "gemstone"));
        tiles.add(new Tile(500, 910, TileType.TREE, "tree1"));
        tiles.add(new Tile(510, 950, TileType.TREE, "tree2"));
        tiles.add(new Tile(540, 990, TileType.TREE, "tree3"));
        
        this.size = size;
        map = new Image("res/ColorMap.png");
        
        if(map != null){
            System.out.println("image found!: " + map.getHeight());           
        }
        
        shader = new Image("res/HeightMap.png");
        if(shader != null){
            System.out.println("image found!: " + shader.getHeight());           
        }
        
        p = new Player("henk", new Coordinate(540,360), 100, "Assets/SpriteSheets/NinjaBob2.png");
        mobs.add(new NPC("Test", new Coordinate(400,300), 100, MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));

    }
    
    public World(Coordinate size) throws SlickException{
        this.tiles = new ArrayList<Tile>();
        this.mobs = new ArrayList<NPC>();
        this.removeTiles = new ArrayList<Tile>();
        this.removeMobs = new ArrayList<NPC>();
        this.itemEnts = new ArrayList<ItemEntity>();
        tiles.add(new Tile(0, 0, TileType.STONE, "stone"));
        tiles.add(new Tile(580, 340, TileType.COAL, "coal"));
        tiles.add(new Tile(580, 400, TileType.GEMSTONE, "gemstone"));
        tiles.add(new Tile(500, 320, TileType.TREE, "tree1"));
        tiles.add(new Tile(510, 420, TileType.TREE, "tree2"));
        tiles.add(new Tile(540, 345, TileType.TREE, "tree3"));
                
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
        
        p = new Player("henk", new Coordinate(540,360), 100, "Assets/SpriteSheets/NinjaBob2.png");
        mobs.add(new NPC("Test", new Coordinate(400,300), 100, MobType.Sheep, "Assets/SpriteSheets/NinjaBob2.png"));

    }
    
    public void draw(int width, int height, GameContainer con, Graphics g) throws IOException, SlickException{  
        Color myFilter = new Color(1f, 1f, 1f, 0.5f);   //50%
        Image img = map.getSubImage(p.getCamera().getCenterPosX() - (con.getWidth()/2), p.getCamera().getCenterPosY() - (con.getHeight()/2), width   , height );
        Image shd = shader.getSubImage(p.getCamera().getX() - (con.getWidth()/2), p.getCamera().getY() - (con.getHeight()/2), width  , height );
        img.setFilter(Image.FILTER_NEAREST);
        img.getScaledCopy(width, height).draw(0, 0  );
        shd.getScaledCopy(width, height).draw(0, 0, new Color(1,1,1,shaderTrans));

        int startX = p.getCamera().getCenterPosX() - (con.getWidth()/2);
        int startY = p.getCamera().getCenterPosY() - (con.getHeight()/2);
        
        int stopX = p.getCamera().getCenterPosX() + (con.getWidth()/2);
        int stopY = p.getCamera().getCenterPosY() + (con.getHeight()/2);
        p.render(con);
        g.draw(p.getBounds());
        for (NPC n : mobs)
        {
            if(n.isDead())
            {
                removeMobs.add(n);
            }
        }
        if(removeMobs.size() > 0)
        {
            mobs.removeAll(removeMobs);
        }
        removeMobs.clear();
        for(Tile t : tiles)
        {
            if(t.isDestroyed())
            {
                removeTiles.add(t);
            }
        }
        if(removeTiles.size() > 0 )
        {
            for(Tile t : removeTiles){
                TileItem ti = new TileItem(t.getName(), t.getType(), 0, 0);
                ti.drop(t.getPosition());
            }
            tiles.removeAll(removeTiles);
        }
        removeTiles.clear();
        for(Tile t : tiles){
            if(t.getPosition().getX() >= startX && t.getPosition().getX() <= stopX){
                if(t.getPosition().getY() >= startY && t.getPosition().getY() <= stopY){
                    t.draw(startX , startY);
                    g.setColor(Color.red);
                    g.draw(t.getBounds());
                }
            }
        }
        for(NPC n : mobs)
        {
            if(n.getLocation().getX() >= startX && n.getLocation().getX() <= stopX){
                if(n.getLocation().getY() >= startY && n.getLocation().getY() <= stopY){
                    n.draw(startX , startY);
                    g.setColor(Color.red);
                    g.draw(n.getBounds());
                }
            }
        }
        for(ItemEntity n : itemEnts)
        {
            if(n.getLocation().getX() >= -16 && n.getLocation().getX() <= 1080){
                if(n.getLocation().getY() >= -16 && n.getLocation().getY() <= 720){
                    n.draw();
                }
            }
        }
    }
    
    public void update(Input input){
        this.p.move(input, this.tiles);
        for(ItemEntity t : this.itemEnts){
            t.move(input);
        }
        for(NPC n : this.mobs)
        {
            n.move(input);
        }       
    }
}