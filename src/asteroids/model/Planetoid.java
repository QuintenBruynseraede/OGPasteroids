package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

public class Planetoid extends MinorPlanet {
	
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius) {
		super(x, y, xVelocity, yVelocity, radius);
		this.mass = (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Planetoid.MASSDENSITY;
		
		if (! isValidRadius(radius))  
			throw new IllegalArgumentException("Non valid radius when initializing bullet");
		this.radius = radius;
		this.initialRadius = radius;
	}

	/**
	 * variable registering the mass of an planetoid in kilogrammes. The mass can be calculated as m = (4/3)pi*radius^3*massDensity
	 */
	private double mass;

	/**
	 * Returns this planetoid's mass.
	 */
	@Basic
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * variable registering the mass density of an asteroid in kilogrammes 
	 */
	private static final double MASSDENSITY = 0.917E12;
	
	/**
	 * Returns the mass density of this planetoid.
	 */
	@Basic
	public double getMassDensity() {
		return Planetoid.MASSDENSITY;
	}
	
	
	/**
	 * Variable representing whether this planetoid has been finalized already.	
	 */
	private boolean finalized = false;
	
	/**
	 * Finalizes the planetoid, preparing it to be removed by the garbage collector.
	 * @post	| new.isFinalzed() == true
	 * @see implementation
	 */
	public void finalize() {
		this.finalized = true;
	}

	/**
	 * Returns whether this planetoid has been finalized.
	 */
	@Basic
	public boolean isFinalized() {
		return this.finalized;
	}

	@Override
	public boolean isValidRadius(double radius) {
		return (radius >= getRadiusLowerBound());
	}

	@Override
	public void setRadius(double radius) {
		if (isValidRadius(radius))
			this.radius = radius;
		else
			this.finalize();
	}

	@Override
	public double getRadiusLowerBound() {
		return 5;
	}

	/**
	 * Variable registering the initial radius of a planetoid.	
	 */
	private final double initialRadius;
	

	/**
	 * Returns this planetoid's initial radius.
	 */
	@Basic
	public double getInitialRadius() {
		return this.initialRadius;
	}
	
	/**
	 * Variable registering the total distance this planetoid has travelled.	
	 */
	private double distanceTravelled = 0;
	

	/**
	 * Returns the total distance this planetoid has travelled.
	 */
	@Basic
	public double getDistanceTravelled() {
		return this.distanceTravelled;
	}
	
	public void addToDistanceTravelled(double d) throws IllegalArgumentException {
		if (d < 0)
			throw new IllegalArgumentException("Negative distance in addToDistanceTravelled");
		this.distanceTravelled += d;
	}

	@Override
	public void advance(double deltaTime) throws IllegalArgumentException {
		if (deltaTime < 0.001 && deltaTime > 0 )
			return;
		if (deltaTime < 0)
			throw new IllegalArgumentException("Advancing by negative time. NOT ALLOWED. ABORT.");
		
		move(deltaTime);
		addToDistanceTravelled(Math.sqrt(Math.pow(deltaTime * this.getXVelocity(), 2) + Math.pow(deltaTime * this.getYVelocity(), 2)));
		this.setRadius(this.getInitialRadius() - 1E-6*this.getDistanceTravelled());
	}
	
	
	
}
