/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.gameUtil;

import shared.Coordinate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomt
 */
public class CoordinateTest {
    private Coordinate c;
    
    @Before
    public void setUp() {
        c = new Coordinate(500,600);
    }

    /**
     * Test of getX method, of class Coordinate.
     */
    @Test
    public void testGetX() {
        assertEquals(500, (int)c.getX());
    }

    /**
     * Test of setX method, of class Coordinate.
     */
    @Test
    public void testSetX() {
        c.setX(100);
        assertEquals(100, (int)c.getX());
    }

    /**
     * Test of getY method, of class Coordinate.
     */
    @Test
    public void testGetY() {
        assertEquals(600, (int)c.getY());
    }

    /**
     * Test of setY method, of class Coordinate.
     */
    @Test
    public void testSetY() {
        c.setY(100);
        assertEquals(100, (int)c.getY());
    }

    /**
     * Test of getXint method, of class Coordinate.
     */
    @Test
    public void testGetXint() {
        assertEquals(500,c.getXint());
    }

    /**
     * Test of getYint method, of class Coordinate.
     */
    @Test
    public void testGetYint() {
        assertEquals(600, c.getYint());
    }

    /**
     * Test of distance method, of class Coordinate.
     */
    @Test
    public void testDistance() {
        assertEquals(141, (int)Coordinate.distance(new Coordinate(100,100),new Coordinate(200,200)));
    }

    /**
     * Test of Origin method, of class Coordinate.
     */
    @Test
    public void testOrigin() {
        assertEquals(0,Coordinate.origin().getXint());
        assertEquals(0,Coordinate.origin().getYint());
    }
    
}
