package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class BreakStatement extends Statement {
	
	public BreakStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public void eval() {
		this.setLastStatement();
		throw new BreakException();
	}

}
