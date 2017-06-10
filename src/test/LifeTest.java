/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import game.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author caleberios
 */
public class LifeTest{
    private Life life;
    
    public LifeTest() {
    }
 
    @Before
    public void setUp() {
        life = new Life(250, 250);
    }
 
    @After
    public void tearDown() {
    }
        
    @Test
    public void testMove() {
        boolean thisReturn = life.move();
        
        assertTrue(thisReturn);
    }
    
    @Test
    public void testInsert(){
        assertNotNull(Life.insert());
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
