package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class FireStatement extends ActionStatement {
	public FireStatement(SourceLocation location) {
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

		
		getProgram().getShip().fire();
		advanceTime();
	}

	@Override
	public void checkForIllegalStatements() {
		throw new IllegalArgumentException("Illegal statement [Fire] in function body.");
	}
}
