package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {
	private Expression value;
	
	public PrintStatement(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		setValue(value);
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	@Override
	public void eval() {
		System.out.println(value.eval());
		this.getProgram().addReturnItem(value.eval());
		
	}
}
