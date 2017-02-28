package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;

/**
 *  A class of ships with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */
// TO DO List
// @Effect lezen voor setXVelocity en setYVelocity

public class Ship {
	
	/**
	 * 
	 * Initialize a new ship with given x and y coordinate.
	 * 
	 * @param 	xCoordinate
	 * 			The X coordinate for this new ship.
	 * @param 	yCoordinate
	 * 			The Y coordinate for this new ship.
	 * @post   	The X coordinate of this new ship is equal to the given X coordinate.
	 *       	| new.getXCoordinate() == xCoordinate
	 * @post   	The Y coordinate of this new ship is equal to the given Y coordinate.
	 *       	| new.getYCoordinate() == yCoordinate
	 */
	public Ship (double x, double y, double xVelocity, double yVelocity, double radius, double orientation){
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Variable registering the X coordinate of this ship expressed in kilometres.
	 */
	private double x;
	
	/**
	 * Variable registering the Y coordinate of this ship expressed in kilometres.
	 */
	private double y;
	
	
	/**
	 * Return the X coordinate of this ship.
	 */
	@Basic
	public double getXCoordinate(){
		return this.x;
	}
	
	/**
	 * Return the Y coordinate of this ship.
	 */
	@Basic
	public double getYCoordinate(){
		return this.y;
	}
	
	/**
	 * 
	 * @param 	xCoordinate
	 * 			The new X coordinate for this ship.
	 * @post	The X coordinate of this ship is equal to the given X coordinate.
	 * 
	 */
	private void setXcoordinate(double x){
		this.x = x;
	}
	
	/**
	 * 
	 * @param 	yCoordinate
	 * 			The new Y coordinate for this ship.
	 * @post	The Y coordinate of this ship is equal to the given X coordinate.
	 * 
	 */
	private void setYCoordinate(double y){
		this.y = y;
	}
	
	/**
	 * Variable registering the X velocity of this ship expressed in kilometres per second.
	 */
	private double xVelocity;
	
	/**
	 * Variable registering the Y velocity of this ship expressed in kilometres per second.
	 */
	private double yVelocity;
	
	/**
	 * Return the X velocity of this ship.
	 */
	@Basic
	public double getXVelocity(){
		return this.xVelocity;
	}
	
	/**
	 * Return the Y velocity of this ship.
	 */
	@Basic
	public double getYVelocity(){
		return this.yVelocity;
	}
	
	/**
	 * 
	 * @param 	xVelocity
	 * 			The new velocity of X for this ship.
	 * 
	 * @post	If xVelocity is a valid velocity for this ship, then the velocity X of this ship is equal to the given X velocity
	 * 			| if(isValidVelocity(xVelocity)
	 * 			| 	new.xVelocity = xVelocity
	 * 
	 * @post	If xVelocity is not a valid velocity for this ship and xVelocity is lower than velocityLowerBound, then the velocity X of this ship is equal to velocityLowerBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity < velocityLowerBound)
	 * 			| 	new.xVelocity = velocityLowerBound
	 * 
	 * @post	If xVelocity is not a valid velocity for this ship and xVelocity is higher than velocityUpperBound, then the velocity X of this ship is equal to velocityUpperBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity > velocityUpperBound)
	 * 			| 	new.xVelocity = velocityUpperBound
	 * 
	 */
	private void setXVelocity(double xVelocity){
		this.xVelocity = xVelocity;
	}
	
	/**
	 * 
	 * @param 	yVelocity
	 * 			The new velocity of Y for this ship.
	 * 
	 * @post	If yVelocity is a valid velocity for this ship, then the velocity Y of this ship is equal to the given Y velocity
	 * 			| if(isValidVelocity(yVelocity)
	 * 			| 	new.yVelocity = yVelocity
	 * 
	 * @post	If yVelocity is not a valid velocity for this ship and yVelocity is lower than velocityLowerBound, then the velocity Y of this ship is equal to velocityLowerBound.
	 * 			| if(! isValidVelocity(xVelocity) && xVelocity < velocityLowerBound)
	 * 			| 	new.xVelocity = velocityLowerBound
	 * 
	 * @post	If yVelocity is not a valid velocity for this ship and yVelocity is higher than velocityUpperBound, then the velocity Y of this ship is equal to velocityUpperBound.
	 * 			| if(! isValidVelocity(yVelocity) && yVelocity > velocityUpperBound)
	 * 			| 	new.yVelocity = velocityUpperBound
	 * 
	 */
	private void setYVelocity(double yVelocity){
		this.yVelocity = yVelocity;
	}
	
	private final double velocityLowerBound = 0;
	private final double velocityUpperBound = 300000;
	
	/**
	 * 
	 * @param 	velocity
	 * 			The given velocity to check.
	 * @return	True if and only if the velocity is greater than velocityLowerBound and lower than velocityLowerBound.
	 * 			| result == (velocity > this.velocityLowerBound && velocity < this.velocityUpperBound)
	 */
	private boolean isValidVelocity(double velocity) {
		return ( velocity > this.velocityLowerBound && velocity < this.velocityUpperBound );
	}
	
}
