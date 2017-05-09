package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

public class Asteroid extends MinorPlanet {

	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius) {
		super(x, y, xVelocity, yVelocity, radius);
		this.mass = (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Asteroid.MASSDENSITY;

	}

	/**
	 * variable registering the mass of an asteroid in kilogrammes. The mass can be calculated as m = (4/3)pi*radius^3*massDensity
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

	@Override
	public boolean isValidRadius(double radius) {
		return (radius >= this.getRadiusLowerBound());
	}

	@Override
	public void setRadius(double radius) {
		if (! isValidRadius(radius))  
			throw new IllegalArgumentException("Non valid radius when initializing bullet");
		this.radius = radius;
	}

	@Override
	public double getRadiusLowerBound() {
		return 5;
	}

	@Override
	public void advance(double deltaTime) {
		move(deltaTime);
	}

	@Override
	public void collideWith(GameObject object2, int collisiontype) {
		// TODO Auto-generated method stub
		
	}

	
}
