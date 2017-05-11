package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression extends Expression<Double> {
	public GetDirectionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Double eval() {
		return this.getStatement().getProgram().getShip().getOrientation();
	}
	
}
