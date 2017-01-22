/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.gameUtil;

import shared.Coordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Tomt
 */
public class CameraTest {
    private Camera camera;
    private Coordinate c;
    private float speed;
    
    @Before
    public void setUp() {
        camera = new Camera(1080, 720, new Coordinate(0,0));
        c = new Coordinate(500,950);
        speed = 0.5f;
    }

    /**
     * Test of move(down) method, of class Camera.
     */
    @Test
    public void testMoveDown() {
        c.setY(c.getY() + speed);
         assertEquals(951 ,c.getYint());
    }
    
    /**
     * Test of move(up) method, of class Camera.
     */
    @Test
    public void testMoveUp() {
        c.setY(c.getY() - speed);
         assertEquals(950 ,c.getYint());
    }
    
    /**
     * Test of move(left) method, of class Camera.
     */
    @Test
    public void testMoveLeft() {
        c.setX(c.getX() - speed);
         assertEquals(500 ,c.getXint());
    }
    
    /**
     * Test of move(right) method, of class Camera.
     */
    @Test
    public void testMoveRight() {
        c.setX(c.getX() + speed);
         assertEquals(501 ,c.getXint());
    }

    /**
     * Test of getCoordinate method, of class Camera.
     */
    @Test
    public void testGetCoordinateX() {
        assertEquals(500, camera.getCoordinate().getXint());
    }
    
    /**
     * Test of getCoordinate method, of class Camera.
     */
    @Test
    public void testGetCoordinateY() {
        assertEquals(950, camera.getCoordinate().getYint());
    }

    /**
     * Test of getX method, of class Camera.
     */
    @Test
    public void testGetX() {
        assertEquals(500,(int)c.getX());
    }

    /**
     * Test of getY method, of class Camera.
     */
    @Test
    public void testGetY() {
        assertEquals(950,(int)c.getY());
    }

    /**
     * Test of getCenterPosX method, of class Camera.
     */
    @Test
    public void testGetCenterPosX() {
        assertEquals(790,((int)c.getX() + 1080) / 2);
    }

    /**
     * Test of getCenterPosY method, of class Camera.
     */
    @Test
    public void testGetCenterPosY() {
        assertEquals(880,((int)c.getY() + 810) / 2);
    }
    
}
