package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * Class containing an asteroid with some properties.
 * @author 	Tom De Backer and Quinten Bruynseraede
 * 
 *  @invar	The radius of this asteroid will always be a valid radius for a asteroid.
 *  		| isValidRadius(getRadius())
 *  @invar	The following correlation between a planetoid's radius and its weight always applies.
 *  		| this.getMass() == (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Planetoid.MASSDENSITY
 *
 */
public class Asteroid extends MinorPlanet {

	/**
	 * 	Creates a new asteroid with a position, velocity, radius and mass.
	 * 	@param 	x
	 * 			The x position for this Asteroid.
	 * 	@param 	y
	 * 			The y position for this Asteroid.
	 * 	@param 	xVelocity
	 * 			The x velocity for this Asteroid.
	 * 	@param 	yVelocity
	 * 			The y velocity for this Asteroid.
	 * 	@param 	radius
	 * 			The radius for this Asteroid.
	 *  @post	new.getMass() = (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Asteroid.MASSDENSITY
	 *  @effect	Initializes this Asteroid as a MinorPlanet with a position, velocity and radius.
	 *  		| super (x, y, xVelocity, yVelocity, radius)
	 *  @effect	| setMass((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Asteroid.MASSDENSITY)
	 */
	@Raw
	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius) {
		super(x, y, xVelocity, yVelocity, radius);
		this.setMass((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Asteroid.MASSDENSITY);
	}

	/**
	 * Variable registering the mass of an asteroid in kilogrammes. The mass can be calculated as m = (4/3)pi*radius^3*massDensity
	 */
	private double mass;

	/**
	 * Returns this asteroid's mass.
	 */
	@Basic
	public double getMass() {
		return this.mass;
	}

	/**
	 * 	Sets the mass for this asteroid.
	 */
	@Basic
	private void setMass(double mass) {
		this.mass = mass;
	}
	
	/**
	 * variable registering the mass density of an asteroid in kilogrammes 
	 */
	private static final double MASSDENSITY = 2.65E12;
	
	/**
	 * Returns the mass density of this asteroid.
	 */
	@Basic
	public double getMassDensity() {
		return Asteroid.MASSDENSITY;
	}
	
	/**
	 * 	Returns whether a given radius is valid for this Asteroid
	 * 	@param	radius
	 * 			The radius to check 
	 *  @see 	implementation
	 */
	@Override
	public boolean isValidRadius(double radius) {
		return (radius >= this.getRadiusLowerBound());
	}

	/**
	 * 	Sets the radius of this asteroid to a given radius
	 *  @param	radius
	 *  		The radius to check if valid
	 *  @post	|this.radius = radius
	 *  @throws	IllegalArgumentException
	 *  		| !isValidRadius(radius)
	 * 
	 */
	@Override
	public void setRadius(double radius) throws IllegalArgumentException {
		if (! isValidRadius(radius))  
			throw new IllegalArgumentException("Non valid radius when initializing bullet");
		this.radius = radius;
	}

	/**
	 * 	Returns the lower bound of this Asteroid
	 * 	@returns	
	 * 		| Asteroid.RADIUSLOWERBOUND
	 */
	@Override
	public double getRadiusLowerBound() {
		return RADIUSLOWERBOUND;
	}
	
	/**
	 * 	The smallest radius an instance of the Asteroid class can have
	 */
	private static double RADIUSLOWERBOUND = 5;

	/**
	 * 	Moves the Asteroid during a given time
	 * 	@param 	deltaTime
	 * 			The time during which the Asteroid is moved.
	 */
	@Override
	@Raw
	public void advance(double deltaTime) {
		move(deltaTime);
	}	
	
	/**
	 * Returns a string representation of an asteroid.
	 * 
	 * @return	A string representation of an asteroid.
	 */
	@Override
	@Raw
	public String toString() {
		return "[Asteroid] " + this;
	}
}
