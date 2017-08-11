package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.model.Bullet;
import asteroids.model.Constants;
import asteroids.model.Entity;
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
		assertTrue(ship.getBulletsLoaded().contains(bullet));
		assertTrue(ship.getBulletsLoaded().size() == 1);
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
		assertTrue(ship.getBulletsLoaded().contains(bullet));
		assertTrue(ship.getBulletsLoaded().contains(bullet2));
		assertTrue(ship.getBulletsLoaded().contains(bullet3));
		assertTrue(ship.getBulletsLoaded().contains(bullet4));
		assertTrue(ship.getBulletsLoaded().contains(bullet5));
		assertTrue(ship.getBulletsLoaded().size() == 5);
	}
	
	@Test
	public void testSetParentBullet() throws ModelException {
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		
		facade.loadBulletOnShip(ship, bullet);
		assertTrue(bullet.getParent() == ship);
		assertTrue(bullet.isLoaded());
		assertTrue(ship.getBulletsLoaded().contains(bullet));
	}
	@Test
	public void testLoadSameBullet() throws ModelException {
		Bullet bullet = facade.createBullet(0, 0, 10, 20, 20);
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		
		facade.loadBulletOnShip(ship, bullet);
		assertTrue(ship.getBulletsLoaded().contains(bullet));
		assertTrue(ship.getBulletsLoaded().size() == 1);
		
		facade.loadBulletOnShip(ship, bullet);
		assertTrue(ship.getBulletsLoaded().contains(bullet));
		assertTrue(ship.getBulletsLoaded().size() == 1);
		
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
		assertTrue(ship.getBulletsLoaded().size() == 1);
		assertTrue(ship.getBulletsLoaded().contains(bullet));
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
		
		for (Bullet b: ship.getBulletsLoaded())
			System.out.println(b); //[]
		
		world.advance(0.000001);
		assertEquals(ship.getXCoordinate(), bullet1.getXCoordinate(), EPSILON);
		
		assertTrue(ship.getBulletsLoaded().size() == 3);
		ship.fire();
		assertTrue(ship.getBulletsLoaded().size() == 2);
		ship.fire();
		assertTrue(ship.getBulletsLoaded().size() == 1);
		ship.fire();

		assertTrue(!(bullet1.isLoaded()));
		assertTrue(!(bullet2.isLoaded()));
		assertTrue(!(bullet3.isLoaded()));
		
		//System.out.println(bullet1.getParent());
		//System.out.println(ship.bulletsLoaded.contains(bullet1));
		assertTrue(bullet1.getParent() == ship);
		assertTrue(bullet2.getParent() == ship);
		assertTrue(bullet3.getParent() == ship);
		for (Bullet b: ship.getBulletsLoaded())
			System.out.println(b); //[]
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
		
		//System.out.println(bullet.getParent());
		//System.out.println(bullet.getWorld());
		bullet.finalize();
		
		boolean foundShip = false;
		for (Bullet i : ship.getBulletsLoaded()) {
			if (bullet == i)
				foundShip = true;
		}
		assertTrue(foundShip == false);
		
		
		boolean foundWorld = false;
		for (Entity i : world.getEntitiesOfType(Bullet.class)) {
			if (bullet == i) 
				foundWorld = true;
		}
		//System.out.println(bullet.getWorld());
		//System.out.println(world.getEntities().contains(bullet));
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
	
	@Test
	public void testGetTimeFirstCollisionBoundary() throws ModelException {
		World world = facade.createWorld(100, 100);
		
		Ship ship1 = facade.createShip(50, 50, -10, 0, 10, 0, 1.0E20);
		Ship ship2 = facade.createShip(50, 50, 10, 0, 10, 0, 1.0E20);
		Ship ship3 = facade.createShip(50, 50, 0, -10, 10, 0, 1.0E20);
		Ship ship4 = facade.createShip(50, 50, 0, 10, 10, 0, 1.0E20);
		
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship3);
		facade.addShipToWorld(world, ship4);
		
		assertEquals(4, ship1.getTimeFirstCollisionBoundary(), 0.1);
		assertEquals(4, ship2.getTimeFirstCollisionBoundary(), 0.1);
		assertEquals(4, ship3.getTimeFirstCollisionBoundary(), 0.1);
		assertEquals(4, ship4.getTimeFirstCollisionBoundary(), 0.1);	
	}
	
	@Test
	public void testGetTimeToCollision() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		
		Ship ship1 = facade.createShip(100, 50, 10, 0, 10, 0, 1.0E20);
		Ship ship2 = facade.createShip(200, 50, -10, 0, 10, 0, 1.0E20);
		Ship ship3 = facade.createShip(10, 10, 0, 0, 10, 0, 1E20);

		
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship3);
		
		assertEquals(4, ship1.getTimeToCollision(ship2), 0.1);
		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(ship3), 0.1);
	}
}
