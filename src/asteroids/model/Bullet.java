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
	public Bullet (double x, double y, double xVelocity, double yVelocity, double radius, Ship parent, World world) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		if (world == null | parent == null) {
			throw new IllegalArgumentException("Assigning property to reference of null object.");
		}
		
		if (parent == null) 
			setWorld(world);
		else if (world == null)
			setParent(parent);
		
		
		this.mass = (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Bullet.MASSDENSITY;
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

	public void setWorld(World world) {
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
	
	public void setParent(Ship ship) {
		this.getParent().removeBullet(this);
		
		this.world = null;
		this.parent = ship;
	}
	
	/**
	 */
	@Basic
	public Ship getParent() {
		return this.parent;
	}


	
//	/**
//	 * Returns the time to a collision between the bullet invoking the method and another bullet.
//	 * @param 	b2
//	 * @return	The time to a collision based on the bullets' position and orientation.
//	 * 			| result ==  {deltaT | (this.move(deltaT) => this.overlap(b2)) && (b2.move(deltaT) => b2.overlap(this))}
//	 * @throws 	IllegalArgumentException
//	 * 			The bullet to check a collision against is a null object.
//	 */
//	public double getTimeToCollision(Bullet b2) throws IllegalArgumentException {
//		if (b2 == null) 
//			throw new IllegalArgumentException("Invalid argument object (null).");
//		
//		double deltaVX = this.getXVelocity() - b2.getXVelocity();
//		double deltaVY = this.getYVelocity() - b2.getYVelocity();
//		double deltaX = this.getXCoordinate() - b2.getXCoordinate();
//		double deltaY = this.getYCoordinate() - b2.getYCoordinate();
//		
//		if ((deltaVX * deltaX) + (deltaVY * deltaY) >= 0)
//			return Double.POSITIVE_INFINITY;
//		
//		double radius1 = this.getRadius();
//		double radius2 = b2.getRadius();
//			
//		double part1 = deltaVX * deltaX + deltaVY * deltaY;
//		double part2 = deltaVX * deltaVX + deltaVY * deltaVY;
//		double part3 = deltaX * deltaX + deltaY * deltaY - (radius1 + radius2) * (radius1 + radius2);
//		double d = part1 * part1 - part2 * part3;
//		
//		if (part2 == 0) {
//			return Double.POSITIVE_INFINITY;
//		}
//		if (d <= 0)
//			return Double.POSITIVE_INFINITY;
//		return -( (part1 + Math.sqrt(d)) / (part2) );
//	}
//	
//	/**
//	 * Move the bullet depending on bullet's position, velocity and a given time duration.
//	 * @param 	time
//	 * 			The given time to move.
//	 * @post	The X and Y coordinates are updated according to the bullet's respective xVelocity and yVelocity.
//	 * 			| new.x = this.x + time * this.xVelocity
//	 * 			| new.y = this.y + time * this.yVelocity
//	 * @throws 	IllegalArgumentException
//	 * 			The given time is not positive.
//	 * 			| time < 0
//	 */
//	public void move(double time) throws IllegalArgumentException {
//		if (time < 0)
//			throw new IllegalArgumentException("Argument time must be positive");
//		else {
//			setXCoordinate(this.getXCoordinate() + time * this.getXVelocity());
//			setYCoordinate(this.getYCoordinate() + time * this.getYVelocity());
//		}
//	}
//	
//	public final static int LEFT = 1;
//	public final static int TOP = 2;
//	public final static int RIGHT = 3;
//	public final static int BOTTOM = 4;
//	
//	public double getTimeToCollision(int boundary) {
//		if (boundary == LEFT) {
//			if (this.getXVelocity() == 0)
//				return Double.POSITIVE_INFINITY;
//			return this.getXCoordinate()/this.getXVelocity();
//		}
//		
//		else if (boundary == TOP) {
//			if (this.getYVelocity() == 0)
//				return Double.POSITIVE_INFINITY;
//			return (World.HEIGHTUPPERBOUND-this.getYCoordinate())/this.getYVelocity();
//			
//		}
//		
//		else if (boundary == RIGHT) {
//			if (this.getXVelocity() == 0)
//				return Double.POSITIVE_INFINITY;
//			return (World.WIDTHUPPERBOUND-this.getXCoordinate())/this.getXVelocity();
//		}
//		
//		else if (boundary == BOTTOM) {
//			if (this.getYVelocity() == 0)
//				return Double.POSITIVE_INFINITY;
//			return this.getYCoordinate()/this.getYVelocity();
//		}
//		
//		else
//			return Double.POSITIVE_INFINITY;
//	}
//	
	public void finalize() {
		this.getParent().removeBullet(this);
		this.getWorld().removeBullet(this);
	}
	
}
