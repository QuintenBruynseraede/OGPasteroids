package asteroids.facade;

import asteroids.model.Ship;
import asteroids.model.World;

import java.util.Collection;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.part2.CollisionListener;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

// METHODS IN THIS INTERFACE ARE ONLY ALLOWED TO THROW EXCEPTIONS OF TYPE ASTEROIDS.UTIL.MODELEXCEPTION
// FACADE IMPLEMENTATION CAN ONLY HAVE TRIVIAL CODE

public class Facade implements asteroids.part2.facade.IFacade {
	
	Facade(){}
	
	@Override
	public Ship createShip() throws ModelException {
		Ship ship1 = new Ship(0.0, 0.0, Ship.getVelocityLowerBound(), Ship.getVelocityLowerBound(), 0.0, Ship.getRadiusLowerBound(), (4/3)*Math.PI*Math.pow(Ship.getRadiusLowerBound()*1.42E12, 3), 1.42E12, (World) null);
		
		return ship1;
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		try {
			Ship ship1 = new Ship(x, y, xVelocity, yVelocity, radius, orientation, 0,0,(World) null);
			return ship1;
		} catch (IllegalArgumentException e) {
			throw new ModelException("IllegalArgumentException thrown in createShip()");
		} catch (AssertionError e) {
			throw new ModelException("orientation invalid");
		}
	}

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
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException {
		
		Ship ship1 = new Ship(0.0, 0.0, Ship.getVelocityLowerBound(), Ship.getVelocityLowerBound(), 0.0, Ship.getRadiusLowerBound(), (4/3)*Math.PI*Math.pow(Ship.getRadiusLowerBound()*1.42E12, 3), 1.42E12, (World) null);
		return ship1;
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Trying to terminate null ship.");
		ship.finalize();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		if (ship == null) throw new ModelException("Trying to terminate null ship.");
		return ship.isTerminated();
		
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
		
		Bullet bullet = new Bullet(x ,y , xVelocity, yVelocity, radius, (Ship) null, (World) null);
		return bullet;
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		bullet.finalize();
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		if (bullet == null) throw new ModelException("Trying to terminate null bullet.");
		return bullet.isTerminated();
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
		return bullet.getMassBullet();
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
		world.finalize();
				
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		double[] size = new double[2];
		
		size[0] = world.getWidth();
		size[1] = world.getHeight();
		
		return size;
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getShips();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getBullets();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		ship.setWorld(world);
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		world.removeShip(ship);
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		bullet.setWorld(world);
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeBullet(bullet);
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.bulletsLoaded;
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.bulletsLoaded.size();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		ship.bulletsLoaded.add(bullet);
		bullet.setParent(ship);
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		for (Bullet bullet : bullets) {
			loadBulletOnShip(ship, bullet);
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBullet(bullet);
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		if (object instanceof Bullet) {
			Bullet b = (Bullet) object;
			return Math.min(Math.min(b.getTimeToCollision(3),  b.getTimeToCollision(4)), Math.min(b.getTimeToCollision(1), b.getTimeToCollision(2) ));
		}
		else if (object instanceof Ship) {
			Ship s = (Ship) object;
			return Math.min(Math.min(s.getTimeToCollision(3),  s.getTimeToCollision(4)), Math.min(s.getTimeToCollision(1), s.getTimeToCollision(2) ));
		}
		else
			return Double.POSITIVE_INFINITY;
		
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}
}
