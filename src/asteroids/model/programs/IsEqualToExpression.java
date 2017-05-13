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
		try {
			if (left.eval() == right.eval()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
