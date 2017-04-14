package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Collision;
import asteroids.model.GameObject;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.part2.CollisionListener;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class Part2TestPartial {

	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}

	@Test
	public void testCreateWorld() throws ModelException {
		World world = facade.createWorld(1000, 800);
		assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(800, facade.getWorldSize(world)[1], EPSILON);
		assertTrue(facade.getWorldShips(world).isEmpty());
		assertTrue(facade.getWorldBullets(world).isEmpty());
	}

	@Test
	public void testLoadBulletOnShipOverlappingBullets() throws ModelException {
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
		Bullet bullet2 = facade.createBullet(130, 110, 10, 5, 30);
		facade.loadBulletOnShip(ship, bullet1);
		facade.loadBulletOnShip(ship, bullet2);
		assertEquals(2, facade.getNbBulletsOnShip(ship));
		assertEquals(bullet1.getParent(), ship);
		bullet1.addBounce();
		assertEquals(1, bullet1.getBounces());
		assertTrue(bullet1.isLoadedOnSameShipAs(bullet2));
		bullet1.finalize();
		assertTrue(bullet1.isTerminated());
	}

//	@Test
//	public void testEvolveShipWithActiveThruster() throws ModelException {
//		World world = facade.createWorld(5000, 5000);
//		Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
//		facade.addShipToWorld(world, ship);
//		facade.setThrusterActive(ship, true);
//		assertEquals(1000.0, facade.getShipAcceleration(ship), EPSILON);
//		assertTrue(facade.isShipThrusterActive(ship));
//		facade.evolve(world, 1, null);
//		assertEquals(-990, facade.getShipVelocity(ship)[0], EPSILON);
//		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
//	}

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
		assertTrue(bullet.isLoaded == true);
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

		assertTrue(!(bullet1.isBulletLoaded()));
		assertTrue(!(bullet2.isBulletLoaded()));
		assertTrue(!(bullet3.isBulletLoaded()));

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
}

