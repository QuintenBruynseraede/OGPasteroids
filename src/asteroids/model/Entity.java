package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;


public class Entity {
	
	public Entity(double x, double y, double xVelocity, double yVelocity, double radius) {
		setXCoordinate(x);
		setYCoordinate(y);
		setXVelocity(xVelocity);
		setYVelocity(yVelocity);
		if (! isValidRadius(radius)) 
			throw new IllegalArgumentException("Non-valid radius");
		else
			this.radius = radius;	
	}


	/**
	 * Variable registering the X coordinate of this entity expressed in kilometres.
	 */
	private double x;
	
	/**
	 * Variable registering the Y coordinate of this entity expressed in kilometres.
	 */
	private double y;
	
	
	/**
	 * Return the X coordinate of this entity expressed in kilometres.
	 */
	@Basic
	public double getXCoordinate() {
		return this.x;
	}
	
	/**
	 * Return the Y coordinate of this entity expressed in kilometres.
	 */
	@Basic
	public double getYCoordinate() {
		return this.y;
	}
	
	/**
	 * 
	 * @param 	X
	 * 			The new X coordinate for this entity.
	 * @post	The X coordinate of this entity is equal to the given X coordinate.
	 * @throws	IllegalArgumentException
	 * 			The given x coordinate is not a valid coordinate for a entity.
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
	 * 			The new Y coordinate for this entity.
	 * @post	The Y coordinate of this entity is equal to the given X coordinate.
	 * @throws	IllegalArgumentException
	 * 			The given y coordinate is not a valid coordinate for a entity.
	 * 			| Double.isNaN(y) || Double.isInfinite(y)
	 */
	void setYCoordinate(double y) throws IllegalArgumentException {
		if (Double.isNaN(y) || Double.isInfinite(y))
			throw new IllegalArgumentException("Non valid y");
		this.y = y;
		}
	
	/**
	 * Variable registering the X velocity of this entity expressed in kilometres per second.
	 */
	private double xVelocity;
	
	/**
	 * Variable registering the Y velocity of this entity expressed in kilometres per second.
	 */
	private double yVelocity;
	
	/**
	 * Returns the X velocity of this entity.
	 */
	@Basic
	public double getXVelocity(){
		return this.xVelocity;
	}
	
	/**
	 * Returns the Y velocity of this entity.
	 */
	@Basic
	public double getYVelocity(){
		return this.yVelocity;
	}
	
	/**
	 * 
	 * @param 	xVelocity
	 * 			The new velocity of X for this entity.
	 * 
	 * @post	If xVelocity is a valid velocity for this entity, then the velocity X of this entity is equal to the given X velocity
	 * 			| if(isValidVelocity(xVelocity)
	 * 			| 	new.xVelocity = xVelocity
	 * 
	 * @post	If xVelocity is not a valid velocity for this entity and xVelocity is lower than velocityLowerBound, then the velocity X of this entity is equal to velocityLowerBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity < velocityLowerBound)
	 * 			| 	new.xVelocity = velocityLowerBound
	 * 
	 * @post	If xVelocity is not a valid velocity for this entity and xVelocity is higher than velocityUpperBound, then the velocity X of this entity is equal to velocityUpperBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity > velocityUpperBound)
	 * 			| 	new.xVelocity = velocityUpperBound
	 * 
	 */
	protected void setXVelocity(double xVelocity){
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
	 * 			The new velocity of Y for this entity.
	 * 
	 * @post	If yVelocity is a valid velocity for this entity, then the velocity Y of this entity is equal to the given Y velocity
	 * 			| if(isValidVelocity(yVelocity)
	 * 			| 	new.yVelocity = yVelocity
	 * 
	 * @post	If yVelocity is not a valid velocity for this entity and yVelocity is lower than velocityLowerBound, then the velocity Y of this entity is equal to velocityLowerBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity < velocityLowerBound)
	 * 			| 	new.xVelocity = velocityLowerBound
	 * 
	 * @post	If yVelocity is not a valid velocity for this entity and yVelocity is higher than velocityUpperBound, then the velocity Y of this entity is equal to velocityUpperBound.
	 * 			| if(! isValidVelocity(yVelocity) && yVelocity > velocityUpperBound)
	 * 			| 	new.yVelocity = velocityUpperBound
	 * 
	 */
	protected void setYVelocity(double yVelocity){
		if (! isValidVelocity(yVelocity) && yVelocity < VELOCITYLOWERBOUND)
			this.yVelocity = VELOCITYLOWERBOUND;
		else if(! isValidVelocity(yVelocity) && yVelocity > VELOCITYUPPERBOUND)
			this.yVelocity = VELOCITYUPPERBOUND;
		else
			this.yVelocity = yVelocity;
	}
	
	private final static double VELOCITYLOWERBOUND = -300000;
	protected final static double VELOCITYUPPERBOUND = 300000;
	
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
	 * This method returns the minimum velocity for this entity.
	 */
	@Basic
	public static double getVelocityLowerBound() {
		return VELOCITYLOWERBOUND;
	}
	
	/**
	 * This method returns the minimum velocity for this entity.
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
	 * @return  The total velocity of the entity 
	 * 			sqrt(xVelocity * xVelocity + yVelocity * yVelocity)
	 */
	public double getTotalVelocity(double xVelocity, double yVelocity) {
		return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
	}
	
	/**
	 * Variable registering the radius of this entity.
	 */
	private final double radius;
	
	/**
	 * Variable registering the radius lower bound of this entity.
	 */
	private final static double RADIUSLOWERBOUND = 1;
	
	/**
	 * @param 	radius
	 * 			The given radius to check.
	 * @return	True if and only if the velocity is greater than the minimum value specified for a entity's radius.
	 * 			| result == radius > this.radiusLowerBound
	 */
	private boolean isValidRadius(double radius) {
		return ( radius >= Entity.RADIUSLOWERBOUND );
	}
	
	/**
	 * This method returns the radius of this entity.
	 */
	@Basic
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * This method returns the minimum value for this entity's radius.
	 */
	@Basic
	public static double getRadiusLowerBound() {
		return RADIUSLOWERBOUND;
	}

	/**
	 * Move the entity depending on its position, velocity and a given time duration.
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
	 * Returns the time to a collision between two entities.
	 * @param 	entity
	 * @return	The time to a collision based on the positions and orientations of the entity and the ship.
	 * 			| result ==  {deltaT | (this.move(deltaT) => this.overlap(b2)) && (b2.move(deltaT) => b2.overlap(this))}
	 * @throws 	IllegalArgumentException
	 * 			The ship to check a collision against is a null object.
	 */
	
	public double getTimeToCollision(Entity other) throws IllegalArgumentException {
		if (other == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		double deltaVX = this.getXVelocity() - other.getXVelocity();
		double deltaVY = this.getYVelocity() - other.getYVelocity();
		double deltaX = this.getXCoordinate() - other.getXCoordinate();
		double deltaY = this.getYCoordinate() - other.getYCoordinate();
		
		if ((deltaVX * deltaX) + (deltaVY * deltaY) >= 0)
			return Double.POSITIVE_INFINITY;
		
		double radius1 = this.getRadius();
		double radius2 = other.getRadius();
			
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
}
