package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.model.Bullet;
import asteroids.model.Collision;
import asteroids.model.Constants;
import asteroids.model.Entity;
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
		
		world.advance(0.1);
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
		Collision coll = world.getNextCollisionData();
		GameObject object1 = world.getObjectsNextCollision()[0];
		GameObject object2 = world.getObjectsNextCollision()[1];
		assertEquals(coll.getX(), 0.0, EPSILON);
		assertEquals(coll.getY(), 120.0, EPSILON);
		assertEquals(coll.getTime(), 10.0, EPSILON);
		assertEquals(coll.getType(), 1, EPSILON);
	}	

	@Test
	public void testTimeFirstCollisionBoundary() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		
		facade.addShipToWorld(world, ship);
		assertEquals(ship.getTimeFirstCollisionBoundary(), 10.0, EPSILON);
		
	}
	// FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
	@Test
	public void testFirstCollisionBoundary() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		
		facade.addShipToWorld(world, ship);
		
		assertEquals(Constants.LEFT, ship.getFirstCollisionBoundary().getBoundaryType(), EPSILON);
		
	}
	
	@Test
	public void testParentOfBullet() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		
		facade.addShipToWorld(world, ship);
		facade.loadBulletOnShip(ship, bullet);
		facade.addBulletToWorld(world, bullet);
		
		assertTrue(bullet.getParent() == ship);
			
	}
	
	@Test
	public void testBulletLoadedOnSameShip() throws ModelException {
		
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		Bullet bullet2 = facade.createBullet(0, 0, 10, 20, 20);
		
		facade.addShipToWorld(world, ship);
		facade.loadBulletOnShip(ship, bullet);
		facade.loadBulletOnShip(ship, bullet2);
		
		assertTrue(bullet.isLoadedOnSameShipAs(bullet2) == true);
		
	}
	
	@Test
	public void testFinalizeBullet() throws ModelException {
		
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		
		facade.addShipToWorld(world, ship);
		facade.loadBulletOnShip(ship, bullet);
		facade.addBulletToWorld(world, bullet);
		
		bullet.finalize();
		
		boolean foundShip = false;
		for (Bullet i : ship.bulletsLoaded) {
			if (bullet == i)
				foundShip = true;
		}
		assertTrue(foundShip == false);
		
		boolean foundWorld = false;
		for (Entity i : world.getEntitiesOfType(Bullet.class)) {
			if (bullet == i)
				foundWorld = true;
		}
		assertTrue(foundWorld == false);
	}
	
	@Test
	public void testAddShipToWorld() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		
		
		facade.addShipToWorld(world, ship);
		
		boolean foundShip = false;
		for (Entity i : world.getEntitiesOfType(Ship.class)) {
			if (ship == i)
				foundShip = true;
		}
		assertTrue(foundShip == true);
		
		assertTrue(ship.getWorld() == world);
		
	}
	
	@Test
	public void testRemoveShipFromWorld() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		
		facade.addShipToWorld(world, ship);
		facade.removeShipFromWorld(world, ship);
		
		boolean foundShip = false;
		for (Entity i : world.getEntitiesOfType(Bullet.class)) {
			if (ship == i)
				foundShip = true;
		}
		assertTrue(foundShip == false);
		
		assertTrue(ship.getWorld() == null);
		
	}
	
	@Test
	public void testInstanceAtPosition() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		
		Ship ship1 = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		Ship ship2 = facade.createShip(200, 100, -10, 0, 20, 0, 1.0E20);
		Ship ship3 = facade.createShip(300, 100, -10, 0, 20, 0, 1.0E20);
		
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship3);
		
		assertTrue((world.getInstanceAtPosition(100, 100) == ship1));
		assertTrue((world.getInstanceAtPosition(200, 100) == ship2));
		assertTrue((world.getInstanceAtPosition(300, 100) == ship3));
		assertTrue((world.getInstanceAtPosition(0, 0) == null));		
	}
	
	@Test
	public void testGetEntities() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		
		Ship ship1 = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		Ship ship2 = facade.createShip(200, 100, -10, 0, 20, 0, 1.0E20);
		Ship ship3 = facade.createShip(300, 100, -10, 0, 20, 0, 1.0E20);
		Ship ship4 = facade.createShip(300, 100, -10, 0, 20, 0, 1.0E20);
		
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship3);
		
		assertTrue(world.getEntities().contains(ship1));
		assertTrue(world.getEntities().contains(ship2));
		assertTrue(world.getEntities().contains(ship3));
		
		assertTrue(world.getEntities().size() == 3);
		
		assertTrue(!world.getEntities().contains(ship4));
	}
	
	@Test
	public void testTimeNextCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		
		Ship ship1 = facade.createShip(100, 100, 10, 0, 10, 0, 1.0E20);
		Ship ship2 = facade.createShip(200, 100, -10, 0, 10, 0, 1.0E20);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		
		
		assertEquals(140, world.getPositionNextCollision()[0], EPSILON);
		assertEquals(100, world.getPositionNextCollision()[1], EPSILON);
	}
	
	@Test
	public void testTimeNextCollisionNoCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		
		Ship ship1 = facade.createShip(100, 500, -10, 0, 20, 0, 1.0E20);
		Ship ship2 = facade.createShip(200, 500, -10, 0, 20, 0, 1.0E20);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		
		
		
		assertEquals(10.0, world.getTimeNextCollision(), EPSILON);
	}

	@Test
	public void testObjectsNextCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship1 = facade.createShip(100, 500, -10, 0, 20, 0, 1.0E20);
		facade.addShipToWorld(world, ship1);
		GameObject[] objects = world.getObjectsNextCollision();
		assertTrue(objects[0] == ship1 || objects[0] == world.getBoundaries()[0]);
		assertTrue(objects[1] == ship1 || objects[1] == world.getBoundaries()[0]);
	}
	
	@Test
	public void testPositionNextCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		
		Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
		Ship ship2 = facade.createShip(200, 100, -10, 0, 20, 0, 1.0E20);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		
		
		assertTrue(world.getPositionNextCollision()[0] == 130 || world.getPositionNextCollision()[0] == 170);
		assertTrue(world.getPositionNextCollision()[1] == 100);
	}
	
	@Test
	public void testFinalizeWorld() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		
		Ship ship1 = facade.createShip(100, 100, -10, 0, 20, 0, 1.0E20);
		Ship ship2 = facade.createShip(200, 100, 10, 0, 20, 0, 1.0E20);
		
		Bullet bullet1 = facade.createBullet(0, 0, 10, 20, 20);
		Bullet bullet2 = facade.createBullet(0, 0, 10, 20, 20);
		
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.loadBulletOnShip(ship1, bullet1);
		facade.loadBulletOnShip(ship2, bullet2);
		facade.addBulletToWorld(world, bullet1);
		facade.addBulletToWorld(world, bullet2);
		
		world.finalize();
		assertTrue(ship1.getWorld() == null);
		assertTrue(ship2.getWorld() == null);
		assertTrue(bullet1.getWorld() == null);
		assertTrue(bullet2.getWorld() == null);
		assertTrue(world.getEntitiesOfType(Bullet.class).size() == 0);
		assertTrue(world.getEntitiesOfType(Ship.class).size() == 0);
		assertTrue(world.isFinalized() == true);
		assertTrue(ship1 != null);
		
		
	}
}
