package downtoearth.world;

import com.sun.glass.ui.Size;
import downtoearth.entities.Player;
import downtoearth.entities.ItemEntity;
import downtoearth.entities.NPC;
import downtoearth.entities.Opponent;
import downtoearth.enums.*;
import downtoearth.gameUtil.SpriteManager;
import shared.Coordinate;
import downtoearth.states.MultiplayerState;
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

    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private final int zoom = 4;
    private MultiplayerState state;
    private final float shaderTrans = 0.4f;
    private final Player p;
    public List<Tile> tiles;
    private List<Tile> removeTiles;
    private List<NPC> mobs;
    private List<NPC> removeMobs;
    public ArrayList<ItemEntity> itemEnts;
    public List<Opponent> opponents;
    private SpriteManager sManager;

    float[][] heightMap;

    int[][] colorMap;

    Coordinate size;
    Image map;
    Image shader;

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y) {
        for (Tile tile : tiles) {
            if (tile.getBounds().contains(x, y)) {
                return tile;
            }
        }
        return null;
    }
    
    public Size getMapSize(){
        return new Size(map.getWidth(), map.getHeight());
    }

    public List<NPC> getMobs() {
        return mobs;
    }

    public Player getPlayer() {
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

    public void addDrop(ItemEntity item) {
        this.itemEnts.add(item);
    }
    //</editor-fold>

    public static boolean checkMap() throws SlickException {
        Image test = new Image("src/resources/ColorMap.png");
        if (test != null) {
            return true;
        } else {
            return false;
        }
    }

    public World(float[][] heightMap, int[][] colorMap, Coordinate size) throws SlickException {
        p = new Player("henk", new Coordinate(540, 360), 100, "Assets/SpriteSheets/NinjaBob2.png", this);
    }

    public World(Coordinate size) throws SlickException {
        this.tiles = new ArrayList<Tile>();
        this.mobs = new ArrayList<NPC>();
        this.removeTiles = new ArrayList<Tile>();
        this.removeMobs = new ArrayList<NPC>();
        this.itemEnts = new ArrayList<ItemEntity>();
        this.opponents = new ArrayList<Opponent>();

        this.heightMap = heightMap;
        this.colorMap = colorMap;
        this.size = size;
        map = new Image("res/ColorMap.png");

        shader = new Image("res/HeightMap.png");
        p = new Player("henk", new Coordinate(540, 360), 100, "Assets/SpriteSheets/NinjaBob2.png", this);
        generateTiles();
    }

    public void generateTiles() {
        try {
            tiles.add(new Tile(640, 1000, TileType.STONE, "stone"));
            tiles.add(new Tile(750, 1340, TileType.COAL, "coal"));
            tiles.add(new Tile(580, 1400, TileType.GEMSTONE, "gemstone"));
            tiles.add(new Tile(500, 1320, TileType.TREE, "tree1"));
            tiles.add(new Tile(510, 1420, TileType.TREE, "tree2"));
            tiles.add(new Tile(540, 1345, TileType.TREE, "tree3"));
            mobs.add(new NPC("Test", new Coordinate(400, 300), 100, MobType.Sheep, "res/tigersprite.png", this, true , 178, 1000000, 10000));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void draw(int width, int height, GameContainer con, Graphics g) throws IOException, SlickException {
        Color myFilter = new Color(1f, 1f, 1f, 0.5f);   //50%
        Image img = map.getSubImage(p.getCamera().getCenterPosX() - (con.getWidth() / 2), p.getCamera().getCenterPosY() - (con.getHeight() / 2), width, height);
        Image shd = shader.getSubImage(p.getCamera().getCenterPosX() - (con.getWidth() / 2), p.getCamera().getCenterPosY() - (con.getHeight() / 2), width, height);
        img.setFilter(Image.FILTER_NEAREST);
        img.getScaledCopy(width, height).draw(0, 0);
        shd.getScaledCopy(width, height).draw(0, 0, new Color(1, 1, 1, shaderTrans));

        int startX = p.getCamera().getCenterPosX() - (con.getWidth() / 2);
        int startY = p.getCamera().getCenterPosY() - (con.getHeight() / 2);
        
        int stopX = p.getCamera().getCenterPosX() + (con.getWidth() / 2);
        int stopY = p.getCamera().getCenterPosY() + (con.getHeight() / 2);
        
        p.render(con);

        drawTiles(startX, startY, stopX, stopY);

        drawMobs(startX, startY, stopX, stopY);

        drawItems(startX, startY, stopX, stopY);

        drawOpponents(startX, startY, stopX, stopY);

    }

    public void drawMobs(int startX, int startY, int stopX, int stopY) {
        for (NPC n : mobs) {
            if (n.getLocation().getX() >= startX && n.getLocation().getX() <= stopX) {
                if (n.getLocation().getY() >= startY && n.getLocation().getY() <= stopY) {
                    n.draw(startX, startY);
                }
            }
        }
    }

    public void drawTiles(int startX, int startY, int stopX, int stopY) {
        for (Tile t : tiles) {
            if (t.getPosition().getX() >= startX && t.getPosition().getX() <= stopX) {
                if (t.getPosition().getY() >= startY && t.getPosition().getY() <= stopY) {
                    t.draw(startX, startY);
                }
            }
        }
    }

    public void drawItems(int startX, int startY, int stopX, int stopY) {
        for (ItemEntity n : itemEnts) {
            if (n.getLocation().getX() >= -16 && n.getLocation().getX() <= 1080) {
                if (n.getLocation().getY() >= -16 && n.getLocation().getY() <= 720) {
                    n.draw();
                }
            }
        }
    }

    public void drawOpponents(int startX, int startY, int stopX, int stopY) {
        if (!opponents.isEmpty()) {
            for (Opponent o : opponents) {
                if (o.getLocation().getX() >= startX && o.getLocation().getX() <= stopX) {
                    if (o.getLocation().getY() >= startY && o.getLocation().getY() <= stopY) {
                        o.draw(startX, startY);

                    }
                }
            }
        }
    }

    public void update(Input input) throws SlickException {
        this.p.move(input, this.tiles, this.mobs, this.opponents);
        this.p.attack(this.tiles, this.mobs, this.opponents, input);
        for (ItemEntity t : this.itemEnts) {
            t.move(input);
        }
        for (NPC n : this.mobs) {
            n.move(input, this.tiles, this.mobs, this.opponents);
        }
    }
}
