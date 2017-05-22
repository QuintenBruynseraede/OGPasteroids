package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class GetVelocityXExpression extends Expression<Double> {
	Entity entity;
	
	public GetVelocityXExpression(Expression<Entity> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.entity = e.eval();
	}

	@Override
	public Double eval() {
		return this.entity.getXVelocity();
	}
}
