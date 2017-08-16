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
 * 			| this.getBounces() <= Bullet.MAXBOUNCES - 1
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
	 * Variable registering the mass density of a bullet in kilogrammes.
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
	 * @post	| new.getParent() = ship
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
	 * Resets the number of bounces against a boundary this bullet has made. 
	 * Necessary when a ship intercepts a bullet it fired.
	 * @post 	| new.getBounces() == 0
	 */
	public void resetBounces() {
		this.bounces = 0;
	}
	
	
	/**
	 * Adds one to the amount of bounces if necessary. Finalizes the object if it has reached its maximum amount of bounces.
	 * @post	If the bullet can bounce once more, add one to the counter.
	 * 			| if (getBounces() < MAXBOUNCES - 1)
	 * 			| 	new.getBounces() = getBounces() + 1
	 * @post	If the bullet cannot withstand another bounce, finalize it
	 * 			| if (getBounces() == MAXBOUNCES-1)
	 * 			| 	finalize();
	 */
	public void addBounce() {
		if (this.getBounces() == MAXBOUNCES-1) {
			this.finalize();
		}
		else
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
	 * Returns whether this bullet has been loaded on a ship.
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
	 * Variable holding the ship that fired this bullet. Doesn't change after assignment.
	 */
	private Ship firedBy = null;
	
	/**
	 * Sets the ship this bullet was fired off of. Can only be invoked once.
	 */
	public void setFiredBy(Ship s) {
		if (getFiredBy() != null) return;
		firedBy = s;
	}
	
	/**
	 * Returns the ship thsi bullet was fired off of.
	 */
	@Basic
	public Ship getFiredBy() {
		return firedBy;
	}
	
	/**
	 * 	The smallest radius an instance of the bullet class can have.
	 */
	private static final double RADIUSLOWERBOUND = 1;
	
	/**
	 * Returns whether a given radius is a valid radius for a bullet.
	 * @param 	radius
	 * 			The given radius to check if valid.
	 * @return	True if and only if the velocity is greater than the minimum value specified for a bullet's radius.
	 * 			| result == radius > getRadiusLowerBound()
	 */
	@Raw
	public boolean isValidRadius(double radius) {
		return (radius >= getRadiusLowerBound());
	}

	/**
	 * Updates the radius of this bullet.
	 * @param 	radius
	 * 			The new radius for this bullet.
	 * @post	| if (isValidRadius(radius))
	 * 			|	new.getRadius() = radius
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
	 * @post	| if (isLoaded())
	 * 			|	new.getXCoordinate() == getParent().getXCoordinate()
	 * 			|	new.getYCoordinate() == getParent().getYCoordinate()
	 * @effect	| if (! isLoaded())
	 *  		| 	move(deltaTime)
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
	 * @see 	implementation
	 */
	@Override
	@Raw
	public void finalize() {
		if (finalized) return;

		if (getWorld() != null) {
			getWorld().removeEntity(this);
		}
		if (getParent() != null) {
			getParent().removeBullet(this);
			setParent(null);
		}
			
		finalized = true;
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

	
	/**
	 *  Resolves a collision between this bullet and another entity
	 * 	@param	entity
	 * 			The entity that will collide with this bullet.
	 *	@post	If the given entity is instance of a Ship and this bullet is a child of that ship, the bullet will be reloaded to his parent and the bounces will be set to 0..
	 * 			| if (entity instanceof Ship) && (entity == getParent())
	 *			|		((Ship) entity).addBulletToLoaded(this)
	 *			|		setLoaded(true)
	 *			|		getWorld().removeEntity(this)
	 *			|		setWorld(null)
	 *			|		resetBounces()
	 *	@post	If the given entity is instance of a Ship and this bullet is not a child of that ship, the ship and bullet will be finalized.
	 *			|if (entity instanceof Ship) && (entity != getParent())
	 *			|		entity.finalize()
	 *			|		finalize()
	 *	@post	If the given entity is instance of MinorPlanet, the minor planet and bullet will be finalized.
	 *			| if (entity instanceof MinorPlanet)
	 *			|		entity.finalize()
	 *			|		finalize()
	 *	@post	If the entity is not instance of a MinorPlanet, Ship or Bullet, the entity will call his own collideWith method.
	 *			| if(!entity instanceof {MinorPlanet, Ship, Bullet})
	 *			| 		entity.collideWith(this)
	 */
	@Override
	public void collideWith(Entity entity) {
		if (entity instanceof Bullet) {
			entity.finalize();
			this.finalize();
		}
		else if (entity instanceof Ship) {
			if (entity == getParent()) {
				((Ship) entity).addBulletToLoaded(this);
				setLoaded(true);
				getWorld().removeEntity(this);
				setWorld(null);
				resetBounces();
				setXCoordinate(entity.getXCoordinate());
				setYCoordinate(entity.getYCoordinate());
			}
			else {
				entity.finalize();
				finalize();
			}	
		}
		else if (entity instanceof Asteroid) {
			entity.finalize();
			this.finalize();
		}
		else if (entity instanceof Planetoid) {
			entity.finalize();
			this.finalize();
		}
		else
			entity.collideWith(this);
	}
	
	/**
	 *  Changes the velocity of this bullet to reflect a collision against a boundary of the world.
	 *  Adds one to the number of bounces against a boundary this bullet has made. 
	 *  @post |	if (getWorld() == null)
	 * 		  |		//Do nothing
	 *  @post | new.getBounces() = getBounces() + 1
	 *  @see Implementation
	 */
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
