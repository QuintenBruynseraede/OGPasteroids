package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class NegativeExpression extends Expression<Double> {
	Expression<Double> expression;
	
	public NegativeExpression(Expression<Double> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = e;
	}

	@Override
	public Double eval() {
		return -(expression.eval());
	}

}
