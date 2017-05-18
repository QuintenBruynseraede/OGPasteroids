package asteroids.model.programs;

public class Variable<T> {
	private String name;
	private Expression expression;
	
	public Variable(String varName, Expression expression) {
		this.setName(varName);
		this.setExpression(expression);
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

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
