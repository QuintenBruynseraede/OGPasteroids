package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class ThrustStatement extends ActionStatement {
	private Boolean state;
	
	//state: true = on
	public ThrustStatement(SourceLocation location, Boolean state) {
		super(location);
		setState(state);
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
	
		if (state)
			getProgram().getShip().thrustOn();
		else
			getProgram().getShip().thrustOff();
		advanceTime();
	}
	
		
	

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@Override
	public void checkForIllegalStatements() {
		throw new IllegalArgumentException("Illegal statement [Thrust] in function body.");
	}


}
