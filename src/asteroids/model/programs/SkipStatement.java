package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class SkipStatement extends ActionStatement {
	
	public SkipStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void eval() throws OutOfTimeException {
		if (getProgram().getLastExecutedStatement() != null) {
			if (getProgram().getLastExecutedStatement() == this) {
				getProgram().setLastExecutedStatement(null);
				return;
			}
			else
				return;
		}
		//Do nothing
		advanceTime();	
	}

	@Override
	public void checkForIllegalStatements() {
		throw new IllegalArgumentException("Illegal statement [Skip] in function body.");
	}


}
