package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class ReadVariableExpression extends Expression<Object> {
	String varName;
	
	public ReadVariableExpression(String varName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.varName = varName;
	}

	public Object eval() {
		System.out.println(getStatement());
		return this.getStatement().getProgram().getVariableValue(varName);
	}

}