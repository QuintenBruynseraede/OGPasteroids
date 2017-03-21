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
	public Bullet (double x, double y, double xVelocity, double yVelocity, double radius, Ship parent, World world) throws IllegalArgumentException {
		setXCoordinate(x);
		setYCoordinate(y);
		setXVelocity(xVelocity);
		setYVelocity(yVelocity);
		
		if (! isValidRadius(radius)) 
			throw new IllegalArgumentException("Non-valid radius");
		else
			this.radius = radius;
		
		if ((world == null && parent == null)  || (world != null && parent != null)) {
			throw new IllegalArgumentException("Either world or ship must be null.");
		}
		
		if (parent == null) 
			setWorld(world);
		else if (world == null)
			setParent(parent);
		
		
		this.mass = (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Bullet.MASSDENSITY;
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
	private static final double MASSDENSITY = 7.8E12;
	
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
		return Bullet.MASSDENSITY;
	}

	/**
	 * Variable registering the world this ship is bound to.
	 */
	private World world = null; 
	
	
	/**
	 * Sets the world this ship is associated with. 
	 * @param 	world
	 * 			| new.getWorld = this.world
	 * @post	| this.getParent() == null
	 * @post	| this.getWorld() == world
	 * @throws 	IllegalArgumentException
	 * 			| world == null
	 */

	public void setWorld(World world) throws IllegalArgumentException {
		if (world == null) {
			throw new IllegalArgumentException("Setting a ship's world to a null value.");
		}
		
		this.getWorld().removeBullet(this);
		this.parent = null;
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
	
	public void setParent(Ship ship) throws IllegalArgumentException {
		if (ship == null) {
			throw new IllegalArgumentException("Setting a bullet's parent to a null value.");
		}
		
		this.getParent().removeBullet(this);
		
		this.world = null;
		this.parent = ship;
	}
	
	/**
	 * Returns the ship this bullet is loaded on or fired by.
	 */
	@Basic
	public Ship getParent() {
		return this.parent;
	}

	/**
	 * Returns the time to a collision between the bullet invoking the method and a ship.
	 * @param 	s
	 * @return	The time to a collision based on the positions and orientations of the bullet and the ship.
	 * 			| result ==  {deltaT | (this.move(deltaT) => this.overlap(b2)) && (b2.move(deltaT) => b2.overlap(this))}
	 * @throws 	IllegalArgumentException
	 * 			The ship to check a collision against is a null object.
	 */
	
	public double getTimeToCollision(Ship s) throws IllegalArgumentException {
		if (s == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		double deltaVX = this.getXVelocity() - s.getXVelocity();
		double deltaVY = this.getYVelocity() - s.getYVelocity();
		double deltaX = this.getXCoordinate() - s.getXCoordinate();
		double deltaY = this.getYCoordinate() - s.getYCoordinate();
		
		if ((deltaVX * deltaX) + (deltaVY * deltaY) >= 0)
			return Double.POSITIVE_INFINITY;
		
		double radius1 = this.getRadius();
		double radius2 = s.getRadius();
			
		double part1 = deltaVX * deltaX + deltaVY * deltaY;
		double part2 = deltaVX * deltaVX + deltaVY * deltaVY;
		double part3 = deltaX * deltaX + deltaY * deltaY - (radius1 + radius2) * (radius1 + radius2);
		double d = part1 * part1 - part2 * part3;
		
		if (part2 == 0) {
			return Double.POSITIVE_INFINITY;
		}
		if (d <= 0)
			return Double.POSITIVE_INFINITY;
		return -( (part1 + Math.sqrt(d)) / (part2) );
	}

	/**
	 * Returns the time to a collision between the bullet invoking the method and another bullet.
	 * @param 	b2
	 * @return	The time to a collision based on the bullets' position and orientation.
	 * 			| result ==  {deltaT | (this.move(deltaT) => this.overlap(b2)) && (b2.move(deltaT) => b2.overlap(this))}
	 * @throws 	IllegalArgumentException
	 * 			The bullet to check a collision against is a null object.
	 */
	public double getTimeToCollision(Bullet b2) throws IllegalArgumentException {
		if (b2 == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		double deltaVX = this.getXVelocity() - b2.getXVelocity();
		double deltaVY = this.getYVelocity() - b2.getYVelocity();
		double deltaX = this.getXCoordinate() - b2.getXCoordinate();
		double deltaY = this.getYCoordinate() - b2.getYCoordinate();
		
		if ((deltaVX * deltaX) + (deltaVY * deltaY) >= 0)
			return Double.POSITIVE_INFINITY;
		
		double radius1 = this.getRadius();
		double radius2 = b2.getRadius();
			
		double part1 = deltaVX * deltaX + deltaVY * deltaY;
		double part2 = deltaVX * deltaVX + deltaVY * deltaVY;
		double part3 = deltaX * deltaX + deltaY * deltaY - (radius1 + radius2) * (radius1 + radius2);
		double d = part1 * part1 - part2 * part3;
		
		if (part2 == 0) {
			return Double.POSITIVE_INFINITY;
		}
		if (d <= 0)
			return Double.POSITIVE_INFINITY;
		return -( (part1 + Math.sqrt(d)) / (part2) );
	}
	
	/**
	 * Move the bullet depending on bullet's position, velocity and a given time duration.
	 * @param 	time
	 * 			The given time to move.
	 * @post	The X and Y coordinates are updated according to the bullet's respective xVelocity and yVelocity.
	 * 			| new.x = this.x + time * this.xVelocity
	 * 			| new.y = this.y + time * this.yVelocity
	 * @throws 	IllegalArgumentException
	 * 			The given time is not positive.
	 * 			| time < 0
	 */
	public void move(double time) throws IllegalArgumentException {
		if (time < 0)
			throw new IllegalArgumentException("Argument time must be positive");
		else {
			setXCoordinate(this.getXCoordinate() + time * this.getXVelocity());
			setYCoordinate(this.getYCoordinate() + time * this.getYVelocity());
		}
	}
	
	public final static int LEFT = 1;
	public final static int TOP = 2;
	public final static int RIGHT = 3;
	public final static int BOTTOM = 4;
	
	public double getTimeToCollision(int boundary) {
		if (boundary == LEFT)
			return this.getXCoordinate()/this.getXVelocity();
		else if (boundary == TOP)
			return (World.HEIGHTUPPERBOUND-this.getYCoordinate())/this.getYVelocity();
		else if (boundary == RIGHT)
			return (World.WIDTHUPPERBOUND-this.getXCoordinate())/this.getXVelocity();
		else if (boundary == BOTTOM)
			return this.getYCoordinate()/this.getYVelocity();
		else
			return Double.POSITIVE_INFINITY;
	}
}
