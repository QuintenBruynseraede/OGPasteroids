package asteroids.facade;

import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.model.*;
import asteroids.model.programs.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Planetoid;
import asteroids.model.Program;
import asteroids.part2.CollisionListener;
import asteroids.part2.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;

// METHODS IN THIS INTERFACE ARE ONLY ALLOWED TO THROW EXCEPTIONS OF TYPE ASTEROIDS.UTIL.MODELEXCEPTION
// FACADE IMPLEMENTATION CAN ONLY HAVE TRIVIAL CODE

public class Facade implements asteroids.part3.facade.IFacade {
	
	public Facade(){}
	
	
	/**@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		try {
			Ship ship1 = new Ship(x, y, xVelocity, yVelocity, radius, orientation, 1.42E12);
			return ship1;
		} catch (IllegalArgumentException e) {
			throw new ModelException("IllegalArgumentException thrown in createShip()");
		} catch (AssertionError e) {
			throw new ModelException("Orientation invalid");
		}
	}
	**/

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		double x = ship.getXCoordinate();
		double y = ship.getYCoordinate();
		double[] position = {x, y};
		return position;
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		double xVelocity = ship.getXVelocity();
		double yVelocity = ship.getYVelocity();
		double[] velocity = {xVelocity, yVelocity};
		return velocity;
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		double radius = ship.getRadius();
		return radius;
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		double orientation = ship.getOrientation();
		return orientation;
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		try {
			ship.move(dt);
		} catch (IllegalArgumentException e) {
			throw new ModelException("Invalid time");
		}
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		ship.thrust(amount);		
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		try {
			ship.turn(angle);
		} catch (AssertionError e) {
			throw new ModelException("orientation invalid");
		}		
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		if (ship1 == null || ship2 == null)
			throw new ModelException("ship == null");
		
		return ship1.getDistanceBetween(ship2);
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		if (ship1 == null || ship2 == null)
			throw new ModelException("ship == null");
		
		return ship1.overlap(ship2);
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		if (ship1 == null || ship2 == null)
			throw new ModelException("ship == null");
		
		return ship1.getTimeToCollision(ship2);
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		if (ship1 == null || ship2 == null)
			throw new ModelException("ship == null");
		
		try {
			return ship1.getCollisionPosition(ship2);
		} catch (IllegalArgumentException e) {
			throw new ModelException("Overlap.");
	
		}
	}


	@Override
	public void terminateShip(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Trying to terminate null ship.");
		ship.finalize();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Trying to terminate null ship.");
		return ship.isFinalized();
		
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Trying to terminate null ship.");
		return ship.getMassTotal();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Trying to terminate null ship.");
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Trying to terminate null ship.");
		return ship.isThrusterEnabled();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if (ship == null) throw new ModelException("Trying to terminate null ship.");
		if (active == true)
			ship.thrustOn();
		else
			ship.thrustOff();
		
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Trying to terminate null ship.");
		return ship.getAcceleration();
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		
		Bullet bullet = new Bullet(x ,y , xVelocity, yVelocity, radius, (Ship) null);
		return bullet;
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		bullet.finalize();
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		if (bullet == null) 
			return true;
		return bullet.isFinalized();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		
		double[] pos = new double[2];
		pos[0] = bullet.getXCoordinate();
		pos[1] = bullet.getYCoordinate();
		return pos;
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		
		double[] velocity = new double[2];
		velocity[0] = bullet.getXVelocity();
		velocity[1] = bullet.getYVelocity();
		return velocity;
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		return bullet.getParent();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		return bullet.getParent();
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		World world = new World(width, height);
		return world;
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		if (world == null) throw new ModelException("Trying to terminate null world.");
		world.finalize();
				
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		if (world == null) throw new ModelException("Checking state of null world");
		return world.isFinalized();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		if (world == null) throw new ModelException("Checking size of null world");
		double[] size = new double[2];
		
		size[0] = world.getWidth();
		size[1] = world.getHeight();
		
		return size;
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		if (world == null) throw new ModelException("Querying list of ships in null world");
		return (Set<? extends Ship>) world.getEntitiesOfType(Ship.class);
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		if (world == null) throw new ModelException("Querying list of bullets in null world");
		return (Set<? extends Bullet>) world.getEntitiesOfType(Bullet.class);
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		if (world == null) throw new ModelException("Null reference to world when adding ship to world");
		if (ship == null) throw new ModelException("Null reference to ship when adding ship to world");
		try {
			ship.setWorld(world);
			world.addEntity(ship);
		} catch (IllegalStateException e) {
			throw new ModelException("");
		} catch (IllegalArgumentException e) {
			throw new ModelException("");
		}
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		if (world == null || ship == null) throw new ModelException("Null reference removing ship");
		try {
			world.removeEntity(ship);
			ship.setWorld(null);
		} catch (IllegalArgumentException e) {
			throw new ModelException(" ");
		} catch (IllegalStateException e) {
			throw new ModelException(" ");
			
		}
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		if (world == null || bullet == null) throw new ModelException("Null reference adding bullet");
		try {
			bullet.setWorld(world);
			world.addEntity(bullet);
		} catch (IllegalStateException e) {
			throw new ModelException("");
		} catch (IllegalArgumentException e) {
			throw new ModelException("");
		}
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		if (world == null || bullet == null) throw new ModelException("Null reference removing bullet");
		world.removeEntity(bullet);
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Querying list of bullets on null ship");
		return ship.getBulletsLoaded();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Querying number of bullets on null ship");
		return ship.getBulletsLoaded().size();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		if (ship == null || bullet == null) throw new ModelException("Null reference adding bullet to ship");
		
		try {
			ship.addBulletToLoaded(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException("");
		}
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		if (ship == null || bullets == null) throw new ModelException("Null reference adding bullets to ship");
		try {
			ship.addBulletToLoaded(bullets);
		} catch (IllegalArgumentException e) {
			throw new ModelException("");
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		if (ship == null || bullet == null) throw new ModelException("Null reference removing bullet from ship");
		try {
			ship.removeBullet(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException("");
		}
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Null reference firing bullet");
		ship.fire();
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		if (object == null) throw new ModelException("Null reference checking collision");
		Entity e = (Entity) object;
		return e.getTimeFirstCollisionBoundary();
		
	}

	//@Override
	///public double[] getPositionCollisionBoundary(Object object) throws ModelException {
	//	Entity entity = (Entity) object;
	//	return entity.getCollisionPosition(entity.get());
	//	
	//}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		Entity e1 = (Entity) entity1;
		Entity e2 = (Entity) entity2;
		
		return e1.getTimeToCollision(e2);
		
		
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		Entity e1 = (Entity) entity1;
		Entity e2 = (Entity) entity2;
		
		return e1.getCollisionPosition(e2);
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return world.getTimeNextCollision();
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return world.getPositionNextCollision();
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.evolve(dt, collisionListener);
		} catch (IllegalArgumentException e) {
			throw new ModelException(" ");
		}
		
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getInstanceAtPosition(x, y);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntities();
	}


	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException {
		try {
			Ship ship1 = new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);
			return ship1;
		} catch (IllegalArgumentException e) {
			throw new ModelException("IllegalArgumentException thrown in createShip()");
		} catch (AssertionError e) {
			throw new ModelException("Orientation invalid");
		}
	}


	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}


	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		return (Set<? extends Asteroid>) world.getEntitiesOfType(Asteroid.class);
	}


	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		asteroid.setWorld(world);
		world.addEntity(asteroid);
	}


	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		world.removeEntity(asteroid);
	}


	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		return (Set<? extends Planetoid>) world.getEntitiesOfType(Planetoid.class);
	}


	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		planetoid.setWorld(world);
		world.addEntity(planetoid);
		
	}


	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		world.removeEntity(planetoid);
		
	}


	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		return new Asteroid(x,y, xVelocity, yVelocity, radius);
	}


	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.finalize();
	}


	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return asteroid.isFinalized();
	}


	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		return new double[] {asteroid.getXCoordinate(), asteroid.getYCoordinate()};
	}


	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		return new double[] {asteroid.getXVelocity(), asteroid.getYVelocity()};
	}


	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return asteroid.getRadius();
	}


	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return asteroid.getMass();
	}


	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return asteroid.getWorld();
	}


	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		Planetoid p = new Planetoid(x, y, xVelocity, yVelocity, radius);
		p.addToDistanceTravelled(totalTraveledDistance);
		return p;
	}


	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		planetoid.finalize();
	}


	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		return planetoid.isFinalized();
	}


	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		return new double[] {planetoid.getXCoordinate(), planetoid.getYCoordinate()};
	}


	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		return new double[] {planetoid.getXVelocity(), planetoid.getYVelocity()};
	}


	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		return planetoid.getRadius();
	}


	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		return planetoid.getMass();
	}


	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		return planetoid.getDistanceTravelled();
	}


	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		return planetoid.getWorld();
	}


	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		return ship.getProgramLoaded();
	}


	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		ship.setProgramLoaded(program);
		program.loadOnShip(ship);
	}


	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		return ship.executeProgram(dt);
	}


	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		return new ProgramFactory();
	}


	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}


}
