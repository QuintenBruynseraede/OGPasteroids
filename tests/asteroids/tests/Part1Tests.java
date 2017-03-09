package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Part1Tests {
	
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	@Test(expected = ModelException.class)
	public void createShipInvalidRadius() throws ModelException {
		Ship ship = facade.createShip(0,0,10,10,-5, Math.PI);
	}

	@Test(expected = ModelException.class)
	public void createShipInvalidOrientation() throws ModelException {
		Ship ship = facade.createShip(0,0,10,10,5, 3*Math.PI);
	}

	@Test//
	public void createShipPrintValues() throws ModelException {
		Ship ship = facade.createShip(8,9,10,11,10, Math.PI);
		assertEquals(8, ship.getXCoordinate(), EPSILON); 
		assertEquals(9, ship.getYCoordinate(), EPSILON); 
		assertEquals(10, ship.getXVelocity(), EPSILON); 
		assertEquals(11, ship.getYVelocity(), EPSILON); 
		assertEquals(10, ship.getRadius(), EPSILON); 
		assertEquals( Math.PI, ship.getOrientation(), EPSILON); 
	}
	

/** 
	@Test
	public void testMove() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, 1);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
	}
	**/
}
