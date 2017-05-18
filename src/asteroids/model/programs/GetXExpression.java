package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class GetXExpression extends Expression<Double> {
	Entity entity;
	
	public GetXExpression(Expression<Entity> e, SourceLocation sourceLocation) throws Exception {
		super(sourceLocation);
		this.entity = e.eval();
	}

	@Override
	public Double eval() {
		return this.entity.getXCoordinate();
	}
}