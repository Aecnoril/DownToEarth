/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.world;

import downtoearth.entities.NPC;
import downtoearth.enums.MobType;
import shared.Coordinate;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tomt
 */
public class SpawnManager {
    
    private NPC npc;
    private int nameint;
    private Random random;
    private MobType[] types = MobType.values();
    private MobType type;
    private Coordinate coordinate;
    private boolean randombool;
    private int size;

    public SpawnManager() {
        random = new Random();
        nameint=1;
        randombool=false;
        size=0;
    }
    
    public void generateMobs(World world){
        size=world.mobs.size();
        if (size<5)
        {
            for (int i=0; i<5-size; i++)
            {
                randombool = random.nextBoolean();
                type = randomMobType();
                try {
                    npc = new NPC(type.toString()+nameint, randomCoordinate(world, randombool), (random.nextInt(30)+50), type, "Assets/SpriteSheets/NinjaBob2.png");
                } catch (SlickException ex) {
                    Logger.getLogger(SpawnManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                world.mobs.add(npc);
                nameint++;
                System.out.println("name: "+npc.getName()+"  Coordinate: "+ npc.getLocation().getXint()+", "+npc.getLocation().getYint()+"  HP: "+ npc.getHitPoints()+" bool: "+randombool);
            }
        }
    }
    
    public MobType randomMobType()
    {
        return types[random.nextInt(types.length)];
    }
    
    public Coordinate randomCoordinate(World world, boolean randombool)
    {   
        if (randombool)
        {
            coordinate = new Coordinate(random.nextInt(587)+1011, random.nextInt(2152)+1658);
        }
        else
        {
            coordinate = new Coordinate(random.nextInt(680)+3406, random.nextInt(350)+2026);
        }
        
        for (Tile t : world.tiles)
        {
            for (NPC n : world.mobs)
            {
                while (coordinate.getXint()==t.getPosition().getXint()||coordinate.getYint()==t.getPosition().getYint()||coordinate.getXint()==n.getLocation().getXint()||coordinate.getYint()==n.getLocation().getYint())
                {
                    if (randombool)
                    {
                        coordinate = new Coordinate(random.nextInt(587)+1011, random.nextInt(2152)+1658);
                    }
                    else
                    {
                        coordinate = new Coordinate(random.nextInt(680)+3406, random.nextInt(350)+2026);
                    }
                }
            }
        }
        return coordinate;
    }
}
