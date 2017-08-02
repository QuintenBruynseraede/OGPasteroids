package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.*;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ConsoleErrorListener;

import asteroids.part2.CollisionListener;


/**
 * A class of worlds with some properties.
 * @version	1.0
 * @author Tom De Backer and Quinten Bruynseraede
 *  
 * @author Tom De Backer and Quinten Bruynseraede
 * @invar 	The height of this world is always less than or equal to the maximum value for the height of a world
 * 			| this.height <= HEIGTHUPPERBOUND
 * @invar	The height of this world is always larger than or equal 0.
 * 			| this.height >= 0
 * @invar	The width of this world is always smaller than or equal to the maximum value for the width of a world
 * 			| this.width <= WIDTHUPPERBOUND
 * @invar	The width of this world is always larger than or equal 0.
 * 			| this.width >= 0
 * @invar 	Each Entity kept in the list of entities belonging to this world is associated with this world.
 * 			| for each Entity e in this.getEntities()
 *  		|	 e.getWorld() == this
 * @invar	Every entity in this world lies fully within the bounds of this world.
 * 			| for each Entity e in this.getEntities()
 *  		|	 isEntityWithinBounds(e)
 */


public class World {
	
	/**
	 * Creates a world with a given height and width
	 * @param 	width		
	 * 			The width for this new world.
	 * @param 	height	
	 * 			The height for this new world.
	 * @effect	Initializes this world as a GameObject of type WORLD
	 * 			| super(Constants.WORLD)
	 * @see		Implementation
	 * @post	This world now has 4 boundaries.
	 */
	@Raw
	public World (double width, double height) {
		if (Double.isNaN(width) || Double.isNaN(height)) {
			if (Double.isNaN(width))
				width = 0;
			if (Double.isNaN(height))
				height = 0;
		}
		
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
	}
	
	/**
	 * Variable registering the width of this world expressed in kilometres.
	 */
	private final double width;
	
	/**
	 * Variable registering the height of this world expressed in kilometres.
	 */
	private final double height;
	
	/**
	 * Constant registering the the maximum width of this world in kilometers.
	 */
	public final static double WIDTHUPPERBOUND = Double.MAX_VALUE;
	
	/**
	 * Constant registering the the maximum height of this world in kilometers.
	 */
	public final static double HEIGHTUPPERBOUND = Double.MAX_VALUE;
	
	/**
	 * Return the width of this world expressed in kilometres.
	 */
	@Basic
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Return the height of this world expressed in kilometres.
	 */
	@Basic
	public double getHeight() {
		return this.height;
	}
	
	/**
	 *  
	 */
	private boolean isEntityWithinBounds(Entity e) {
		return e.getXCoordinate() >= e.getRadius() && e.getYCoordinate() >= e.getRadius() && e.getXCoordinate() <= this.getWidth() - e.getRadius() && e.getYCoordinate() <= this.getHeight() - e.getRadius(); 
	}
	
	/**
	 * Set registering the entity contained by this world
	 */	
	private Set<Entity> entities = new HashSet<Entity>();
	
	/**
	 * 
	 * @param 	entity
	 * 			The entity to be added to this world.
	 * @post	| new.getEntities().contains(entity)
	 */
	public void addEntity(Entity e) throws IllegalArgumentException {
		if (e == null)
			throw new IllegalArgumentException("Null object");
		
		entities.add(e);
		for (Entity entity: getEntities()) {
			if (e.overlap(entity) && entity != e) {
				if (e instanceof Bullet && ((Bullet) e).getParent() == entity) return;
				e.collideWith(entity);
				return;
			}
		}
		
		
	}

	/**
	 * Removes a given entity from this world.
	 * @param 	entity
	 * 			The entity to remove from this world.
	 * @throws	IllegalArgumentException
	 * 			The list containing all entity in this world does not contain the entity provided as an argument.
	 * @post	| ! entities.contains(entity)
	 */
	@Raw
	public void removeEntity(Entity entity) throws IllegalArgumentException {
		if (! this.entities.contains(entity))
			throw new IllegalArgumentException("Trying to remove entity that is not in this world.");
		//System.out.println("removeEntity");
		entity.setWorld(null);
		
		//for (Entity e: entities)
			//System.out.println(entity);
		entities.remove(entity);
		
	}
	
	/**
	 * 
	 * @param 	x
	 * 			The X coordinate to check for presence of ship and bullet.
	 * @param 	y
	 * 			The Y coordinate to check for presence of ship and bullet.
	 * @return	Returns the entity at position x, y.
	 * 			| result == Entity e where
	 * 			| 	e.getXCoordinate() == x
	 * 			| 	e.getYCoordinate() == y
	 * @return	returns null, if there is no entity at position (x,y)
	 * 			| result == null
	 * @note	To account for rounding errors, a minimum of 99% correctness is handled in this method.
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
	
	/**
	 * 	Returns all entities of a certain class associated with this world, determined in a functional way.
	 * 	@param  c
	 * 			Class of which we want to get instances of as a result.
	 * 	@return | Set s where
	 * 			| for each Object o in s: (o.getClass() == c)
	 */
	public Set<? extends Entity> getEntitiesOfType(Class<? extends Entity> c) {
		return entities.stream().filter(p -> p.getClass() == c).collect(Collectors.toSet());
	}
	
	/**
	 * Returns a list of all entities in this world.
	 * 
	 * @return 	A list of all entities in this world.
	 * 			| { entity1, entity2, ..., entityN | entityI.getWorld() = this}
	 * @note	A copy is made to prevent any changes in this world to be reflected in the list we return.
	 */
	public Set<Entity> getEntities() {
		Set<Entity> copy = new HashSet<Entity>();
		for (Entity e : entities) {
			copy.add(e);
		}
		return copy;
	}
	
	/**
	 * Returns the time to the next collision.
	 * 
	 * @return	The time of the first collision in this world.
	 * 			| result == (time where for each Collision c: c.getTime() >= time)
	 */
	public double getTimeNextCollision() {
		double time = Double.POSITIVE_INFINITY;
		for (Entity e: getEntities()) {
			time = Math.min(time, e.getTimeFirstCollisionBoundary());
			for (Entity e2: getEntities()) {
				if (e != e2) {
					if (e.overlap(e2)) return 0;
					time = Math.min(time, e.getTimeToCollision(e2));
				}
			}
		}
		return time;
	}
	
	/**
	 * Returns the two objects of the next collision.
	 * 
	 * @return	The two objects involved in the next collision.
	 * 			| [object1, object2] where getNextCollisionData().getObject1() == object1 && getNextCollisionData().getObject2() == object2
	 */
	public Entity[] getEntitiesNextCollision() {
		Entity[] entities = new Entity[]{null,null};
		double timeNextCollision = Double.POSITIVE_INFINITY;
		for (Entity entity1 : getEntities()){
			if (timeNextCollision > entity1.getTimeFirstCollisionBoundary()){
				timeNextCollision = entity1.getTimeFirstCollisionBoundary();
				entities = new Entity[]{entity1,null};
			}
			for (Entity entity2 : getEntities()){
				if (entity1 != entity2) {
					if (entity1.overlap(entity2)) return new Entity[]{entity1,entity2};
					
					if (timeNextCollision > entity1.getTimeToCollision(entity2)){
						timeNextCollision = entity1.getTimeToCollision(entity2);
						entities = new Entity[]{entity1,entity2};
					}
				}
				
			}
		}
		return entities;
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
		Entity[] nextCollisionEntities = getEntitiesNextCollision();
		double nextCollisionTime = getTimeNextCollision();
		if (nextCollisionEntities[0] == null) return new double[] {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}; //No objects involved
		
		if (nextCollisionEntities[1] == null) { //Boundary collision
			Entity e = nextCollisionEntities[0];
			double entityXCollision = e.getXCoordinate() + e.getXVelocity() * nextCollisionTime;
			double entityYCollision = e.getYCoordinate() + e.getYVelocity() * nextCollisionTime;
			double entityVelocity = e.getTotalVelocity(e.getXVelocity(), e.getYVelocity());
			double time = e.getRadius() / entityVelocity;
			entityXCollision -= time * e.getXVelocity();
			entityYCollision -= time * e.getYVelocity();
			return new double[] {entityXCollision, entityYCollision};
		}
		
		Entity e1 = nextCollisionEntities[0];
		Entity e2 = nextCollisionEntities[1];
		return e1.getCollisionPosition(e2); //Entity <-> Entity collision
		
				
	}
	
	/**
	 * Evolves the world for a duration dt
	 */
	public void evolve(double dt, CollisionListener l) throws IllegalArgumentException {
		if (dt < 0) throw new IllegalArgumentException("Delta time cannot be negative");
		
		double timeToCollision = getTimeNextCollision();
		double[] positionNextCollision = getPositionNextCollision();
		Entity[] entitiesNextCollision = getEntitiesNextCollision();
		
		while (timeToCollision <= dt && timeToCollision > 0) {
			//System.out.println("Advancing " + timeToCollision);
			advance(timeToCollision);
			if (entitiesNextCollision[1] == null) {
				Entity e = entitiesNextCollision[0];
				if (l != null) 
					l.boundaryCollision(e, e.getXCoordinate() + Math.cos(e.getXVelocity()/e.getYVelocity())*e.getRadius(), e.getYCoordinate() + Math.sin(e.getXVelocity()/e.getYVelocity())*e.getRadius());
				e.collideBoundary();
			}
			else {
				Entity e1 = entitiesNextCollision[0];
				Entity e2 = entitiesNextCollision[1];
				if (l != null) 
					l.objectCollision(e1, e2, e1.getCollisionPosition(e2)[0], e1.getCollisionPosition(e2)[1]);
				e1.collideWith(e2);
				advance(0.0000001);
			}
			dt -= timeToCollision;
			
			timeToCollision = getTimeNextCollision();
			positionNextCollision = getPositionNextCollision();
			entitiesNextCollision = getEntitiesNextCollision();
		}
		advance(dt);
		
	}
	
	
	
	
	
	/**
	 * Updates all entities' positions depending on their position and velocity. 
	 * Ships' velocities may be updated depending on its state(thruster on/off).
	 * @param 	deltaTime
	 * 			The time duration over which to update the entities' properties.
	 * @post	| for each Entity e : this.getEntities()
	 * 			|	e.advance()
	 * @note	Specific behaviour in advance() is specified in detail at the level of each subclass.
	 * 
	 */
	public void advance(double deltaTime) {
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
	@Raw
	public boolean isFinalized() {
		return this.finalized;
	}
	
	
	/**
	 * A method that prepares this world to be removed by the garbage collector.
	 * @post	| this.getEntites().getSize() == 0
	 * @post	| for each Entity e : old.getEntities()
	 * 			| 	e.getWorld() == null;
	 * @post	| this.finalized = true
	 */
	public void finalize() {
		for (Entity e : this.getEntities()) {
			e.setWorld(null);
		}
		entities.clear();
		this.finalized = true;
	}
	

	/**
	 * Returns a string representation of a world.
	 * 
	 * @return	A string representation of a world.
	 */
	@Raw
	public String toString() {
		return "[World] " + this.hashCode();
	}
}
