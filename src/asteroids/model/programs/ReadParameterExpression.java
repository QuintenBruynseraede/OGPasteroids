package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends Expression {
	private String parameterName;
	
	public ReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		super(sourceLocation);
		setParameterName(parameterName);
	}

	@Override
	public Expression<?> eval() {
		return this.getStatement().getProgram().getCurrentFunction().getParameterValue(getParameterName());
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

}
