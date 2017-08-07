package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class IsNotExpression extends Expression<Boolean> {
	private Expression<Boolean> expression;
	
	public IsNotExpression(Expression<Boolean> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		setExpression(e);
		
	}
	
	public Boolean eval() {
		expression.setStatement(getStatement());
		return !(expression.eval());
	}

	public Expression<Boolean> getExpression() {
		return expression;
	}

	private void setExpression(Expression<Boolean> expression) {
		this.expression = expression;
	}
	

}
