package downtoearth.states;

import downtoearth.Inventorys.Inventory;
import downtoearth.Items.crafting.CraftingScreen;
import downtoearth.entities.ItemEntity;
import downtoearth.gameUtil.Coordinate;
import downtoearth.world.World;
import downtoearth.world.worldGen.WorldGen;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Demian
 */
public class GameState extends BasicGameState {
    
    public static World w;
    private Inventory inv;
    private CraftingScreen cs;
    private static int mapSize = 5012;
    private static WorldGen worldGen = new WorldGen(new Coordinate(mapSize, mapSize));

    public static void main(String[] args) {
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        w = new World(new Coordinate(mapSize, mapSize));
        inv = new Inventory(25, 100, 1025, 500, new Color(122, 118, 118));
        cs = new CraftingScreen(25, 100, 1025, 500, new Color(122, 118, 118));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        try {
            w.draw(gc.getWidth(), gc.getHeight(), gc, g);
        } catch (IOException ex) {
            Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawString("State 3: Game", 10, 30);
        for (ItemEntity i : w.itemEnts) {
            if (w.getPlayer().getBounds().intersects(i.getBounds())) {
                w.itemEnts.remove(i);
                break;
            }
        }

        if (this.inv.isInvOpen()) {
            this.inv.render(g);
        }
        if (this.cs.isCsOpen()) {
            this.cs.render(g);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        if (!inv.isInvOpen() && !cs.isCsOpen()) {
            w.update(gc.getInput());
        }
        inv.ePressed(gc);
        cs.setInventory(inv);
        cs.cPressed(gc);
    }

    @Override
    public void mouseWheelMoved(int change) {
        if (cs.isCsOpen()) {
            double res = Math.floor(change * 0.1);
            cs.setScroll((float) res);
        }
    }
}
