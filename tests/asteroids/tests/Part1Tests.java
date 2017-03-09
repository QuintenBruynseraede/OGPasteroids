package asteroids.tests;

import static org.junit.Assert.*;

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
	public void createShipValues() throws ModelException {
		Ship ship = facade.createShip(8,9,10,11,10, Math.PI);
		assertEquals(8, ship.getXCoordinate(), EPSILON); 
		assertEquals(9, ship.getYCoordinate(), EPSILON); 
		assertEquals(10, ship.getXVelocity(), EPSILON); 
		assertEquals(11, ship.getYVelocity(), EPSILON); 
		assertEquals(10, ship.getRadius(), EPSILON); 
		assertEquals( Math.PI, ship.getOrientation(), EPSILON); 
	}
	
	@Test//
	public void createShipOverlap() throws ModelException {
		Ship ship1 = facade.createShip(0,0,0,0,10,0);
		Ship ship2 = facade.createShip(10,10,0,0,30,0);
		assertTrue(facade.overlap(ship1, ship2)); 
	}
	
	@Test//
	public void createShipOverlapSelf() throws ModelException {
		Ship ship1 = facade.createShip(0,0,0,0,10,0);
		Ship ship2 = ship1;
		assertTrue(facade.overlap(ship1, ship2)); 
	}
	
	@Test (expected = ModelException.class)
	public void createShipOverlapNull() throws ModelException {
		Ship ship1 = null;
		Ship ship2 = facade.createShip(10,10,0,0,30,0);
		assertTrue(facade.overlap(ship1, ship2)); 
	}
	
	@Test 
	public void createShipTimeToCollision() throws ModelException {
		Ship ship1 = facade.createShip(0,0,10,0,10,0);
		Ship ship2 = facade.createShip(20,20,10,0,10,0);
		assertEquals(Double.POSITIVE_INFINITY, facade.getTimeToCollision(ship1, ship2), EPSILON); 
	}
	
	@Test 
	public void createShipTimeToCollisionReasonableTime() throws ModelException {
		Ship ship1 = facade.createShip(0,0,10,0,10,0);
		Ship ship2 = facade.createShip(50,0,-10,0,10,Math.PI);
		assertEquals(1, facade.getTimeToCollision(ship1, ship2), 5); 
	}
	
	@Test (expected = ModelException.class)
	public void createShipTimeToCollisionNull() throws ModelException {
		Ship ship1 = null;
		Ship ship2 = null;
		facade.getTimeToCollision(ship1, ship2);
	}
	
	@Test 
	public void createShipTurn() throws ModelException {
		Ship ship1 = facade.createShip(0,0,10,0,10,0);
		facade.turn(ship1, Math.PI);
		assertEquals(Math.PI, facade.getShipOrientation(ship1), EPSILON);
	}
	
	@Test  (expected = ModelException.class)
	public void createShipTurnInvalid() throws ModelException {
		Ship ship1 = facade.createShip(0,0,10,0,10,0);
		facade.turn(ship1, 2*Math.PI);
	}
	
	@Test  (expected = ModelException.class)
	public void createShipTurnNull() throws ModelException {
		Ship ship1 = null;
		facade.turn(ship1, Math.PI);
	}
	
	@Test 
	public void createShipCollisionPosition() throws ModelException {
		Ship ship1 = facade.createShip(0,0,10,0,10,0);
		Ship ship2 = facade.createShip(50,0,-10,0,10,Math.PI);
		assertEquals(15, facade.getCollisionPosition(ship1, ship2)[0], EPSILON); 
		assertEquals(35, facade.getCollisionPosition(ship2, ship1)[0], EPSILON); 
	}	
	
	@Test (expected = ModelException.class)
	public void createShipCollisionPositionOverlap() throws ModelException {
		Ship ship1 = facade.createShip(0,0,10,0,10,0);
		Ship ship2 = facade.createShip(10,0,10,0,20,0);
		facade.getCollisionPosition(ship1, ship2);
	}	
	
	@Test 
	public void createShipVelocity() throws ModelException {
		Ship ship1 = facade.createShip(0,0,300000,0,10,0);
		facade.thrust(ship1, 1);
		System.out.println(facade.getShipVelocity(ship1)[0]);
	}	
}
