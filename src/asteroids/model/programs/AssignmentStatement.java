package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends Statement {
	private String variableName;
	private Expression<?> value;
	
	public AssignmentStatement(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
		setValue(value);
		setVariableName(variableName);
	}

	public Expression<?> getValue() {
		return value;
	}

	public void setValue(Expression<?> value) {
		this.value = value;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	@Override
	public void eval() {
		//TODO doesn't check whether this variable exists already.
		this.getProgram().addVariable(variableName, value);
	}
	
	
}
