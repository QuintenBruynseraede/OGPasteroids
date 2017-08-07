package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class IsEqualToExpression extends Expression<Boolean> {
	private Expression<?> left;
	private Expression<?> right;
	
	public IsEqualToExpression(SourceLocation sourceLocation, Expression<?> left, Expression<?> right) {
		super(sourceLocation);
		this.left = left;
		this.right = right;
	}

	@Override
	public Boolean eval() {
		left.setStatement(getStatement());
		right.setStatement(getStatement());
		
		if (left.eval().equals(right.eval())) {
			return true;
		}
		return false;
		
	}
}
