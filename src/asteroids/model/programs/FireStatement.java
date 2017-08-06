package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class FireStatement extends ActionStatement {
	public FireStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void eval() {
		if (this.getProgram().getTimeLeft() >= 0.2) {
			this.setLastStatement();
			this.getProgram().getShip().fire();
		}
		else
			//throw new OutOfTimeException();
			return;
	}

}
