package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class IsNotExpression extends Expression<Boolean> {
	private boolean val;
	
	public IsNotExpression(Expression<Boolean> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.val = e.eval();
	}
	
	public Boolean eval() {
		return !(val);
	}

}
