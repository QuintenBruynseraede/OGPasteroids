package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.model.MinorPlanet;
import asteroids.model.World;
import asteroids.part3.programs.SourceLocation;

public class ClosestEntityExpression extends Expression<Entity> {
	Class<? extends Entity> c;
	
	public ClosestEntityExpression(Class<? extends Entity> c, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.c = c;
	}

	@Override
	public Entity eval() {
		World world = this.getStatement().getProgram().getShip().getWorld();
		double minDistance = Double.MAX_VALUE;
		Entity closestEntity = null;
		
		for (Entity e : world.getEntitiesOfType(c)) {
			if (getStatement().getProgram().getShip().getDistanceBetween(e) < minDistance && e != getStatement().getProgram().getShip()) {
				minDistance = this.getStatement().getProgram().getShip().getDistanceBetween(e);
				closestEntity = e;
			}
		}
		
		if (c.equals(MinorPlanet.class)) {
			for (Entity e : world.getEntities()) {
				if (e instanceof MinorPlanet) {
					if (getStatement().getProgram().getShip().getDistanceBetween(e) < minDistance && e != getStatement().getProgram().getShip()) {
						minDistance = this.getStatement().getProgram().getShip().getDistanceBetween(e);
						closestEntity = e;
					}
				}
			}
		}
			
		
		return closestEntity;
	}
	

}
