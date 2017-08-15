package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * Class containing an asteroid with some properties.
 * @author 	Tom De Backer and Quinten Bruynseraede
 * @version	1.0
 * 
 *  @invar	The radius of this asteroid will always be a valid radius for a asteroid.
 *  		| isValidRadius(getRadius())
 *  @invar	The following correlation between a planetoid's radius and its weight always applies.
 *  		| this.getMass() == (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Planetoid.MASSDENSITY
 *
 */
public class Asteroid extends MinorPlanet {

	/**
	 * 	Creates a new asteroid with a position, velocity, and a radius.
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
	 *  @effect	Initializes this Asteroid as a MinorPlanet with a position, velocity and radius.
	 *  		| super (x, y, xVelocity, yVelocity, radius)
	 *  @effect	| setMass((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * Asteroid.MASSDENSITY)
	 */
	@Raw
	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius) {
		super(x, y, xVelocity, yVelocity, radius);
		setMass(4*Math.PI/3);
		setMass(getMass() * MASSDENSITY * Math.pow(radius, 3));
	}

	/**
	 * Variable registering the mass of an asteroid in kilogrammes. The mass can be calculated as m = (4/3)pi*radius^3*massDensity
	 */
	private double mass;

	/**
	 * Returns the mass of this asteroid.
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
	@Immutable
	public double getMassDensity() {
		return Asteroid.MASSDENSITY;
	}
	
	/**
	 * 	Returns whether a given radius is valid for this Asteroid
	 * 	@param	radius
	 * 			The radius to check 
	 *  @see 	implementation
	 */
	public boolean isValidRadius(double radius) {
		return (radius >= getRadiusLowerBound());
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
	 * 	Returns the lower bound of this Asteroid.
	 * 	@returns	| Asteroid.RADIUSLOWERBOUND
	 */
	@Immutable
	public double getRadiusLowerBound() {
		return RADIUSLOWERBOUND;
	}
	
	/**
	 * 	The smallest radius an instance of the Asteroid class can have.
	 */
	private static double RADIUSLOWERBOUND = 5;

	/**
	 * 	Moves the Asteroid during a given time
	 * 	@param 	deltaTime
	 * 			The time during which the Asteroid is moved.
	 *  @effect		| move(deltaTime);
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
		return "[Asteroid] " + this.hashCode();
	}

	
	/**
	 *	Resolves a collision between this Asteroid and another entity
	 * 	@param	entity
	 * 			The entity that will collide with this Asteroid.
	 *	@post	If the given entity is instance of an Asteroid, the two asteroids will bounce off each other.
	 *			|	if (entity instanceof Asteroid)
	 *			|  		see implementation
	 *	@post	If the given entity is instance of a Planetoid, the asteroid and plantetoid will bounce off each other.
	 *			|if (entity instanceof Planetoid)
	 *			|		see implementation
	 *	@post	If the given entity is instance of Ship, the ship will be finalized.
	 *			| if (entity instanceof Ship)
	 *			|		entity.finalize()
	 *	@post	If the entity is not instance of a MinorPlanet Ship or Bullet, the entity will call his own collideWith method.
	 *			| if (entity is not instanceof {MinorPlanet, Ship, Bullet})
	 *			| 		entity.collideWith(this)
	 */
	@Override
	public void collideWith(Entity entity) {
		if (entity instanceof Asteroid) {
			Asteroid asteroid1 = this;
			Asteroid asteroid2 = (Asteroid) entity;
			
			 
			double deltaVX = asteroid2.getXVelocity() - asteroid1.getXVelocity();
			double deltaVY = asteroid2.getYVelocity() - asteroid1.getYVelocity();
			double deltaRX = asteroid2.getXCoordinate() - asteroid1.getXCoordinate();
			double deltaRY = asteroid2.getYCoordinate() - asteroid1.getYCoordinate();
			double sigma = Math.sqrt(Math.pow(asteroid1.getXCoordinate()-asteroid2.getXCoordinate(), 2) + Math.pow(asteroid1.getYCoordinate()-asteroid2.getYCoordinate(), 2));
			
			double J = (2*asteroid1.getMass()*asteroid2.getMass()*(deltaVX * deltaRX + deltaVY * deltaRY))/(sigma*(asteroid1.getMass()+asteroid2.getMass()));
			double Jx = J*deltaRX/sigma;
			double Jy = J*deltaRY/sigma;
			
			asteroid1.setXVelocity(asteroid1.getXVelocity() + (Jx/asteroid1.getMass()));
			asteroid1.setYVelocity(asteroid1.getYVelocity() + (Jy/asteroid1.getMass()));
			
			asteroid2.setXVelocity(asteroid2.getXVelocity() - (Jx/asteroid2.getMass()));
			asteroid2.setYVelocity(asteroid2.getYVelocity() - (Jy/asteroid2.getMass()));
			return;
		}
		
		if (entity instanceof Planetoid) {
			Asteroid asteroid1 = this;
			Planetoid planetoid2 = (Planetoid) entity;
			
			 
			double deltaVX = planetoid2.getXVelocity() - asteroid1.getXVelocity();
			double deltaVY = planetoid2.getYVelocity() - asteroid1.getYVelocity();
			double deltaRX = planetoid2.getXCoordinate() - asteroid1.getXCoordinate();
			double deltaRY = planetoid2.getYCoordinate() - asteroid1.getYCoordinate();
			double sigma = Math.sqrt(Math.pow(asteroid1.getXCoordinate()-planetoid2.getXCoordinate(), 2) + Math.pow(asteroid1.getYCoordinate()-planetoid2.getYCoordinate(), 2));
			
			double J = (2*asteroid1.getMass()*planetoid2.getMass()*(deltaVX * deltaRX + deltaVY * deltaRY))/(sigma*(asteroid1.getMass()+planetoid2.getMass()));
			double Jx = J*deltaRX/sigma;
			double Jy = J*deltaRY/sigma;
			
			asteroid1.setXVelocity(asteroid1.getXVelocity() + (Jx/asteroid1.getMass()));
			asteroid1.setYVelocity(asteroid1.getYVelocity() + (Jy/asteroid1.getMass()));
			
			planetoid2.setXVelocity(planetoid2.getXVelocity() - (Jx/planetoid2.getMass()));
			planetoid2.setYVelocity(planetoid2.getYVelocity() - (Jy/planetoid2.getMass()));
			return;
		}
		if (entity instanceof Ship) {
			entity.finalize();
		}
		else {
			entity.collideWith(this);
		}
		
	}
}
