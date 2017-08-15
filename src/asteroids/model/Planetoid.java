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
	 * 	Initializes a new Planetoid with  a position, velocity, radius and mass.
	 * @param 	x
	 * 			The X position for this Planetoid.
	 * @param 	y
	 * 			The Y position for this Planetoid.
	 * @param	xVelocity
	 * 			The X velocity for this Planetoid.
	 * @param 	yVelocity
	 * 			The Y velocity for this Planetoid.
	 * @param 	radius
	 * 			The radius for this Planetoid.
	 * @effect	Initializes this Planetoid as a MinorPlanet with a position, velocity and radius
	 * 			| super(x, y, xVelocity, yVelocity, radius)
	 * @effect	| setMass((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Planetoid.MASSDENSITY)
	 * @effect	| setRadius(radius)
	 * @post	| new.initialRadius = radius
	 */
	@Raw
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius) {
		super(x, y, xVelocity, yVelocity, radius);

		setMass(4 * Math.PI / 3);
		setMass(getMass() * MASSDENSITY * radius * radius * radius);
		
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
	 * 			| result == radius >= getRadiusLowerBound()
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
	 * @post	The new radius of the Planetoid is equal to the given argument radius, and its weight is updated as well.
	 * 			| new.getRadius() = radius
	 * 			| new.getMass() == (4/3) * Math.PI * Math.pow(new.getRadius(), 3) * Planetoid.MASSDENSITY
	 * @effect	If the new radius is no valid radius for a planetoid, finalize the object
	 * 			| if (! isValidRadius(radius))
	 * 			|	this.finalize()
	 * @note	When the planetoid shrinks to a value lower than RADIUSLOWERBOUND, this method will also make sure it is destroyed.
	 */
	@Override
	@Raw
	public void setRadius(double radius) {
		if (isValidRadius(radius)) {
			this.radius = radius;
			setMass(4 * Math.PI / 3);
			setMass(getMass() * MASSDENSITY * radius * radius * radius);
		}
		else {
			this.finalize();
		}
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
	 * Adds a distance (in km) to the number of kilometres this Planetoid has travelled.
	 * @param 	d
	 * 			The distance to add to the counter of kilometers travelled by this Planetoid
	 * @throws 	IllegalArgumentException
	 * 			| d < 0
	 * @post	| new.getDistanceTravelled() = getDistanceTravelled() + d
	 * @post	If, because of this addition, the radius has shrunk too small, finalize the Planetoid immediately (i.e. don't wait for the next invocation of advance())
	 *  		| if (initialRadius - 0.000001 * getDistanceTravelled() < getRadiusLowerBound())
	 * 			| 	finalize()
	 * @post	If, after the addition, the radius would still be valid, modify the radius.
	 * 			| if (!( initialRadius - 0.000001 * getDistanceTravelled() < getRadiusLowerBound()))
	 * 			|	new.getRadius() = intialRadius - 0.000001 * getDistanceTravelled()
	 */
	@Raw
	public void addToDistanceTravelled(double d) throws IllegalArgumentException {
		if (d < 0)
			throw new IllegalArgumentException("Negative distance in addToDistanceTravelled");
		this.distanceTravelled += d;
		if (initialRadius - 0.000001 * getDistanceTravelled() < getRadiusLowerBound())
			finalize();
		else
			setRadius(initialRadius - 0.000001 * getDistanceTravelled());
	}

	/**
	 * Advances the Planetoid, updating its position, distance-travelled-counter and radius.
	 * @param	deltaTime
	 * 			The time to advance this Planetoid
	 * @throws	IllegalArgumentException
	 * 			| deltatime < 0
	 * @effect	To account for rounding errors, advancing the Planetoid a very small amount of time can be ignored.
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
	 * @post	If the radius of this planetoid is equal to or larger than 30km, spawn two smaller Planetoids, and remove the larger Planetoid
	 * 			The old planetoids will be created near this Planetoid's position, and their radiuses will equal the radius of this Planetoid, divided by 2. 
	 * 			| if (getWorld() != null && getRadius() >= 30) 
	 * 			|	w = old.getWorld()
	 * 			|	new.w.getEntitiesOfType(Planetoid.class) = old.w.getEntitiesOfType(Planetoid.class) + 1 
	 * 			| 	new.getWorld() = null
	 * 			|	isFinalized() = true
	 * @post	If the radius of this planetoid is smaller than 30km, don't spawn any new Planetoids.
	 * 			| if (! (getWorld() != null && getRadius() >= 30)) 
	 * 			|  	new.getWorld() = null
	 * 			|	isFinalized() = true
	 * @post	If this planetoid was not associated with a world, simply finalize it
	 * 			| if (getWorld() == null)
	 * 			|	isFinalized() = true
	 * @see implementation for details concerning the two new planetoids
	 */
	public void finalize() {
		if (this.getWorld() != null && this.getRadius() >= 30) {
			double child1XVelocity = Math.random()*(9/4)*(this.getXVelocity()*this.getXVelocity()+this.getYVelocity()*this.getYVelocity());
			double child1YVelocity = Math.sqrt((9/4)*(this.getXVelocity()*this.getXVelocity()+this.getYVelocity()*this.getYVelocity()) - child1XVelocity*child1XVelocity);
			
			Asteroid a1 = new Asteroid(this.getXCoordinate() + this.getRadius()/2, this.getYCoordinate(), child1XVelocity, child1YVelocity, this.getRadius()/2);
			Asteroid a2 = new Asteroid(this.getXCoordinate() - this.getRadius()/2, this.getYCoordinate(), -child1XVelocity, -child1YVelocity, this.getRadius()/2);
			World w = getWorld();
			w.removeEntity(this);
			w.addEntity(a1);
			w.addEntity(a2);
			
			a1.setWorld(this.getWorld());
			a2.setWorld(this.getWorld());
		}
		else if (getWorld() != null){
			getWorld().removeEntity(this);
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
		return "[Planetoid] " + this.hashCode();
	}

	
	/**
	 *  Resolves a collision between this planetoid and another entity
	 * 	@param	entity
	 * 			The entity that will collide with this planetoid.
	 *	@effect	If the given entity is an instance of the Ship class, that ship will be teleported to a random location.
	 * 			| if (entity instanceof Ship)
	 * 			|	((Ship) entity).teleport()
	 *  @post	If the given entity is another Planetoid, update the planetoids's velocity to reflect a collision (see implementation)
	 *	@post	If the given entity is an instance of some other class, that entity will call his own collideWith method.
	 *			| if (entity !instanceof Ship)
	 *			|		entity.collideWith(this)

	 */
	@Override
	public void collideWith(Entity entity) {
		if (entity instanceof Ship) {
			((Ship) entity).teleport();
		}
		else if (entity instanceof Planetoid) {
			Planetoid planetoid1 = this;
			Planetoid planetoid2 = (Planetoid) entity;
			
			 
			double deltaVX = planetoid2.getXVelocity() - planetoid1.getXVelocity();
			double deltaVY = planetoid2.getYVelocity() - planetoid1.getYVelocity();
			double deltaRX = planetoid2.getXCoordinate() - planetoid1.getXCoordinate();
			double deltaRY = planetoid2.getYCoordinate() - planetoid1.getYCoordinate();
			double sigma = Math.sqrt(Math.pow(planetoid1.getXCoordinate()-planetoid2.getXCoordinate(), 2) + Math.pow(planetoid1.getYCoordinate()-planetoid2.getYCoordinate(), 2));
			
			double J = (2*planetoid1.getMass()*planetoid2.getMass()*(deltaVX * deltaRX + deltaVY * deltaRY))/(sigma*(planetoid1.getMass()+planetoid2.getMass()));
			double Jx = J*deltaRX/sigma;
			double Jy = J*deltaRY/sigma;
			
			planetoid1.setXVelocity(planetoid1.getXVelocity() + (Jx/planetoid1.getMass()));
			planetoid1.setYVelocity(planetoid1.getYVelocity() + (Jy/planetoid1.getMass()));
			
			planetoid2.setXVelocity(planetoid2.getXVelocity() - (Jx/planetoid2.getMass()));
			planetoid2.setYVelocity(planetoid2.getYVelocity() - (Jy/planetoid2.getMass()));
			return;
		}
		else
			entity.collideWith(this);
	}
	
}
