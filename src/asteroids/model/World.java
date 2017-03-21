package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import java.util.*;

/**
 * A class of worlds with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */

//TODO Uitgebreid testen van getShips()
//TODO Evolve hulpfuncties maken!!

public class World {
	
	/**
	 * 
	 * @param 	width		
	 * 			The width for this new world.
	 * @param 	height	
	 * 			The height for this new world.
	 * @see		implementation
	 */
	public World (double width, double height) {
		if (width < 0 )
			this.width = 0;
		else if (width > WIDTHUPPERBOUND)
			this.width = WIDTHUPPERBOUND;
		else
			this.width = width;
		
		if (height < 0 )
			this.height = 0;
		else if (height > HEIGHTUPPERBOUND)
			this.height = HEIGHTUPPERBOUND;
		else
			this.height = height;
	}
	
	/**
	 * Variable registering the width of this world expressed in kilometres.
	 */
	private final double width;
	
	/**
	 * Variable registering the height of this world expressed in kilometres.
	 */
	private final double height;
	
	public final static double WIDTHUPPERBOUND = Double.MAX_VALUE;
	public final static double HEIGHTUPPERBOUND = Double.MAX_VALUE;
	
	/**
	 * Return the width of this ship expressed in kilometres.
	 */
	@Basic
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Return the height of this ship expressed in kilometres.
	 */
	@Basic
	public double getHeight() {
		return this.height;
	}

	/**
	 * Set registering the bullets contained in this world
	 */
	private Set<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * Set registering the ships contained in this world
	 */
	private Set<Ship> ships = new HashSet<Ship>();
	
	
	/**
	 * 
	 * @param 	x
	 * 			The X coordinate for this new ship.
	 * @param 	yCoordinate
	 * 			The Y coordinate for this new ship.
	 * @param	xVelocity
	 * 			The Velocity in the X direction for this new ship.
	 * @param	yVelocity
	 * 			The Velocity in the Y direction for this new ship.
	 * @param	radius
	 * 			The radius for this new ship.
	 * @param	orientation
	 * 			The orientation for this new ship.
	 * @param	mass
	 * 			The mass for this new ship.
	 * @param	massDensity
	 * 			The mass density for this new ship.
	 * @param	world
	 * 			The world for this new ship.
	 * @post	See constructor Ship.
	 * @post	| this.getShips().contains(ship)
	 */
	public void addShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass, double massDensity, World world) {
		Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, orientation, mass, massDensity, world);
		ships.add(ship);
	}

	/**
	 * Removes a given ship object from this world.
	 * @param 	ship
	 * 			The ship to remove from this world.
	 * @throws 	IllegalArgumentException
	 * 			The argument contains no correct reference to an object of the Ship class.
	 * @throws	IllegalArgumentException
	 * 			The list containing all ships in this world does not contain the ship provided as an argument.
	 * @post	| ! ships.contains(ship)
	 */
	public void removeShip(Ship ship) throws IllegalArgumentException {
		if (!(ship instanceof Ship)) 
			throw new IllegalArgumentException("Trying to remove non ship object.");

		if (! ships.contains(ship))
			throw new IllegalArgumentException("Trying to remove ship that is not in this world.");
		
		ship.setWorld(null);
		ships.remove(ship);
	}
	
	/**
	 * 
	 * @param 	x
	 * 			The X coordinate for this new bullet.
	 * @param 	yCoordinate
	 * 			The Y coordinate for this new bullet.
	 * @param	xVelocity
	 * 			The Velocity in the X direction for this new bullet.
	 * @param	yVelocity
	 * 			The Velocity in the Y direction for this new bullet.
	 * @param	radius
	 * 			The radius for this new  bullet.
	 * @param	orientation
	 * 			The orientation for this new bullet.
	 * @param	ship
	 * 			The ship carrying this bullet.
	 * @param	world
	 * 			The world encapsulating this bullet.
	 * @post	| this.getBullets().contains(bullet)
	 */
	public void addBullet(double x, double y, double xVelocity, double yVelocity, double radius, Ship ship, World world) {
		Bullet bullet = new Bullet(x, y, xVelocity, yVelocity, radius, ship, world);
		bullets.add(bullet);
	}

	/**
	 * Removes a given ship object from this world.
	 * @param 	bullet
	 * 			The bullet to remove from this world.
	 * @throws 	IllegalArgumentException
	 * 			The argument contains no correct reference to an object of the Bullet class.
	 * @throws	IllegalArgumentException
	 * 			The list containing all bullets in this world does not contain the bullet provided as an argument.
	 * @post	| ! bullets.contains(bullet)
	 */
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (!(bullet instanceof Bullet)) 
			throw new IllegalArgumentException("Trying to remove non bullet object.");

		if (! bullets.contains(bullet))
			throw new IllegalArgumentException("Trying to remove bullet that is not in this world.");

		bullet.setWorld(null);
		bullets.remove(bullet);
	}
	
	/**
	 * 
	 * @param 	x
	 * 			The X coordinate to check for presence of ship and bullet.
	 * @param 	y
	 * 			The Y coordinate to check for presence of ship and bullet.
	 * @return	
	 * 			
	 */
	//TODO finish specification
	public Object[] getInstancesAtPosition(double x, double y) {
		Object[] instances = {null, null};
		
		for ( Ship s : ships) {
			if( s.getXCoordinate() == x && s.getYCoordinate() == y)
				instances[0] = s;
		}
		
		for ( Bullet b : bullets) {
			if( b.getXCoordinate() == x && b.getYCoordinate() == y)
				instances[1] = b;
		}
		return instances;	
	}
	
	/**
	 * Return the list of ships of a specific world.
	 */
	@Basic
	public HashSet<Ship> getShips() {
		HashSet<Ship> clone = new HashSet();
		for ( Ship s : ships) 
			clone.add(s);
		return clone;
	}
	
	/**
	 * Return the list of bullets of a specific world.
	 */
	@Basic
	public HashSet<Bullet> getBullets() {
		HashSet<Bullet> clone = new HashSet();
		for ( Bullet b : bullets) 
			clone.add(b);
		return clone;
	}
	
	/**
	 * Returns a list of all ships and bullets in this world.
	 * 
	 * @return 	A list of all ships and bullets in this world.
	 * 			| { entity1, entity2, ..., entityN | entityI.getWorld() = this}
	 */
	public HashSet<Object> getShipsAndBullets() {
		HashSet<Object> entities = new HashSet();
		for ( Ship s : ships) 
			entities.add(s);
		for ( Bullet b : bullets) 
			entities.add(b);
		return entities;
	}
	
	public final static int LEFT = 1;
	public final static int TOP = 2;
	public final static int RIGHT = 3;
	public final static int BOTTOM = 4;
	
	public void evolve(double deltaTime) {
		double minTime = Double.MAX_VALUE;
		Object object1 = null; 
		Object object2 = null;
		int boundary = 0;
		
		for (Bullet b : bullets) {
			for (Ship s : ships) {
				double timeToCollision = b.getTimeToCollision(s);
				
				if (timeToCollision < minTime) {
					minTime = timeToCollision;
					object1 = b;
					object2 = s;	
				}
			}
		}
		
		for (Bullet b1 : bullets) {
			for (Bullet b2 : bullets) {
				double timeToCollision = b1.getTimeToCollision(b2);
				if (timeToCollision < minTime) {
					minTime = timeToCollision;
					object1 = b1;
					object2 = b2;	
				}
			}
		}
		
		for (Ship s1 : ships) {
			for (Ship s2 : ships) {
				double timeToCollision = s1.getTimeToCollision(s2);
				if (timeToCollision < minTime) {
					minTime = timeToCollision;
					object1 = s1;
					object2 = s2;	
				}
			}
		}
		
		for (Ship ship : getShips()) {
			double timeToCollision = ship.getTimeToCollision(LEFT);
			if (timeToCollision < minTime) {
				object1 = ship;
				boundary = LEFT;	
			}
			
			timeToCollision = ship.getTimeToCollision(RIGHT);
			if (timeToCollision < minTime) {
				object1 = ship;
				boundary = RIGHT;	
			}
			
			timeToCollision = ship.getTimeToCollision(BOTTOM);
			if (timeToCollision < minTime) {
				object1 = ship;
				boundary = BOTTOM;	
			}
			
			timeToCollision = ship.getTimeToCollision(TOP);
			if (timeToCollision < minTime) {
				object1 = ship;
				boundary = TOP;	
			}
		}
		
		for (Bullet bullet : getBullets()) {
			double timeToCollision = bullet.getTimeToCollision(LEFT);
			if (timeToCollision < minTime) {
				object1 = bullet;
				boundary = LEFT;	
			}
			
			timeToCollision = bullet.getTimeToCollision(RIGHT);
			if (timeToCollision < minTime) {
				object1 = bullet;
				boundary = RIGHT;	
			}
			
			timeToCollision = bullet.getTimeToCollision(BOTTOM);
			if (timeToCollision < minTime) {
				object1 = bullet;
				boundary = BOTTOM;	
			}
			
			timeToCollision = bullet.getTimeToCollision(TOP);
			if (timeToCollision < minTime) {
				object1 = bullet;
				boundary = TOP;	
			}
		}
		
		if (minTime < deltaTime) {
			advance(minTime);
			resolveCollision(object1, object2);
			evolve(deltaTime - minTime);
		}
		else
			advance(deltaTime);	
	}
	
	private void resolveCollision(Object object1, Object object2) throws IllegalStateException {
		if (object1 == null || object2 == null) 
			throw new IllegalStateException("This world does not have any collisions.");
		
		
	}

	public void advance(double deltaTime) {
		for (Ship ship : ships) {
			ship.move(deltaTime);
			if (ship.isThrusterEnabled()) 
				ship.updateVelocity();
		}
		
		for (Bullet bullet : bullets) {
			bullet.move(deltaTime);
		}
	}
}
