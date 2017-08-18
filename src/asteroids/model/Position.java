package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Value;

/**
 * A class of a position in a 2D world, consisting of an X and Y coordinate.
 * @author  Quinten Bruynseraede
 * @version	1.0
 * @invar	| isValidCoordinate(x)
 * @invar	| isValidCoordinate(y)
 */
@Value
public class Position {
	
	/**
	 * Variable holding the x position of this position
	 */
	private final double x;
	
	/**
	 * Variable holding the y position of this position
	 */
	private final double y;
	
	/**
	 * Creates a new Position instance
	 * @param	x
	 * 			The position on the x axis
	 * @param	y
	 * 			The position on the y axis
	 * @throws 	IllegalArgumentException
	 * 			| !isValidCoordinate(x) || !isValidCoordinate(y)
	 * @post	| (new this).getX() == x;
	 * @post	| (new this).getY() == y;
	 */
	public Position(double x, double y) throws IllegalArgumentException {
		if (!isValidCoordinate(x) || !isValidCoordinate(y))
			throw new IllegalArgumentException("Non-valid coordinate for new Position instance.");
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns whether a certain coordinate x is valid for this Position
	 * @param 	x
	 * 		  	The value to be examined for validity
	 * @return	| result == (!Double.isInfinite(x) && !Double.isNan(x))
	 */
	private boolean isValidCoordinate(double x) {
		return (!Double.isInfinite(x) && !Double.isNaN(x));
	}
	
	/**
	 * Returns the X value associated with this Position
	 */
	@Basic
	public double getX() {
		return x;
	}
	
	/**
	 * Returns the Y value associated with this Position
	 */
	@Basic
	public double getY() {
		return y;
	}
	
	/**
	 * Returns true iff two positions are equals, i.e. their x and y components are equal
	 * @param	other
	 * 			The position examined for equality
	 * @return	| if (other == null)
	 * 		 	|	result == false
	 * @return	| if (! other instanceof Position)
	 * 			|	result == false
	 * @return	| if (other != null && other instanceof Position)
	 * 			|	result == (other.getX() == getX() && other.getY() == getY())
	 * @note	This method account for rounding errors by tolerating a 1% error margin.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		
		if (!(other instanceof Position))
			return false;
		
		Position otherPosition = (Position) other;
		return (Math.abs(getX() - otherPosition.getX()) < 0.01*getX() && Math.abs(getY() - otherPosition.getY()) < 0.01*getY());
	}
	
	/**
	 * Returns a hashcode representation for this Position
	 */
	@Override
	public int hashCode() {
		return (37 * (int) getX() + 29 * (int) getY());
	}
	
	/**
	 * Returns a string representation for this Position
	 */
	@Override
	public String toString() {
		return "[Position] (" + getX() + ", " + getY() + ")";
	}
}
