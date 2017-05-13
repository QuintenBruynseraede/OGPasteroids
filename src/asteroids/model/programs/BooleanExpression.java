package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class BooleanExpression extends Expression<Boolean>{
	private boolean truthValue;
	public BooleanExpression(boolean truthValue, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.truthValue = truthValue;
	}
	@Override
	public Boolean eval() {
		return this.truthValue;
	}
	
}
