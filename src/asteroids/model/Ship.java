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
// Thrust aanpassen fout

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
	 * Return the total velocity as a function of a velocity in the X and Y direction
	 * @param 	xVelocity
	 * 		  	The velocity in the X direction.
	 * @param 	yVelocity
	 * 	      	The velocity in the Y direction
	 * @return  The total velocity of the ship 
	 * 			sqrt(xVelocity * xVelocity + yVelocity * yVelocity)
	 */
	public double getVelocity(double xVelocity, double yVelocity) {
		return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
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
	 * 			| result == orientation >= 0 && orientation < 2 * Math.PI
	 */
	public boolean isValidOrientation(double orientation) {
		return (orientation >= 0 && orientation < 2 * Math.PI);
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
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
	
	/**
	 * Variable registering the radius lower bound of this ship.
	 */
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
	
	/**
	 * Move the ship depending on ship's position, velocity and a given time duration.
	 * @param 	time
	 * 			The given time to move.
	 * @post	The X and Y coordinates are updated according to the ship's respective xVelocity and yVelocity.
	 * 			| for (int t = 0; t < time; t++) 
	 * 			| 	this.x += xVelocity;
	 * 			| 	this.y += yVelocity;
	 * @throws 	IllegalArgumentException
	 * 			The given time is not positive.
	 * 			| time < 0
	 */
	public void move(double time) throws IllegalArgumentException {
		if (time < 0)
			throw new IllegalArgumentException("Argument time must be positive");
		else {
			for (int t = 0; t < time; t++) {
				this.x += xVelocity;
				this.y += yVelocity;
			}
		}
	}
	
	/**
	 * Add a given angle to the ship's orientation.
	 * @pre		The ship must be able to turn the given angle whilst ensuring the ship's new orientation is still 
	 * 			a valid orientation.
	 * 			isValidOrientation(this.orientation + angle)
	 * @param 	angle
	 * 			The given angle to turn.
	 */
	public void turn(double angle) {
		assert isValidOrientation(this.orientation + angle);
		this.orientation += angle;
	}
	/**
	 * Change the ship's velocity based on the current velocity and a specified amount of thrust.
	 * @param 	amount	
	 * 			The amount of thrust the ship generates.
	 * @post 	If the specified amount to thrust is an illegal value, that is a negative number, no thrust is generated
	 * 			| if ( amount < 0 )
	 *  		| 		this.thrust(0)
	 * @post 	If the specified amount of thrust would result in a speed greater than allowed for this spaceship,
	 * 			an adjusted amount of thrust is generated to ensure the ship's x and y velocity are maximised yet still valid.
	 * 			| ==========================================
	 * 
	 * @post	If a valid amount of thrust is specified, the ship's x and y velocity are updated accordingly
	 * 			| new.xVelocity = this.xVelocity + amount * Math.cos(this.orientation)
	 * 			| new.yVelocity = this.yVelocity + amount * Math.sin(this.orientation)
	 * 
	 * 
	 */
	public void thrust(double amount) {
		if ( amount <= 0 )
			return; 
		else if (getVelocity(this.getXVelocity() + amount * Math.cos(this.orientation), this.getYVelocity() + amount * Math.cos(this.orientation)) > velocityUpperBound) {
			//NOT CORRECT
			this.xVelocity = this.xVelocity + amount * Math.cos(this.orientation);
			this.yVelocity = this.yVelocity + amount * Math.sin(this.orientation);
			
			final double factor = ( this.velocityUpperBound * this.velocityUpperBound) / (this.xVelocity * this.xVelocity + this.yVelocity * this.yVelocity);
			this.xVelocity *= factor;
			this.yVelocity *= factor;
			
		}
		else {
			this.xVelocity = this.xVelocity + amount * Math.cos(this.orientation);
			this.yVelocity = this.yVelocity + amount * Math.sin(this.orientation);
		}
	
	}
	
	/**
	 * 			Checks the distance in km between two ships.
	 * @param 	ship1
	 * 			The ship from which this method checks the distance.
	 * @param 	ship2
	 * 			The ship to which this method checks the distance.
	 * @return  The distance between the two ships provided as arguments.
	 * 			| result == sqrt( (ship1.getXCoordinate()-ship2.getXCoordinate())^2 + (ship1.getYCoordinate()-ship2.getYCoordinate())^2 ) - (ship1.getRadius() + ship2.getRadius());
	 * @return  If the method checks the distance between two ships represented by the same object, it returns 0.
	 * 			| if ( ship1 == ship2 )
	 * 			| 	return 0; 
	 * @note	As a result of the provided formula, the distance between two overlapping ships shall be negative.
	 * @note	To make getDistanceBetween a more distinct class specific method, getDistanceBetween is an overloaded function.
	 * 			When only one ship is provided as an argument, the distance between the ship that calls this method and the ship that is
	 * 			provided as an argument is returned.
	 * @return	The distance between the prime object (an instance of the class Ship) and the ship provided as an argument.
	 * 			| result == getDistanceBetween(this, otherShip);
	 */
	public double getDistanceBetween(Ship ship1, Ship ship2) {
		if ( ship1 == ship2 )
			return 0;
		return Math.sqrt( Math.pow(ship1.getXCoordinate()-ship2.getXCoordinate(), 2) + Math.pow(ship1.getYCoordinate()-ship2.getYCoordinate(), 2) ) - (ship1.getRadius() + ship2.getRadius());
	}
	
	public double getDistanceBetween(Ship otherShip) {
		return getDistanceBetween(this, otherShip);
	}	
	
	/**
	 * This method checks whether two ships overlap.
	 * @param 	ship1
	 * 			A given ship to check whether ship1 and ship2 overlap.
	 * @param 	ship2
	 * 			A given ship to check whether ship1 and ship2 overlap.
	 * @return	True if and only if the distance between ship1 and ship2 is greater than 0.
	 * 			| result == getDistanceBetween(ship1, ship2) < 0
	 */
	public boolean overlap(Ship ship1, Ship ship2) {
		if ( getDistanceBetween(ship1, ship2) <= 0 )
			return true;
		else
			return false;
	}
	
	public double getTimeToCollision(Ship ship1, Ship ship2) {
		return;
	}
}
