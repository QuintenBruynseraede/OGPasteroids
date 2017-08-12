package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement{
	private Expression value;
	
	public ReturnStatement (Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		setValue(value);
		getValue().setStatement(this);
	}

	@Override
	public void eval() {
		if (getProgram().getCurrentFunction() == null)
			throw new IllegalStateException("Cannot return when not in function");
		getProgram().getCurrentFunction().setReturnValue(value.eval());
	}
	
	public Expression<?> evaluate() {
		this.setLastStatement();
		return (Expression<?>) value.eval();
	}

	public Expression getValue() {
		return value;
	}
	
	public void setValue(Expression value) {
		this.value = value;
	}

}
