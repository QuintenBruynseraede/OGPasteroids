package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class ConditionalExpression extends Expression<Object> {
	Expression<Boolean> booleanExpression;
	Expression<Object> thenExpression;
	Expression<Object> elseExpression;
	
	public ConditionalExpression(Expression<Boolean> b, Expression<Object> t, Expression<Object> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		setBooleanExpression(b);
		setThenExpression(t);
		setElseExpression(e);
	}

	@Override
	public Object eval() {
		if (getBooleanExpression().eval())
			return getThenExpression();
		else
			return getElseExpression();
	}
	
	private boolean canHaveAsBooleanExpression(Expression<Boolean> e) {
		return (e != null);
	}
	
	private boolean canHaveAsThenExpression(Expression<Object> e) {
		return (e != null);
	}
	
	private boolean canHaveAsElseExpression(Expression<Object> e) {
		return (e != null);
	}
	
	public Expression<Boolean> getBooleanExpression() {
		return booleanExpression;
	}
	
	public Expression<Object> getThenExpression() {
		return thenExpression;
	}

	public Expression<Object> getElseExpression() {
		return elseExpression;
	}
	
	public void setBooleanExpression(Expression<Boolean> booleanExpression) {
		if (canHaveAsBooleanExpression(booleanExpression))
			this.booleanExpression = booleanExpression;
	}

	public void setThenExpression(Expression<Object> thenExpression) {
		if (canHaveAsThenExpression(thenExpression))
			this.thenExpression = thenExpression;
	}


	public void setElseExpression(Expression<Object> elseExpression) {
		if (canHaveAsElseExpression(elseExpression))
			this.elseExpression = elseExpression;
	}

}

