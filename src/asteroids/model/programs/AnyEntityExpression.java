package asteroids.model.programs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import asteroids.model.Entity;
import asteroids.model.World;
import asteroids.part3.programs.SourceLocation;

public class AnyEntityExpression extends Expression<Entity> {
	Entity entity;
	
	public AnyEntityExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		
		Random r = new Random();
		List<Entity> entities = (ArrayList<Entity>) this.getStatement().getProgram().getShip().getWorld().getEntities();
		entity = entities.get(r.nextInt(entities.size()));
	}

	@Override
	public Entity eval() {
		return this.entity;
	}
	

}