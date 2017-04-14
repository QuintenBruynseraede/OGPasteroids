
package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Value;
/**
* Simple class to wrap information about a collision in a usable format. 
* @note Collision objects are only used to pass information about a certain collision to a next method, 
* 	therefore no checking of the collisions' data has been implemented.
*/
@Value
public class Collision {\
	
	/**
	* Creates a new collision object
	* @param object1
	* 	 The first object involved in this collision.
	* @param object2
	*	 The second object involved in this collision.
	* @param x
	*	 the x position at which this collision will occur.
	* @param y
	*	 the y position at which this collision will occur.
	* @param type
	*	 this collision's type. Collision types are defined in Constants.java
	* @post  | new.getObject1() == object1
	* @post  | new.getObject2() == object2
	* @post  | new.getX() == x
	* @post  | new.getY() == y
	* @post  | new.getTime() == time
	* @post  | new.getType() == type
	**/
	public Collision(GameObject object1, GameObject object2, double x, double y, double time, int type) {
		this.setObject1(object1);
		this.setObject2(object2);
		this.setX(x);
		this.setY(y);
		this.setTime(time);
		this.setType(type);
	}
	
	/**
	* Variable representing the collision's x position.
	**/
	private double x;
			
	/**
	* Variable representing the collision's y position.
	**/
	private double y;
	
	/**
	* Variable representing the first object involved in this collision.
	**/		
	private GameObject object1;
			
	/**
	* Variable representing the second object involved in this collision.
	**/
	private GameObject object2;
			
	/**
	* Variable representing the time after which the collision will occur (given the state of the world doesn't change).
	**/
	private double time;
			
	/**
	* Variable representing the type of this collision. Collision types are defined in Constants.java.
	**/
	private int type;
	
	/**
	 * Return the X coordinate of this collision.
	 */
	@Basic
	public double getX() {
		return this.x;
	}
	
	/**
	 * Return the Y coordinate of this collision.
	 */
	@Basic
	public double getY() {
		return this.y;
	}
	
	/**
	 * Return the time to this collision.
	 */
	@Basic
	public double getTime() {
		return this.time;
	}
	
	/**
	 * Return the first object of this collision.
	 */
	@Basic
	public GameObject getObject1() {
		return this.object1;
	}
	
	/**
	 * Return the second object of this collision.
	 */
	@Basic
	public GameObject getObject2() {
		return this.object2;
	}
	
	/**
	 * Return the type of this collision.
	 */
	@Basic
	public int getType() {
		return this.type;
	}
	
	/**
	* Sets the collision's X coordinate.
	**/
	@Basic
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	* Sets the collision's Y coordinate.
	**/
	@Basic		
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	* Sets the collision's first object.
	**/
	@Basic		
	public void setObject1(GameObject object) {
		this.object1 = object;
	}
	
	/**
	* Sets the collision's second object.
	**/
	@Basic		
	public void setObject2(GameObject object) {
		this.object2 = object;
	}
	
	/**
	* Sets the collision's time.
	**/
	@Basic
	public void setTime(double time) {
		this.time = time;
	}
	
	/**
	* Sets the collision's type.
	**/
	@Basic
	public void setType(int type) {
		this.type = type;
	}
	
}
