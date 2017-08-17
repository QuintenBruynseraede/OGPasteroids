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
 *  @invar	The radius of this black hole will always be a valid radius for a black hole.
 *  		| isValidRadius(getRadius())
 *  @invar	The velocity of this black hole will always be zero.
 *  		| getXVelocity == 0 && getYVelocity == 0
 *  @invar	| canHaveAsWorld(getWorld())
 *
 */

public class BlackHole extends Entity {

	/**
	 * Initialize a new black hole with given x and y coordinate and a radius.
	 * 
	 * @param 	x
	 * 			The x coordinate for this new black hole.
	 * @param 	y
	 * 			The y coordinate for this new black hole.
	 * @param 	radius
	 * 			The radius for this new black hole.
	 * @effect	Initializes this black hole as an Entity with a position and radius.
	 * 			| super(x, y, radius)
	 */
	public BlackHole(double x, double y, double radius) {
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
	 * Sets the radius for this black hole.
	 * @param 	radius
	 * 			The new radius for this black hole.
	 * @post	| if (isValidRadius(radius))
	 * 			| 	new.radius = radius
	 * @throws	IllegalArgumentException
	 * 			| !isValidRadius(radius)
	 */
	@Override
	@Raw
	public void setRadius(double radius) throws IllegalArgumentException {
		if (isValidRadius(radius)) 
			this.radius = radius;
		else 
			throw new IllegalArgumentException("Non valid radius.");	
	}

	/**
	 * Updates the position of the black hole according to its velocity.
	 * As black holes cannot have a velocity, the state of the game doesn't change
	 */
	@Override
	public void advance(double deltaTime) {
		return;
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
	 * 			The world to check whether, the black hole can be positioned in it or not.
	 * 	@see 	implementation
	 * 	@throws	IllegalArgumentException
	 * 			If the black hole does not overlap with a ship, minorplanet or another blackhole and the black hole can be positioned in the given world.
	 */
	@Raw
	@Override
	public boolean canHaveAsWorld(World world) throws IllegalArgumentException {
		for (Entity e : world.getEntities()) {
			if (overlap(e) && e != this)
				return false;
		}
		
		return ( this.getXCoordinate() - this.radius >= 0 
				&& this.getXCoordinate() + this.radius < world.getWidth() 
				&& this.getYCoordinate() - this.radius >= 0 
				&& this.getYCoordinate() + this.radius < world.getHeight());		
	}


	/**
	 * 	Resolves a collision between this Black Hole and another entity
	 * 	@param	entity
	 * 			The entity that will collide with this black hole.
	 * 	@post	If the given entity is instance of a Minorplanet, the minorplanet will be finalized.
	 * 			| if(entity instanceof MinorPlanet)
				| 		entity.isFinalized() == true
	 *	@post	If the given entity is instance of a Ship, the ship will be finalized.
	 * 			| if(entity instanceof Ship)
	 *			| 		entity.isFinalized() == true;
	 *	@post	If the given entity is instance of a Bullet, there is no effect.
	 *			| if (entity instanceof Bullet)
	 *			|		return;
	 * 	@post	If the given entity is instance of a BlackHole, both holes are destroyed and replaced with a new black hole whose
				center is at the point of collision and whose radius is equal to the sum of the radii of both
				colliding black holes. Say B is the newly created black hole
				| if (entity instanceof BlackHole)
				|		(new.getWorld()).getEntities() = getWorld().getEntities() + 1
				| 		B.getXCoordinate() == getCollisionPosition(entity)[0]
				|		B.getYCoordinate() == getCollisionPosition(entity)[1]
				|		B.getRadius() == this.getRadius() + entity.getRadius()
				|		B.getWorld() == getWorld()
				|		entity.isFinalized() == true
				|		this.isFinalized() == true
	 */
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
			
			World w = getWorld();
			w.removeEntity(entity);
			w.removeEntity(this);
			
			w.addEntity(new BlackHole(newX, newY, newRadius));
			
			entity.finalize();
			this.finalize();
		}
		else
			entity.collideWith(this);
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
}
