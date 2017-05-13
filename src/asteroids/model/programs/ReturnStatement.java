package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement{
	private Expression value;
	
	public ReturnStatement (Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		setValue(value);
	}

	@Override
	public void eval() {
		
	}

	public Expression getValue() {
		return value;
	}
ffff
	public void setValue(Expression value) {
		this.value = value;
	}
}
