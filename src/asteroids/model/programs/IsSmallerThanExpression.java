package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class IsSmallerThanExpression extends Expression<Boolean> {
	Expression<Double> expression1;
	Expression<Double> expression2;
	
	public IsSmallerThanExpression(Expression<Double> e1, Expression<Double> e2, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.expression1 = e1;
		this.expression2 = e2;
		
	}

	@Override
	public Boolean eval() {
		return (this.expression1.eval() < this.expression2.eval());
	}
}