package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {
	private Expression value;
	
	public PrintStatement(Expression v, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = v;
		v.setStatement(this);
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	@Override
	public void eval() {
		if (getProgram().getLastExecutedStatement() != null)
			return;
		
		System.out.println(value.eval());
		this.getProgram().addReturnItem(value.eval());
	}

	@Override
	public void checkForIllegalStatements() {
		throw new IllegalArgumentException("Illegal statement [Print] in function body.");
	}

}
