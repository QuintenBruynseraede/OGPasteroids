package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;


/**
 * A class of bullets with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 * @version	1.0
 *
 * @invar	The radius of this bullet is always a valid radius for a bullet.
 * 			| isValidRadius(getRadius())
 * @invar	The initial correlation between a bullet's radius and its weight is kept during its entire lifetime.
 * 			| getMass() = 4 * Math.PI * Math.pow(this.getRadius(), 3) * Bullet.MASSDENSITY / 3.0
 * @invar	A bullet has never withstood more bounces than its allowed number of bounces - 1
 * 			| this.getBounces() <= MAXBOUNCES - 1
 */

public class Bullet extends Entity {
	 
	/**
	 * Initializes a bullet with a position, velocity, radius, parent ship and mass.
	 * 
	 * @param 	x
	 * 			The X coordinate for this new bullet.
	 * @param 	y
	 * 			The Y coordinate for this new bullet.
	 * @param	xVelocity
	 * 			The Velocity in the X direction for this new bullet.
	 * @param	yVelocity
	 * 			The Velocity in the Y direction for this new bullet.
	 * @param	radius
	 * 			The radius for this new bullet.
	 * @param	parent
	 *			The parent for this new bullet.
	 * @effect	| setMass( 4 * Math.PI * Math.pow(this.getRadius(), 3) * Bullet.MASSDENSITY / 3.0 )
	 * @effect	The parent of this new bullet is equal to the given ship.
	 * 			| setParent(parent);
	 * @effect	This bullet is initialized as an Entity with a position, velocity and radius
	 * 			| super(x, y, xVelocity, yVelocity, radius)
	 * @throws	IllegalArgumentException
	 * 			| !isValidRadius(radius)
	 * 
	 */
	@Raw
	public Bullet (double x, double y, double xVelocity, double yVelocity, double radius, Ship parent) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		this.setMass(4 * Math.PI * Math.pow(this.getRadius(), 3) * Bullet.MASSDENSITY / 3.0);
		this.setParent(parent);	
		
		if (! isValidRadius(radius))  
			throw new IllegalArgumentException("Non valid radius when initializing bullet");
	}
	
	
	/**
	 * variable registering the mass of a bullet in kilogrammes. The mass can be calculated as m = (4/3)pi*radius^3*massDensity
	 */
	private double mass;

	
	/**
	 * Returns this bullet's mass.
	 */
	@Basic
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * 	Sets the mass for this bullet.
	 */
	@Basic
	private void setMass(double mass) {
		this.mass = mass;
	}
	
	/**
	 * variable registering the mass density of a bullet in kilogrammes 
	 */
	private static final double MASSDENSITY = 7.8E12;
	
	/**
	 * Returns the mass density of this bullet.
	 */
	@Basic
	@Immutable
	public double getMassDensity() {
		return Bullet.MASSDENSITY;
	}
	
	/**
	 * Variable registering the ship this bullet is carried by.
	 */
	private Ship parent = null; 
		
	
	/**
	 * Sets the ship this bullet is loaded on or fired by. 
	 * @param 	ship
	 * @post	| new.getParent() = this.getParent()
	 */
	@Raw
	public void setParent(Ship ship) {
		if (this.getParent() == ship)
			return;
		if (ship == null)
			this.setLoaded(false);
		else
			this.setLoaded(true);
		this.parent = ship;
	}
	
	/**
	 * Return this ship's parent object.
	 */
	@Basic
	@Raw
	public Ship getParent() {
		return this.parent;
	}
	
	/**
	 * Variable registering the number of bounces against a boundary this bullet has made.
	 */
	private int bounces = 0;
	
	/**
	 * The maximum number of bounces against a boundary a bullet can withstand.
	 */
	private final static int MAXBOUNCES = 3;

	/**
	 * Returns the number of bounces against a boundary this bullet has made.
	 */
	@Basic
	public int getBounces() {
		return this.bounces;
	}
	
	
	
	/**
	 * Adds one to the amount of bounces if necessary. Finalizes the object if it has reached its maximum amount of bounces.
	 * @post	If the bullet can bounce once more, add one to the counter.
	 * 			| new.getBounces() = this.getBounces() + 1
	 * @post	If the bullet cannot withstand another bounce, finalize it
	 * 			| if (this.getBounces() == MAXBOUNCES-1)
	 * 			| 	this.finalize();
	 */
	public void addBounce() {
		if (this.getBounces() == MAXBOUNCES-1) {
			System.out.println("Removed bullet on third bounce.");
			this.finalize();
		}
		this.bounces++;
	}
	
	/**
	 * Returns whether this bullet and another bullet are loaded and both loaded on the same ship.
	 * @param 	bullet
	 * 		  	The second bullet
	 * @return	result == ((this.isBulletLoaded() && bullet.isBulletLoaded()) && (this.getParent() == bullet.getParent())
	 */
	@Raw
	public boolean isLoadedOnSameShipAs(Bullet bullet) {
		return ((this.isLoaded() && bullet.isLoaded()) && (this.getParent() == bullet.getParent()));
	}
	
	/**
	 * Variable representing whether this bullet has been loaded on a ship.
	 */
	private boolean isLoaded = false;
	
	/**
	 * Returns whether a bullet has been loaded on a ship.
	 */
	@Basic
	@Raw
	public boolean isLoaded() {
		return this.isLoaded;
	}
	
	
	/**
	 * Sets the loaded state for a bullet.
	 * @param 	loaded
	 * 		  	Whether this bullet is loaded or not.
	 * @post	new.isBulletLoaded() == loaded
	 */
	@Basic
	@Raw
	public void setLoaded(boolean loaded) {
		this.isLoaded = loaded;
	}

	
	/**
	 * 	The smallest radius an instance of the bullet class can have.
	 */
	private static final double RADIUSLOWERBOUND = 1;
	
	/**
	 * Returns whether a given radius is a valid radius for a bullet.
	 * @param 	radius
	 * 			The given radius to check.
	 * @return	True if and only if the velocity is greater than the minimum value specified for a bullet's radius.
	 * 			| result == radius > this.getRadiusLowerBound()
	 */
	@Raw
	public boolean isValidRadius(double radius) {
		return (radius >= getRadiusLowerBound());
	}

	/**
	 * Updates the radius of this bullet.
	 * @param 	radius
	 * 			The new radius for this bullet.
	 * @post	The new radius of the bullet is equal to the given argument radius.
	 * 			| new.radius = radius
	 * @throws	IllegalArgumentException
	 * 			| !isValidRadius(radius)
	 */
	@Override
	public void setRadius(double radius) throws IllegalArgumentException{
		if (isValidRadius(radius))
			this.radius = radius;
		else
			throw new IllegalArgumentException("Non valid radius.");
	}

	/**
	 * Returns the lower bound for the radius of a bullet.
	 * @return	 | Bullet.RADIUSLOWERBOUND
	 */
	@Override
	@Raw
	@Immutable
	public double getRadiusLowerBound() {
		return Bullet.RADIUSLOWERBOUND;
	}

	/**
	 * Advances the bullet during a given time duration deltaTime
	 * @param	deltaTime
	 * 			The time during which to advance this bullet.
	 * @effect	| move(deltaTime)
	 */
	@Override
	@Raw
	public void advance(double deltaTime) {
		if (this.isLoaded) {
			this.setXCoordinate(getParent().getXCoordinate());
			this.setYCoordinate(getParent().getYCoordinate());
		}
		else {
			move(deltaTime);
		}
		
	}

	
	
	/**
	 * Finalizes the bullet, preparing it to be removed by the garbage collector.
	 * @post	If this bullet has a parent, make it remove it from its list of bullets
	 * @post	If this bullet has been added to a world, make the world remove it from its list of bullets
	 * @post	| new.isFinalized() == true
	 * @see 	implementation
	 */
	@Override
	@Raw
	public void finalize() {
		if (finalized) return;
		if (this.getParent() != null)
			this.getParent().removeBullet(this);
		if (this.getWorld() != null) {
			this.getWorld().removeEntity(this);
		}
		this.finalized = true;
		System.out.println("Finalizing bullet");
	}

	/**
	 * Returns a string representation of a bullet.
	 * 
	 * @return	A string representation of a bullet.
	 */
	@Override
	@Raw
	public String toString() {
		return "[Bullet] " + this.hashCode();
	}

	@Override
	public void collideWith(Entity entity) {
		if (entity instanceof Bullet) {
			entity.finalize();
			this.finalize();
			System.out.println("Collision with other bullet.");
		}
		else if (entity instanceof Ship) {
			if (entity == getParent()) {
				((Ship) entity).addBulletToLoaded(this);
				setLoaded(true);
				getWorld().removeEntity(this);
				setWorld(null);
				System.out.println("Picked up own bullet");
				
			}
			else {
				System.out.println("Collision with other ship");
				entity.finalize();
				finalize();
			}	
		}
		else
			entity.collideWith(this);
	}
	
	@Override
	public void collideBoundary() {
		if (getWorld() == null) return;
		if (getXCoordinate() < 1.01*getRadius())
			setXVelocity(-getXVelocity());
		else if (getXCoordinate() > getWorld().getWidth()-1.01*getRadius())
			setXVelocity(-getXVelocity());
		else if (getYCoordinate() < 1.01 * getRadius())
			setYVelocity(-getYVelocity());
		else if (getYCoordinate() > getWorld().getHeight()-1.01*getRadius())
			setYVelocity(-getYVelocity());
		
		addBounce();
	}
	
}
