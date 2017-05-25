package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
* A class representing a GameObject, the most basic object in our game.
* 
* @version	1.0
* @author Tom De Backer and Quinten Bruynseraede
*/
public abstract class GameObject {
	/**
	* Variable representing the type of this GameObject. GameObject types are defined in Constants.java.
	*/
	private final int type;
	
	/**
	* Creates a new GameObject that has a type.
	* @param 	type
	* 			The GameObject's type. GameObject types are defined in Constants.java
	**/
	protected GameObject(int type) {
		this.type = type;
	}
	
	/**
	* Creates a new GameObject. If no type is specified, we assume it is a boundary.
	**/
	public GameObject() {
		type = Constants.BOUNDARY;
	}
	
	/**
	* Return this GameObject's type
	**/
	@Basic
	public int getType() {
		return this.type;
	}
	
	/**
	 * Returns a string representation of a gameObject.
	 * 
	 * @return	A string representation of a gameObject.
	 */
	public String toString() {
		return "[GameObject] " + this;
	}
}