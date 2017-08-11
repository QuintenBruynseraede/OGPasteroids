package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends Expression<Object> {
	private String parameterName;
	
	public ReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		super(sourceLocation);
		setParameterName(parameterName);
	}

	@Override
	public Object eval() {
		if (getStatement().getProgram().getCurrentFunction() == null) throw new IllegalStateException();
		return this.getStatement().getProgram().getCurrentFunction().getParameterValue(getParameterName()).eval();
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

}
