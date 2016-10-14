/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.states;

import downtoearth.gameUtil.Camera;
import downtoearth.gameUtil.Coordinate;
import downtoearth.world.World;
import downtoearth.world.worldGen.WorldGen;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Demian
 */
public class GameState extends BasicGameState{

    private static Camera c;
    private static World w;
    
    private static int mapSize = 5012;
    private static WorldGen worldGen = new WorldGen(new Coordinate(mapSize, mapSize));
    
    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        w = new World(new Coordinate(mapSize, mapSize));
        
        c = new Camera(0,0);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        try {
            w.draw(c.getCoordinate(), gc.getWidth(), gc.getHeight(), g);
        } catch (IOException ex) {
            Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawString("State 3: Game", 10, 30);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        c.move(container);
    }
    
}
