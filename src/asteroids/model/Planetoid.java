package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 	Class representing a Planetoid with some properties. A planetoid shrinks based on the distance it has travelled during its lifetime.
 * 
 * @version	1.0
 * @author	Tom De Backer and Quinten Bruynseraede
 * 
 *  @invar	The radius of this planetoid will always be a valid radius for a planetoid.
 *  		| isValidRadius(getRadius())
 *  @invar	The following correlation between a planetoid's radius and its weight always applies.
 *  		| this.getMass() == (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Planetoid.MASSDENSITY
 *
 */
public class Planetoid extends MinorPlanet {
	/**
	 * 	Initializes a new Planetoid with  a position, velocity, radius and mass
	 * @param 	x
	 * 			The X position for this Planetoid
	 * @param 	y
	 * 			The Y position for this Planetoid
	 * @param	xVelocity
	 * 			The X velocity for this Planetoid
	 * @param 	yVelocity
	 * 			The Y velocity for this Planetoid
	 * @param 	radius
	 * 			The radius for this Planetoid
	 * @effect	Initializes this Planetoid as a MinorPlanet with a position, velocity and radius
	 * 			| super(x, y, xVelocity, yVelocity, radius)
	 * @effect	| setMass((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Planetoid.MASSDENSITY)
	 * @effect	| setRadius(radius)
	 * @post	| new.initialRadius = radius
	 */
	@Raw
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius) {
		super(x, y, xVelocity, yVelocity, radius);
		setMass((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Planetoid.MASSDENSITY);
		
		if (! isValidRadius(radius))  
			throw new IllegalArgumentException("Non valid radius when initializing bullet");
		setRadius(radius);
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
	 * 	Sets the mass for this planetoid.
	 */
	@Basic
	private void setMass(double mass) {
		this.mass = mass;
	}
	
	
	/**
	 * variable registering the mass density of an asteroid in kilogrammes 
	 */
	private static final double MASSDENSITY = 0.917E12;
	
	/**
	 * Returns the mass density of this planetoid.
	 */
	@Basic
	@Raw
	@Immutable
	public double getMassDensity() {
		return Planetoid.MASSDENSITY;
	}
	
	
	

	/**
	 * Constant registering the lowest possible value for the radius of a planetoid.
	 */
	private static final double RADIUSLOWERBOUND = 5;
	
	/**
	 * Returns whether a given radius is a valid radius for a planetoid.
	 * @param 	radius
	 * 			The given radius to check.
	 * @return	True if and only if the velocity is greater than the minimum value specified for a planetoid's radius.
	 * 			| result == radius > this.getRadiusLowerBound()
	 */
	@Override
	@Raw
	public boolean isValidRadius(double radius) {
		return (radius >= getRadiusLowerBound());
	}

	
	/**
	 * Updates the Planetoid's radius and weight, or destroys it when it has shrunk too small.
	 * @param 	radius
	 * 			The new radius for this Planetoid.
	 * @post	The new radius of the Planetoid is equal to the given argument radius, and its weight is updates as well.
	 * 			| new.radius = radius
	 * 			| new.getMass() == (4/3) * Math.PI * Math.pow(new.getRadius(), 3) * Planetoid.MASSDENSITY
	 * @effect	If the new radius is no valid radius for a planetoid, finalize the object
	 * 			| if (! isValidRadius(radius))
	 * 			|	this.finalize()
	 * @throws	IllegalArgumentException
	 * 			| !isValidRadius(radius)
	 * @note	When the planetoid shrinks to a value lower than RADIUSLOWERBOUND, this method will make sure it is destroyed.
	 */
	@Override
	@Raw
	public void setRadius(double radius) {
		if (isValidRadius(radius)) {
			this.radius = radius;
			setMass((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Planetoid.MASSDENSITY);
		}
		else
			this.finalize();
	}

	/**
	 * Returns the lowest minimum value for the radius of this planetoid.
	 */
	@Override
	@Basic
	@Immutable
	public double getRadiusLowerBound() {
		return RADIUSLOWERBOUND;
	}


	/**
	 * Variable registering the initial radius of a planetoid.	
	 */
	private final double initialRadius;
	

	/**
	 * Returns this planetoid's initial radius.
	 */
	@Basic
	@Raw
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
	@Raw
	public double getDistanceTravelled() {
		return this.distanceTravelled;
	}
	
	/**
	 * 
	 * @param 	d
	 * 			The distance to add to the counter of kilometers travelled by this Planetoid
	 * @throws 	IllegalArgumentException
	 * 			| d < 0
	 */
	@Raw
	public void addToDistanceTravelled(double d) throws IllegalArgumentException {
		if (d < 0)
			throw new IllegalArgumentException("Negative distance in addToDistanceTravelled");
		this.distanceTravelled += d;
	}

	/**
	 * Updates the position, distance-travelled-counter and radius of this Planetoid
	 * @param	deltaTime
	 * 			The time to advance this Planetoid
	 * @throws	IllegalArgumentException
	 * 			| deltatime < 0
	 * @effect	| move(deltatime)
	 * @post	| new.getDistanceTravelled() = old.getDistanceTravelled + distance
	 * @post	| new.getRadius() = old.getInitialRadius() - 1E-6*old.getDistanceTravelled());
	 */
	@Override
	@Raw
	public void advance(double deltaTime) throws IllegalArgumentException {
		if (deltaTime < 0.001 && deltaTime > 0 )
			return;
		if (deltaTime < 0)
			throw new IllegalArgumentException("Advancing by negative time. NOT ALLOWED. ABORT.");
		
		move(deltaTime);
		addToDistanceTravelled(Math.sqrt(Math.pow(deltaTime * this.getXVelocity(), 2) + Math.pow(deltaTime * this.getYVelocity(), 2)));
		this.setRadius(this.getInitialRadius() - 1E-6*this.getDistanceTravelled());
	}
	

	
	/**
	 * Finalizes the planetoid, preparing it to be removed by the garbage collector.
	 * @post	| new.isFinalzed() == true
	 * @see implementation
	 */
	public void finalize() {
		if (this.getWorld() != null && this.getRadius() >= 30) {
			double child1XVelocity = Math.random()*(9/4)*(this.getXVelocity()*this.getXVelocity()+this.getYVelocity()*this.getYVelocity());
			double child1YVelocity = Math.sqrt((9/4)*(this.getXVelocity()*this.getXVelocity()+this.getYVelocity()*this.getYVelocity()) - child1XVelocity*child1XVelocity);
			
			
			Asteroid a1 = new Asteroid(this.getXCoordinate() + this.getRadius()/2, this.getYCoordinate(), child1XVelocity, child1YVelocity, this.getRadius()/2);
			Asteroid a2 = new Asteroid(this.getXCoordinate() - this.getRadius()/2, this.getYCoordinate(), -child1XVelocity, -child1YVelocity, this.getRadius()/2);
			this.getWorld().addEntity(a1);
			this.getWorld().addEntity(a2);
			
			a1.setWorld(this.getWorld());
			a2.setWorld(this.getWorld());
		}
		this.finalized = true;
	}



	
	/**
	 * Returns a string representation of a planetoid.
	 * 
	 * @return	A string representation of a planetoid.
	 */
	@Override
	@Raw
	public String toString() {
		return "[Planetoid] " + this;
	}
	
}
