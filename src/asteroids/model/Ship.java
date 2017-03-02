package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;

/**
 *  A class of ships with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */
// TO DO List
// @Effect lezen voor setXVelocity en setYVelocity

public class Ship {
	
	/**
	 * 
	 * Initialize a new ship with given x and y coordinate.
	 * 
	 * @param 	xCoordinate 
	 * 			The X coordinate for this new ship.
	 * @param 	yCoordinate
	 * 			The Y coordinate for this new ship.
	 * @pre		
	 * @post   	The X coordinate of this new ship is equal to the given X coordinate.
	 *       	| new.getXCoordinate() == xCoordinate
	 * @post   	The Y coordinate of this new ship is equal to the given Y coordinate.
	 *       	| new.getYCoordinate() == yCoordinate
	 * @throws	IllegalArgumentException
	 * 			The given x coordinate is not a valid coordinate for a ship.
	 * 			| x < Double.MIN_VALUE || x >  Double.MAX_VALUE
	 * @throws	IllegalArgumentException
	 * 			The given y coordinate is not a valid coordinate for a ship.
	 * 			| y < Double.MIN_VALUE || y >  Double.MAX_VALUE
	 * @throws	IllegalArgumentException
	 * 			The given radius is not a valid radius for this ship.
	 * 			| ! isValidRadius(radius)
	 */
	public Ship (double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws IllegalArgumentException {
		setXCoordinate(x);
		setYCoordinate(y);
		setXVelocity(xVelocity);
		setYVelocity(yVelocity);
		setOrientation(orientation);
		if (! isValidRadius(radius)) 
			throw new IllegalArgumentException("Non-valid radius");
		else
			this.radius = radius;
	}
	
	
	/**
	 * Variable registering the X coordinate of this ship expressed in kilometres.
	 */
	private double x;
	
	/**
	 * Variable registering the Y coordinate of this ship expressed in kilometres.
	 */
	private double y;
	
	
	/**
	 * Return the X coordinate of this ship.
	 */
	@Basic
	public double getXCoordinate() {
		return this.x;
	}
	
	/**
	 * Return the Y coordinate of this ship.
	 */
	@Basic
	public double getYCoordinate() {
		return this.y;
	}
	
	/**
	 * 
	 * @param 	xCoordinate
	 * 			The new X coordinate for this ship.
	 * @post	The X coordinate of this ship is equal to the given X coordinate.
	 * @throws	IllegalArgumentException
	 * 			The given x coordinate is not a valid coordinate for a ship.
	 * 			| x < Double.MIN_VALUE || x >  Double.MAX_VALUE
	 */
	private void setXCoordinate(double x) throws IllegalArgumentException {
		if (x < Double.MIN_VALUE || x >  Double.MAX_VALUE)
			throw new IllegalArgumentException("Non-valid X coordinate");
		else
			this.x = x;
	}
	
	/**
	 * 
	 * @param 	yCoordinate
	 * 			The new Y coordinate for this ship.
	 * @post	The Y coordinate of this ship is equal to the given X coordinate.
	 * @throws	IllegalArgumentException
	 * 			The given y coordinate is not a valid coordinate for
	 * 		   	a ship.
	 * 			| y < Double.MIN_VALUE || y >  Double.MAX_VALUE
	 */
	private void setYCoordinate(double y) throws IllegalArgumentException {
		if (y < Double.MIN_VALUE || y >  Double.MAX_VALUE)
			throw new IllegalArgumentException("Non-valid Y coordinate");
		else
			this.y = y;
	}
	
	/**
	 * Variable registering the X velocity of this ship expressed in kilometres per second.
	 */
	private double xVelocity;
	
	/**
	 * Variable registering the Y velocity of this ship expressed in kilometres per second.
	 */
	private double yVelocity;
	
	/**
	 * Returns the X velocity of this ship.
	 */
	@Basic
	public double getXVelocity(){
		return this.xVelocity;
	}
	
	/**
	 * Returns the Y velocity of this ship.
	 */
	@Basic
	public double getYVelocity(){
		return this.yVelocity;
	}
	
	/**
	 * 
	 * @param 	xVelocity
	 * 			The new velocity of X for this ship.
	 * 
	 * @post	If xVelocity is a valid velocity for this ship, then the velocity X of this ship is equal to the given X velocity
	 * 			| if(isValidVelocity(xVelocity)
	 * 			| 	new.xVelocity = xVelocity
	 * 
	 * @post	If xVelocity is not a valid velocity for this ship and xVelocity is lower than velocityLowerBound, then the velocity X of this ship is equal to velocityLowerBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity < velocityLowerBound)
	 * 			| 	new.xVelocity = velocityLowerBound
	 * 
	 * @post	If xVelocity is not a valid velocity for this ship and xVelocity is higher than velocityUpperBound, then the velocity X of this ship is equal to velocityUpperBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity > velocityUpperBound)
	 * 			| 	new.xVelocity = velocityUpperBound
	 * 
	 */
	private void setXVelocity(double xVelocity){
		if (! isValidVelocity(xVelocity) && xVelocity < velocityLowerBound)
			this.xVelocity = velocityLowerBound;
		else if(! isValidVelocity(xVelocity) && xVelocity > velocityUpperBound)
			 this.xVelocity = velocityUpperBound;
		else
			this.xVelocity = xVelocity;
	}
	
	/**
	 * 
	 * @param 	yVelocity
	 * 			The new velocity of Y for this ship.
	 * 
	 * @post	If yVelocity is a valid velocity for this ship, then the velocity Y of this ship is equal to the given Y velocity
	 * 			| if(isValidVelocity(yVelocity)
	 * 			| 	new.yVelocity = yVelocity
	 * 
	 * @post	If yVelocity is not a valid velocity for this ship and yVelocity is lower than velocityLowerBound, then the velocity Y of this ship is equal to velocityLowerBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity < velocityLowerBound)
	 * 			| 	new.xVelocity = velocityLowerBound
	 * 
	 * @post	If yVelocity is not a valid velocity for this ship and yVelocity is higher than velocityUpperBound, then the velocity Y of this ship is equal to velocityUpperBound.
	 * 			| if(! isValidVelocity(yVelocity) && yVelocity > velocityUpperBound)
	 * 			| 	new.yVelocity = velocityUpperBound
	 * 
	 */
	private void setYVelocity(double yVelocity){
		if (! isValidVelocity(yVelocity) && yVelocity < velocityLowerBound)
			this.yVelocity = velocityLowerBound;
		else if(! isValidVelocity(yVelocity) && yVelocity > velocityUpperBound)
			 this.yVelocity = velocityUpperBound;
		else
			this.yVelocity = yVelocity;
	}
	
	private final double velocityLowerBound = 0;
	private final double velocityUpperBound = 300000;
	
	/**
	 * 
	 * @param 	velocity
	 * 			The given velocity to check.
	 * @return	True if and only if the velocity is greater than velocityLowerBound and lower than velocityLowerBound.
	 * 			| result == (velocity > this.velocityLowerBound && velocity < this.velocityUpperBound)
	 */
	private boolean isValidVelocity(double velocity) {
		return ( velocity > this.velocityLowerBound && velocity < this.velocityUpperBound );
	}
	
	/**
	 * Variable registering the orientation of this ship expressed in radians.
	 */
	private double orientation;
	
	/**
	 * 
	 * @param 	orientation
	 * 			The given orientation to check
	 * @return	True if and only if the orientation is greater than or equal to 0 and lower than pi.
	 * 			| result == orientation >= 0 && orientation < Math.PI
	 */
	public boolean isValidOrientation(double orientation) {
		return (orientation >= 0 && orientation < Math.PI);
	}
	
	/**
	 * 
	 * @param 	orientation
	 * 			The new orientation for this ship.
	 * @pre		The orientation must be a valid angle in radians.
	 * 			| isValidOrientation(orientation)
	 * @post	The new orientation of the ship is equal to the given argument orientation.
	 * 			| new.orientation = orientation
	 */
	private void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * This method returns the orientation of this ship.
	 */
	@Basic
	public double getOrientation() {
		return this.orientation;
	}
	
	private final double radius;
	private final double radiusLowerBound = 10;
	
	/**
	 * 
	 * @param 	radius
	 * 			The given radius to check.
	 * @return	True if and only if the velocity is greater than the minimum value specified for a ship's radius.
	 * 			| result == radius > this.radiusLowerBound
	 */
	private boolean isValidRadius(double radius) {
		return ( radius >= this.radiusLowerBound );
	}
	
	/**
	 * This method returns the radius of this ship.
	 */
	@Basic
	private double getRadius() {
		return this.radius;
	}
	
}
