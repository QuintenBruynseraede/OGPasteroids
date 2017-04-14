package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;


/**
 * A class of bullets with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */

public class Bullet extends Entity {
	 
	/**
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
	 * 			
	 * @post   	The X coordinate of this new bullet is equal to the given X coordinate.
	 *       	| new.getXCoordinate() == xCoordinate
	 * @post   	The Y coordinate of this new bullet is equal to the given Y coordinate.
	 *       	| new.getYCoordinate() == yCoordinate
	 * @post   	The x velocity of this new bullet is equal to the given X velocity.
	 *       	| new.getXVelocity() == xVelocity
	 * @post   	The Y velocity of this new bullet is equal to the given Y velocity.
	 *       	| new.getYVelocity() == yVelocity
	 * @post	The radius of this new bullet is set to the given radius if valid, or a predefined lower bound otherwise.
	 * 			| new.getRadius() == max(Bullet.RADIUSLOWERBOUND, radius) 
	 * @post	The mass for this bullet is set.
	 * 			| new.mass = (4/3)*PI*radius^3*MASSDENSITY
	 * @post	The parent of this new bullet is equal to the given ship.
	 * 			| new.parent = parent
	 * 
	 */
	public Bullet (double x, double y, double xVelocity, double yVelocity, double radius, Ship parent) {
		super(x, y, xVelocity, yVelocity, radius);
		this.mass = (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Bullet.MASSDENSITY;
		this.setParent(parent);	
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
	 * variable registering the mass density of a bullet in kilogrammes 
	 */
	private static final double MASSDENSITY = 7.8E12;
	
	/**
	 * Returns the mass density of this bullet.
	 */
	@Basic
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
	 * 			| new.getParent() = this.getParent()
	 */
	
	public void setParent(Ship ship) {
		if (! (this.getParent() == null))
			this.getParent().removeBullet(this);
		if (ship == null)
			this.isLoaded = false;
		else
			this.isLoaded = true;
		this.parent = ship;
		
	}
	
	/**
	 * Return this ship's parent object.
	 */
	
	@Basic
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
		if (this.getBounces() == MAXBOUNCES-1)
			this.finalize();
		this.bounces++;
	}
	
	/**
	 * Returns whether this bullet and another bullet are loaded and loaded on the same ship.
	 * @param 	bullet
	 * 		  	The second bullet
	 * @return	result == ((this.isBulletLoaded() && bullet.isBulletLoaded()) && (this.getParent() == bullet.getParent())
	 */
	public boolean isLoadedOnSameShipAs(Bullet bullet) {
		if ((this.isBulletLoaded() && bullet.isBulletLoaded()) && (this.getParent() == bullet.getParent())) 
			return true;
		return false;
	}
	
	/**
	 * Variable representing whether this bullet has been loaded on a ship.
	 */
	public boolean isLoaded = false;
	
	/**
	 * Returns whether a bullet has been loaded on a ship.
	 */
	@Basic
	public boolean isBulletLoaded() {
		return this.isLoaded;
	}

	/**
	 * Variable representing whether this bullet has been finalized already.	
	 */
	private boolean finalized = false;
	
	/**
	 * Finalizes the bullet, preparing it to be removed by the garbage collector.
	 * @post	If this bullet has a parent, make it remove it from its list of bullets
	 * @post	If this bullet has been added to a world, make the world remove it from its list of bullets
	 * @post	| new.isTerminated() == true
	 */
	public void finalize() {
		
		if (this.getParent() != null)
			this.getParent().removeBullet(this);
		if (this.getWorld() != null)
			this.getWorld().removeBullet(this);
		this.finalized = true;
	}

	/**
	 * Returns whether this bullet has been terminated
	 */
	@Basic
	public boolean isTerminated() {
		return this.finalized;
	}
	
}
