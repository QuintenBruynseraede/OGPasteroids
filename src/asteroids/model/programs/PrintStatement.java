package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {
	private Expression value;
	
	public PrintStatement(Expression v, SourceLocation sourceLocation) {
		super(sourceLocation);
		//System.out.println("Value set for " + v);
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
		this.setLastStatement();
		System.out.println(value.eval());
		this.getProgram().addReturnItem(value.eval());
	}

}
