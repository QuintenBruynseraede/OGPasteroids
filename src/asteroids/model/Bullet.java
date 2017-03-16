package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;


/**
 * A class of bullets with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */

public class Bullet {
	 
	/**
	 * 
	 * @param x
	 * @param y
	 * @param xVelocity
	 * @param yVelocity
	 * @param radius
	 * @param ship
	 * @throws IllegalArgumentException
	 */
	public Bullet (double x, double y, double xVelocity, double yVelocity, double radius, Ship ship) throws IllegalArgumentException {
	
	}
	
	/**
	 * Variable registering the X coordinate of this bullet expressed in kilometres.
	 */
	private double x;
	
	/**
	 * Variable registering the Y coordinate of this bullet expressed in kilometres.
	 */
	private double y;
	
	
	/**
	 * Return the X coordinate of this bullet expressed in kilometres.
	 */
	@Basic
	public double getXCoordinate() {
		return this.x;
	}
	
	/**
	 * Return the Y coordinate of this bullet expressed in kilometres.
	 */
	@Basic
	public double getYCoordinate() {
		return this.y;
	}
	
	/**
	 * 
	 * @param x
	 */
	private void setXCoordinate(double x) {
		this.x = x;
	}
}
