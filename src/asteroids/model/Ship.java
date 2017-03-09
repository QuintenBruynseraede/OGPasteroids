package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;

/**
 *  A class of ships with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */

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
	
	private final static double velocityLowerBound = -300000;
	private final static double velocityUpperBound = 300000;
	
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
	 * This method returns the minimum velocity for this ship.
	 */
	@Basic
	public static double getVelocityLowerBound() {
		return velocityLowerBound;
	}
	
	/**
	 * This method returns the minimum velocity for this ship.
	 */
	@Basic
	public static double getVelocityUpperBound() {
		return velocityUpperBound;
	}
	
	/**
	 * Return the total velocity as a function of a velocity in the X and Y direction
	 * @param 	xVelocity
	 * 			The velocity in the X direction.
	 * @param 	yVelocity
	 * 			The velocity in the Y direction
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
	private final static double radiusLowerBound = 10;
	
	/**
	 * 
	 * @param 	radius
	 * 			The given radius to check.
	 * @return	True if and only if the velocity is greater than the minimum value specified for a ship's radius.
	 * 			| result == radius > this.radiusLowerBound
	 */
	private boolean isValidRadius(double radius) {
		return ( radius >= Ship.radiusLowerBound );
	}
	
	/**
	 * This method returns the radius of this ship.
	 */
	@Basic
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * This method returns the minimum value for this ship's radius.
	 */
	@Basic
	public static double getRadiusLowerBound() {
		return radiusLowerBound;
	}
	
	/**
	 * Move the ship depending on ship's position, velocity and a given time duration.
	 * @param 	time
	 * 			The given time to move.
	 * @post	The X and Y coordinates are updated according to the ship's respective xVelocity and yVelocity.
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
	 * 			| if (totalVelocity > velocityUpperBound)
	 * 			|		new.xVelocity = this.xVelocity + amount * Math.cos(this.orientation)
	 * 			| 		new.yVelocity = this.yVelocity + amount * Math.sin(this.orientation)
	 * 			| 		double velocityRatio = this.getXVelocity()/this.getYVelocity()
	 *			| 		this.setYVelocity( Math.sqrt( (this.velocityUpperBound*this.velocityUpperBound) / (velocityRatio * velocityRatio + 1)))
	 *			| 		this.setXVelocity( velocityRatio * this.getYVelocity())
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
			this.xVelocity = this.xVelocity + amount * Math.cos(this.orientation);
			this.yVelocity = this.yVelocity + amount * Math.sin(this.orientation);
			
			double velocityRatio = this.getXVelocity()/this.getYVelocity();
			
			this.setYVelocity( Math.sqrt( (this.velocityUpperBound*this.velocityUpperBound) / (velocityRatio * velocityRatio + 1)));
			this.setXVelocity( velocityRatio * this.getYVelocity());
		}
		else {
			this.xVelocity = this.xVelocity + amount * Math.cos(this.orientation);
			this.yVelocity = this.yVelocity + amount * Math.sin(this.orientation);
		}
	
	}
	
	/**
	 * 			Checks the distance in km between two ships.
	 * @param 	otherShip
	 * 			The ship to which this method checks the distance.
	 * @return  The distance between the two ships provided as arguments.
	 * 			| result == sqrt( (this.getXCoordinate()-otherShip.getXCoordinate())^2 + (this.getYCoordinate()-otherShip.getYCoordinate())^2 ) - (this.getRadius() + otherShip.getRadius());
	 * @return  If the method checks the distance between two ships represented by the same object, it returns 0.
	 * 			| if ( otherShip == this )
	 * 			| 	result == 0; 
	 * @throws	IllegalArgumentException 
	 * 			The ship to check a collision against is a null object.
	 * @note	As a result of the provided formula, the distance between two overlapping ships shall be negative.
	 */
	public double getDistanceBetween(Ship otherShip) throws IllegalArgumentException {
		if (otherShip == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		if ( otherShip == this )
			return 0;
		return Math.sqrt( Math.pow(this.getXCoordinate()-otherShip.getXCoordinate(), 2) + Math.pow(this.getYCoordinate()-otherShip.getYCoordinate(), 2) ) - (this.getRadius() + otherShip.getRadius());
	}
	
	
	/**
	 * This method checks whether two ships overlap.
	 * @param 	otherShip
	 * 			A Ship to check against whether the object invoking the method and the argument Ship overlap.
	 * @return	True if and only if the distance between ship1 and ship2 is greater than 0.
	 * 			| result == thisgetDistanceBetween(otherShip) < 0
	 * @throws 	IllegalArgumentException
	 * 			The ship to check an overlap against is a null object.
	 */
	public boolean overlap(Ship otherShip) throws IllegalArgumentException {
		if (otherShip == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		if ( this.getDistanceBetween(otherShip) <= 0 )
			return true;
		else
			return false;
	}
	/**
	 * Returns the time to a collision between the ship invoking the method and another ship.
	 * @param 	otherShip
	 * 			
	 * @return	The time to a collision based on the ships' position and orientation
	 * 			| result ==  {deltaT | (ship1.move(deltaT) => ship1.overlap(ship2)) && (ship2.move(deltaT) => ship2.overlap(ship1))}
	 * @throws 	IllegalArgumentException
	 * 			The ship to check a collision against is a null object.
	 * @note	Knowing that a spaceship always moves in a straight line, a ship's position can
	 * 			easily be calculated as a function of the current position and the ship's velocity 
	 * 			| newPos = currPos + time * velocity (I)
	 * 			This formula holds for ship1.x, ship1.y, ship2.x, ship2.y
	 * 			A collision occurs if two spaceships are seperated by a distance equal to the sum of their radiuses
	 * 			| getDistanceBetween(ship1, ship2) == ship1.radius + ship2.radius (II)
	 * 			Substituting the position functions (I) into the collision position (II) gives us a quadratic equation
	 * 			that makes use of the ship's position, velocity, radius and the time to a collision.
	 * 			We can now find an expression that returns this time as a function of all previously mentioned variables.
	 * 			The roots of this quadratic equation are our solution.
	 * 			Special cases include a divide by zero (no solutions => no collision => infinity time to collision)
	 * 			and the case where two ships move in the same direction, the furthest one faster than the other ship,
	 * 			resulting in a situation where the distance between them keeps increasing forever => no collision
	 * 			=> infinity time to collision.
	 */
	public double getTimeToCollision(Ship otherShip) throws IllegalArgumentException {
		if (otherShip == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		double deltaVX = this.getXVelocity() - otherShip.getXVelocity();
		double deltaVY = this.getYVelocity() - otherShip.getYVelocity();
		double deltaX = this.getXCoordinate() - otherShip.getXCoordinate();
		double deltaY = this.getYCoordinate() - otherShip.getYCoordinate();
		
		if ((deltaVX * deltaX) + (deltaVY * deltaY) >= 0)
			return Double.POSITIVE_INFINITY;
		
		double radius1 = this.getRadius();
		double radius2 = otherShip.getRadius();
			
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
	 * 			Returns the position of a possible collision between the ship itself (prime object) and another ship.
	 * @param 	otherShip
	 * 			The ship used to calculate the position of a collision with.
	 * @return	If the two ships never collide, returns null.
	 * 			| if getTimeToCollision(otherShip) == Double.POSITIVE_INFINITY
	 * 			| 		result == null;
	 * @return	If (based on the ships' current position, velocity and orientation), there will be a collision, returns
	 * 			the position of this collision.
	 * 			| 	result == [ this.getXCoordinate() + this.getTimeToCollision(otherShip) * this.getXVelocity(), this.getYCoordinate() + this.getTimeToCollision(otherShip) * this.getYVelocity()]
	 * @throws 	IllegalArgumentException
	 * 			The two ships overlap already.
	 * 			| this.overlap(otherShip)
	 * @throws 	IllegalArgumentException
	 * 			The ship to check a collision against is a null object.
	 * @note 	The position of a collision is returned from the viewpoint of the Ship object calling the function.
	 * 			The position returned represents the centerpoint of the Ship at the moment of impact.
	 * 			Therefore, calling a.getCollisionPosition(b) is not equal to b.getCollisionPosition(b)
	 */
	public double[] getCollisionPosition(Ship otherShip) throws IllegalArgumentException {
		if (otherShip == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		if ( this.overlap(otherShip) )
			throw new IllegalArgumentException("No collision position specified between two overlapping ships.");
		
		if ( this.getTimeToCollision(otherShip) == Double.POSITIVE_INFINITY)
			return null;
		
		double collisionX = this.getXCoordinate() + this.getTimeToCollision(otherShip) * this.getXVelocity();
		double collisionY = this.getYCoordinate() + this.getTimeToCollision(otherShip) * this.getYVelocity();
		
		double[] collision = {collisionX, collisionY};
		return collision;
		}
}









