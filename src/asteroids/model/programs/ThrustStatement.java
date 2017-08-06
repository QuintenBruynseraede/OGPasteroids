package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class ThrustStatement extends ActionStatement {
	private Boolean state;
	
	//state: true = on
	public ThrustStatement(SourceLocation location, Boolean state) {
		super(location);
		setState(state);
	}

	@Override
	public void eval() {
		if (this.getProgram().getTimeLeft() >= 0.2) {
			this.setLastStatement();
			if (state)
				this.getProgram().getShip().thrustOn();
			else
				this.getProgram().getShip().thrustOff();
		}
		else 
			//throw new OutOfTimeException();
			return;
		
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}


}
