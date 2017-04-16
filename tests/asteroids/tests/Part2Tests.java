package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.model.Bullet;
import asteroids.model.Collision;
import asteroids.model.GameObject;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class Part2Tests {
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	@Test
	public void testResolveCollisions() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship1 = facade.createShip(100, 120, 50, 0, 50, 0, 1.1E18);
		Ship ship2 = facade.createShip(300, 120, -50, 0, 50, 0, 1.1E18);
		
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		//world.resolveCollision(ship1, ship2, null);
		facade.evolve(world, 1, null);
		
	}
	
	@Test
	public void testLoadBullet() throws ModelException {
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		facade.loadBulletOnShip(ship, bullet);
		assertTrue(ship.bulletsLoaded.contains(bullet));
		assertTrue(ship.bulletsLoaded.size() == 1);
	}
	
	@Test
	public void testLoadMultipleBullets() throws ModelException {
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		Bullet bullet2 = facade.createBullet(100, 100, 10, 20, 20);
		Bullet bullet3 = facade.createBullet(200, 200, 10, 20, 20);
		Bullet bullet4 = facade.createBullet(300, 300, 10, 20, 20);
		Bullet bullet5 = facade.createBullet(400, 400, 10, 20, 20);
		HashSet<Bullet> b = new HashSet<Bullet>();
		b.add(bullet);
		b.add(bullet2);
		b.add(bullet3);
		b.add(bullet4);
		b.add(bullet5);
		
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		facade.loadBulletsOnShip(ship, b);
		assertTrue(ship.bulletsLoaded.contains(bullet));
		assertTrue(ship.bulletsLoaded.contains(bullet2));
		assertTrue(ship.bulletsLoaded.contains(bullet3));
		assertTrue(ship.bulletsLoaded.contains(bullet4));
		assertTrue(ship.bulletsLoaded.contains(bullet5));
		assertTrue(ship.bulletsLoaded.size() == 5);
	}
	
	@Test
	public void testSetParentBullet() throws ModelException {
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		
		facade.loadBulletOnShip(ship, bullet);
		assertTrue(bullet.getParent() == ship);
		assertTrue(bullet.isLoaded());
		assertTrue(ship.bulletsLoaded.contains(bullet));
	}
	
	@Test
	public void testLoadSameBullet() throws ModelException {
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		
		facade.loadBulletOnShip(ship, bullet);
		assertTrue(ship.bulletsLoaded.contains(bullet));
		assertTrue(ship.bulletsLoaded.size() == 1);
		
		facade.loadBulletOnShip(ship, bullet);
		assertTrue(ship.bulletsLoaded.contains(bullet));
		assertTrue(ship.bulletsLoaded.size() == 1);
		
	}
	
	@Test
	public void testRemoveBullets() throws ModelException {
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		Bullet bullet2 = facade.createBullet(0, 0, 10, 20, 20);
		
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		
		facade.addShipToWorld(world, ship);
		facade.addBulletToWorld(world, bullet2);
		facade.addBulletToWorld(world, bullet);
		
		facade.loadBulletOnShip(ship, bullet2);
		facade.loadBulletOnShip(ship, bullet);
		
		facade.removeBulletFromShip(ship, bullet2);
		assertTrue(ship.bulletsLoaded.size() == 1);
		assertTrue(ship.bulletsLoaded.contains(bullet));
	}
	

	
	@Test
	public void testFireBullets() throws ModelException {
		Bullet bullet1 = facade.createBullet(0, 0, 10, 20, 20);
		Bullet bullet2 = facade.createBullet(0, 0, 10, 20, 20);
		Bullet bullet3 = facade.createBullet(0, 0, 10, 20, 20);
		
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		
		facade.addShipToWorld(world, ship);
		facade.addBulletToWorld(world, bullet1);
		facade.addBulletToWorld(world, bullet2);
		facade.addBulletToWorld(world, bullet3);
		
		
		facade.loadBulletOnShip(ship, bullet1);
		facade.loadBulletOnShip(ship, bullet2);
		facade.loadBulletOnShip(ship, bullet3);
		
		assertEquals(ship.getXCoordinate(), bullet1.getXCoordinate(), EPSILON);
		
		assertTrue(ship.bulletsLoaded.size() == 3);
		ship.fire();
		assertTrue(ship.bulletsLoaded.size() == 2);
		ship.fire();
		assertTrue(ship.bulletsLoaded.size() == 1);
		ship.fire();

		assertTrue(!(bullet1.isLoaded()));
		assertTrue(!(bullet2.isLoaded()));
		assertTrue(!(bullet3.isLoaded()));

		assertTrue(bullet1.getParent() == ship);
		assertTrue(bullet2.getParent() == ship);
		assertTrue(bullet3.getParent() == ship);
	}
	
	@Test	
	public void testGetObjectsNextCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, -10, 0, 20, 0, 1.0E20);
		
		facade.addShipToWorld(world, ship);
		System.out.println(world.getTimeNextCollision());
		Collision coll = world.getNextCollisionData();
		GameObject object1 = world.getObjectsNextCollision()[0];
		GameObject object2 = world.getObjectsNextCollision()[1];
		System.out.println(coll.getObject1());
		System.out.println(coll.getObject2());
		System.out.println(object1);
		System.out.println(object2);
		assertEquals(coll.getX(), 0.0, EPSILON);
		assertEquals(coll.getY(), 120.0, EPSILON);
		assertEquals(coll.getTime(), 10.0, EPSILON);
		assertEquals(coll.getType(), 1, EPSILON);
	}	

	@Test
	public void testTimeFirstCollisionBoundary() throws ModelException {
		
	}
	
	@Test
	public void testFirstCollisionBoundary() throws ModelException {
		
	}
	
	@Test
	public void testParentOfBullet() throws ModelException {
		
	}
	
	@Test
	public void testBulletLoadedOnSameShip() throws ModelException {
		
	}
	
	@Test
	public void testFinalizeBullet() throws ModelException {
		
	}
	
	@Test
	public void testAddShipToWorld() throws ModelException {
		
	}
	
	@Test
	public void testRemoveShipFromWorld() throws ModelException {
		
	}
	
	@Test
	public void testInstanceAtPosition() throws ModelException {
		
	}
	
	@Test
	public void testGetEntities() throws ModelException {
		
	}
	
	@Test
	public void testTimeNextCollision() throws ModelException {
		
	}

	@Test
	public void testObjectsNextCollision() throws ModelException {
		
	}
	
	@Test
	public void testPositionNextCollision() throws ModelException {
		
	}
	
	@Test
	public void testFinalizeWorld() throws ModelException {
		
	}
}
