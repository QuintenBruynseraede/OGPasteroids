package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 	Class representing a black hole with some properties. A black hole may shrink or grow over time. They do not have a velocity or a mass.
 * 
 * @version	1.0
 * @author	Tom De Backer and Quinten Bruynseraede
 * 
 *  @invar	The radius of this black hole will always be a valid radius for a planetoid.
 *  		| isValidRadius(getRadius())
 *  @invar	The velocity of this black hole will always be zero.
 *  		| getXVelocity == 0 && getYVelocity == 0
 *
 */

public class BlackHole extends Entity {

	/**
	 * 
	 * @param 	x
	 * 			The x coordinate for this new black hole.
	 * @param 	y
	 * 			The y coordinate for this new black hole.
	 * @param 	radius
	 * 			The radius for this new black hole
	 * @throws	IllegalArgumentException
	 * 			|!isValidXCoordinate(x)
	 * @throws	IllegalArgumentException
	 * 			|!isValidYCoordinate(y)
	 * @throws	IllegalArgumentException
	 * 			|!isValidRadius(radius)
	 * @effect	Initializes this black hole as an Entity with a position and radius.
	 * 			| super(x, y, radius)
	 */
	public BlackHole(double x, double y, double radius) throws IllegalArgumentException {
		super(x, y, 0, 0, radius);
	}


	/**
	 * Constant registering the lowest possible value for the radius of a black hole.
	 */
	private static final double RADIUSLOWERBOUND = 100;
	
	
	/**
	 * Returns the lowest minimum value for the radius of a black hole.
	 */
	@Override
	@Basic
	@Immutable
	public double getRadiusLowerBound() {
		return RADIUSLOWERBOUND;
	}

	
	/**
	 * Returns whether a given radius is a valid radius for a black hole.
	 * @param 	radius
	 * 			The given radius to check.
	 * @return	True if and only if the velocity is greater than the minimum value specified for a black hole's radius.
	 * 			| result == radius > getRadiusLowerBound()
	 */
	@Override
	@Raw
	public boolean isValidRadius(double radius) {
		return (radius > getRadiusLowerBound());
	}

	/**
	 * Updates the black hole's radius or destroys it when it has shrunk too small.
	 * @param 	radius
	 * 			The new radius for this black hole.
	 * @post	The new radius of the black hole is equal to the given argument radius.
	 * 			| new.radius = radius
	 * @effect	If the new radius is no valid radius for a black hole, finalize the object.
	 * 			| if (! isValidRadius(radius))
	 * 			|	this.finalize()
	 * @throws	IllegalArgumentException
	 * 			| !isValidRadius(radius)
	 */
	@Override
	@Raw
	public void setRadius(double radius) throws IllegalArgumentException {
		if (isValidRadius(radius)) 
			this.radius = radius;
		else 
			this.finalize();
		
	}

	@Override
	public void advance(double deltaTime) {

	}


	/**
	 * Finalizes the black hole, preparing it to be removed by the garbage collector.
	 * @post	| this.getWorld().removeEntity(this)
	 * @post	| new.finalized == true
	 */
	@Override
	public void finalize() {
		this.getWorld().removeEntity(this);
		this.finalized = true;
	}


	/**
	 * 	Returns whether it is possible to set this black hole to the world specified as a parameter.
	 * 	@param 	world
	 * 	@see 	implementation
	 */
	@Raw
	@Override
	public boolean canHaveAsWorld(World world) {
		for (Entity element : world.getEntitiesOfType(Ship.class))  {
			if (this.overlap(element))
				return false;
		}
		
		for (Entity element : world.getEntitiesOfType(MinorPlanet.class))  {
			if (this.overlap(element))
				return false;
		}
			
		for (Entity element : world.getEntitiesOfType(BlackHole.class))  {
			if (this.overlap(element))
				return false;
		}
		
		return (this.getXCoordinate() - this.radius >= 0 
				&& this.getXCoordinate() + this.radius < World.WIDTHUPPERBOUND 
				&& this.getYCoordinate() - this.radius >= 0 
				&& this.getYCoordinate() + this.radius < World.HEIGHTUPPERBOUND);
	}
	
	/**
	 * Returns a string representation of a black hole.
	 * 
	 * @return	A string representation of a black hole.
	 */
	@Override
	@Raw
	public String toString() {
		return "[BlackHole] " + this.hashCode();
	}


	@Override
	public void collideWith(Entity entity) {
		if (entity instanceof MinorPlanet)
			entity.finalize();
		else if (entity instanceof Ship)
			entity.finalize();
		else if (entity instanceof Bullet)
			return;
		else if (entity instanceof BlackHole) {
			double newX = getCollisionPosition(entity)[0];
			double newY = getCollisionPosition(entity)[1];
			double newRadius = this.getRadius() + entity.getRadius();
			
			getWorld().addEntity(new BlackHole(newX, newY, newRadius));
			
			entity.finalize();
			this.finalize();
		}
		
	}
	
}
