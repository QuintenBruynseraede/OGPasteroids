package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class ThrustStatement extends Statement {
	private Boolean state;
	
	//state: true = on
	public ThrustStatement(SourceLocation location, Boolean state) {
		super(location);
		setState(state);
	}

	@Override
	public void eval() {
		if (state)
			this.getProgram().getShip().thrustOn();
		else
			this.getProgram().getShip().thrustOff();
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

}
