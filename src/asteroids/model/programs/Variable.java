package asteroids.model.programs;

import asteroids.model.Entity;

public class Variable<T> {
	private String name;
	private Expression expression = null;

	//private final Class<T> type;
	
	public Variable(String varName, Expression<?> expression) {
		setName(varName);
		setExpression(expression);
	}
	
	public T eval(){
		return (T) this.expression.eval();		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Expression<?> getExpression() {
		return expression;
	}

	public void setExpression(Expression<?> expression) {
		if (!canHaveAsExpression(expression)) 
			throw new IllegalArgumentException();
		else
			this.expression = expression;
	}

	private boolean canHaveAsExpression(Expression<?> expression2) {
		if (getExpression() == null) 
			return true;
		else if (expression2.eval() instanceof Double && getExpression().eval() instanceof Double) 
			return true;
		else if (expression2.eval() instanceof Boolean && getExpression().eval() instanceof Boolean)
			return true;
		else if (expression2.eval() instanceof Entity && getExpression().eval() instanceof Entity)
			return true;
		else
			return false;
	}
}
