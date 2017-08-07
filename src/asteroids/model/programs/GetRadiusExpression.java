package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class GetRadiusExpression extends Expression<Double> {
	private Expression<Entity> expression;
	
	public GetRadiusExpression(Expression<Entity> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		setExpression(e);
		
	}

	@Override
	public Double eval() {
		expression.setStatement(getStatement());
		if (expression.eval() == null) 
			throw new IllegalArgumentException();
		else
			return expression.eval().getRadius();
	}

	public Expression<Entity> getExpression() {
		return expression;
	}

	public void setExpression(Expression<Entity> expression) {
		this.expression = expression;
	}
}