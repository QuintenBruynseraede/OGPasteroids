package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;


/**
 * A class of bullets with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */

public class Bullet {
	 
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
				| //TODO formal definition
	 * @post   	The X coordinate of this new bullet is equal to the given X coordinate.
	 *       	| new.getXCoordinate() == xCoordinate
	 * @post   	The Y coordinate of this new bullet is equal to the given Y coordinate.
	 *       	| new.getYCoordinate() == yCoordinate
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
	public Bullet (double x, double y, double xVelocity, double yVelocity, double radius, Ship ship, World world) throws IllegalArgumentException {
		setXCoordinate(x);
		setYCoordinate(y);
		setXVelocity(xVelocity);
		setYVelocity(yVelocity);
		if (! isValidRadius(radius)) 
			throw new IllegalArgumentException("Non-valid radius");
		else
			this.radius = radius;
		setWorld(world);
		setParent(ship);
	}
	
	/**
	 * Variable registering the X coordinate of this bullet expressed in kilometres.
	 */
	private double x;
	
	/**
	 * Variable registering the Y coordinate of this bullet expressed in kilometres.
	 */
	private double y;
	
	
	/**
	 * Return the X coordinate of this bullet expressed in kilometres.
	 */
	@Basic
	public double getXCoordinate() {
		return this.x;
	}
	
	/**
	 * Return the Y coordinate of this bullet expressed in kilometres.
	 */
	@Basic
	public double getYCoordinate() {
		return this.y;
	}
	
	/**
	 * 
	 * @param 	X
	 * 			The new X coordinate for this bullet.
	 * @post	The X coordinate of this bullet is equal to the given X coordinate.
	 * @throws	IllegalArgumentException
	 * 			The given x coordinate is not a valid coordinate for a bullet.
	 * 			| Double.isNaN(x) || Double.isInfinite(x)
	 */
	void setXCoordinate(double x) throws IllegalArgumentException {
		if (Double.isNaN(x) || Double.isInfinite(x))
			throw new IllegalArgumentException("Non valid x");
		this.x = x;			
	}
	
	/**
	 * 
	 * @param 	yCoordinate
	 * 			The new Y coordinate for this bullet.
	 * @post	The Y coordinate of this bullet is equal to the given X coordinate.
	 * @throws	IllegalArgumentException
	 * 			The given y coordinate is not a valid coordinate for a bullet.
	 * 			| Double.isNaN(y) || Double.isInfinite(y)
	 */
	void setYCoordinate(double y) throws IllegalArgumentException {
		if (Double.isNaN(y) || Double.isInfinite(y))
			throw new IllegalArgumentException("Non valid y");
		this.y = y;
		}
	
	/**
	 * Variable registering the X velocity of this bullet expressed in kilometres per second.
	 */
	private double xVelocity;
	
	/**
	 * Variable registering the Y velocity of this bullet expressed in kilometres per second.
	 */
	private double yVelocity;
	
	/**
	 * Returns the X velocity of this bullet.
	 */
	@Basic
	public double getXVelocity(){
		return this.xVelocity;
	}
	
	/**
	 * Returns the Y velocity of this bullet.
	 */
	@Basic
	public double getYVelocity(){
		return this.yVelocity;
	}
	
	/**
	 * 
	 * @param 	xVelocity
	 * 			The new velocity of X for this bullet.
	 * 
	 * @post	If xVelocity is a valid velocity for this bullet, then the velocity X of this bullet is equal to the given X velocity
	 * 			| if(isValidVelocity(xVelocity)
	 * 			| 	new.xVelocity = xVelocity
	 * 
	 * @post	If xVelocity is not a valid velocity for this bullet and xVelocity is lower than velocityLowerBound, then the velocity X of this bullet is equal to velocityLowerBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity < velocityLowerBound)
	 * 			| 	new.xVelocity = velocityLowerBound
	 * 
	 * @post	If xVelocity is not a valid velocity for this bullet and xVelocity is higher than velocityUpperBound, then the velocity X of this bullet is equal to velocityUpperBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity > velocityUpperBound)
	 * 			| 	new.xVelocity = velocityUpperBound
	 * 
	 */
	private void setXVelocity(double xVelocity){
		if (! isValidVelocity(xVelocity) && xVelocity < VELOCITYLOWERBOUND)
			this.xVelocity = VELOCITYLOWERBOUND;
		else if(! isValidVelocity(xVelocity) && xVelocity > VELOCITYUPPERBOUND)
			this.xVelocity = VELOCITYUPPERBOUND;
		else
			this.xVelocity = xVelocity;
	}
	
	/**
	 * 
	 * @param 	yVelocity
	 * 			The new velocity of Y for this bullet.
	 * 
	 * @post	If yVelocity is a valid velocity for this bullet, then the velocity Y of this bullet is equal to the given Y velocity
	 * 			| if(isValidVelocity(yVelocity)
	 * 			| 	new.yVelocity = yVelocity
	 * 
	 * @post	If yVelocity is not a valid velocity for this bullet and yVelocity is lower than velocityLowerBound, then the velocity Y of this bullet is equal to velocityLowerBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity < velocityLowerBound)
	 * 			| 	new.xVelocity = velocityLowerBound
	 * 
	 * @post	If yVelocity is not a valid velocity for this bullet and yVelocity is higher than velocityUpperBound, then the velocity Y of this bullet is equal to velocityUpperBound.
	 * 			| if(! isValidVelocity(yVelocity) && yVelocity > velocityUpperBound)
	 * 			| 	new.yVelocity = velocityUpperBound
	 * 
	 */
	private void setYVelocity(double yVelocity){
		if (! isValidVelocity(yVelocity) && yVelocity < VELOCITYLOWERBOUND)
			this.yVelocity = VELOCITYLOWERBOUND;
		else if(! isValidVelocity(yVelocity) && yVelocity > VELOCITYUPPERBOUND)
			this.yVelocity = VELOCITYUPPERBOUND;
		else
			this.yVelocity = yVelocity;
	}
	
	private final static double VELOCITYLOWERBOUND = -300000;
	private final static double VELOCITYUPPERBOUND = 300000;
	
	/**
	 * 
	 * @param 	velocity
	 * 			The given velocity to check.
	 * @return	True if and only if the velocity is greater than velocityLowerBound and lower than velocityLowerBound.
	 * 			| result == (velocity > this.velocityLowerBound && velocity < this.velocityUpperBound)
	 */
	private boolean isValidVelocity(double velocity) {
		return ( velocity > VELOCITYLOWERBOUND && velocity < VELOCITYUPPERBOUND );
	}
	
	/**
	 * This method returns the minimum velocity for this bullet.
	 */
	@Basic
	public static double getVelocityLowerBound() {
		return VELOCITYLOWERBOUND;
	}
	
	/**
	 * This method returns the minimum velocity for this bullet.
	 */
	@Basic
	public static double getVelocityUpperBound() {
		return VELOCITYUPPERBOUND;
	}
	
	/**
	 * 
	 * Return the total velocity as a function of a velocity in the X and Y direction
	 * @param 	xVelocity
	 * 			The velocity in the X direction.
	 * @param 	yVelocity
	 * 			The velocity in the Y direction
	 * @return  The total velocity of the bullet 
	 * 			sqrt(xVelocity * xVelocity + yVelocity * yVelocity)
	 */
	public double getVelocity(double xVelocity, double yVelocity) {
		return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
	}
	
	/**
	 * Variable registering the radius of this bullet.
	 */
	private final double radius;
	
	/**
	 * Variable registering the radius lower bound of this bullet.
	 */
	private final static double RADIUSLOWERBOUND = 1;
	
	/**
	 * @param 	radius
	 * 			The given radius to check.
	 * @return	True if and only if the velocity is greater than the minimum value specified for a bullet's radius.
	 * 			| result == radius > this.radiusLowerBound
	 */
	private boolean isValidRadius(double radius) {
		return ( radius >= Bullet.RADIUSLOWERBOUND );
	}
	
	/**
	 * This method returns the radius of this bullet.
	 */
	@Basic
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * This method returns the minimum value for this bullet's radius.
	 */
	@Basic
	public static double getRadiusLowerBound() {
		return RADIUSLOWERBOUND;
	}

	/**
	 * variable registering the mass of a bullet in kilogrammes. The mass can be calculated as m = (4/3)pi*radius^3*massDensity
	 */
	private double mass;

	/**
	 * variable registering the mass density of a bullet in kilogrammes 
	 */
	private double massDensity;
	
	/**
	 * Returns the mass of this bullet not including any entities it is carrying.
	 */
	@Basic
	public double getMassBullet(){
		return this.mass;
	}
	
	/**
	 * Returns the mass density of this bullet.
	 */
	@Basic
	public double getmassDensity(){
		return this.massDensity;
	}
	
	/**
	 * 
	 * @param 	massDensity
	 * 			The new mass density for this bullet.
	 * @post	This.massDensity = max(minimum, massDensity)
	 * @note	The minimum mass density for a bullet is defined as 1.42E12
	 */
	void setMassDensity(double massDensity) {
		if (this.getMassBullet() >= (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * massDensity)
			this.massDensity = massDensity;
		else
			this.massDensity = 1420000000000.0;
	}

	/**
	 * Variable registering the world this ship is bound to.
	 */
	private World world = null; 
	
	
	/**
	 * Sets the world this ship is associated with. 
	 * @param 	world
	 * 			| new.getWorld = this.world
	 * @throws 	IllegalArgumentException
	 * 			| world == null
	 */

	public void setWorld(World world) throws IllegalArgumentException {
		if (world == null) {
			throw new IllegalArgumentException("Setting a ship's world to a null value.");
		}
		this.world = world;
	}
	
	/**
	 * Returns the world this ship is currently associated with.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Variable registering the world this ship is bound to.
	 */
	private Ship parent = null; 
		
	
	/**
	 * Sets the ship this bullet is loaded on or fired by. 
	 * @param 	ship
	 * 			| new.getParent() = this.getParent()
	 * @throws 	IllegalArgumentException
	 * 			| ship == null
	 */
	
	public void setParent(Ship ship) throws IllegalArgumentException {
		if (ship == null) {
			throw new IllegalArgumentException("Setting a bullet's parent to a null value.");
		}
		this.parent = ship;
	}
	
	/**
	 * Returns the ship this bullet is loaded on or fired by.
	 */
	@Basic
	public Ship getParent() {
		return this.parent;
	}
	
}
