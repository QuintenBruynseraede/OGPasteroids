package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class of Minor planets with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */


public abstract class MinorPlanet extends Entity {
	
	
	public MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius) {
		super(x, y, xVelocity, yVelocity, radius);
	}

	
	
}
