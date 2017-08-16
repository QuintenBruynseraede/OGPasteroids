package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class BreakStatement extends Statement {
	
	public BreakStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public void eval() {
		if (getProgram().getLastExecutedStatement() != null)
			return;
		
		if (! getProgram().isCurrentlyInWhile())
			throw new IllegalStateException();
		throw new BreakException();
	}

	@Override
	public void checkForIllegalStatements() {
		return;
	}

}
