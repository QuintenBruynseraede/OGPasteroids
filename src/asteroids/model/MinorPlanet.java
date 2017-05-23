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

	
	@Override
	public void finalize(){
		this.getWorld().removeEntity(this);
		this.finalized = true;
	}
	
	public abstract double getMass();
	
	@Override
	public void collideWith(GameObject object2, int collisionType) {
		if (collisionType == Constants.BOUNDARYCOLLISION) {
			Boundary boundary = (Boundary) object2;
			
			if (boundary.getBoundaryType() == Constants.BOTTOM || boundary.getBoundaryType() == Constants.TOP)
				this.setYVelocity(-this.getYVelocity());
			else 
				this.setXVelocity(-this.getXVelocity());
			return;
		}
		else if (collisionType == Constants.ENTITYCOLLISION) {
			if (object2 instanceof MinorPlanet) {
				MinorPlanet minorPlanet1 = this;
				MinorPlanet minorPlanet2 = (MinorPlanet) object2;
				 
				double deltaVX = minorPlanet1.getXVelocity() - minorPlanet2.getXVelocity();
				double deltaVY = minorPlanet1.getYVelocity() - minorPlanet2.getYVelocity();
				double deltaX = minorPlanet1.getXCoordinate() - minorPlanet2.getXCoordinate();
				double deltaY = minorPlanet1.getYCoordinate() - minorPlanet2.getYCoordinate();
				
				double ship1J = (2*minorPlanet1.getMass()*minorPlanet2.getMass() * (deltaVX * deltaX + deltaVY * deltaY)) / (minorPlanet1.getRadius() * (minorPlanet1.getMass() + minorPlanet2.getMass()));
				double ship1JX = (ship1J * deltaX) / minorPlanet1.getRadius();
				double ship1JY = (ship1J * deltaY) / minorPlanet1.getRadius();
				
				deltaVX = minorPlanet2.getXVelocity() - minorPlanet1.getXVelocity();
				deltaVY = minorPlanet2.getYVelocity() - minorPlanet1.getYVelocity();
				deltaX = minorPlanet2.getXCoordinate() - minorPlanet1.getXCoordinate();
				deltaY = minorPlanet2.getYCoordinate() - minorPlanet1.getYCoordinate();
				
				double ship2J = (2*minorPlanet1.getMass()*minorPlanet2.getMass() * (deltaVX * deltaX + deltaVY * deltaY)) / (minorPlanet2.getRadius() * (minorPlanet1.getMass() + minorPlanet2.getMass()));
				double ship2JX = (ship2J * deltaX) / minorPlanet2.getRadius();
				double ship2JY = (ship2J * deltaY) / minorPlanet2.getRadius();
				
				minorPlanet1.setXVelocity(minorPlanet1.getXVelocity() + ship1JX / minorPlanet1.getMass());
				minorPlanet1.setYVelocity(minorPlanet1.getYVelocity() + ship1JY / minorPlanet1.getMass());
				minorPlanet2.setXVelocity(minorPlanet2.getXVelocity() + ship2JX / minorPlanet2.getMass());
				minorPlanet2.setYVelocity(minorPlanet2.getYVelocity() + ship2JY / minorPlanet2.getMass());
				
				return;
			}
			else if (object2 instanceof Bullet) {
				Bullet b = (Bullet) object2;
				b.collideWith(this, Constants.ENTITYCOLLISION);
				return;
			}
			else if (object2 instanceof Ship) {
				Ship s = (Ship) object2;
				s.collideWith(this, Constants.ENTITYCOLLISION);
				return;
			}
			else
				throw new IllegalArgumentException("undefined collision in MinorPlanet.java");
		}
	}
	
	@Override
	public String toString() {
		return "[MinorPlanet] " + this;
	}
}
