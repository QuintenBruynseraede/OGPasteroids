package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class EntityExpression extends Expression<Entity> {
	Entity entity;
	
	public EntityExpression(Entity e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setEntity(e);
	}
	
	private void setEntity(Entity e) {
		this.entity = e;
	}
	
	public Entity getEntity() {
		return this.entity;
	}

	@Override
	public Entity eval() {
		return this.entity;
	}

}
