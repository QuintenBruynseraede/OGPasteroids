package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class GetVelocityYExpression extends Expression<Double> {
	Entity entity;
	
	public GetVelocityYExpression(Expression<Entity> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.entity = e.eval();
	}

	@Override
	public Double eval() {
		return this.entity.getYVelocity();
	}
}