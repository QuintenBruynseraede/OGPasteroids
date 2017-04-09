package asteroids.model;
import java.util.HashSet;

import be.kuleuven.cs.som.annotate.Basic;

/**
 *  A class of ships with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 */
//TODO: modify documentation for all methods

public class Ship extends Entity {
	
	/**
	 * 
	 * Initialize a new ship with given x and y coordinate.
	 * 
	 * @param 	xCoordinate 
	 * 			The X coordinate for this new ship.
	 * @param 	yCoordinate
	 * 			The Y coordinate for this new ship.
	 * @param	xVelocity
	 * 			The Velocity in the X direction for this new ship.
	 * @param	yVelocity
	 * 			The Velocity in the Y direction for this new ship.
	 * @param	radius
	 * 			The radius for this new ship.
	 * @param	orientation
	 * 			The orientation for this new ship.
	 * @param	mass
	 * 			The mass for this new ship.
	 * @param 	world
	 * 			The world this ship is associated with.
	 * @post   	The X coordinate of this new ship is equal to the given X coordinate.
	 *       	| new.getXCoordinate() == xCoordinate
	 * @post   	The Y coordinate of this new ship is equal to the given Y coordinate.
	 *       	| new.getYCoordinate() == yCoordinate
	 * @post   	The x velocity of this new ship is equal to the given X velocity.
	 *       	| new.getXVelocity() == xVelocity
	 * @post   	The Y velocity of this new ship is equal to the given Y velocity.
	 *       	| new.getYVelocity() == yVelocity
	 * @post	The radius of this new ship is set to the given radius if valid, or a predefined lower bound otherwise.
	 * 			| new.getRadius() == max(Ship.RADIUSLOWERBOUND, radius)
	 * @post	The orientation of this ship is set to the given orientation.
	 *			| new.orientation == this.orientation
	 * @post	The massdensity for this ship is set.
	 * 			| new.massDensity = max(massDensity, 1.42E12)
	 * @post	The mass for this ship is set.
	 * 			| new.mass = max(mass, lowerBound)
	 * @post	The world for this ship is set.
	 * 			| new.getWorld() = world;
	 * @note	The mass for a ship must not be lower than (4/3)*PI*r^3*massDensity
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
	public Ship (double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass, double massDensity, World world) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		
		setOrientation(orientation);
		
		if (massDensity < 1.42E12)
			this.massDensity = 1.42E12;
		else
			this.massDensity = massDensity;
		
		setMassShip(mass);
		setWorld(world);
	}
	
	
	
	/**
	 * Variable registering the orientation of this ship expressed in radians.
	 */
	private double orientation;
	
	/**
	 * @param 	orientation
	 * 			The given orientation to check
	 * @return	True if and only if the orientation is greater than or equal to 0 and lower than pi.
	 * 			| result == orientation >= 0 && orientation < 2 * Math.PI
	 */
	public boolean isValidOrientation(double orientation) {
		return (orientation >= 0 && orientation < 2 * Math.PI);
	}
	
	/**
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
	 * @note	If YVelocity is equal to zero, the velocityRatio can be calculated. Therefore we only set XVelocity to velocityUpperBound, 
	 * 			as multiplying zero by a ratio would be redundant.
	 * @post	If a valid amount of thrust is specified, the ship's x and y velocity are updated accordingly
	 * 			| new.xVelocity = this.xVelocity + amount * Math.cos(this.orientation)
	 * 			| new.yVelocity = this.yVelocity + amount * Math.sin(this.orientation) 
	 */
	public void thrust(double amount) {
		if ( amount <= 0 )
			return; 
		else if (getTotalVelocity(this.getXVelocity() + amount * Math.cos(this.orientation), this.getYVelocity() + amount * Math.cos(this.orientation)) > VELOCITYUPPERBOUND) {
		
			this.setXVelocity(this.getXVelocity() + amount * Math.cos(this.orientation));
			this.setYVelocity(this.getYVelocity() + amount * Math.sin(this.orientation));
			
			if(this.getYVelocity() == 0) 
				this.setXVelocity(VELOCITYUPPERBOUND);
			else {
				double velocityRatio = this.getXVelocity()/this.getYVelocity();
				this.setYVelocity( Math.sqrt( (VELOCITYUPPERBOUND*VELOCITYUPPERBOUND) / (velocityRatio * velocityRatio + 1)));
				this.setXVelocity( velocityRatio * this.getYVelocity());
			}
		}
		else {
			this.setXVelocity(this.getXVelocity() + amount * Math.cos(this.orientation));
			this.setYVelocity(this.getYVelocity() + amount * Math.sin(this.orientation));
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
	
	/**
	 * variable registering the mass of a ship in kilograms. 
	 * @note	Not including any objects ship is carrying.
	 */
	private double massShip;		
	
	/**
	 * Returns the mass of this ship not including any entities it is carrying.
	 */
	@Basic
	public double getMassShip(){
		return this.massShip;
	}
	
	public void setMassShip(double mass) {
		if (mass < (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * massDensity)
			this.massShip = (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * massDensity;
		else
			this.massShip = mass;
	}
	
	/**
	 * Returns the mass of this ship including any entities it is carrying.
	 */
	@Basic
	public double getMassTotal(){
		double totalMass = this.getMassShip();
		for (Bullet b : bulletsLoaded)
			totalMass += b.getMass();
		return totalMass;
	}
	
	/**
	 * variable registering the mass density of this ship.
	 */
	private final double massDensity;
	
	/**
	 * Returns the mass density of this ship.
	 */
	@Basic
	public double getmassDensity(){
		return this.massDensity;
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
	 *  A HashSet registering the bullets that are currently loaded by this ship.
	 */
	public HashSet<Bullet> bulletsLoaded = new HashSet();
	
	/**
	 *  A HashSet registering the bullets that have been fired by this ship.
	 */
	public HashSet<Bullet> bulletsFired = new HashSet();

	
	/**
	 *  Variable registering whether this ship's thruster is currently on.
	 */
	public boolean thrusterOn = false;
	
	/**
	 * Returns whether the ship's thruster is currently on.
	 * @return this.thrusterOn
	 */
	public boolean isThrusterEnabled() {
		return this.thrusterOn;
	}
	
	/**
	 * Sets the ship's thruster state to on.
	 */
	public void thrustOn() {
		this.thrusterOn = true;
	}
	
	/**
	 * Set the ship's thruster state to off.
	 */
	public void thrustOf() {
		this.thrusterOn = false;
	}
	
	/**
	 * Variable registering the force this ship's thruster exerts.
	 */
	public final double THRUSTERFORCE = 1.1e21;
	
	/**
	 * Returns the acceleration this ship is subsceptible to, making use of Newton's second law of motion (F = ma).
	 * @see implementation
	 */
	public double getAcceleration() {
		return THRUSTERFORCE / this.getMassTotal();
	}
	
	/**
	 * Removes a bullet from the list of bullets this ship is carrying.
	 * @param 	bullet
	 * 			The bullet to remove.
	 * @post	| bulletsLoaded.contains(bullet) == false
	 */
	public void removeBullet(Bullet bullet) {
		for (Bullet b : bulletsLoaded) {
			if (b == bullet) {
				bulletsLoaded.remove(b);
				return;
			}
		}
		
		for (Bullet b : bulletsFired) {
			if (b == bullet) {
				bulletsFired.remove(b);
				return;
			}
		}
		
	}

	public void updateVelocity() {
		this.thrust(getAcceleration());
	}
	
	public void finalize() {
		for (Bullet b : bulletsLoaded)
			b.setParent(null);
		for (Bullet b : bulletsFired)
			b.setParent(null);
		this.getWorld().removeShip(this);
		
	}
	
}
