package asteroids.model.programs;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class AnyEntityExpression extends Expression<Entity> {
	
	public AnyEntityExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Entity eval() {
		Random r = new Random();
		
		//System.out.println(getStatement());
		Set<Entity> entities = new HashSet<Entity>(getStatement().getProgram().getShip().getWorld().getEntities());
		int i = r.nextInt(entities.size());
		return (Entity) entities.toArray()[i];
	}
}