package asteroids.model;

import be.kuleuven.cs.som.annotate.Raw;

/**
 * 	A class of Minor planets with some properties.
 *  
 * @author 	Tom De Backer and Quinten Bruynseraede
 *
 * @version	1.0
 */


public abstract class MinorPlanet extends Entity {
	
	/**
	 * 	Creates a new MinorPlanet
	 * @param 	x
	 * 			The X position for this MinorPlanet
	 * @param 	y
	 * 			The Y position for this MinorPlanet
	 * @param	xVelocity
	 * 			The X velocity for this MinorPlanet
	 * @param 	yVelocity
	 * 			The Y velocity for this MinorPlanet
	 * @param 	radius
	 * 			The radius for this MinorPlanet
	 * @effect	Initializes this MinorPlanet as an Entity with a position, velocity and radius.
	 * 			| super(x, y, xVelocity, yVelocity, radius)
	 * 			
	 */
	@Raw
	protected MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius) {
		super(x, y, xVelocity, yVelocity, radius);
	}

	
	/**
	 * Returns this MinorPlanet's mass. Implemented by MinorPlanet's subclasses.
	 */
	public abstract double getMass();
	
	/**
	 *  Finalizes this MinorPlanet, preparing it to be removed by the GC.
	 *  @post	| ! new.getWorld().getEntities().contains(this)
	 *  @post	| this.finalized == true
	 */
	@Override
	public void finalize(){
		this.getWorld().removeEntity(this);
		this.finalized = true;
	}

	/**
	 * Returns a string representation of a minorplanet.
	 * 
	 * @return	A string representation of a minorplanet.
	 */
	@Override
	@Raw
	public String toString() {
		return "[MinorPlanet] " + this;
	}

	/**
	 * Returns whether a given radius is a valid radius for a MinorPlanet
	 * @see	Implementation in subclass for more details.
	 */
	public abstract boolean isValidRadius(double radius);
}
