package asteroids.model;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import be.kuleuven.cs.som.annotate.Basic;

/**
 *  A class of ships with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 */
//TODO: modify documentation for all methods
//TODO if the bullet’s initial position is already
// (partially) occupied by another entity, then the bullet immediately collides
// with that entity

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
	 * @post   	The X coordinate of this new ship is equal to the given X coordinate.
	 *       	| new.getXCoordinate() == x
	 * @post   	The Y coordinate of this new ship is equal to the given Y coordinate.
	 *       	| new.getYCoordinate() == y
	 * @post   	The x velocity of this new ship is equal to the given X velocity.
	 *       	| new.getXVelocity() == xVelocity
	 * @post   	The Y velocity of this new ship is equal to the given Y velocity.
	 *       	| new.getYVelocity() == yVelocity
	 * @post	The radius of this new ship is set to the given radius if valid, or a predefined lower bound otherwise.
	 * 			| new.getRadius() == max(Ship.RADIUSLOWERBOUND, radius)
	 * @post	The orientation of this ship is set to the given orientation.
	 *			| new.orientation == this.orientation
	 * @post	The massdensity for this ship is set.
	 * 			| new.massDensity = max(massDensity, 1.42E12)
	 * @post	The mass for this ship is set.
	 * 			| new.massShip = (4/3)*Math.PI*Math.pow(radius, 3)*massDensity
	 */
	public Ship (double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double massDensity) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		
		setOrientation(orientation);
		
		if (massDensity < 1.42E12)
			this.massDensity = 1.42E12;
		else
			this.massDensity = massDensity;
		
		this.massShip = (4/3)*Math.PI*Math.pow(radius, 3)*massDensity;
		
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
	private void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * This method returns the orientation of this ship.
	 */
	@Basic
	public double getOrientation() {
		return this.orientation;
	}
	

	/**
	 * Move the ship depending on ship's position, velocity and a given time duration.
	 * @param 	time
	 * 			The given time to move.
	 * @post	The X and Y coordinates are updated according to the ship's respective xVelocity and yVelocity.
	 * 			| new.x = this.x + time * this.xVelocity
	 * 			| new.y = this.y + time * this.yVelocity
	 * @throws 	IllegalArgumentException
	 * 			The given time is not positive.
	 * 			| time < 0
	 */
	public void move(double time) throws IllegalArgumentException {
		if (time < 0.01) {
			System.out.println(time);
			throw new IllegalArgumentException("Argument time must be positive");
		
		}
		else {
			setXCoordinate(this.getXCoordinate() + time * this.getXVelocity());
			setYCoordinate(this.getYCoordinate() + time * this.getYVelocity());
		}
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
	@Basic
	public double getMassTotal(){
		double totalMass = this.getMassShip();
		for (Bullet b : bulletsLoaded)
			totalMass += b.getMass();
		return totalMass;
	}
	
	/**
	 * variable registering the mass density of this ship.
	 */
	private double massDensity;
	
	/**
	 * Returns the mass density of this ship.
	 */
	@Basic
	public double getmassDensity(){
		return this.massDensity;
	}
	
	public void setMassDensity(double massDensity) {
		this.massDensity = massDensity;
		this.massShip = (4/3)*Math.PI*Math.pow(this.getRadius(), 3)*massDensity;
	}
	
	/**
	 *  A HashSet registering the bullets that are currently loaded by this ship.
	 */
	public HashSet<Bullet> bulletsLoaded = new HashSet();

	/**
	 *  Variable registering whether this ship's thruster is currently on.
	 */
	public boolean thrusterOn = false;
	
	/**
	 * Returns whether the ship's thruster is currently on.
	 * @return this.thrusterOn
	 */
	public boolean isThrusterEnabled() {
		return this.thrusterOn;
	}
	
	/**
	 * Sets the ship's thruster state to on.
	 */
	public void thrustOn() {
		this.thrusterOn = true;
	}
	
	/**
	 * Set the ship's thruster state to off.
	 */
	public void thrustOff() {
		this.thrusterOn = false;
	}
	
	/**
	 * Variable registering the force this ship's thruster exerts.
	 */
	public final double THRUSTERFORCE = 1.1e21;
	
	/**
	 * Returns the acceleration this ship is subsceptible to, making use of Newton's second law of motion (F = ma).
	 * @see implementation
	 */
	public double getAcceleration() {
		return THRUSTERFORCE / this.getMassTotal();
	}
	
	/**
	 * Removes a bullet from the list of bullets this ship is carrying.
	 * @param 	bullet
	 * 			The bullet to remove.
	 * @post	| bulletsLoaded.contains(bullet) == false
	 */
	public void removeBullet(Bullet bullet) {
		for (Bullet b : bulletsLoaded) {
			if (b.equals(bullet)) {
				bulletsLoaded.remove(b);
				b.isLoaded = false;
				return;
			}
		}
		
	}
	
	/**
	 * Adds a bullet to the list of bullets this ship is carrying.
	 * @param 	bullet
	 * 			The bullet to be added.
	 * @post	| bulletsLoaded.contains(bullet) == true
	 * @post	| bullet.getParent() == this
	 */
	public void addBulletToLoaded(Bullet bullet) {
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
	public void addBulletToLoaded(Collection<Bullet> bullets) {
		for (Bullet bullet : bullets) {
			if (! this.bulletsLoaded.contains(bullet)) {
				bulletsLoaded.add(bullet);
				bullet.setParent(this);
			}
				
		}
	}

	public final int FIRINGSPEED = 250;
	
	public void fire() throws IllegalArgumentException {
		if (this.getWorld() == null) return;
		
		if (this.bulletsLoaded.isEmpty())
			{ return;}
		Bullet bullet = this.bulletsLoaded.iterator().next();

		
		if (bulletsLoaded.contains(bullet) == false)
			{ throw new IllegalArgumentException("Firing bullet that is not loaded."); }
		
		
		this.removeBullet(bullet);
		//System.out.println(bulletsLoaded);
		bullet.isLoaded = false;
		//System.out.println(bullet.isBulletLoaded());
		
		bullet.setXVelocity(FIRINGSPEED * Math.cos(getOrientation()));
		bullet.setYVelocity(FIRINGSPEED * Math.sin(getOrientation()));
		
		
		bullet.setXCoordinate(bullet.getXCoordinate() + (this.getRadius() + bullet.getRadius() + 50) * Math.cos(this.getOrientation()));
		bullet.setYCoordinate(bullet.getYCoordinate() + (this.getRadius() + bullet.getRadius() + 50) * Math.sin(this.getOrientation()));
		//bullet.setXCoordinate(100);
		//bullet.setYCoordinate(100);
	
//		if (bullet.getXCoordinate() < bullet.getRadius() 
//			|| bullet.getYCoordinate() < bullet.getRadius() 
//			|| bullet.getXCoordinate() + bullet.getRadius() > this.getWorld().WIDTHUPPERBOUND 
//			|| bullet.getYCoordinate() + bullet.getRadius() > this.getWorld().HEIGHTUPPERBOUND) {
//			bullet.finalize();
//		}
//		
		
	}
	
	public void updateVelocity() {
		this.thrust(getAcceleration());
	}
	
	public boolean finalized = false;
	
	public void finalize() {
		if (! bulletsLoaded.isEmpty()) {
			for (Bullet b : bulletsLoaded) {
				b.setParent(null);
			}
		}

		this.getWorld().removeShip(this);
		this.finalized = true;
	}
	
	public boolean isTerminated() {
		return this.finalized;
	}
	
}
