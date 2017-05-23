package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
* A Class wrapping the abstract concept of a world's boundary in a usable GameObject
*/

public class Boundary extends GameObject {
	/**
	* Variable representing the type of this boundary. Boundary types are defined in Constants.java
	*/
	public final int boundaryType;
	
	/**
	* Creates a new boundary of a certain type.
	* @param the type of this boundary
	* @post  new.getBoundaryType() == type
	*/
	public Boundary(int type) {
			this.boundaryType = type;
	}
	
	/**
	* Returns the type of this boundary.
	*/
	@Basic
	public int getBoundaryType() {
		return this.boundaryType;
	}
	
	@Override
	public String toString() {
		return "[Boundary] " + this;
	}
}

