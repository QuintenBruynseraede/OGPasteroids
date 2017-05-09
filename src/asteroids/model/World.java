package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.*;

import asteroids.part2.CollisionListener;


/**
 * A class of worlds with some properties.
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */


public class World extends GameObject {
	
	/**
	 * Creates a world with a given height and width
	 * @param 	width		
	 * 			The width for this new world.
	 * @param 	height	
	 * 			The height for this new world.
	 * @see		implementation
	 * @invar 	| this.height <= HEIGTHUPPERBOUND
	 * @invar	| this.width <= WIDTHUPPERBOUND
	 * @invar 	| for each Ship s in ships
	 * 			|	s.getWorld() == this
	 * @invar 	| for each Bullet b in bullet
	 * 			|	b.getWorld() == this
	 */
	public World (double width, double height) {
		super(Constants.WORLD);
		if (width < 0 )
			this.width = 0;
		else if (width > WIDTHUPPERBOUND)
			this.width = WIDTHUPPERBOUND;
		else
			this.width = width;
		
		if (height < 0 )
			this.height = 0;
		else if (height > HEIGHTUPPERBOUND)
			this.height = HEIGHTUPPERBOUND;
		else
			this.height = height;
		
		Boundary boundaryLeft = new Boundary(Constants.LEFT);
		Boundary boundaryRight = new Boundary(Constants.RIGHT);
		Boundary boundaryBottom = new Boundary(Constants.BOTTOM);
		Boundary boundaryTop = new Boundary(Constants.TOP);
		
		boundaries[0] = boundaryLeft;
		boundaries[1] = boundaryRight;
		boundaries[2] = boundaryBottom;
		boundaries[3] = boundaryTop;
	}
	
	/**
	 * Variable registering the width of this world expressed in kilometres.
	 */
	private final double width;
	
	/**
	 * Variable registering the height of this world expressed in kilometres.
	 */
	private final double height;
	
	public final static double WIDTHUPPERBOUND = Double.MAX_VALUE;
	public final static double HEIGHTUPPERBOUND = Double.MAX_VALUE;
	
	/**
	 * Return the width of this ship expressed in kilometres.
	 */
	@Basic
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Return the height of this ship expressed in kilometres.
	 */
	@Basic
	public double getHeight() {
		return this.height;
	}
	/**
	 * Set registering the entity contained by this world
	 */	
	private Set<Entity> entities = new HashSet<Entity>();
	


//	/**
//	 * Removes a given ship object from this world.
//	 * @param 	ship
//	 * 			The ship to remove from this world.
//	 * @throws 	IllegalArgumentException
//	 * 			The argument contains no correct reference to an object of the Ship class.
//	 * @throws	IllegalArgumentException
//	 * 			The list containing all ships in this world does not contain the ship provided as an argument.
//	 * @post	| ! ships.contains(ship)
//	 */
//	@Raw
//	public void removeShip(Ship ship) throws IllegalArgumentException {
//		if (!(ship instanceof Ship)) 
//			throw new IllegalArgumentException("Trying to remove non ship object.");
//
//		if (! this.getShips().contains(ship))
//			throw new IllegalArgumentException("Trying to remove ship that is not in this world.");
//		
//		
//		entities.remove(ship);
//	}
	
	
	/**
	 * 
	 * @param 	entity
	 * 			The entity to be added to this world.
	 * @post	| new.getEntities().contains(entity)
	 */
	public void addEntity(Entity e) {
		entities.add(e);
	}

	/**
	 * Removes a given entity from this world.
	 * @param 	entity
	 * 			The entity to remove from this world.
	 * @throws 	IllegalArgumentException
	 * 			The argument contains no correct reference to an object of the Entity class.
	 * @throws	IllegalArgumentException
	 * 			The list containing all entity in this world does not contain the entity provided as an argument.
	 * @post	| ! entities.contains(entity)
	 */
	@Raw
	public void removeEntity(Entity entity) throws IllegalArgumentException {
		if (!(entity instanceof Entity)) 
			throw new IllegalArgumentException("Trying to remove object that is no entity in removeEntity");
		
		if (! this.entities.contains(entity))
			throw new IllegalArgumentException("Trying to remove entity that is not in this world.");
		
		entity.setWorld(null);
		entities.remove(entity);
	}
	
	/**
	 * 
	 * @param 	x
	 * 			The X coordinate to check for presence of ship and bullet.
	 * @param 	y
	 * 			The Y coordinate to check for presence of ship and bullet.
	 * @return	Returns the entity at position x, y.
	 * 			| result == Entity e
	 * 			| e.getXCoordinate() == x
	 * 			| e.getYCoordinate() == y
	 * @return	returns null, if there is no entity at position x,y
	 * 			| result == null
	 * 			
	 */
	public Entity getInstanceAtPosition(double x, double y) {
		for (Entity e : this.getEntities()) {
			if (e.getXCoordinate() > 0.99 * x && e.getXCoordinate() < 1.01 * x && e.getYCoordinate() > 0.99 * y && e.getYCoordinate() < 1.01 * y) {
				return e;
			}
		}
		return null;
	}
	
	public Set<? extends Entity> getEntitiesOfType(Class<? extends Entity> c) {
		Set<Entity> result = new HashSet<Entity>();
		
		for (Entity e : this.getEntities()) {
			if (e.getClass() == c)
				result.add(e);	
		}
		return result;
	}
	
	/**
	 * Returns a list of all entities in this world.
	 * 
	 * @return 	A list of all entities in this world.
	 * 			| { entity1, entity2, ..., entityN | entityI.getWorld() = this}
	 */
	public Set<Entity> getEntities() {
		return this.entities;
	}
	
	/**
	 * Array containing the four boundaries of this world.
	 */
	private Boundary[] boundaries = new Boundary[4];
	
	/**
	 * Returns an array of four boundaries of this world.
	 */
	@Basic
	public Boundary[] getBoundaries() {
		return this.boundaries;
	}
	
	/**
	 * Returns the time of the next collision.
	 * 
	 * @return	The time of the first collision in this world.
	 * 			| result == (time where for each Collision c: c.getTime() >= time)
	 */
	public double getTimeNextCollision() {
		double minTime = Double.MAX_VALUE;
		
		for (Entity e1 : this.getEntities()) {
			for (Entity e2 : this.getEntities()) {
				if ( e1 != e2 ) {
					if (!(e1 instanceof Bullet && e2 instanceof Bullet && (((Bullet) e1).isLoadedOnSameShipAs((Bullet) e2)))) {
						double time = e1.getTimeToCollision(e2);
						if (time < minTime ) {
							minTime = time;
						}
					}
				}
			}
		}
		for (Entity e1 : this.getEntities()) {
			for (Boundary b : boundaries) {
				double time = e1.getTimeToCollision(b);
				if (time < minTime) {
					minTime = time;
				}
			}
		}
		
		return minTime;
	}
	
	/**
	 * Returns the two objects of the next collision.
	 * 
	 * @return	The two objects involved in the next collision.
	 * 			| [object1, object2] where getNextCollisionData().getObject1() == object1 && getNextCollisionData().getObject2() == object2
	 */
	public GameObject[] getObjectsNextCollision() {
		double minTime = Double.MAX_VALUE;
		GameObject object1 = null;
		GameObject object2 = null;
		
		for (Entity e1 : this.getEntities()) {
			for (Entity e2 : this.getEntities()) {
				if ( e1 != e2 ) {
					if (!(e1 instanceof Bullet && e2 instanceof Bullet && (((Bullet) e1).isLoadedOnSameShipAs((Bullet) e2)))) {
						double time = e1.getTimeToCollision(e2);
						if (time  < minTime ) {
							minTime = time;
							object1 = (GameObject) e1;
							object2 = (GameObject) e2;
						}
					}
				}
			}
		}
		
		for (Entity e1 : this.getEntities()) {
			for (Boundary b : boundaries) {
				double time = e1.getTimeToCollision(b);
				if (time < minTime) {
					minTime = time;
					object1 = e1;
					object2 = b;
				}
			}
		}
		
		GameObject[] objects = {object1, object2};
		return objects;
	}
	
	/**
	 * Returns the position of the next collision.
	 * 
	 * @return	The X and Y coordinate of the next collision.
	 * 			| result == {xCoordinate, yCoordinate} for each Collision c
	 * 			| (c.getX() != xCoordinate || c.getY() != yCoordinate) => c.getTime() > getTimeNextCollision()
	 * 		
	 */
	public double[] getPositionNextCollision() {
		GameObject[] objects = getObjectsNextCollision();
		if (objects[0] instanceof Entity) {
			return ((Entity) objects[0]).getCollisionPosition(objects[1]);
		}
		
		return ((Entity) objects[1]).getCollisionPosition(objects[0]);
	}
	
	/**
	 * Returns a collision object of the next collision.
	 * 
	 * @return	A new collision object with the data of the next collision.
	 * 			| result == new Collision(objects[0], objects[1], pos[0], pos[1], time, type)
	 * 			| For each future Collision c
	 * 			| c.getTime() >= time
	 */
	public Collision getNextCollisionData() {
		// [ object1, object2, x, y, time, type (1 = boundary, 2 = entity) ]
		
		double time = 0;
		double[] pos;
		int type;
		
		GameObject[] objects = getObjectsNextCollision();
		
		if (objects[0] instanceof Entity) {
			time = ((Entity) objects[0]).getTimeToCollision(objects[1]);
			pos = ((Entity) objects[0]).getCollisionPosition(objects[1]);
			if (objects[1] instanceof Entity)
				type = 2;
			else
				type = 1;
			Collision c;
			try {
				c = new Collision(objects[0], objects[1], pos[0], pos[1], time, type);
				return c;
			} catch (Exception e) {
				System.out.println(objects[0]);
				System.out.println(objects[1]);
				System.out.println(pos[0]);
				System.out.println(pos[1]);
				System.out.println(time);
				System.out.println(type);
				return null;
			}
			
			
		}
		else {
			time = ((Entity) objects[1]).getTimeToCollision(objects[0]);
			pos = ((Entity) objects[1]).getCollisionPosition(objects[0]);
			type = 1;
			
			return new Collision(objects[1], objects[0], pos[0], pos[1], time, type);
		}
	}
	
	/**
	 * 
	 * @param 	deltaTime
	 * 			The time to advance this world.
	 * @param 	collisionListener
	 * 
	 * @post	If no collisions will occur within the timeframe [now, now + deltaTime], all objects' positions
	 * 			are updated, according to their current position and velocity
	 * 			| if (nextCollision.getTime() > deltaTime) {
	 * 			|	this.advance()
	 * @post	If a collision will occur within the timeframe [now, now + deltaTime], all objects' positions
	 * 			are updated to the moment right before the next collision, the collision is resolved, and positions are updated again
	 * 			| if (nextCollision.getTime() <= deltaTime) {
	 * 			|	this.advance(nextCollision.getTime())
	 * 			|	this.resolvecollision(nextCollision)
	 * 			|	this.evolve(deltaTime - nextCollision.getTime())
	 *
	 */
	public void evolve(double deltaTime, CollisionListener collisionListener) {
		Collision nextCollision = getNextCollisionData();
		
		if (nextCollision.getTime() > deltaTime) 
			advance(deltaTime);
		else {
			advance(nextCollision.getTime());
			if (collisionListener != null) {
				if (nextCollision.getType() == Constants.BOUNDARYCOLLISION) 
					collisionListener.boundaryCollision(nextCollision.getObject1(), nextCollision.getX(), nextCollision.getY());
				else 
					collisionListener.objectCollision(nextCollision.getObject1(), nextCollision.getObject2(), nextCollision.getX(), nextCollision.getY());
			}
			
			resolveCollision(nextCollision);
			advance(0.05);
			evolve(deltaTime - nextCollision.getTime(), collisionListener);
		}
	}
	
	/**
	 * 
	 * @param 	object1
	 * 			The first object involved in the collision this method resolves.
	 * @param 	object2
	 * 			The second object involved in the collision this method resolves.
	 * @param 	collisionListener
	 * @throws 	IllegalStateException
	 * 			This world does not have any collisions.
	 * @post	If the two objects involved in this collision are ships:
	 * 			the ships' orientation and velocity are updated to make them bounce off each other.
	 * @post	If the objects involved in this collision are bullets, they both are terminated.
	 * 			| collision.getObject1.finalize()
	 * 			| collision.getObject2.finalize()
	 * @post	If the objects involved in this collision are a bullet and a boundary, the bullet bounces off the boundary.
	 * 			Bouncing off a horizontal boundary means inverting its vertical velocity, bouncing off a vertical boundary
	 * 			means inverting its horizontal velocity.
	 * 			A bullet can only bounce off a boundary three times, the method bullet.addBounce() keeps track of this number. 
	 * 			If the maximum amount of bounces has been reached, the bullet gets destroyed.
	 * 			|	if (boundary.getType() == LEFT || boundary.getType() == RIGHT)
	 * 			|		bullet.setXVelocity(-bullet.getXVelocity());
	 * 			|	else 
	 * 			|		bullet.setYVelocity(-bullet.getXVelocity());
	 * 			|	bullet.addBounce()
	 * @post 	If the objects involved in this collision are a ship and a boundary, the ship bounces off the boundary.
	 * 			Bouncing off a horizontal boundary means inverting its vertical velocity, bouncing off a vertical boundary
	 * 			means inverting its horizontal velocity.
	 * 			| if (boundary.getType() == LEFT || boundary.getType() == RIGHT)
	 * 			|		ship.setXVelocity(-ship.getXVelocity());
	 * 			|	else 
	 * 			|		ship.setYVelocity(-ship.getXVelocity());
	 * @post	If the objects involved in this collision are a bullet and a ship, the ship and bullet are destroyed immediately.
	 * 			| bullet.finalize()
	 * 			| ship.finalize();
	 * @post	If the objects involved in this collision are a bullet and the ship that fired this bullet, the bullet is readded
	 * 			to the ship.
	 * 			| ship.bulletsLoaded.add(bullet);
	 *			| bullet.setParent(ship);
	 * 			
	 */
	public void resolveCollision(Collision collision) throws IllegalStateException {
		GameObject object1 = collision.getObject1();
		GameObject object2 = collision.getObject2();
		int collisiontype = collision.getType();
		
		
		if (object1 == null || object2 == null) 
			throw new IllegalStateException("This world does not have any collisions.");

		((Entity) object1).collideWith(object2, collisiontype);
		
		if (object1 instanceof Ship && object2 instanceof Ship) {	
			System.out.println("ship ship");
			Ship ship1 = (Ship) object1;
			Ship ship2 = (Ship) object2;
			 
			double deltaVX = ship1.getXVelocity() - ship2.getXVelocity();
			double deltaVY = ship1.getYVelocity() - ship2.getYVelocity();
			double deltaX = ship1.getXCoordinate() - ship2.getXCoordinate();
			double deltaY = ship1.getYCoordinate() - ship2.getYCoordinate();
			
			double ship1J = (2*ship1.getMassTotal()*ship2.getMassTotal() * (deltaVX * deltaX + deltaVY * deltaY)) / (ship1.getRadius() * (ship1.getMassTotal() + ship2.getMassTotal()));
			double ship1JX = (ship1J * deltaX) / ship1.getRadius();
			double ship1JY = (ship1J * deltaY) / ship1.getRadius();
			
			deltaVX = ship2.getXVelocity() - ship1.getXVelocity();
			deltaVY = ship2.getYVelocity() - ship1.getYVelocity();
			deltaX = ship2.getXCoordinate() - ship1.getXCoordinate();
			deltaY = ship2.getYCoordinate() - ship1.getYCoordinate();
			
			double ship2J = (2*ship1.getMassTotal()*ship2.getMassTotal() * (deltaVX * deltaX + deltaVY * deltaY)) / (ship2.getRadius() * (ship1.getMassTotal() + ship2.getMassTotal()));
			double ship2JX = (ship2J * deltaX) / ship2.getRadius();
			double ship2JY = (ship2J * deltaY) / ship2.getRadius();
			
			ship1.setXVelocity(ship1.getXVelocity() + ship1JX / ship1.getMassTotal());
			ship1.setYVelocity(ship1.getYVelocity() + ship1JY / ship1.getMassTotal());
			ship2.setXVelocity(ship2.getXVelocity() + ship2JX / ship2.getMassTotal());
			ship2.setYVelocity(ship2.getYVelocity() + ship2JY / ship2.getMassTotal());
			
			return;
		}
		
		if ((object1 instanceof Bullet && object2 instanceof Ship) || (object1 instanceof Ship && object2 instanceof Bullet)) {
			System.out.println("bullet ship");

			Bullet bullet = null;
			Ship ship = null;
			
			if (object1 instanceof Bullet) {
				bullet = (Bullet) object1;
				ship = (Ship) object2;	
			}
			else {
				ship = (Ship) object1;	
				bullet = (Bullet) object2;
			}
			
			if (bullet.getParent() == ship) {
				ship.bulletsLoaded.add(bullet);
				bullet.setParent(ship);
				
			}
			else {
				ship.finalize();
				bullet.finalize();	
			}
			return;
		}
		
		if (object1 instanceof Bullet && object2 instanceof Bullet) {
			System.out.println("bullet bullet");

			Bullet bullet1 = (Bullet) object1;
			Bullet bullet2 = (Bullet) object2;
			
			bullet1.finalize();
			bullet2.finalize();
			
			return;
		}
		
		
		if (object1 instanceof Ship && object2 instanceof Boundary) {
			System.out.println("ship boundary");

			Boundary boundary = (Boundary) object2;
			Ship ship = (Ship) object1;
			
			if (boundary.getBoundaryType() == Constants.BOTTOM || boundary.getBoundaryType() == Constants.TOP)
				ship.setYVelocity(-ship.getYVelocity());
			else 
				ship.setXVelocity(-ship.getXVelocity());
			return;
		}
		
		if (object1 instanceof Bullet && object2 instanceof Boundary) {
			System.out.println("bullet boundary");

			Boundary boundary = (Boundary) object2;
			Bullet bullet = (Bullet) object1;
			
			if (boundary.getBoundaryType() == Constants.BOTTOM || boundary.getBoundaryType() == Constants.TOP)
				bullet.setYVelocity(-bullet.getYVelocity());
			else 
				bullet.setXVelocity(-bullet.getXVelocity());
			bullet.addBounce();
			return;
		}
		throw new IllegalStateException("Undefined collision.");
	}
	
	/**
	 * Updates all entities' positions depending on their position and velocity. 
	 * Ships' velocities may be updated depending on its state(thruster on/off).
	 * @param 	deltaTime
	 * 			The time duration over which to update the entities' properties.
	 * @post	Every ship in this world is moved during an interval deltaTime, if a ship's thruster is on, the velocity will be updated.
	 * 			| if (ship.isThrustherEnabled())
	 * 			|	ship.updateVelocity()
	 * @post	Every loaded bullet will get the coordinates of his parent ship.
	 * 			| if (this.isLoaded())
	 * 			| 	this.setXCoordinate(this.getParent().getXCoordinate())
	 * 			|	this.setYCoordinate(this.getParent().getYCoordinate())
	 * @post	Every unloaded bullet in this world is moved during an interval deltaTime.
	 * 			| bullet.move(deltaTime)
	 * @post	Every ship is moved during an interval deltaTime.
	 * 			| ship.move(deltaTime)
	 * 
	 */
	public void advance(double deltaTime) {
//		for (Ship ship : ships) {
//			ship.move(deltaTime);
//			if (ship.isThrusterEnabled()) 
//				ship.updateVelocity();
//		}
//		
//		for (Bullet bullet : bullets) {
//			if (bullet.isLoaded()) {	
//				bullet.setXCoordinate(bullet.getParent().getXCoordinate());
//				bullet.setYCoordinate(bullet.getParent().getYCoordinate());
//			}
//			else {
//				bullet.move(deltaTime);
//			}
//		}
		
		for (Entity e : entities) {
			e.advance(deltaTime);
		}
		
		
	}
	
	/**
	 * Variable registering whether the world is finalized.
	 */
	private boolean finalized = false;
	
	/**
	 * Returns whether this world is finalized.
	 */
	@Basic
	public boolean isFinalized() {
		return this.finalized;
	}
	
	/**
	 * A method prepares this world to be removed by the garbage collector.
	 * @see implementation
	 */
	public void finalize() {
		for (Entity e : this.getEntities()) {
			e.setWorld(null);
		}
		entities.clear();
		this.finalized = true;
	}
}
