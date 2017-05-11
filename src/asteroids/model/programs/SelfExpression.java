package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class SelfExpression extends Expression<Entity> {
	Entity entity;
	
	public SelfExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setEntity(this.getStatement().getProgram().getShip());
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
