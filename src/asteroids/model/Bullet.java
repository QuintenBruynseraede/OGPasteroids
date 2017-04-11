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
	 * @param 	xCoordinate 
	 * 			The X coordinate for this new bullet.
	 * @param 	yCoordinate
	 * 			The Y coordinate for this new bullet.
	 * @param	xVelocity
	 * 			The Velocity in the X direction for this new bullet.
	 * @param	yVelocity
	 * 			The Velocity in the Y direction for this new bullet.
	 * @param	radius
	 * 			The radius for this new bullet.
	 * @param	ship
	 *			The ship this bullet is carried by
	 * @param 	world
	 * 			The world this bullet is held by			
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
	 * @post	The orientation of this bullet is set to the given orientation.
	 *			| new.orientation == this.orientation
	 * @post	The massdensity for this bullet is set.
	 * 			| new.massDensity == Bullet.MASSDENSITY
	 * @post	The mass for this bullet is set.
	 * 			| new.mass = (4/3)*PI*radius^3*MASSDENSITY
	 * @post	The world for this bullet is set.
	 * 			| new.getWorld() == world;
	 * @post	The ship which is carrying this bullet is set.
	 * 			| new.getParent() == ship;
	 * @note	A bullet can only be associated with a bullet or a world. If the bullet is still being carried by a ship,
	 * 			world should be null. If the bullet is flying independently, ship should be null. Other cases are in theory
	 * 			not possible.
	 * @throws	IllegalArgumentException
	 * 			The given x coordinate is not a valid coordinate for a bullet.
	 * 			| Double.isNaN(x) || Double.isInfinity(x)
	 * @throws	IllegalArgumentException
	 * 			The given y coordinate is not a valid coordinate for a bullet.
	 * 			| Double.isNaN(y) || Double.isInfinity(y)
	 * @throws	IllegalArgumentException
	 * 			The given radius is not a valid radius for this bullet.
	 * 			| ! isValidRadius(radius)
	 * 
	 */
	public Bullet (double x, double y, double xVelocity, double yVelocity, double radius, Ship parent) throws IllegalArgumentException {
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
	public double getmassDensity(){
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
	 * @throws 	IllegalArgumentException
	 * 			| ship == null
	 */
	
	public void setParent(Ship ship) {
		this.getParent().removeBullet(this);
		this.isLoaded = true;
		this.setWorld(null);
		this.parent = ship;
	}
	
	/**
	 */
	@Basic
	public Ship getParent() {
		return this.parent;
	}

	private int bounces = 0;
	private final static int MAXBOUNCES = 3;
	
	public int getBounces() {
		return this.bounces;
	}
	
	public void addBounce() {
		this.bounces++;
	}
	
	public boolean isLoaded = false;
	
	public boolean isBulletLoaded() {
		return this.isLoaded;
	}
	
	public void finalize() {
		this.getParent().removeBullet(this);
		this.getWorld().removeBullet(this);
	}
	
}
