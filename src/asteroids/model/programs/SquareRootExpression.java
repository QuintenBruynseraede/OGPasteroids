package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class SquareRootExpression extends Expression<Double> {
	Expression<Double> expression;
	
	public SquareRootExpression(Expression<Double> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = e;
	}

	@Override
	public Double eval() {
		return Math.sqrt(expression.eval());
	}

}
