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
//TODO Destructor ship en bullet

public class World extends GameObject {
	
	/**
	 * 
	 * @param 	width		
	 * 			The width for this new world.
	 * @param 	height	
	 * 			The height for this new world.
	 * @see		implementation
	 */
	public World (double width, double height) {
		super(Constants.WORLD);
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
		
		Boundary boundaryLeft = new Boundary(Constants.LEFT);
		Boundary boundaryRight = new Boundary(Constants.RIGHT);
		Boundary boundaryBottom = new Boundary(Constants.BOTTOM);
		Boundary boundaryTop = new Boundary(Constants.TOP);
		
		boundaries[0] = boundaryLeft;
		boundaries[1] = boundaryRight;
		boundaries[2] = boundaryBottom;
		boundaries[3] = boundaryTop;
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
	public void addShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass, double massDensity) {
		Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, orientation, massDensity);
		ship.setWorld(this);
		ships.add(ship);
	}
	
	public void addShip(Ship ship) {
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
	public void addBullet(double x, double y, double xVelocity, double yVelocity, double radius, Ship ship) {
		Bullet bullet = new Bullet(x, y, xVelocity, yVelocity, radius, ship);
		bullet.setWorld(this);
		bullets.add(bullet);
	}
	
	public void addBullet(Bullet bullet) {
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
		
		bullet.getParent().removeBullet(bullet);
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
	public Entity[] getInstancesAtPosition(double x, double y) {
		Entity[] instances = {null, null};
		
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
	public HashSet<Entity> getEntities() {
		HashSet<Entity> entities = new HashSet();
		for ( Ship s : ships) 
			entities.add(s);
		for ( Bullet b : bullets) 
			entities.add(b);
		return entities;
	}
	
	private Boundary[] boundaries = new Boundary[4];
	
	public void evolve(double deltaTime) {
		double minTime = Double.MAX_VALUE;
		GameObject object1 = null;
		GameObject object2 = null;
		
		for (Entity e1 : this.getEntities()) {
			for (Entity e2 : this.getEntities()) {
				if ( e1 != e2) {
					double time = e1.getTimeToCollision(e2);
					if (time  < minTime ) {
						minTime = time;
						object1 = (GameObject) e1;
						object2 = (GameObject) e2;
					}
				}
			}
		}
		
		for (Entity e1 : this.getEntities()) {
			for (Boundary b : boundaries) {
				double time = e1.getTimeToCollision(b.getBoundaryType());
				if (time < minTime) {
					minTime = time;
					object1 = e1;
					object2 = b;
				}
			}
		}
		
		if (minTime > deltaTime) 
			advance(deltaTime);
		else {
			advance(minTime);
			resolveCollision(object1, object2);
			evolve(deltaTime - minTime);
		}
		
	}
	
	private void resolveCollision(GameObject object1, GameObject object2) throws IllegalStateException {
		if (object1 == null || object2 == null) 
			throw new IllegalStateException("This world does not have any collisions.");

		if (object1 instanceof Ship && object2 instanceof Ship) {		
			Ship ship1 = (Ship) object1;
			Ship ship2 = (Ship) object2;
			
			double deltaVX = ship1.getXVelocity() - ship2.getXVelocity();
			double deltaVY = ship1.getYVelocity() - ship2.getYVelocity();
			double deltaX = ship1.getXCoordinate() - ship2.getXCoordinate();
			double deltaY = ship1.getYCoordinate() - ship2.getYCoordinate();
			
			double ship1J = (2*ship1.getMassTotal()*ship2.getMassTotal() * (deltaVX * deltaX + deltaVY * deltaY)) / (ship1.getRadius() * (ship1.getMassTotal() + ship2.getMassTotal()));
			double ship1JX = (ship1J * deltaX) / ship1.getRadius();
			double ship1JY = (ship1J * deltaY) / ship1.getRadius();
			ship1.setXVelocity(ship1.getXVelocity() + ship1JX / ship1.getMassTotal());
			ship1.setYVelocity(ship1.getYVelocity() + ship1JY / ship1.getMassTotal());
			
			deltaVX = ship2.getXVelocity() - ship1.getXVelocity();
			deltaVY = ship2.getYVelocity() - ship1.getYVelocity();
			deltaX = ship2.getXCoordinate() - ship1.getXCoordinate();
			deltaY = ship2.getYCoordinate() - ship1.getYCoordinate();
			
			double ship2J = (2*ship1.getMassTotal()*ship2.getMassTotal() * (deltaVX * deltaX + deltaVY * deltaY)) / (ship2.getRadius() * (ship1.getMassTotal() + ship2.getMassTotal()));
			double ship2JX = (ship2J * deltaX) / ship2.getRadius();
			double ship2JY = (ship2J * deltaY) / ship2.getRadius();
			ship2.setXVelocity(ship2.getXVelocity() + ship2JX / ship2.getMassTotal());
			ship2.setYVelocity(ship2.getYVelocity() + ship2JY / ship2.getMassTotal());
			
			return;
		}
		
		if ((object1 instanceof Bullet && object2 instanceof Ship) || (object1 instanceof Ship && object2 instanceof Bullet)) {
			Bullet bullet = null;
			Ship ship = null;
			
			if (object1 instanceof Bullet) {
				bullet = (Bullet) object1;
				ship = (Ship) object2;	
			}
			else {
				ship = (Ship) object1;	
				bullet = (Bullet) object2;
			}
			
			if (bullet.getParent() == ship) {
				ship.bulletsLoaded.add(bullet);
				bullet.setWorld(null);
				bullet.setParent(ship);
			}
			else {
				ship.finalize();
				bullet.finalize();	
			}
			return;
		}
		
		if (object1 instanceof Bullet && object2 instanceof Bullet) {
			Bullet bullet1 = (Bullet) object1;
			Bullet bullet2 = (Bullet) object2;
			
			bullet1.finalize();
			bullet2.finalize();
			
			return;
		}
		
		
		if (object1 instanceof Ship && object2 instanceof Boundary) {
			Boundary boundary = (Boundary) object2;
			Ship ship = (Ship) object1;
			
			if (boundary.getBoundaryType() == Constants.BOTTOM || boundary.getBoundaryType() == Constants.TOP)
				ship.setYVelocity(-ship.getYVelocity());
			else 
				ship.setXVelocity(-ship.getXVelocity());
			return;
		}
		
		if (object1 instanceof Bullet && object2 instanceof Boundary) {
			Boundary boundary = (Boundary) object2;
			Bullet bullet = (Bullet) object1;
			
			if (boundary.getBoundaryType() == Constants.BOTTOM || boundary.getBoundaryType() == Constants.TOP)
				bullet.setYVelocity(-bullet.getYVelocity());
			else 
				bullet.setXVelocity(-bullet.getXVelocity());
			bullet.addBounce();
			return;
		}
		throw new IllegalStateException("Undefined collision.");
	}
	

	public void advance(double deltaTime) {
		for (Ship ship : ships) {
			ship.move(deltaTime);
			if (ship.isThrusterEnabled()) 
				ship.updateVelocity();
		}
		
		for (Bullet bullet : bullets) {
			bullet.move(deltaTime);
			
			if (bullet.isBulletLoaded()) {
				bullet.setXCoordinate(bullet.getParent().getXCoordinate());
				bullet.setYCoordinate(bullet.getParent().getYCoordinate());
			}
		}
	}
}
