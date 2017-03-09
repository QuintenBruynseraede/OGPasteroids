package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

// METHODS IN THIS INTERFACE ARE ONLY ALLOWED TO THROW EXCEPTIONS OF TYPE ASTEROIDS.UTIL.MODELEXCEPTION
// FACADE IMPLEMENTATION CAN ONLY HAVE TRIVIAL CODE

public class Facade implements IFacade{

	@Override
	public Ship createShip() throws ModelException {
		Ship ship1 = new Ship(0, 0, Ship.getVelocityLowerBound(), 0, Ship.getRadiusLowerBound(), 0);
		return ship1;
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		try {
			Ship ship1 = new Ship(x, y, xVelocity, yVelocity, radius, orientation);
			return ship1;
		} catch (IllegalArgumentException e) {
			throw new ModelException("Radius invalid");
		} catch (AssertionError e) {
			throw new ModelException("orientation invalid");
		}
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		double x = ship.getXCoordinate();
		double y = ship.getYCoordinate();
		double[] position = {x, y};
		return position;
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		double xVelocity = ship.getXVelocity();
		double yVelocity = ship.getYVelocity();
		double[] velocity = {xVelocity, yVelocity};
		return velocity;
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		double radius = ship.getRadius();
		return radius;
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		double orientation = ship.getOrientation();
		return orientation;
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		try {
			ship.move(dt);
		} catch (IllegalArgumentException e) {
			throw new ModelException("Invalid time");
		}
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		ship.thrust(amount);		
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		if (ship == null)
			throw new ModelException("ship == null");
		
		try {
			ship.turn(angle);
		} catch (AssertionError e) {
			throw new ModelException("orientation invalid");
		}		
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		if (ship1 == null || ship2 == null)
			throw new ModelException("ship == null");
		
		return ship1.getDistanceBetween(ship2);
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		if (ship1 == null || ship2 == null)
			throw new ModelException("ship == null");
		
		return ship1.overlap(ship2);
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		if (ship1 == null || ship2 == null)
			throw new ModelException("ship == null");
		
		return ship1.getTimeToCollision(ship2);
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		if (ship1 == null || ship2 == null)
			throw new ModelException("ship == null");
		
		return ship1.getCollisionPosition(ship2);
	}
	
	
	
}
