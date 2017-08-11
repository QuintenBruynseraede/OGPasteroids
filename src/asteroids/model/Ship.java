package asteroids.model;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 *  A class of ships with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 * @version	1.0
 * 
 * @invar	The orientation of this ship is always a valid orientation for a ship.
 * 			| isValidOrientation(getOrientation())
 * @invar	The radius of this ship is always a valid radius for a ship.
 * 			| isValidRadius(getRadius())
 * @invar	The mass density of this ship is always greater than the minimum mass density specified.
 * 			| this.massDensity >= 1.42E12
 * @invar	The mass of a ship is always at least equal to the minimum mass for a certain radius.
 * 			| this.massShip >= 4.0*Math.PI*Math.pow(radius, 3)*1.42E12/3.0
 */


public class Ship extends Entity {

	/**
	 * 
	 * Initialize a new ship with given x and y coordinate.
	 * 
	 * @param 	x
	 * 			The X coordinate for this new ship.
	 * @param 	y
	 * 			The Y coordinate for this new ship.
	 * @param	xVelocity
	 * 			The Velocity in the X direction for this new ship.
	 * @param	yVelocity
	 * 			The Velocity in the Y direction for this new ship.
	 * @param	radius
	 * 			The radius for this new ship.
	 * @param	orientation
	 * 			The orientation for this new ship.
	 * @param	massDensity
	 * 			The massDensity for this new ship.
	 * @effect	Initializes this Ship as an Entity with a position, velocity and radius.
	 * 			| super(x, y, xVelocity, yVelocity, radius)
	 * @post	The orientation of this ship is set to the given orientation.
	 *			| new.orientation == this.orientation
	 * @post	The massdensity for this ship is set.
	 * 			| new.massDensity = max(massDensity, 1.42E12)
	 * 
	 * @post	If the provided mass for this ship is not a valid double value, a default value is applied.
	 * 			| if (Double.isNaN(mass) || Double.isInfinite(mass)) 
	 * 			| 	new.massShip = (4/3)*Math.PI*Math.pow(radius, 3)*massDensity
	 * @post	If the provided mass is too small (assuming a ship has a minimum mass density of 1.42E12 kg/m^3),
	 * 			the minimum possible value is applied.
	 * 			| if (mass < Math.PI * 4 / 3.0 * Math.pow(radius, 3) * 1.42E12)
	 *			| 	this.massShip = (4.0*Math.PI*Math.pow(radius, 3)*1.42E12/3.0);
	 * @post	If the two other cases don't apply, set the mass to the provided value.
	 * 			| this.massShip  = mass;
	 */
	@Raw
	public Ship (double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		setOrientation(orientation);
		
		if (! isValidRadius(radius))  
			throw new IllegalArgumentException("Non valid radius when initializing ship");
		this.radius = radius;
		
		if (Double.isNaN(mass) || Double.isInfinite(mass)) 
			this.massShip = (4*Math.PI*Math.pow(radius, 3)*1.42E12)/3.0;
			
		else {
			double minimalMass = Math.PI * 4 / 3.0 * Math.pow(radius, 3) * 1.42E12;
			if (mass < minimalMass)
				this.massShip = (4.0*Math.PI*Math.pow(radius, 3)*1.42E12/3.0);
			else
				this.massShip = mass;
			
		}
		
		
		
	}
	
	
	/**
	 * Variable registering the orientation of this ship expressed in radians.
	 */
	private double orientation;
	
	/**
	 * @param 	orientation
	 * 			The given orientation to check
	 * @return	True if and only if the orientation is greater than or equal to 0 and lower than pi.
	 * 			| result == orientation >= 0 && orientation < 2 * Math.PI
	 */
	@Raw
	public boolean isValidOrientation(double orientation) {
		return (orientation >= 0 && orientation < 2 * Math.PI);
	}
	
	/**
	 * @param 	orientation
	 * 			The new orientation for this ship.
	 * @pre		The orientation must be a valid angle in radians.
	 * 			| isValidOrientation(orientation)
	 * @post	The new orientation of the ship is equal to the given argument orientation.
	 * 			| new.orientation = orientation
	 */
	@Raw
	private void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * Returns the orientation of this ship.
	 */
	@Basic
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Add a given angle to the ship's orientation.
	 * @pre		The ship must be able to turn the given angle whilst ensuring the ship's new orientation is still 
	 * 			a valid orientation.
	 * 			isValidOrientation(this.orientation + angle)
	 * @param 	angle
	 * 			The given angle to turn.
	 */
	public void turn(double angle) {
		assert isValidOrientation(this.orientation + angle);
		this.orientation += angle;
	}
	
	
	
	
	/**
	 * variable registering the mass of a ship in kilograms. 
	 * @note	Not including any objects ship is carrying.
	 */
	private double massShip;		
	
	/**
	 * Returns the mass of this ship not including any entities it is carrying.
	 */
	@Basic
	public double getMassShip(){
		return this.massShip;
	}
	
	
	/**
	 * Returns the mass of this ship including any entities it is carrying.
	 */
	public double getMassTotal(){
		double totalMass = this.getMassShip();
		for (Bullet b : bulletsLoaded)
			totalMass += b.getMass();
		return totalMass;
	}
	
	/**
	 * Returns the mass density of this ship.
	 */
	@Basic
	public double getmassDensity(){
		return (3*this.getMassShip()/(4*Math.PI*Math.pow(this.getRadius(), 3)));
	}
	
	/**
	 *  A HashSet registering the bullets that are currently loaded by this ship.
	 */
	private HashSet<Bullet> bulletsLoaded = new HashSet<Bullet>();

	/**
	 * Returns a list of bullets loaded on this ship
	 * @return | {bullet1, bullet2, ...} where bulletN.getParent() == this
	 */
	public Set<Bullet> getBulletsLoaded() {
		return new HashSet<Bullet>(bulletsLoaded);
	}
	
	/**
	 *  Variable registering whether this ship's thruster is currently on.
	 */
	public boolean thrusterOn = false;
	
	/**
	 * Returns whether the ship's thruster is currently on.
	 * @return this.thrusterOn
	 */
	@Raw
	public boolean isThrusterEnabled() {
		return this.thrusterOn;
	}
	
	/**
	 * Sets the ship's thruster state to on.
	 */
	@Raw
	public void thrustOn() {
		this.thrusterOn = true;
	}
	
	/**
	 * Set the ship's thruster state to off.
	 */
	@Raw
	public void thrustOff() {
		this.thrusterOn = false;
	}
	
	/**
	 * Constant registering the force this ship's thruster exerts.
	 */
	public final double THRUSTERFORCE = 1.1e18;
	
	/**
	 * Returns the acceleration this ship is subsceptible to, making use of Newton's second law of motion (F = ma).
	 * @see implementation
	 */
	@Raw
	public double getAcceleration() {
		if (this.isThrusterEnabled())
			return THRUSTERFORCE / this.getMassTotal();
		else
			return 0;
	}
	
	/**
	 * Change the ship's velocity based on the current velocity and a specified amount of thrust.
	 * @param 	amount	
	 * 			The amount of thrust the ship generates.
	 * @post 	If the specified amount to thrust is an illegal value, that is a negative number, no thrust is generated
	 * 			| if ( amount < 0 )
	 *  		| 		this.thrust(0)
	 * @post 	If the specified amount of thrust would result in a speed greater than allowed for this spaceship,
	 * 			an adjusted amount of thrust is generated to ensure the ship's x and y velocity are maximised yet still valid.
	 * 			| if (totalVelocity > velocityUpperBound)
	 * 			|		new.xVelocity = this.xVelocity + amount * Math.cos(this.orientation)
	 * 			| 		new.yVelocity = this.yVelocity + amount * Math.sin(this.orientation)
	 * 			| 		double velocityRatio = this.getXVelocity()/this.getYVelocity()
	 *			| 		this.setYVelocity( Math.sqrt( (this.velocityUpperBound*this.velocityUpperBound) / (velocityRatio * velocityRatio + 1)))
	 *			| 		this.setXVelocity( velocityRatio * this.getYVelocity())
	 * @note	If YVelocity is equal to zero, the velocityRatio can be calculated. Therefore we only set XVelocity to velocityUpperBound, 
	 * 			as multiplying zero by a ratio would be redundant.
	 * @post	If a valid amount of thrust is specified, the ship's x and y velocity are updated accordingly
	 * 			| new.xVelocity = this.xVelocity + amount * Math.cos(this.orientation)
	 * 			| new.yVelocity = this.yVelocity + amount * Math.sin(this.orientation) 
	 */
	public void thrust(double amount) {
		if ( amount <= 0 )
			return; 
		else if (getTotalVelocity(this.getXVelocity() + amount * Math.cos(this.orientation), this.getYVelocity() + amount * Math.cos(this.orientation)) > VELOCITYUPPERBOUND) {
		
			this.setXVelocity(this.getXVelocity() + amount * Math.cos(this.orientation));
			this.setYVelocity(this.getYVelocity() + amount * Math.sin(this.orientation));
			
			if(this.getYVelocity() == 0) 
				this.setXVelocity(VELOCITYUPPERBOUND);
			else {
				double velocityRatio = this.getXVelocity()/this.getYVelocity();
				this.setYVelocity( Math.sqrt( (VELOCITYUPPERBOUND*VELOCITYUPPERBOUND) / (velocityRatio * velocityRatio + 1)));
				this.setXVelocity( velocityRatio * this.getYVelocity());
			}
		}
		else {
			this.setXVelocity(this.getXVelocity() + amount * Math.cos(this.orientation));
			this.setYVelocity(this.getYVelocity() + amount * Math.sin(this.orientation));
		}
	}
	
	/**
	 * Updates the velocity to the current acceleration.
	 * @post	this.thrust(getAcceleration())
	 */
	public void updateVelocity() {
		thrust(getAcceleration());
	}
	
	
	/**
	 * Removes a bullet from the list of bullets this ship is carrying.
	 * @param 	bullet
	 * 			The bullet to remove.
	 * @post	| bulletsLoaded.contains(bullet) == false
	 * @post	| bullet.setLoaded(false)
	 */
	@Raw
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (!bulletsLoaded.contains(bullet)) 
			throw new IllegalArgumentException("Can't remove bullet not on this ship.");
		bulletsLoaded.remove(bullet);
		bullet.setLoaded(false);
		//bullet.setWorld(null);
		//bullet.setParent(null);
		return;
	}
	
	/**
	 * Adds a bullet to the list of bullets this ship is carrying.
	 * @param 	bullet
	 * 			The bullet to be added.
	 * @post	| bulletsLoaded.contains(bullet) == true
	 * @post	| bullet.getParent() == this
	 */
	@Raw
	public void addBulletToLoaded(Bullet bullet) throws IllegalArgumentException {
		
		//System.out.println("addBulletToLoaded");
		bulletsLoaded.add(bullet);
		bullet.setParent(this);
	}
	
	/**
	 * Adds a list of bullets to the list of bullets this ship is carrying.
	 * @param 	bullets
	 * 			The collection of bullets to be added.
	 * @post	| For each Bullet b in bullets
	 * 			| 	bulletsLoaded.contains(b) == true
	 * @post	| For each Bullet b in bullets
	 * 			| 	b.getParent() == this
	 */
	@Raw
	public void addBulletToLoaded(Collection<Bullet> bullets) throws IllegalArgumentException {
		for (Bullet bullet : bullets) {
			if (bullet == null)
				throw new IllegalArgumentException("List of bullets contains null bullet");
			if (! this.bulletsLoaded.contains(bullet)) {
				bulletsLoaded.add(bullet);
				bullet.setParent(this);
			}
				
		}
	}

	/**
	 * Constant registering the firingspeed of this ship.
	 */
	public final int FIRINGSPEED = 250;
	
	/**
	 * Fires a bullet off this ship.
	 * @post	| new.bulletsLoaded.size() = old.bulletsLoaded.size() - 1
	 * @post	The position and velocity of one of the bullets are updated to reflect being launched.
	 * 			| bullet.setXVelocity(FIRINGSPEED * Math.cos(getOrientation()))
	 * 			| bullet.setYVelocity(FIRINGSPEED * Math.sin(getOrientation()))
	 *			| bullet.setXCoordinate(this.getXCoordinate() + (this.getRadius() + bullet.getRadius()) * Math.cos(this.getOrientation()))
	 *			| bullet.setYCoordinate(this.getYCoordinate() + (this.getRadius() + bullet.getRadius()) * Math.sin(this.getOrientation()))
	 * 	@throws IllegalArgumentException
	 * 			The bullet we're trying to fire is not loaded by this ship. For testing purposes, should never happen.
	 * 		
	 * 	@post	| bulletsLoaded.contains(bullet) == false
	 *  @post	| bullet.isLoaded() == false
	 */
	public void fire() throws IllegalArgumentException {
		if (this.getWorld() == null) return;
		
		if (this.bulletsLoaded.isEmpty())
			{return;}
		Bullet bullet = this.bulletsLoaded.iterator().next();
		
		//System.out.printf("BEFORE: position: (%f, %f), Velocity: (%f, %f)\n", bullet.getXCoordinate(), bullet.getYCoordinate(), bullet.getXVelocity(), bullet.getYVelocity());
		//System.out.println("BEFORE: world: " + bullet.getWorld());
		//System.out.println(getWorld().getEntities().size());
		//System.out.println(getWorld().getEntitiesOfType(Bullet.class).size());
		if (bulletsLoaded.contains(bullet) == false)
			{ throw new IllegalArgumentException("Firing bullet that is not loaded."); }
		
		
		this.removeBullet(bullet);
		bullet.setLoaded(false);
		bullet.setWorld(getWorld());
		getWorld().addEntity(bullet);
		
		//assert getWorld().getEntities().contains(bullet);
		//assert !bullet.isLoaded();
		//assert bullet.getWorld() == getWorld();
		//assert bullet.getXVelocity() != 0;
		//assert bullet.getYVelocity() != 0;
		
		//System.out.println("Bullets left: " + bulletsLoaded.size());
		
		bullet.setXVelocity(FIRINGSPEED * Math.cos(getOrientation()));
		bullet.setYVelocity(FIRINGSPEED * Math.sin(getOrientation()));
		
		
		bullet.setXCoordinate(this.getXCoordinate() + (this.getRadius() + bullet.getRadius()) * Math.cos(this.getOrientation()));
		bullet.setYCoordinate(this.getYCoordinate() + (this.getRadius() + bullet.getRadius()) * Math.sin(this.getOrientation()));
		
		for (Entity e: getWorld().getEntities()) {
			if (bullet.overlap(e) && e != bullet) {
				System.out.println("Removing bullet on launch");
				bullet.finalize();
			}
		}

		//System.out.printf("Fired bullet from ship at (%f, %f)\n", getXCoordinate(), getYCoordinate());
		//System.out.printf("Inital position: (%f, %f), Velocity: (%f, %f)\n", bullet.getXCoordinate(), bullet.getYCoordinate(), bullet.getXVelocity(), bullet.getYVelocity());
		//System.out.println("World: " + bullet.getWorld());
		//System.out.println(getWorld().getEntities().size());
		//System.out.println(getWorld().getEntitiesOfType(Bullet.class).size());
	}
	

	/**
	 * Returns whether a given radius is a valid radius for a ship.
	 * @param 	radius
	 * 			The given radius to check.
	 * @return	True if and only if the velocity is greater than the minimum value specified for a ship's radius.
	 * 			| result == radius > this.getRadiusLowerBound()
	 */
	@Override
	@Raw
	public boolean isValidRadius(double radius) {
		return (radius >= this.getRadiusLowerBound());
	}

	/**
	 * Updates the radius of this ship.
	 * @param 	radius
	 * 			The new radius for this ship.
	 * @post	The new radius of the ship is equal to the given argument radius.
	 * 			| new.radius = radius
	 * @throws	IllegalArgumentException
	 * 			| !isValidRadius(radius)
	 */
	@Override
	public void setRadius(double radius) {
		if (isValidRadius(radius))
			this.radius = radius;
		else
			throw new IllegalArgumentException("Non valid radius.");
	}

	/**
	 * Constant registering the radius lower bound of a ship.
	 */
	private static final double RADIUSLOWERBOUND = 1;
	
	/**
	 * Returns the lower bound for the radius of a ship.	
	 */
	@Override @Raw @Basic @Immutable
	public double getRadiusLowerBound() {
		return RADIUSLOWERBOUND;
	}
	
	
	/**
	 * Moves this ship for a given delta time.
	 * @effect	move(deltaTime)
	 * @post	| if (this.isThrusterEnabled())
	 * 			| 	this.updateVelocity()
	 */
	@Override
	public void advance(double deltaTime) {
		move(deltaTime);
		if (isThrusterEnabled()) 
			updateVelocity();
	}



	/**
	 * Teleports this ship to a random place in its world and destroys all its entities.
	 * @post	| new.setXCoordinate(this.getRadius() + r.nextInt() % (this.getWorld().getWidth() - 2 * this.getRadius()))
	 * @post 	| new.setYCoordinate(this.getRadius() + r.nextInt() % (this.getWorld().getHeight() - 2 * this.getRadius()))
	 * @effect	| for each entity i: getWorld().getEntities()
	 * 			|	if(i.overlap(this))
	 *			| 		this.finalize();
	 */
	public void teleport() {
		Random r = new Random();

		this.setXCoordinate(this.getRadius() + r.nextInt() % (this.getWorld().getWidth() - 2 * this.getRadius()));
		this.setYCoordinate(this.getRadius() + r.nextInt() % (this.getWorld().getHeight() - 2 * this.getRadius()));
		
		for (Entity i : this.getWorld().getEntities()) {
			if(i.overlap(this) && i != this) {
				System.out.println("Finalizing ship because of overlap on teleport.");
				this.finalize();
			}
		}		
	}
	
	/**
	 * 	Variable holding the program this ship has currently loaded. A null value means no program is loaded at the moment.
	 */
	private Program program = null;
	
	/**
	 * 	Loads a program on this ship.
	 */
	@Basic
	@Raw
	public void setProgramLoaded(Program program) {
		this.program = program;
	}
	
	/**
	 * 	Return the program loaded on this ship
	 */
	@Basic
	@Raw
	public Program getProgramLoaded() {
		return this.program;
	}
	
	/**
	 * 	Executes the program loaded on this ship
	 *  @return	List containing all objects printed out by the program.
	 * 
	 */
	@Raw
	public List<Object> executeProgram(double dt) {
		List<Object> r = this.getProgramLoaded().execute(dt);
		return r;
	}
	
	/**
	 * Finalizes the bullet, preparing it to be removed by the garbage collector.
	 * @post	| for each bullet: getBulletsLoaded()
	 * 			| 	bullet.parent == null
	 * @post	| this.getWorld().removeEntity(this)
	 * @post	| new.finalized == true
	 */
	@Override
	@Raw
	public void finalize() {
		
		if (! bulletsLoaded.isEmpty()) {
			for (Bullet b : bulletsLoaded) {
				b.setParent(null);
			}
		}
		System.out.println(getWorld());
		this.getWorld().removeEntity(this);
		this.finalized = true;
	}
	
	/**
	 * Returns a string representation of a ship.
	 * 
	 * @return	A string representation of a ship.
	 */
	@Override
	@Raw
	public String toString() {
		return "[Ship] " + this.hashCode();
	}

	
	
	/**
	 *  Resolves a collision between this ship and another entity
	 * 	@param	entity
	 * 			The entity that will collide with this ship.
	 *	@post	If the given entity is instance of a Ship, they will bounce off each other.
	 * 			| if (entity instanceof Ship)
	 * 			|	@see implementation
	 *	@post	If the given entity is not instance of Ship, that entity will call his own collideWith method.
	 *			| if (entity !instanceof Ship)
	 *			|		entity.collideWith(this);
	 */
	@Override
	public void collideWith(Entity entity) {
		if (entity instanceof Ship) {
			//System.out.println("Collision ship ship");
			Ship ship1 = this;
			Ship ship2 = (Ship) entity;
			
			//System.out.println(ship1.getMassTotal());
			//System.out.println(ship2.getMassTotal());
			 
			double deltaVX = ship2.getXVelocity() - ship1.getXVelocity();
			double deltaVY = ship2.getYVelocity() - ship1.getYVelocity();
			double deltaRX = ship2.getXCoordinate() - ship1.getXCoordinate();
			double deltaRY = ship2.getYCoordinate() - ship1.getYCoordinate();
			double sigma = Math.sqrt(Math.pow(ship1.getXCoordinate()-ship2.getXCoordinate(), 2) + Math.pow(ship1.getYCoordinate()-ship2.getYCoordinate(), 2));
			
			double J = (2*ship1.getMassTotal()*ship2.getMassTotal()*(deltaVX * deltaRX + deltaVY * deltaRY))/(sigma*(ship1.getMassTotal()+ship2.getMassTotal()));
			double Jx = J*deltaRX/sigma;
			double Jy = J*deltaRY/sigma;
			
			ship1.setXVelocity(ship1.getXVelocity() + (Jx/ship1.getMassTotal()));
			ship1.setYVelocity(ship1.getYVelocity() + (Jy/ship1.getMassTotal()));
			ship2.setXVelocity(ship2.getXVelocity() - (Jx/ship2.getMassTotal()));
			ship2.setYVelocity(ship2.getYVelocity() - (Jy/ship2.getMassTotal()));
			
			return;
		}
		else 
			entity.collideWith(this);
	}
}
