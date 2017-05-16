package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

public class Asteroid extends MinorPlanet {

	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius) {
		super(x, y, xVelocity, yVelocity, radius);
		this.mass = (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Asteroid.MASSDENSITY;

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
	private static double RADIUSLOWERBOUND;

	/**
	 * 	Moves the Asteroid during a given time
	 * 	@param 	deltaTime
	 * 			The time during which the Asteroid is moved.
	 */
	@Override
	public void advance(double deltaTime) {
		move(deltaTime);
	}	
}
