package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
* Abstract class containing an entity.
* 
* @version	1.0
* @author Tom De Backer and Quinten Bruynseraede
* 
* @invar	The radius of this entity will always be a valid radius.
* 			| isValidRadius(getRadius())
* @invar	The X Velocity of this entity will always be a valid velocity.
* 			| isValidVelocity(getXVelocity())
* @invar	The Y Velocity of this entity will always be a valid velocity.
* 			| isValidVelocity(getYVelocity())
* @invar	The world this entity is associated with will always be a proper world.
* 			| canHaveAsWorld(getWorld())
* @invar	The X position of this entity will always be a valid position in the current world.
* 			| isValidXCoordinate(getXCoordinate())
* @invar	The Y position of this entity will always be a valid position in the current world.
* 			| isValidYCoordinate(getYCoordinate())
*/
public abstract class Entity {
	
	/**
	 * @param 	x
	 * 			The X coordinate for this new entity.
	 * @param 	y
	 * 			The Y coordinate for this new entity.
	 * @param 	xVelocity
	 * 			The velocity in the x direction of this new entity.
	 * @param 	yVelocity
	 * 			The velocity in the y direction of this new entity.
	 * @param 	radius
	 * 			The radius for this entity.	
	 * @throws	IllegalArgumentException
	 * 			The given radius is not a valid radius for this entity.
	 * 			| ! isValidRadius(radius)
	 * 
	 * @throws	IllegalArgumentException
	 * 			|!isValidXCoordinate(x)
	 * 
	 * @throws	IllegalArgumentException
	 * 			|!isValidYCoordinate(y)
	 * @effect	| setXCoordinate(x)
	 * @effect 	| setYCoordinate(y)
	 * @effect	| setXVelocity(xVelocity)
	 * @effect	| setYVelocity(yVelocity)
	 * @effect	| setRadius(radius)
	 * @effect	Initializes this entity as a GameObject, type Entity
	 * 			| super(Constants.ENTITY);
	 */
	@Raw
	public Entity(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		setXCoordinate(x);
		setYCoordinate(y);
		setXVelocity(xVelocity);
		setYVelocity(yVelocity);	
		setRadius(radius);
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
	 * 			| new.getXCoordinate() = x
	 * @throws	IllegalArgumentException
	 * 			The given x coordinate is not a valid coordinate for a entity.
	 * 			| Double.isNaN(x) || Double.isInfinite(x)
	 */
	@Raw
	protected void setXCoordinate(double x) throws IllegalArgumentException {
		if (Double.isNaN(x))
			throw new IllegalArgumentException("Non valid x");
		if (isValidXCoordinate(x))
			this.x = x;			
	}
	
	/**
	 * 
	 * @param 	yCoordinate
	 * 			The new Y coordinate for this entity.
	 * @post	The Y coordinate of this entity is equal to the given X coordinate.
	 * 			| new.getYCoordinate() = y
	 * @throws	IllegalArgumentException
	 * 			The given y coordinate is not a valid coordinate for a entity.
	 * 			| Double.isNaN(y) || Double.isInfinite(y)
	 */
	@Raw
	protected void setYCoordinate(double y) throws IllegalArgumentException {
		if (Double.isNaN(y)) 
			throw new IllegalArgumentException("Non valid y");
		if (isValidYCoordinate(y))
			this.y = y;
		}
	
	/**
	 * Returns whether a given coordinate is a valid x coordinate in this world. 
	 * @param 	x
	 * 			The x coordinate
	 * @see implementation
	 */
	@Raw
	private boolean isValidXCoordinate(double x) {
		if (getWorld() == null)
			return true;
		return (x < getWorld().getWidth() && x >= 0);
	}
	
	/**
	 * Returns whether a given coordinate is a valid y coordinate in this world.
	 * @param 	y
	 * 			The y coordinate	
	 * @see implementation
	 */
	@Raw
	private boolean isValidYCoordinate(double y) {
		if (getWorld() == null)
			return true;
		return (y < getWorld().getHeight() && y >= 0);
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
	@Raw
	protected void setXVelocity(double xVelocity){
		if (!Double.isFinite(xVelocity))
			this.xVelocity = 0;
		else if (! isValidVelocity(xVelocity) && xVelocity < VELOCITYLOWERBOUND)
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
	@Raw
	protected void setYVelocity(double yVelocity){
		if (!Double.isFinite(yVelocity))
			this.yVelocity = 0;
		else if (! isValidVelocity(yVelocity) && yVelocity < VELOCITYLOWERBOUND)
			this.yVelocity = VELOCITYLOWERBOUND;
		else if(! isValidVelocity(yVelocity) && yVelocity > VELOCITYUPPERBOUND)
			this.yVelocity = VELOCITYUPPERBOUND;
		else
			this.yVelocity = yVelocity;
	}
	
	/**
	 * Constant holding the highest possible velocity for an Entity object
	 */
	protected final static double VELOCITYLOWERBOUND = -300000;
	
	/**
	 * Constant holding the lowest possible velocity for an Entity object
	 */
	protected final static double VELOCITYUPPERBOUND = 300000;
	
	/**
	 * 
	 * @param 	velocity
	 * 			The given velocity to check.
	 * @return	| result == (!Double.isFinite(velocity)) &&
	 *			| (velocity > this.velocityLowerBound && velocity < this.velocityUpperBound)
	 */
	@Raw
	private boolean isValidVelocity(double velocity) {
		if  (!Double.isFinite(velocity))
			return false;
		return ( velocity > VELOCITYLOWERBOUND && velocity < VELOCITYUPPERBOUND);
	}
	
	/**
	 * This method returns the minimum velocity for this entity.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getVelocityLowerBound() {
		return VELOCITYLOWERBOUND;
	}
	
	/**
	 * This method returns the minimum velocity for this entity.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getVelocityUpperBound() {
		return VELOCITYUPPERBOUND;
	}
	
	/**
	 * 
	 * Return the total velocity as a function of a velocity in the X and Y direction.
	 * @param 	xVelocity
	 * 			The velocity in the X direction.
	 * @param 	yVelocity
	 * 			The velocity in the Y direction
	 * @return  The total velocity of the entity .
	 * 			| sqrt(xVelocity * xVelocity + yVelocity * yVelocity)
	 */
	public double getTotalVelocity(double xVelocity, double yVelocity) {
		return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
	}
	
	
	/**
	 * Variable registering the world this entity is bound to.
	 */
	private World world = null;
	
	/**
	 * 	Sets the world this entity is associated with.
	 * 	@param 	world
	 * 		The world this entity is to be associated with.
	 * 	@throws IllegalStateException
	 * 		| !canHaveAsWorld(world)
	 * 	@post Associations with this enitity's previous world are undone if possible.
	 * 		| if (!(getWorld() == null) && ! (world == null)
	 * 		| 	new.getWorld() == world
	 * 		| if (!(getWorld() == null) && ! (world == null)
	 * 		| 	old.getWorld().removeEntity(this)
	 * 		This entity is added to the new world
	 * 		| new.getWorld().getEntitiesOfType(this.getClass()).contains(this) == true
	 *  
	 *  @note 	To allow the user to 'reset' this association, providing null as 
	 *  		an argument is allowed. In this case, the last two post-conditions
	 *  		are not executed, to prevent NullPointerExceptions.
	 */
	public void setWorld(World world) throws IllegalStateException {
		if (!canHaveAsWorld(world))
			throw new IllegalStateException("Invalid position in the world trying to assign this entity to.");
	
		//If current world is null, don't try to remove 'this' from it
		//If world is null, don't try to add anything to it
		//This allows us to provide 'null' as an argument in case we want to 
		//undo the association for this entity.
		if (!(this.getWorld() == null) && !(world == null)) {
			this.getWorld().removeEntity(this);
			world.addEntity(this);
		}

		this.world = world;
		
	}
	
	/**
	 * 	Returns whether it is possible to set this Entity's world to the world specified as a parameter
	 * 	@param 	world
	 * 	@see implementation
	 */
	@Raw
	public boolean canHaveAsWorld(World world) {
		if (world != null && (getXCoordinate() > world.getWidth() || getYCoordinate() > world.getHeight()))
			return false;
		return true;
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
	 * 			The given time is not positive (accounting for rounding errors)
	 * 			| time < 0
	 */
	public void move(double time) throws IllegalArgumentException {
		if (time < -0.000001)
			throw new IllegalArgumentException("Argument time must be positive");
		if (time < 0.000001 && time > -0.000001)
			return;
		else {
			setXCoordinate(this.getXCoordinate() + time * this.getXVelocity());
			setYCoordinate(this.getYCoordinate() + time * this.getYVelocity());
		}
	}
	
	
	/**
	 * Variable registering the radius of this planetoid.
	 */
	protected double radius;
	
	/**
	 * Abstract function that returns whether a given radius is valid for an entity.
	 * @param 	radius
	 * 			The given radius to check.
	 * @note	See implementation in subclasses for details.
	 */
	public abstract boolean isValidRadius(double radius);
	
	
	/**
	 * This method returns the radius of this entity.
	 */
	@Basic
	@Raw
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * @param 	radius
	 * 			The new radius for this entity.
	 * @note	See impolementation in subclasses for details.
	 */
	@Basic
	public abstract void setRadius(double radius);
	
	/**
	 * This method returns the minimum value for this entity's radius.
	 */
	@Basic
	@Immutable
	public abstract double getRadiusLowerBound();

	
	
	/**
	 * Returns the time to a collision between the entity invoking the method and another entity.
	 * @param 	otherEntity
	 * @return	The time to a collision based on the entity's position and orientation
	 * 			| result ==  {deltaT | (entity1.move(deltaT) => entity1.overlap(entity2)) && (entity1.move(deltaT) => entity2.overlap(entity1))}
	 * @throws 	IllegalArgumentException
	 * 			The entity to check a collision against is a null object.
	 * 			| (other == null)
	 * @note	Knowing that an entity always moves in a straight line, a ship's position can
	 * 			easily be calculated as a function of the current position and the ship's velocity 
	 * 			| newPos = currPos + time * velocity (I)
	 * 			This formula holds for entity1.x, entity1.y, entity2.x, entity2.y
	 * 			A collision occurs if two entities are seperated by a distance equal to the sum of their radiuses
	 * 			| getDistanceBetween(entity1, entity2) == entity1.radius + entity2.radius (II)
	 * 			Substituting the position functions (I) into the collision position (II) gives us a quadratic equation
	 * 			that makes use of the entity's position, velocity, radius and the time to a collision.
	 * 			We can now find an expression that returns this time as a function of all previously mentioned variables.
	 * 			The roots of this quadratic equation are our solutions.
	 * 			Special cases include a divide by zero (no solutions => no collision => infinity time to collision)
	 * 			and the case where two entities move in the same direction, the furthest one faster than the other entity,
	 * 			resulting in a situation where the distance between them keeps increasing forever => no collision
	 * 			=> infinity time to collision.
	 */
	
	public double getTimeToCollision(Entity other) throws IllegalArgumentException {
		if (other == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		
		Entity otherEntity = (Entity) other;
		
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
	
	
	/**
	 * 	Returns the time to the first collision with one of the four boundaries of this entity's world
	 * 
	 * 	@return | result == min(a, b, c, d) where
	 * 		    | a = time to Top Left Boundary
	 * 		    | b = time to Top Right Boundary
	 * 		    | c = time to Bottom Left Boundary
	 * 	  	    | d = time to Bottom Right Boundary
	 *  @return | if (getWorld() == null)
	 * 			|	result == Double.POSTIIVE_INFINITY
	 * 			
	 */
	public double getTimeFirstCollisionBoundary() {
		if (getWorld() == null) return Double.POSITIVE_INFINITY;
		double xTime = Double.POSITIVE_INFINITY;
		double yTime = Double.POSITIVE_INFINITY;
		
		if (this.getXVelocity() > 0)
			xTime  = (getWorld().getWidth() - getXCoordinate() - getRadius()) / getXVelocity();
		if (this.getXVelocity() < 0)
			xTime = - ( getXCoordinate() - getRadius()) / getXVelocity();
		if (this.getXVelocity() == 0)
			xTime = Double.POSITIVE_INFINITY;
		
		if (this.getYVelocity() > 0)
			yTime = (getWorld().getHeight() - getYCoordinate()  -getRadius()) / getYVelocity();
		if (this.getYVelocity() < 0)
			yTime = - ( getYCoordinate() - getRadius()) / getYVelocity();
		if (this.getYVelocity() == 0)
			yTime = Double.POSITIVE_INFINITY;
		
		return Math.min(xTime, yTime);	
	}
	
		
	/**
	 * This method checks whether two entities overlap.
	 * @param 	otherEntities
	 * 			An entity to check against whether the object invoking the method and the argument Entity overlap.
	 * @return	True if and only if the distance between entity1 and entity2 is smaller than 0.
	 * 			| result == thisgetDistanceBetween(otherEntity) < 0
	 * @return	| if (this == otherEntity)
	 * 			| 	result == true
	 * @throws 	IllegalArgumentException
	 * 			The entity to check an overlap against is a null object.
	 * 			| (otherEntity == null)
	 * @note	This method uses the notion of apparent overlap, to account for rounding errors.
	 */
	public boolean overlap(Entity otherEntity) throws IllegalArgumentException {
		if (otherEntity == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		if (this == otherEntity) return true;
		
		if ( this.getDistanceBetween(otherEntity) < -0.01)
			return true;
		else
			return false;
	}
	
	/**
	 * Checks the distance in km between two entities.
	 * @param 	otherEntity
	 * 			The entity to which this method checks the distance.
	 * @return  The distance between the two entities provided as arguments.
	 * 			| result == sqrt( (this.getXCoordinate()-otherEntity.getXCoordinate())^2 + (this.getYCoordinate()-otherEntity.getYCoordinate())^2 ) - (this.getRadius() + otherEntity.getRadius());
	 * @return  If the method checks the distance between two entities represented by the same object, it returns 0.
	 * 			| if ( otherEntity == this )
	 * 			| 	result == 0; 
	 * @throws	IllegalArgumentException 
	 * 			The entity to check a collision against is a null object.
	 * 			| otherEntity == null
	 * @note	As a result of the provided formula, the distance between two overlapping entities shall be negative.
	 */
	public double getDistanceBetween(Entity otherEntity) throws IllegalArgumentException {
		if (otherEntity == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		
		if (otherEntity == this)
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
	 * 			The entity to check a collision against is a null object.
	 * @note 	The position of a collision is returned from the viewpoint of the entity object calling the function.
	 * 			The position returned represents the centerpoint of the entity at the moment of impact.
	 * 			Therefore, calling a.getCollisionPosition(b) is not equal to b.getCollisionPosition(b)
	 */
	public double[] getCollisionPosition(Entity otherEntity) throws IllegalArgumentException {
		if (otherEntity == null) 
			throw new IllegalArgumentException("Invalid argument object (null).");
		

		if ( this.overlap(otherEntity) ) {
			return null;
		}
		
		if ( this.getTimeToCollision(otherEntity) == Double.POSITIVE_INFINITY)
			return null;

		double collisionXSelf = this.getXCoordinate() + this.getTimeToCollision(otherEntity) * this.getXVelocity();
		double collisionYSelf = this.getYCoordinate() + this.getTimeToCollision(otherEntity) * this.getYVelocity();

		double collisionXOther = otherEntity.getXCoordinate() + otherEntity.getTimeToCollision(this) * otherEntity.getXVelocity();
		double collisionYOther = otherEntity.getYCoordinate() + otherEntity.getTimeToCollision(this) * otherEntity.getYVelocity();
		
		double collisionX;
		double collisionY;
		
		if (this.getXCoordinate() > otherEntity.getXCoordinate()) {
			collisionX = collisionXSelf - Math.cos(Math.atan((collisionYOther - collisionYSelf) / (collisionXOther - collisionXSelf))) * radius;
			collisionY = collisionYSelf - Math.sin(Math.atan((collisionYOther - collisionYSelf) / (collisionXOther - collisionXSelf))) * radius;
			
		}
		else {
			collisionX = collisionXSelf + Math.cos(Math.atan((collisionYOther - collisionYSelf) / (collisionXOther - collisionXSelf))) * radius;
			collisionY = collisionYSelf + Math.sin(Math.atan((collisionYOther - collisionYSelf) / (collisionXOther - collisionXSelf))) * radius;
		}
		
		double[] collision = {collisionX, collisionY};
		return collision;
		
	}

	/**
	 * 	Updates the entity based on a few parameters.
	 * 	@param 	deltaTime
	 * 			number of time units to advance this entity.
	 * 	@note	See implementations in subclasses for specification.
	 */
	public abstract void advance(double deltaTime);

	
	/**
	 * 	Prepares the instance to be removed by the Garbage Collector.
	 * 	@note	See implementation in subclass for specification.
	 */
	public abstract void finalize();
	
	/**
	 * 	Variable representing whether this entity has been finalized already.
	 */
	protected boolean finalized = false;
	
	
	/**
	 * 	Returns whether this entity has been finalized already.
	 */
	@Basic
	@Raw
	public boolean isFinalized() {
		return this.finalized;
	}

		/**
	 *  Changes the velocity of this entity to reflect a collision against a boundary of the world.
	 *  @post |	if (getWorld() == null)
	 * 		  |		//Do nothing
	 *  @see Implementation
	 */
	public void collideBoundary() {
		if (getWorld() == null) return;
		if (getXCoordinate() < 1.01*getRadius())
			setXVelocity(-getXVelocity());
		if (getXCoordinate() > getWorld().getWidth()-1.01*getRadius())
			setXVelocity(-getXVelocity());
		if (getYCoordinate() < 1.01 * getRadius())
			setYVelocity(-getYVelocity());
		if (getYCoordinate() > getWorld().getHeight()-1.01*getRadius())
			setYVelocity(-getYVelocity());
	}
	
	/**
	 * Resolves a collision between this entity and another.
	 * See implementation in subclass for details.
	 * @param 	entity
	 * 			The entity this entity collides with.
	 */
	public abstract void collideWith(Entity entity);
	
	/**
	 * Returns the position where the entity collides with a boundary.
	 * @return | {x, y} where abs(getWorld().getWidth() - x) = getRadius()
	 * 		   |        or abs(x) = getRadius()
	 * 		   |		or abs(getWorld().getHeight() - y) = getRadius()
	 * 		   |		or abs(y) = getRadius()
	 * @return | if (getWorld() == null || getTotalVelocity() == 0)
	 * 		   | 	return null;
	 * @note   This method utilizes the notion of significant overlap, and as such an overlap smaller than 1% of this enitity's radius will be tolerated.
	 */
	public double[] getCollisionPositionFirstBoundary() {
		if (getWorld() == null)
			return null;
		
		double dt = getTimeFirstCollisionBoundary();
		
		if (dt == Double.POSITIVE_INFINITY)
			return null;
		
		double xCenter = getXCoordinate() + dt * getXVelocity();
		double yCenter = getYCoordinate() + dt * getYVelocity();
		
		if (xCenter < 1.01*getRadius()) //LEFT
			return new double[] {xCenter - getRadius(), yCenter};
		else if (xCenter > getWorld().getWidth()- 1.01 * getRadius()) //RIGHT
			return new double[] {xCenter + getRadius(), yCenter};
		else if (yCenter < 1.01 * getRadius()) //TOP
			return new double[] {xCenter, yCenter - getRadius()};
		else if (yCenter > getWorld().getHeight()- 1.01 * getRadius()) //BOTTOM
			return new double[] {xCenter, yCenter + getRadius()};
		else
			return null;
	}
}
