package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class ConstantExpression extends Expression<Double> {
	double value;
	
	public ConstantExpression(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}

	@Override
	public Double eval() {
		return this.value;
	}

}
