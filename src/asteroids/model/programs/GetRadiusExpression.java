package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class GetRadiusExpression extends Expression<Double> {
	private Entity entity;
	
	public GetRadiusExpression(Expression<Entity> e, SourceLocation sourceLocation) throws Exception {
		super(sourceLocation);
		setEntity(e.eval());
		
	}

	private void setEntity(Entity e) {
		this.entity = e;
	}
	
	public Entity getEntity() {
		return this.entity;
	}

	@Override
	public Double eval() {
		return this.entity.getRadius();
	}
}