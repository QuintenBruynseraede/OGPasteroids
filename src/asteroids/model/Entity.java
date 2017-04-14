package asteroids.model;

import org.hamcrest.core.IsNull;

import be.kuleuven.cs.som.annotate.Basic;

//TODO collisionPosition symmetrie

public class Entity extends GameObject {
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param xVelocity
	 * @param yVelocity
	 * @param radius
	 * 
	 * @throws	IllegalArgumentException
	 * 			The given radius is not a valid radius for this bullet.
	 * 			| ! isValidRadius(radius)
	 */
	public Entity(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		super(Constants.ENTITY);
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
	 * Variable registering the world this ship is bound to.
	 */
	private World world = null;
	
	public void setWorld(World world) throws IllegalStateException {
		if (this.getXCoordinate() < 0 || this.getXCoordinate() > world.WIDTHUPPERBOUND || this.getXCoordinate() < 0 || this.getYCoordinate() > world.HEIGHTUPPERBOUND)
			throw new IllegalStateException("Ship's position is invalid the world it is being assigned to.");
		
		
		
		if (this instanceof Bullet) {
			if (! (this.getWorld() == null)) {
				this.getWorld().removeBullet((Bullet) this);
				world.addBullet((Bullet) this);
			}
		}
		
		if (this instanceof Ship) {
			if (! (this.getWorld() == null)) {
				this.getWorld().removeShip((Ship) this);
				world.addShip((Ship) this);
			}

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
	
	public double getTimeToCollision(GameObject other) throws IllegalArgumentException {
		if (other instanceof Entity) {
			Entity otherEntity = (Entity) other;
			
			if (other == null) 
				throw new IllegalArgumentException("Invalid argument object (null).");
			
			
			double deltaVX = this.getXVelocity() - otherEntity.getXVelocity();
			double deltaVY = this.getYVelocity() - otherEntity.getYVelocity();
			double deltaX = this.getXCoordinate() - otherEntity.getXCoordinate();
			double deltaY = this.getYCoordinate() - otherEntity.getYCoordinate();
			
			if ((deltaVX * deltaX) + (deltaVY * deltaY) >= 0)
				return Double.POSITIVE_INFINITY;
			
			double radius1 = this.getRadius();
			double radius2 = otherEntity.getRadius();
				
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
		
		if (other instanceof Boundary) {
			Boundary otherBoundary = (Boundary) other;
			
			if (otherBoundary.getBoundaryType() == Constants.LEFT) {
				if (this.getXVelocity() == 0) return Double.POSITIVE_INFINITY;
				double time = -this.getXCoordinate() / this.getXVelocity();
				return (time < 0 ? Double.POSITIVE_INFINITY : time);
			}
			
			if (otherBoundary.getBoundaryType() == Constants.BOTTOM) {
				if (this.getYVelocity() == 0) return Double.POSITIVE_INFINITY;
				double time = -this.getYCoordinate() / this.getYVelocity();
				return (time < 0 ? Double.POSITIVE_INFINITY : time);
			}
			
			if (otherBoundary.getBoundaryType() == Constants.RIGHT) {
				if (this.getXVelocity() == 0) return Double.POSITIVE_INFINITY;
				double time = (this.getWorld().WIDTHUPPERBOUND-this.getXCoordinate()) / this.getXVelocity();
				return (time < 0 ? Double.POSITIVE_INFINITY : time);
			}
			
			if (otherBoundary.getBoundaryType() == Constants.TOP) {
				if (this.getYVelocity() == 0) return Double.POSITIVE_INFINITY;
				double time = (this.getWorld().HEIGHTUPPERBOUND - this.getYCoordinate()) / this.getYVelocity();
				return (time < 0 ? Double.POSITIVE_INFINITY : time);
			}
			
			throw new IllegalArgumentException("No valid boundary.");
		}
		
		return Double.POSITIVE_INFINITY;
	}
	
	
	public double getTimeFirstCollisionBoundary() {
		return Math.min(Math.min(this.getTimeToCollision(this.getWorld().getBoundaries()[0]), this.getTimeToCollision(this.getWorld().getBoundaries()[1])), Math.min(this.getTimeToCollision(this.getWorld().getBoundaries()[2]), this.getTimeToCollision(this.getWorld().getBoundaries()[3])));
	}
	
	public Boundary getFirstCollisionBoundary() {
		double minTime = Double.MAX_VALUE;
		Boundary[] boundaries = this.getWorld().getBoundaries();
		Boundary minBoundary = null;
		
		for (Boundary b : boundaries) {
			if (this.getTimeToCollision(b) < minTime) {
				minTime = this.getTimeToCollision(b);
				minBoundary = b;
			}
		}

		return minBoundary;
		
	}
	
		
	/**
	 * This method checks whether two entities overlap.
	 * @param 	otherEntities
	 * 			An entity to check against whether the object invoking the method and the argument Entity overlap.
	 * @return	True if and only if the distance between entity1 and entity2 is greater than 0.
	 * 			| result == thisgetDistanceBetween(otherEntity) < 0
	 * @throws 	IllegalArgumentException
	 * 			The entity to check an overlap against is a null object.
	 */
	public boolean overlap(Entity otherEntity) throws IllegalArgumentException {
		if (otherEntity == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		if ( this.getDistanceBetween(otherEntity) > 0.99 * (this.getRadius()+otherEntity.getRadius())  && this.getDistanceBetween(otherEntity) < 1.01 * (this.getRadius()+otherEntity.getRadius()))
			return true;
		else
			return false;
	}
	
	/**
	 * 			Checks the distance in km between two entities.
	 * @param 	otherEntity
	 * 			The entity to which this method checks the distance.
	 * @return  The distance between the two entities provided as arguments.
	 * 			| result == sqrt( (this.getXCoordinate()-otherEntity.getXCoordinate())^2 + (this.getYCoordinate()-otherEntity.getYCoordinate())^2 ) - (this.getRadius() + otherEntity.getRadius());
	 * @return  If the method checks the distance between two entities represented by the same object, it returns 0.
	 * 			| if ( otherEntity == this )
	 * 			| 	result == 0; 
	 * @throws	IllegalArgumentException 
	 * 			The entity to check a collision against is a null object.
	 * @note	As a result of the provided formula, the distance between two overlapping entities shall be negative.
	 */
	public double getDistanceBetween(Entity otherEntity) throws IllegalArgumentException {
		if (otherEntity == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		if (otherEntity == this )
			return 0;
		return Math.sqrt( Math.pow(this.getXCoordinate()-otherEntity.getXCoordinate(), 2) + Math.pow(this.getYCoordinate()-otherEntity.getYCoordinate(), 2) ) - (this.getRadius() + otherEntity.getRadius());
	}
	
	/**
	 * 			Returns the position of a possible collision between the entity itself (prime object) and another entity.
	 * @param 	otherEntity
	 * 			The entity used to calculate the position of a collision with.
	 * @return	If the two entities never collide, returns null.
	 * 			| if getTimeToCollision(otherEntity) == Double.POSITIVE_INFINITY
	 * 			| 		result == null;
	 * @return	If (based on the entities' current position, velocity and orientation), there will be a collision, returns
	 * 			the position of this collision.
	 * 			| 	result == [ this.getXCoordinate() + this.getTimeToCollision(otherentity) * this.getXVelocity(), this.getYCoordinate() + this.getTimeToCollision(otherentity) * this.getYVelocity()]
	 * @throws 	IllegalArgumentException
	 * 			The two entities overlap already.
	 * 			| this.overlap(otherEntity)
	 * @throws 	IllegalArgumentException
	 * 			The entity to check a collision against is a null object.
	 * @note 	The position of a collision is returned from the viewpoint of the entity object calling the function.
	 * 			The position returned represents the centerpoint of the entity at the moment of impact.
	 * 			Therefore, calling a.getCollisionPosition(b) is not equal to b.getCollisionPosition(b)
	 */
	public double[] getCollisionPosition(GameObject gameObject) throws IllegalArgumentException {
		if ( gameObject.getType() == Constants.BOUNDARY ) {
			double time = this.getTimeToCollision(((Boundary) gameObject));
			
			if (time == 0)
				return null;
			double collisionX = this.getXCoordinate() + time * this.getXVelocity();
			double collisionY = this.getYCoordinate() + time * this.getYVelocity();
			
			double[] collision = {collisionX, collisionY};
			return collision;
		}
		
		else {
			Entity otherEntity = (Entity) gameObject;
			if (otherEntity == null) 
				throw new IllegalArgumentException("Invalid argument object (null).");
			
			if ( this.overlap(otherEntity) )
				throw new IllegalArgumentException("No collision position specified between two overlapping ships.");
			
			if ( this.getTimeToCollision(otherEntity) == Double.POSITIVE_INFINITY)
				return null;
			
			double collisionX = this.getXCoordinate() + this.getTimeToCollision(otherEntity) * this.getXVelocity();
			double collisionY = this.getYCoordinate() + this.getTimeToCollision(otherEntity) * this.getYVelocity();
			
			double[] collision = {collisionX, collisionY};
			return collision;
		}
	}
}
