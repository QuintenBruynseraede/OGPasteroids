package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class PrintStatement {
	private Expression value;
	
	public PrintStatement(Expression value, SourceLocation sourceLocation) {
		setValue(value);
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}
}
